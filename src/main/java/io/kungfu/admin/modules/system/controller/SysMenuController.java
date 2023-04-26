package io.kungfu.admin.modules.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.dto.SysMenuDTO;
import io.kungfu.admin.modules.system.model.SysMenu;
import io.kungfu.admin.modules.system.service.SysMenuService;
import io.kungfu.admin.modules.system.validate.SysMenuValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.*;

import java.util.List;
import java.util.Map;

@Api(value = "系统菜单", tags = "系统菜单接口")
@Path("/sys-menu")
public class SysMenuController extends KungfuController {

    @Inject
    private SysMenuService sysMenuService;

    @ApiOperation(value = "菜单树查询", notes = "菜单树查询，查询整棵树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "searchName", value = "菜单名称", defaultValue = "")
    })
    public void tree() {
        String searchName = get("searchName");

        // 缓存
        Record menuTree = CacheKit.get("sysMenu", "menuTree", () -> sysMenuService.buildTree(searchName));

        renderJson(R.ok().set("data", menuTree));
    }

    @ApiOperation(value = "菜单信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysMenu", value = "菜单信息", dataTypeClass = SysMenuDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysMenuValidator.class)
    public void saveOrUpdate() {
        SysMenu sysMenu = toModel(SysMenu.class);
        renderJson(sysMenuService.saveOrUpdate(sysMenu, getUserInfo()));
    }

    @ApiOperation(value = "分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysMenu.class)));
    }

    @ApiOperation(value = "系统菜单表信息查询", notes = "根据表ID查询系统菜单表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysMenuId", value = "菜单ID", defaultValue = "100")
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysMenuId = getLong("sysMenuId");
        renderJson(selectById(sysMenuId, SysMenu.class));
    }


    @ApiOperation(value = "删除菜单记录", notes = "删除单条菜单记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysMenuId", value = "菜单ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysMenu")
    public void deleteById() {
        Long sysMenuId = getLong("sysMenuId");
        renderJson(sysMenuService.deleteById(sysMenuId));
    }

    @ApiOperation(value = "用户菜单树查询", notes = "根据用户编码查询当前菜单及其子菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userCode", value = "用户编码", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void userMenuTree() {
        String userCode = get("userCode");
        paramValid(userCode, "userCode");
        // 缓存
        List<Record> userMenuTree = CacheKit.get("sysMenu", "userMenuTree"+ userCode, () -> sysMenuService.getUserMenuTree(userCode, "root"));

        renderJson(R.ok("data", userMenuTree));
    }

    @ApiOperation(value = "用户权限按钮查询", notes = "根据用户编码查询当前用户的权限按钮集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户编码", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void userAuthButtons() {
        String userCode = get("userCode");
        paramValid(userCode, "userCode");
        // 缓存
        Map<String, List<String>> userAuthButtons = CacheKit.get("sysMenu", "userAuthButtons"+ userCode, () -> sysMenuService.getUserAuthButtons(userCode));

        renderJson(R.ok("data", userAuthButtons));
    }

    @ApiOperation(value = "角色菜单树查询", notes = "根据角色编码查询当前菜单及其子菜单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleCode", value = "角色编号", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void roleMenuTree() {
        String roleCode = get("roleCode");
        paramValid(roleCode, "roleCode");
        // 缓存
        List<SysMenu> roleMenuTree = CacheKit.get("sysMenu", "roleMenuTree"+ roleCode, () -> sysMenuService.getRoleMenuTree(roleCode, "root"));

        renderJson(R.ok("data", roleMenuTree));
    }

    @ApiOperation(value = "设置菜单状态", notes = "设置菜单状态，菜单启用停用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "menuId", value = "菜单ID", dataType = "Long", required = true),
        @ApiImplicitParam(name = "isEnabled", value = "启用状态", dataType = "Boolean", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysMenu")
    public void setStatus() {
        UserInfo userInfo = getUserInfo();
        Long menuId = getLong("menuId");
        Boolean isEnabled = getBoolean("isEnabled");
        paramValid(menuId, "menuId");
        paramValid(isEnabled, "isEnabled");

        renderJson(sysMenuService.setStatus(menuId, isEnabled, userInfo) ? R.ok("更新成功") : R.fail("更新失败"));
    }

}
