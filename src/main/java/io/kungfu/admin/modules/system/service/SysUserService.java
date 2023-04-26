package io.kungfu.admin.modules.system.service;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysUser;
import io.kungfu.admin.modules.system.model.SysUserRole;
import io.kungfu.admin.util.Md5Kit;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;

import java.util.*;

public class SysUserService extends KungfuService<SysUser> {
    private final SysUser dao = SysUser.dao;

    public Page<Record> wapperQueryPage(Page<Record> page) {
        Map<String, String> codeNameMap = new HashMap<>();
        Db.findByCache("sysOrg", "ogrCodeNameMap", "select org_name, org_code from sys_org").forEach(record -> codeNameMap.put(record.getStr("org_code"), record.getStr("org_name")));

        page.getList().forEach(record -> {
            record.set("orgName", codeNameMap.get(record.getStr("orgCode")));
            record.remove("password");
        });

        return page;
    }

    public R saveOrUpdate(SysUser sysUser, UserInfo userInfo) {

        if (sysUser == null) {
            return R.fail(630, "信息不能为空");
        }

        Date date = new Date();

        R result = existValid(sysUser.getId() == null, sysUser, "user_code", "user_name");
        if (result.isFail()) {
            return result;
        }

        if (StrKit.isBlank(sysUser.getPassword())) {
            // 默认手机后六位
            sysUser.setPassword(Md5Kit.md5(sysUser.getMobile().substring(5)));
        }
        else {
            sysUser.setPassword(Md5Kit.md5(sysUser.getPassword()));
        }

        if (sysUser.getId() != null) {
            sysUser.setUpdateUser(userInfo.getUserName());
            sysUser.setUpdateUserId(userInfo.getUserId());
            sysUser.setUpdateTime(date);
            if (sysUser.update()) {
                return R.ok("更新成功");
            }

            return R.fail(641, "更新失败");
        }
        else {
            sysUser.setCreateUser(userInfo.getUserName());
            sysUser.setCreateUserId(userInfo.getUserId());
            sysUser.setCreateTime(date);

            if (sysUser.save()) {
                return R.ok("保存成功");
            }

            return R.fail(640, "保存失败");
        }

    }

    public boolean setStatus(Long userId, Boolean isAllowLogin, UserInfo userInfo) {
        SysUser sysUser = dao.findById(userId);
        sysUser.setAllowLogin(isAllowLogin);
        sysUser.setUpdateUser(userInfo.getUserName());
        sysUser.setUpdateUserId(userInfo.getUserId());
        sysUser.setUpdateTime(new Date());
        return sysUser.update();
    }


    public SysUser findByUserCodeOrUserName(String userCode) {

        return dao.findFirst("select * from sys_user where user_code=? or user_name=?", userCode, userCode);
    }

    public boolean saveUserRole(String userCode, String roleCodes, UserInfo userInfo) {
        return Db.tx(() -> {
            String sql = "delete from sys_user_role where user_code=?";
            if (Db.update(sql, userCode) >= 0) {
                List<SysUserRole> userRoleList = new ArrayList<>();
                for (String roleCode : roleCodes.split(",")) {
                    SysUserRole userRole = new SysUserRole();

                    userRole.setUserCode(userCode);
                    userRole.setRoleCode(roleCode);
                    userRole.setCreateUserId(userInfo.getUserId());
                    userRole.setCreateUser(userInfo.getUserName());
                    userRole.setCreateTime(new Date());

                    userRoleList.add(userRole);
                }

                int[] result = Db.batchSave(userRoleList, userRoleList.size());

                for (int r : result) {
                    if (r < 0) {
                        return false;
                    }
                }
                CacheKit.removeAll("sysMenu");
                return true;
            }
            return false;
        });
    }


    public boolean deleteUserRole(String userCode, String roleCode) {
        return Db.update("delete from sys_user_role where user_code=? and role_code=?", userCode,roleCode) >= 0;
    }

    public boolean resetPassword(Long[] userIds) {
        return Db.tx(() -> {
            for (Long userId : userIds) {
                SysUser user = dao.findById(userId);
                if (!user.getUserCode().equals("admin")) {
                    // 默认手机后六位
                    user.setPassword(Md5Kit.md5(user.getMobile().substring(5)));
                    if(!user.update()) {
                        return false;
                    }
                }
            }
            return true;
        });
    }

    public String getUserRoleCodes(String userCode) {
        return Db.queryStr("select group_concat(role_code) from sys_user_role where user_code=?", userCode);
    }
}
