package io.kungfu.admin.modules.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.kit.Kv;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.dto.SysUserDTO;
import io.kungfu.admin.modules.system.model.SysUser;
import io.kungfu.admin.modules.system.service.SysUserService;
import io.kungfu.admin.modules.system.validate.DeleteUserRoleValidator;
import io.kungfu.admin.modules.system.validate.SysUserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "系统用户表", tags = "系统用户表接口")
@Path("/sys-user")
public class SysUserController extends KungfuController {

    @Inject
    private SysUserService sysUserService;


    @ApiOperation(value = "系统用户表信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysUser", value = "系统用户表信息", dataTypeClass = SysUserDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysUserValidator.class)
    public void saveOrUpdate() {
        SysUser sysUser = toModel(SysUser.class);
        renderJson(sysUserService.saveOrUpdate(sysUser, getUserInfo()));
    }

    @ApiOperation(value = "分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        QueryCondition qc = toDTO(QueryCondition.class);

//        Map<String, Object> modelMap = qc.getModelMap() == null ? new HashMap<>() : qc.getModelMap();
//        Map<String, String> queryTypeMap = qc.getQueryTypeMap() == null ? new HashMap<>() : qc.getQueryTypeMap();
//        modelMap.put("userCode", "admin");
//        queryTypeMap.put("userCode", "neq");
//        qc.setModelMap(modelMap);
//        qc.setQueryTypeMap(queryTypeMap);

//        List<Triple<String, String, String>> paramList = new ArrayList<>();
//        Triple triple = new Triple<>("userCode", "admin", "neq");
//        paramList.add(triple);

        qc = wapperQueryCondition(qc, new Triple<>("userCode", "admin", "neq"));

        renderJson(R.ok("data", sysUserService.wapperQueryPage(queryPage(SysUser.class, qc))));
    }

    @ApiOperation(value = "系统用户表信息查询", notes = "根据表ID查询系统用户表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysUserId", value = "系统用户表ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysUserId = getLong("sysUserId");
        renderJson(selectById(sysUserId, SysUser.class));
    }


    @ApiOperation(value = "删除系统用户表记录", notes = "删除系统用户表记录,支持单条及批量删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysUserIds", value = "系统用户表IDs", defaultValue = "100,200,300", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void deleteByIds() {
        String sysUserIds = get("sysUserIds");
        renderJson(deleteByIds(sysUserIds, SysUser.class));
    }

    @ApiOperation(value = "设置用户登录状态", notes = "设置用户登录状态")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userId", value = "用户ID", type = "String", required = true),
        @ApiImplicitParam(name = "isAllowLogin", value = "是否允许登录", type = "Boolean", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void setAllowLogin() {
        Long userId = getLong("userId");
        Boolean isAllowLogin = getBoolean("isAllowLogin");
        paramValid(userId, 631, "userId");
        paramValid(isAllowLogin, 632, "isAllowLogin");

        renderJson(sysUserService.setStatus(userId, isAllowLogin, getUserInfo()) ? R.ok("更新成功") : R.fail("更新失败"));
    }

    @ApiOperation(value = "给用户添加角色", notes = "给用户添加角色，支持单个及批量添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户编码", type = "String", required = true),
            @ApiImplicitParam(name = "roleCodes", value = "角色编码，多个英文逗号隔开", type = "String", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void saveUserRole() {
        String userCode = get("userCode");
        String roleCodes = get("roleCodes");
        paramValid(userCode, 631, "userCode");
        paramValid(roleCodes, 632, "roleCodes");

        renderJson(sysUserService.saveUserRole(userCode, roleCodes, getUserInfo()) ? R.ok("添加成功") : R.fail("添加失败"));
    }


    @ApiOperation(value = "删除用户角色", notes = "删除用户单个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户编码", type = "String", required = true),
            @ApiImplicitParam(name = "roleCode", value = "角色编码", type = "String", required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(DeleteUserRoleValidator.class)
    public void deleteUserRole() {
        String userCode = get("userCode");
        String roleCode = get("roleCode");

        renderJson(sysUserService.deleteUserRole(userCode, roleCode) ? R.ok("删除成功") : R.fail("删除失败"));
    }

    @ApiOperation(value = "重置用户密码", notes = "重置用户密码，支持单个及批量设置")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "userIds", value = "用户IDs,多个英文逗号隔开", example = "100,200,300", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void resetPassword() {
        String userIds = get("userIds");
        paramValid(userIds, "userIds");

        Long[] array = toArray(userIds, Long::new);

        renderJson(sysUserService.resetPassword(array) ? R.ok("重置密码成功") : R.fail("重置密码失败"));
    }

    @ApiOperation(value = "用户角色信息查询", notes = "根据用户Code查询角色Codes信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCode", value = "用户编码", type = "String", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getUserRoleCodes() {
        String userCode = get("userCode");
        paramValid(userCode, "userCode");
        Kv kv = Kv.by("roleCodes", sysUserService.getUserRoleCodes(userCode));
        renderJson(R.ok("data", kv));
    }
}
