package io.kungfu.admin.modules.system.controller;

import com.jfinal.core.Path;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.model.SysLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.KungfuController;
import org.kungfu.core.QueryCondition;
import org.kungfu.core.R;
import org.kungfu.core.ResultVO;

@Api(value = "系统日志", tags = "系统日志接口")
@Path("/sys-log")
public class SysLogController extends KungfuController {

    @ApiOperation(value = "分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysLog.class)));
    }

    @ApiOperation(value = "系统日志信息查询", notes = "根据表ID查询系统日志信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysLogId", value = "系统日志ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysLogId = getLong("sysLogId");
        renderJson(selectById(sysLogId, SysLog.class));
    }


    @ApiOperation(value = "删除系统日志记录", notes = "根据表ID删除系统日志记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysLogId", value = "系统日志ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void deleteById() {
        Long sysLogId = getLong("sysLogId");

        renderJson(deleteById(sysLogId, SysLog.class));
    }

}
