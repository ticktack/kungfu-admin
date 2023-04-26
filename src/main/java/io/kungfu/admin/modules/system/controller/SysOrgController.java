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
import io.kungfu.admin.modules.system.dto.SysOrgDTO;
import io.kungfu.admin.modules.system.model.SysOrg;
import io.kungfu.admin.modules.system.service.SysOrgService;
import io.kungfu.admin.modules.system.validate.SysOrgValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.*;

import java.util.List;

@Api(value = "部门", tags = "部门接口")
@Path("/sys-org")
public class SysOrgController extends KungfuController {

    @Inject
    private SysOrgService sysOrgService;

    @ApiOperation(value = "部门树查询", notes = "部门树查询，查询整棵树")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "searchName", value = "部门名称", defaultValue = "")
    })
    public void tree() {
        String searchName = get("searchName");

        // 缓存
        Record sysOrgTree = CacheKit.get("sysOrg", "sysOrgTree", () -> sysOrgService.buildTree(searchName));

        renderJson(R.ok("data", sysOrgTree));
    }

    @ApiOperation(value = "部门信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysOrg", value = "部门信息", dataTypeClass = SysOrgDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysOrgValidator.class)
    public void saveOrUpdate() {
        SysOrg sysOrg = toModel(SysOrg.class);
        renderJson(sysOrgService.saveOrUpdate(sysOrg, getUserInfo()));
    }

    @ApiOperation(value = "部门分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysOrg.class)));
    }

    @ApiOperation(value = "部门信息查询", notes = "根据表ID查询部门信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysOrgId", value = "部门ID", defaultValue = "100")
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysOrgId = getLong("sysOrgId");
        renderJson(selectById(sysOrgId, SysOrg.class));
    }


    @ApiOperation(value = "删除部门记录", notes = "根据表ID删除部门记录，支持批量删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysOrgIds", value = "部门IDs", defaultValue = "100,200,300", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysOrg")
    public void deleteByIds() {
        String sysOrgIds = get("sysOrgIds");
        renderJson(deleteByIds(sysOrgIds, SysOrg.class));
    }

    @ApiOperation(value = "设置状态", notes = "设置状态：启用停用")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysOrgId", value = "部门ID", dataType = "Long", required = true),
        @ApiImplicitParam(name = "isEnabled", value = "启用状态", dataType = "Boolean", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysOrg")
    public void setStatus() {
        UserInfo userInfo = getUserInfo();
        Long sysOrgId = getLong("sysOrgId");
        Boolean isEnabled = getBoolean("isEnabled");
        paramValid(sysOrgId, "sysOrgId");
        paramValid(isEnabled, "isEnabled");

        renderJson(sysOrgService.setStatus(sysOrgId, isEnabled, userInfo) ? R.ok("更新成功") : R.fail("更新失败"));
    }

}
