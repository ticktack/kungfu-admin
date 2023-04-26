package io.kungfu.admin.modules.system.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Path;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;
import com.lastb7.swagger.annotation.ApiResCustom;
import com.lastb7.swagger.enumeration.ApiEnum;
import io.kungfu.admin.modules.system.dto.SysDictDTO;
import io.kungfu.admin.modules.system.model.SysDict;
import io.kungfu.admin.modules.system.service.SysDictItemService;
import io.kungfu.admin.modules.system.service.SysDictService;
import io.kungfu.admin.modules.system.validate.SysDictValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.kungfu.core.KungfuController;
import org.kungfu.core.QueryCondition;
import org.kungfu.core.R;
import org.kungfu.core.ResultVO;

import java.util.List;

@Api(value = "系统字典", tags = "系统字典接口")
@Path("/sys-dict")
public class SysDictController extends KungfuController {
    @Inject
    private SysDictService sysDictService;

    @Inject
    private SysDictItemService sysDictItemService;

    @ApiOperation(value = "字典树查询", notes = "根据分组查询字典树")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tagId", value = "标签ID", example = "100")
    })
    @ApiResCustom(ResultVO.class)
    public void tree() {
        Long tagId = getLong("tagId");
        // 缓存
        Record dictTree = CacheKit.get("sysDict", "dictTree", () -> sysDictService.buildTree(tagId));

        renderJson(R.ok("data", dictTree));
    }

    @ApiOperation(value = "系统字典信息保存或修改", notes = "根据表单内容保存或更新内容", httpMethod = ApiEnum.METHOD_POST, produces = ApiEnum.PRODUCES_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysDict", value = "系统字典信息", dataTypeClass = SysDictDTO.class, paramType = ApiEnum.PARAM_TYPE_BODY)
    })
    @ApiResCustom(ResultVO.class)
    @Before(SysDictValidator.class)
    public void saveOrUpdate() {
        SysDict sysDict = toModel(SysDict.class);
        renderJson(sysDictService.saveOrUpdate(sysDict, getUserInfo()));
    }

    @ApiOperation(value = "分页查询", notes = "根据页码及查询条件分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "queryCondition", value = "查询条件", dataTypeClass = QueryCondition.class, paramType = ApiEnum.PARAM_TYPE_BODY, required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryPage() {
        renderJson(R.ok("data", queryPage(SysDict.class)));
    }

    @ApiOperation(value = "系统字典信息查询", notes = "根据表ID查询系统字典信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysDictId", value = "系统字典ID", example = "100")
    })
    @ApiResCustom(ResultVO.class)
    public void getInfo() {
        Long sysDictId = getLong("sysDictId");
        renderJson(selectById(sysDictId, SysDict.class));
    }

    @ApiOperation(value = "删除系统字典记录", notes = "根据表ID删除系统字典记录，支持批量删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysDictIds", value = "系统字典IDs", example = "100,200,300")
    })
    @ApiResCustom(ResultVO.class)
    @Before(EvictInterceptor.class)
    @CacheName("sysDict")
    public void deleteByIds() {
        String sysDictIds = get("sysDictIds");
        paramValid(sysDictIds, 631, "sysDictIds");

        renderJson(sysDictService.deleteByIds(sysDictIds));
    }

    @ApiOperation(value = "字典查询接口", notes = "根据字典编码查询字典值列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictCode", value = "字典编码", required = true)
    })
    @ApiResCustom(ResultVO.class)
    public void queryDict() {
        String dictCode = get("dictCode");
        paramValid(dictCode, "dictCode");

        List<Record> dictList = CacheKit.get("sysDict", dictCode, () -> sysDictItemService.queryDictItemList(dictCode));

        renderJson(R.ok("data", dictList));
    }

    @ApiOperation(value = "清理字典缓存", notes = "根据字典编码清理字典缓存", httpMethod = ApiEnum.METHOD_GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dictCode", value = "字典编码")
    })
    @ApiResCustom(ResultVO.class)
    public void removeCacheByCode() {
        String dictCode = get("dictCode");
        if (StrKit.isBlank(dictCode)) {
            CacheKit.removeAll("sysDict");
        } else {
            CacheKit.remove("sysDict", dictCode);
        }
        renderJson(R.ok("清理字典缓存成功"));
    }

}
