package io.kungfu.admin.modules.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.dto.SysDictItemDTO;
import io.kungfu.admin.modules.system.model.SysDictItem;
import io.kungfu.admin.modules.system.service.SysDictItemService;
import io.kungfu.admin.modules.system.validate.SysDictItemValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.KungfuController;
import org.kungfu.core.QueryCondition;
import org.kungfu.core.R;
import org.kungfu.core.ResultVO;

@Api(value = "字典数据", tags = "字典数据接口")
@Path("/sys-dict-item")
public class SysDictItemController extends KungfuController {
    @Inject
    private SysDictItemService sysDictItemService;

    @ApiOperation(value = "字典数据信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysDictItem", value = "字典数据信息", dataTypeClass = SysDictItemDTO.class,  paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysDictItemValidator.class)
    public void saveOrUpdate() {
        SysDictItem sysDictItem = toModel(SysDictItem.class);
        renderJson(sysDictItemService.saveOrUpdate(sysDictItem, getUserInfo()));
    }


    @ApiOperation(value = "分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysDictItem.class)));
    }

    @ApiOperation(value = "字典数据信息查询", notes = "根据表ID查询字典数据信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysDictItemId", value = "字典数据ID", defaultValue = "100", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysDictItemId = getLong("sysDictItemId");
        renderJson(deleteById(sysDictItemId, SysDictItem.class));
    }


    @ApiOperation(value = "删除字典数据记录", notes = "根据表ID删除字典数据记录，支持批量删除")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "sysDictItemIds", value = "字典数据IDs", defaultValue = "100,200,300", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void deleteByIds() {
        String sysDictItemIds = get("sysDictItemIds");
        renderJson(deleteByIds(sysDictItemIds, SysDictItem.class));
    }

}
