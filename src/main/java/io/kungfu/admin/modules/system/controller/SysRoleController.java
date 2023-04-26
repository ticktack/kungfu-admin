package io.kungfu.admin.modules.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.dto.SysRoleDTO;
import io.kungfu.admin.modules.system.model.SysRole;
import io.kungfu.admin.modules.system.service.SysRoleService;
import io.kungfu.admin.modules.system.validate.SysRoleValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.*;

@Api(value = "角色", tags = "角色接口")
@Path("/sys-role")
public class SysRoleController extends KungfuController {

    @Inject
    private SysRoleService sysRoleService;

    @ApiOperation(value = "角色树查询", notes = "角色树查询，查询整棵树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "searchName", value = "角色名称", defaultValue = "")
    })
    public void tree() {
        String searchName = get("searchName");

        // 缓存
        Record sysRoleTree = CacheKit.get("sysRole", "sysRoleTree", () -> sysRoleService.buildTree(searchName));

        renderJson(R.ok("data", sysRoleTree));
    }

    @ApiOperation(value = "角色信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysRole", value = "角色信息", dataTypeClass = SysRoleDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysRoleValidator.class)
    @CacheName("sysRole")
    public void saveOrUpdate() {
        SysRole sysRole = toModel(SysRole.class);
        renderJson(sysRoleService.saveOrUpdate(sysRole, getUserInfo()));
    }

    @ApiOperation(value = "角色分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysRole.class)));
    }

    @ApiOperation(value = "角色信息查询", notes = "根据表ID查询角色信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysRoleId", value = "角色ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysRoleId = getLong("sysRoleId");
        renderJson(selectById(sysRoleId, SysRole.class));
    }


    @ApiOperation(value = "删除角色记录", notes = "根据表ID删除角色记录，支持批量删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysRoleIds", value = "角色IDs", defaultValue = "100,200,300", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysRole")
    public void deleteByIds() {
        String sysRoleIds = get("sysRoleIds");
        renderJson(deleteByIds(sysRoleIds, SysRole.class));
    }

    @ApiOperation(value = "设置状态", notes = "设置状态：启用停用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysRoleId", value = "角色ID", dataType = "Long", required = true),
        @ApiImplicitParam(name = "isEnabled", value = "启用状态", dataType = "Boolean", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysRole")
    public void setStatus() {
        UserInfo userInfo = getUserInfo();
        Long sysRoleId = getLong("sysRoleId");
        Boolean isEnabled = getBoolean("isEnabled");
        paramValid(sysRoleId, 631,"sysRoleId");
        paramValid(isEnabled, 632, "isEnabled");

        renderJson(sysRoleService.setStatus(sysRoleId, isEnabled, userInfo) ? R.ok("更新成功") : R.fail("更新失败"));
    }

    @ApiOperation(value = "给角色添加菜单", notes = "给角色添加菜单，支持单个及批量添加")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "roleCode", value = "角色编码", type = "String", required = true),
        @ApiImplicitParam(name = "menuCodes", value = "菜单编码，多个英文逗号隔开", type = "String", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void saveRoleMenus() {
        String roleCode = get("roleCode");
        String menuCodes = get("menuCodes");
        paramValid(roleCode, 631,"roleCode");
        paramValid(menuCodes, 632,"menuCodes");

        renderJson(sysRoleService.saveRoleMenu(roleCode, menuCodes, getUserInfo()) ? R.ok("添加成功") : R.fail("添加失败"));
    }

    @ApiOperation(value = "角色菜单信息查询", notes = "根据角色Code查询菜单Codes信息")
    @ApiImplicitParams({
       @ApiImplicitParam(name = "roleCode", value = "角色编码", type = "String", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getRoleMenuCodes() {
        String roleCode = get("roleCode");
        paramValid(roleCode, "roleCode");
        Kv kv = Kv.by("menuCodes", sysRoleService.getRoleMenuCodes(roleCode));
        renderJson(R.ok("data", kv));
    }


}
