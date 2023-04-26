package io.kungfu.admin.modules.system.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysDictItem;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;

import java.util.Date;
import java.util.List;

public class SysDictItemService extends KungfuService<SysDictItem> {
    private final SysDictItem dao = SysDictItem.dao;


    public SysDictItem findByCodeOrName(String dictCode, String codeOrName) {
        return dao.findFirst("select * from sys_dict_item where dict_code=? and (item_value=? or item_key=?)", dictCode, codeOrName, codeOrName);
    }

    public SysDictItem findByOtherName(String dictCode, Long itemId, String itemName) {
        return dao.findFirst("select * from sys_dict_item where dict_code=? and id<>? and (item_value=? or item_key=?)", dictCode, itemId, itemName, itemName);
    }

    public R saveOrUpdate(SysDictItem sysDictItem, UserInfo userInfo) {

        if (sysDictItem == null) {
            return R.fail(630, "信息不能为空");
        }

        if (sysDictItem.getId() == null) {
            SysDictItem exist = findByCodeOrName(sysDictItem.getDictCode(), sysDictItem.getItemValue());
            if(exist != null) {
                return R.fail(631, "新增的字典数据项值重复，请重新填写");
            }

            exist = findByCodeOrName(sysDictItem.getDictCode(), sysDictItem.getItemKey());
            if(exist != null) {
                return R.fail(632, "新增的字典数据项名称重复，请重新填写");
            }
        }

        Date date = new Date();
        if (sysDictItem.getId() != null) {

            SysDictItem exist = findByOtherName(sysDictItem.getDictCode(), sysDictItem.getId(), sysDictItem.getItemKey());
            if(exist != null) {
                return R.fail(633, "修改的字典数据项名称重复，请重新填写");
            }

            sysDictItem.setUpdateUser(userInfo.getUserName());
            sysDictItem.setUpdateUserId(userInfo.getUserId());
            sysDictItem.setUpdateTime(date);
            if (sysDictItem.update()) {
                CacheKit.remove("sysDict", sysDictItem.getDictCode());
                return R.ok("更新成功");
            }

            return R.fail(620, "更新失败");
        }
        else {


            sysDictItem.setCreateUser(userInfo.getUserName());
            sysDictItem.setCreateUserId(userInfo.getUserId());
            sysDictItem.setCreateTime(date);

            if (sysDictItem.save()) {
                CacheKit.remove("sysDict", sysDictItem.getDictCode());
                return R.ok("保存成功");
            }

            return R.fail(630, "保存失败");
        }

    }

    public List<Record> queryDictItemList(String dictCode) {
        return Db.find("select item_key as text,item_value as value from sys_dict_item where dict_code=? order by display_no", dictCode);
    }

    public void initDictCacheData() {
        List<Record> dictList = Db.find("select dict_code from sys_dict where is_enabled=1");

        dictList.forEach(record -> CacheKit.put("sysDict", record.getStr("dict_code"), queryDictItemList(record.getStr("dict_code"))));
    }
}
