package io.kungfu.admin.config;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import io.kungfu.admin.modules.system.model.*;

public class MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		// system
		arp.addMapping("sys_menu", "id", SysMenu.class);
		arp.addMapping("sys_org", "id", SysOrg.class);
		arp.addMapping("sys_role", "id", SysRole.class);
		arp.addMapping("sys_role_menu", "id", SysRoleMenu.class);
		arp.addMapping("sys_user", "id", SysUser.class);
		arp.addMapping("sys_user_role", "id", SysUserRole.class);
		arp.addMapping("sys_log", "id", SysLog.class);
		arp.addMapping("sys_dict", "id", SysDict.class);
		arp.addMapping("sys_dict_item", "id", SysDictItem.class);

	}
}

