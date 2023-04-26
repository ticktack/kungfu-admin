package io.kungfu.admin.modules.system.service;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.modules.system.model.SysRole;
import io.kungfu.admin.modules.system.model.SysRoleMenu;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;
import org.kungfu.util.KungfuKit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleService extends KungfuService<SysRole> {
    private final SysRole dao = SysRole.dao;

    public Record buildTree(String queryName) {
        String sql = "select * from sys_role order by display_no";

        if (StrKit.notBlank(queryName)) {
            sql = "select * from sys_role where role_name like '%" + queryName + "%' order by display_no";
        }

        List<Record> sysRoleList = Db.find(sql);

        return buildTree(KungfuKit.toHumpsList(sysRoleList), "roleCode", "roleName", "角色目录");
    }

    public SysRole findByCodeOrName(String codeOrName) {
        return dao.findFirst("select * from sys_role where role_code=? or role_name=?", codeOrName, codeOrName);
    }

    public R saveOrUpdate(SysRole sysRole, UserInfo userInfo) {

        if (sysRole == null) {
            return R.fail(630, "信息不能为空");
        }

        R result = existValid(sysRole.getId() == null, sysRole, "role_code", "role_name");
        if (result.isFail()) {
            return result;
        }

        SysRole parent = findByCodeOrName(sysRole.getParentCode());
        if (parent == null) {
            sysRole.setParentName("角色目录");
        }
        else {
            sysRole.setParentName(parent.getRoleName());
        }

        Date date = new Date();

        if (sysRole.getId() != null) {
            sysRole.setUpdateUser(userInfo.getUserName());
            sysRole.setUpdateUserId(userInfo.getUserId());
            sysRole.setUpdateTime(date);
            if (sysRole.update()) {
                CacheKit.remove("sysRole", "sysRoleTree");
                return R.ok("更新成功");
            }

            return R.fail(620, "更新失败");
        }
        else {
            sysRole.setCreateUser(userInfo.getUserName());
            sysRole.setCreateUserId(userInfo.getUserId());
            sysRole.setCreateTime(date);

            if (sysRole.save()) {
                CacheKit.removeAll("sysRole");
                return R.ok("保存成功");
            }

            return R.fail(630, "保存失败");
        }

    }

    public boolean setStatus(Long sysRoleId, Boolean isEnabled, UserInfo userInfo) {
        SysRole sysRole = dao.findById(sysRoleId);
        sysRole.setIsEnabled(isEnabled);
        sysRole.setUpdateUserId(userInfo.getUserId());
        sysRole.setUpdateUser(userInfo.getUserName());
        sysRole.setUpdateTime(new Date());
        return sysRole.update();
    }

    public boolean saveRoleMenu(String roleCode, String menuCodes, UserInfo userInfo) {
        return Db.tx(() -> {
            String sql = "delete from sys_role_menu where role_code=?";
            if (Db.update(sql, roleCode) >= 0) {
                List<SysRoleMenu> roleMenuList = new ArrayList<>();
                for (String menuCode : menuCodes.split(",")) {
                    SysRoleMenu roleMenu = new SysRoleMenu();

                    roleMenu.setMenuCode(menuCode);
                    roleMenu.setRoleCode(roleCode);
                    roleMenu.setCreateUserId(userInfo.getUserId());
                    roleMenu.setCreateUser(userInfo.getUserName());
                    roleMenu.setCreateTime(new Date());

                    roleMenuList.add(roleMenu);
                }

                int[] result = Db.batchSave(roleMenuList, roleMenuList.size());

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

    public String getRoleMenuCodes(String menuCode) {
        return Db.queryStr("select group_concat(menu_code) from sys_role_menu where role_code=?", menuCode);
    }

}
