package io.kungfu.admin.common;

/**
 * 需要留痕审计的接口备注
 */
public class LogKit {

    public static String logRemark(String url) {
        String remark = "";

        switch (url) {
            case "/sys-menu/saveOrUpdate":
                remark = "新增或更新了菜单";
                break;
            case "/sys-menu/deleteById":
                remark = "删除了菜单记录";
                break;
            case "/sys-menu/setStatus":
                remark = "设置了菜单状态";
                break;
            case "/sys-role/saveOrUpdate":
                remark = "新增或更新了角色";
                break;
            case "/sys-role/deleteByIds":
                remark = "删除了角色记录";
                break;
            case "/sys-role/setStatus":
                remark = "设置了角色状态";
                break;
            case "/sys-role/saveRoleMenus":
                remark = "给角色添加菜单权限";
                break;
            case "/sys-org/saveOrUpdate":
                remark = "新增或更新了部门";
                break;
            case "/sys-org/deleteByIds":
                remark = "删除了部门记录";
                break;
            case "/sys-org/setStatus":
                remark = "设置了部门状态";
                break;
            case "/sys-user/saveOrUpdate":
                remark = "新增或更新了用户";
                break;
            case "/sys-user/deleteByIds":
                remark = "删除了用户记录";
                break;
            case "/sys-user/setAllowLogin":
                remark = "设置了用户登录状态";
                break;
            case "/sys-user/saveUserRole":
                remark = "给用户添加了角色权限";
                break;
            case "/sys-user/resetPassword":
                remark = "重置了用户密码";
                break;
        }


        return remark;
    }
}
