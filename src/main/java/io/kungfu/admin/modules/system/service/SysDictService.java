package io.kungfu.admin.modules.system.service;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysDict;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;

import java.util.Date;
import java.util.List;

public class SysDictService extends KungfuService<SysDict> {
    private final SysDict dao = SysDict.dao;

    public R saveOrUpdate(SysDict sysDict, UserInfo userInfo) {

        if (sysDict == null) {
            return R.fail(641, "信息不能为空");
        }

        R result = existValid(sysDict.getId() == null, sysDict, "dict_code", "dict_name");
        if (result.isFail()) {
            return result;
        }

        Date date = new Date();
        if (sysDict.getId() != null) {
            sysDict.setUpdateUser(userInfo.getUserName());
            sysDict.setUpdateUserId(userInfo.getUserId());
            sysDict.setUpdateTime(date);
            if (sysDict.update()) {
                // 更新成功，移除缓存
                CacheKit.remove("sysDict", sysDict.getDictCode());
                CacheKit.remove("sysDict", "dictTree");
                return R.ok("更新成功");
            }

            return R.fail(643, "更新失败");
        }
        else {

            sysDict.setCreateUser(userInfo.getUserName());
            sysDict.setCreateUserId(userInfo.getUserId());
            sysDict.setCreateTime(date);

            if (sysDict.save()) {
                CacheKit.removeAll("sysDict");
                return R.ok("保存成功");
            }

            return R.fail(642, "保存失败");
        }

    }

    public Record buildTree(Long tagId) {

        String sql = "select id,dict_name as dictName, dict_code as dictCode, is_enabled as isEnabled,display_no as displayNo,remark from sys_dict where 1=1";

        if (tagId != null && tagId != 0L) {
            sql += " and id in (select object_id from pub_tag_relation where tag_id=" + tagId + ")";
        }

        sql += " order by display_no";

        List<Record> dictList =  Db.find(sql);

        Record record = new Record();
        record.set("dictName", "业务字典");
        record.set("dictCode", null);
        record.set("children", dictList);
        return record;
    }

    @Before(Tx.class)
    public R deleteByIds(String sysDictIds) {
        List<String> dictCodes = Db.query("select dict_code from sys_dict where id in (?)",sysDictIds);

        if (Db.update("delete from sys_dict where id in (?)",sysDictIds) < 0 ) {
            return R.fail(641, "删除字典数据失败");
        }

        for (String dictCode : dictCodes) {
            if (Db.update("delete from sys_dict_item where dict_code=?", dictCode) < 0) {
                return R.fail(642, "删除字典项数据失败");
            }
        }

        CacheKit.removeAll("sysDict");

        return R.ok("删除字典数据成功");
    }
}
