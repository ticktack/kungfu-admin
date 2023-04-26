package io.kungfu.admin.modules.system.service;

import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheKit;
import io.kungfu.admin.common.Constant;
import io.kungfu.admin.modules.system.model.SysMenu;
import org.kungfu.core.KungfuService;
import org.kungfu.core.R;
import org.kungfu.core.UserInfo;
import org.kungfu.util.KungfuKit;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SysMenuService extends KungfuService<SysMenu> {
    private final SysMenu dao = SysMenu.dao;

    public Record buildTree(String queryName) {
        String selectSql = "select id,parent_code,parent_name,menu_code,menu_name,is_enabled,link_page,menu_type,icon,display_no,component ";
        String sql = selectSql + "from sys_menu order by display_no";

        if (StrKit.notBlank(queryName)) {
            sql = selectSql + "from sys_menu where menu_name like '%" + queryName + "%' order by display_no";
        }

        List<Record> menuList = Db.find(sql);

        return buildTree(KungfuKit.toHumpsList(menuList), "menuCode", "menuName","菜单目录");
    }

    public SysMenu findByCodeOrName(String codeOrName) {
        return dao.findFirst("select * from sys_menu where menu_code=? or menu_name=?", codeOrName, codeOrName);
    }

    public R saveOrUpdate(SysMenu sysMenu, UserInfo userInfo) {

        if (sysMenu == null) {
            return R.fail(630, "信息不能为空");
        }

        R result = existValid(sysMenu.getId() == null, sysMenu, "menu_code", "menu_name");
        if (result.isFail()) {
            return result;
        }

        SysMenu parent = findByCodeOrName(sysMenu.getParentCode());
        if (parent == null) {
            sysMenu.setParentName("后管平台");
        }
        else {
            sysMenu.setParentName(parent.getMenuName());
        }

        Date date = new Date();

        if (sysMenu.getId() != null) {
            sysMenu.setUpdateUser(userInfo.getUserName());
            sysMenu.setUpdateUserId(userInfo.getUserId());
            sysMenu.setUpdateTime(date);
            if (sysMenu.update()) {
                CacheKit.removeAll("sysMenu");
                return R.ok("更新成功");
            }

            return R.fail(641, "更新失败");
        }
        else {
            sysMenu.setCreateUser(userInfo.getUserName());
            sysMenu.setCreateUserId(userInfo.getUserId());
            sysMenu.setCreateTime(date);

            if (sysMenu.save()) {
                CacheKit.removeAll("sysMenu");
                return R.ok("保存成功");
            }

            return R.fail(640, "保存失败");
        }

    }

    public List<Record> getUserMenuTree(String userCode, String menuCode) {
        String sql = "select a.menu_code,a.parent_code,a.menu_name,a.icon" +
                " from sys_menu a" +
                " left join sys_role_menu b on a.menu_code=b.menu_code" +
                " left join sys_user_role c on b.role_code=c.role_code" +
                " where c.user_code=? and a.parent_code=? and a.is_enabled=1 and a.menu_type=0" +
                " group by a.menu_code order by a.display_no asc";

        List<Record> list = Db.find(sql, userCode, menuCode);
        list = KungfuKit.toHumpsList(list);
        list.forEach(menu -> {
            List<Record> children = this.getUserMenuTree(userCode, menu.getStr("menuCode"));
            if (children != null && children.size() > 0) {
                menu.set("children", children);
            }
        });

        return list;
    }

    public Map<String, List<String>> getUserAuthButtons(String userCode) {
        String menuCodesSql = "select distinct a.parent_code from sys_menu a " +
                "left join sys_role_menu b on a.menu_code=b.menu_code " +
                "left join sys_user_role c on b.role_code=c.role_code " +
                "where c.user_code=? and a.menu_type=1";
        String buttonCodesSql = "select a.menu_code from sys_menu a " +
                "left join sys_role_menu b on a.menu_code=b.menu_code " +
                "left join sys_user_role c on b.role_code=c.role_code " +
                "where c.user_code=? and a.menu_type=1 and a.parent_code=?";
        List<String> menuCodes = Db.query(menuCodesSql, userCode);
        Map<String, List<String>> authButtons = new HashMap<>();
        for (String menuCode : menuCodes) {
            List<String> buttonCodes = Db.query(buttonCodesSql, userCode, menuCode);
            authButtons.put(menuCode, buttonCodes);
        }

        return authButtons;
    }


    public List<Record> getRoleMenuTree(String roleCode, String menuCode) {
        String sql = "select a.menu_code,a.parent_code,a.menu_name,a.icon" +
                " from sys_menu a" +
                " left join sys_role_menu b on a.menu_code=b.menu_code" +
                " where b.role_code=? and a.parent_code=? and a.is_enabled=1 and a.menu_type=0" +
                " group by a.menu_code order by a.display_no asc";

        List<Record> list = Db.find(sql, roleCode, menuCode);
        list = KungfuKit.toHumpsList(list);
        list.forEach(menu -> {
            List<Record> children = this.getRoleMenuTree(roleCode, menu.getStr("menuCode"));
            if (children != null && children.size() > 0) {
                menu.set("children", children);
            }
        });

        return list;
    }

    public boolean setStatus(Long menuId, Boolean isEnabled, UserInfo userInfo) {
        SysMenu menu = dao.findById(menuId);
        menu.setIsEnabled(isEnabled);
        menu.setUpdateUserId(userInfo.getUserId());
        menu.setUpdateUser(userInfo.getUserName());
        menu.setUpdateTime(new Date());
        return menu.update();
    }


    public R deleteById(Long menuId) {

        if (menuId == null) {
            return R.fail(661, "删除记录ID为空");
        }

        SysMenu menu = dao.findById(menuId);

        List<SysMenu> list = dao.find("select * from sys_menu where parent_code=?", menu.getMenuCode());

        if (list != null && list.size() > 0) {
            return R.fail(662, "当前节点有子节点不允许删除");
        }

        return dao.deleteById(menuId) ? R.ok("删除成功") : R.fail("删除失败");
    }
}
