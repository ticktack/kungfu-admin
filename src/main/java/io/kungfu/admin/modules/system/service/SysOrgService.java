package io.kungfu.admin.modules.system.service;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysOrg;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;
import org.kungfu.util.KungfuKit;

import java.util.Date;
import java.util.List;

public class SysOrgService extends KungfuService<SysOrg> {
    private final SysOrg dao = SysOrg.dao;

    public Record buildTree(String queryName) {
        String sql = "select * from sys_org order by display_no";

        if (StrKit.notBlank(queryName)) {
            sql = "select * from sys_org where org_name like '%" + queryName + "%' order by display_no";
        }

        List<Record> sysOrgList = Db.find(sql);

        return buildTree(KungfuKit.toHumpsList(sysOrgList), "orgCode", "orgName", "部门目录");
    }

    public SysOrg findByCode(String codeOrName) {
        return dao.findFirst("select * from sys_org where org_code=? or org_name=?", codeOrName, codeOrName);
    }

    public R saveOrUpdate(SysOrg sysOrg, UserInfo userInfo) {

        if (sysOrg == null) {
            return R.fail(630, "信息不能为空");
        }

        R result = existValid(sysOrg.getId() == null, sysOrg, "org_code", "org_name");
        if (result.isFail()) {
            return result;
        }

        SysOrg parent = findByCode(sysOrg.getParentCode());
        if (parent == null) {
            sysOrg.setParentName("部门目录");
        }
        else {
            sysOrg.setParentName(parent.getOrgName());
        }

        Date date = new Date();

        if (sysOrg.getId() != null) {
            sysOrg.setUpdateUser(userInfo.getUserName());
            sysOrg.setUpdateUserId(userInfo.getUserId());
            sysOrg.setUpdateTime(date);
            if (sysOrg.update()) {
                CacheKit.remove("sysOrg", "sysOrgTree");
                return R.ok("更新成功");
            }

            return R.fail(620, "更新失败");
        }
        else {
            sysOrg.setCreateUser(userInfo.getUserName());
            sysOrg.setCreateUserId(userInfo.getUserId());
            sysOrg.setCreateTime(date);

            if (sysOrg.save()) {
                CacheKit.removeAll("sysOrg");
                return R.ok("保存成功");
            }

            return R.fail(630, "保存失败");
        }

    }

    public boolean setStatus(Long sysOrgId, Boolean isEnabled, UserInfo userInfo) {
        SysOrg sysOrg = dao.findById(sysOrgId);
        sysOrg.setIsEnabled(isEnabled);
        sysOrg.setUpdateUserId(userInfo.getUserId());
        sysOrg.setUpdateUser(userInfo.getUserName());
        sysOrg.setUpdateTime(new Date());
        return sysOrg.update();
    }
}
