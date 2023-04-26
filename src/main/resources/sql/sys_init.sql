
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_code` varchar(32) NOT NULL COMMENT '字典编号',
  `dict_name` varchar(32) NOT NULL COMMENT '字典名称',
  `remark` varchar(80) NOT NULL DEFAULT '' COMMENT '备注',
  `display_no` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用(1:是,0:否)',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=604 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('600', 'object_type', '标签对象类型', '打标签对象', '2', '1', '600', 'admin', '2023-04-10 10:13:58', '600', '管理员', '2023-04-15 12:07:26');
INSERT INTO `sys_dict` VALUES ('601', 'sex', '性别', '', '1', '1', '600', '管理员', '2023-04-15 12:06:58', '600', '管理员', '2023-04-15 12:07:22');
INSERT INTO `sys_dict` VALUES ('602', 'auth_button', '权限按钮', '', '3', '1', '600', '管理员', '2023-04-21 09:25:03', '0', '', null);
INSERT INTO `sys_dict` VALUES ('603', 'menu_type', '菜单类型', '', '4', '1', '600', '管理员', '2023-04-21 09:34:19', '0', '', null);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_code` varchar(32) NOT NULL COMMENT '字典编码',
  `item_key` varchar(32) NOT NULL COMMENT '字典项键',
  `item_value` varchar(80) NOT NULL COMMENT '字典项值',
  `display_no` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `remark` varchar(80) NOT NULL DEFAULT '' COMMENT '备注',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_code` (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=622 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO `sys_dict_item` VALUES ('600', 'object_type', '字典', 'dict', '1', '', '600', 'admin', '2023-04-10 10:14:51', '0', '', '2023-04-15 07:14:32');
INSERT INTO `sys_dict_item` VALUES ('601', 'object_type', '字段元数据', 'metadata_column', '2', '', '600', 'admin', '2023-04-10 10:15:04', '0', '', '2023-04-15 07:15:09');
INSERT INTO `sys_dict_item` VALUES ('605', 'sex', '女', '2', '2', '字典编号：sex', '600', '管理员', '2023-04-15 12:12:58', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('606', 'sex', '男', '1', '1', '字典编号：sex', '600', '管理员', '2023-04-20 18:32:43', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('607', 'auth_button', '新增', 'add', '1', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:25:16', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('608', 'auth_button', '编辑', 'edit', '2', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:25:40', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('609', 'auth_button', '删除', 'delete', '3', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:26:00', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('610', 'auth_button', '查看', 'view', '4', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:26:14', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('611', 'auth_button', '增加树节点', 'addTreeNode', '5', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:27:21', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('612', 'auth_button', '添加角色', 'addRole', '6', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:28:06', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('613', 'auth_button', '重置密码', 'resetPassword', '7', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:28:34', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('614', 'auth_button', '设置状态', 'setStatus', '8', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:28:50', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('615', 'auth_button', '允许登录设置', 'loginAllow', '9', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:30:16', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('616', 'auth_button', '批量删除', 'batchDelete', '10', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:31:14', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('617', 'auth_button', '批量添加', 'batchAdd', '11', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:31:31', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('618', 'auth_button', '导入', 'import', '12', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:32:12', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('619', 'auth_button', '导出', 'export', '13', '字典编号：auth_button', '600', '管理员', '2023-04-21 09:32:31', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('620', 'menu_type', '菜单', '0', '1', '字典编号：menu_type', '600', '管理员', '2023-04-21 09:34:31', '0', '', null);
INSERT INTO `sys_dict_item` VALUES ('621', 'menu_type', '按钮', '1', '2', '字典编号：menu_type', '600', '管理员', '2023-04-21 09:34:41', '0', '', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_code` varchar(32) NOT NULL COMMENT '上级编码',
  `parent_name` varchar(32) NOT NULL COMMENT '上级名称',
  `menu_code` varchar(32) NOT NULL COMMENT '编码',
  `menu_name` varchar(32) NOT NULL COMMENT '功能名称',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用(1:是,0:否)',
  `link_page` varchar(128) NOT NULL DEFAULT '' COMMENT '菜单地址',
  `menu_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '菜单类型(0:菜单,1:按钮)',
  `icon` varchar(50) NOT NULL DEFAULT '' COMMENT '图标',
  `display_no` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `remark` varchar(80) NOT NULL DEFAULT '' COMMENT '注释',
  `component` varchar(80) NOT NULL DEFAULT '' COMMENT '组件名称',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=629 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('600', 'root', '开发平台', 'system', '系统管理', '1', '', '0', 'Setting', '1', '', '', '600', 'admin', '2023-04-10 10:06:18', '600', '管理员', '2023-04-22 01:15:45');
INSERT INTO `sys_menu` VALUES ('601', 'system', '系统管理', 'menu', '菜单管理', '1', '', '0', 'Menu', '1', '', '', '600', 'admin', '2023-04-10 10:06:34', '600', '管理员', '2023-04-22 01:15:53');
INSERT INTO `sys_menu` VALUES ('603', 'system', '系统管理', 'dict', '字典管理', '1', '', '0', 'Management', '6', '', '', '600', 'admin', '2023-04-10 10:07:39', '604', '杨乐', '2023-04-21 10:57:19');
INSERT INTO `sys_menu` VALUES ('618', 'system', '系统管理', 'org', '部门管理', '1', '', '0', 'Cherry', '3', '', '', '600', '管理员', '2023-04-16 22:56:02', '604', '杨乐', '2023-04-21 15:02:24');
INSERT INTO `sys_menu` VALUES ('619', 'system', '系统管理', 'role', '角色管理', '1', '', '0', 'Unlock', '4', '', '', '600', '管理员', '2023-04-16 22:56:14', '604', '杨乐', '2023-04-21 14:58:45');
INSERT INTO `sys_menu` VALUES ('620', 'system', '系统管理', 'user', '用户管理', '1', '', '0', 'Avatar', '5', '', '', '600', '管理员', '2023-04-18 17:27:04', '600', '管理员', '2023-04-21 10:27:17');
INSERT INTO `sys_menu` VALUES ('621', 'system', '系统管理', 'log', '日志管理', '1', '', '0', 'List', '7', '', '', '600', '管理员', '2023-04-18 17:28:42', '604', '杨乐', '2023-04-21 10:57:26');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_code` varchar(32) NOT NULL COMMENT '上级部门编号',
  `parent_name` varchar(32) NOT NULL COMMENT '上级部门名称',
  `org_code` varchar(32) NOT NULL COMMENT '部门编号',
  `org_name` varchar(32) NOT NULL COMMENT '部门名称',
  `head_name` varchar(30) NOT NULL DEFAULT '' COMMENT '部门负责人姓名',
  `head_mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '部门负责人手机号',
  `head_email` varchar(50) NOT NULL DEFAULT '' COMMENT '部门负责人邮件',
  `remark` varchar(80) NOT NULL DEFAULT '' COMMENT '备注',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用(1:是,0:否)',
  `display_no` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=606 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('600', 'root', '部门目录', 'sysadmin', '管理部', '', '', '', '', '0', '3', '0', '', '2023-04-10 10:09:33', '600', '管理员', '2023-04-15 21:45:06');
INSERT INTO `sys_org` VALUES ('601', 'root', '开发平台', 'develop', '开发部', '', '', '', '', '0', '1', '0', '', '2023-04-10 10:09:46', '0', '', null);
INSERT INTO `sys_org` VALUES ('604', 'develop', '开发部', 'develop-1', '开发一部', '', '', '', '', '1', '1', '600', '管理员', '2023-04-15 21:53:06', '600', '管理员', '2023-04-15 22:07:30');
INSERT INTO `sys_org` VALUES ('605', 'develop', '开发部', 'develop-2', '开发二部', '', '', '', '', '1', '1', '600', '管理员', '2023-04-15 21:53:24', '600', '管理员', '2023-04-15 22:07:37');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `role_name` varchar(32) NOT NULL COMMENT '角色名称',
  `parent_code` varchar(32) NOT NULL COMMENT '父角色编码',
  `parent_name` varchar(32) NOT NULL COMMENT '父角色名称',
  `display_no` int(8) NOT NULL DEFAULT '1' COMMENT '排序',
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否启用(1:是,0:否)',
  `remark` varchar(80) NOT NULL DEFAULT '' COMMENT '备注',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=605 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('600', 'admin', '管理员', 'superadmin', '超级管理员', '1', '1', '', '600', 'admin', '2023-04-10 10:10:42', '0', '', null);
INSERT INTO `sys_role` VALUES ('603', '123', '运维', 'root', '角色目录', '1', '1', '', '600', '管理员', '2023-04-16 22:27:14', '0', '', null);
INSERT INTO `sys_role` VALUES ('604', 'pm', '产品经理', 'root', '角色目录', '3', '1', '', '600', '管理员', '2023-04-18 17:35:14', '0', '', null);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '角色编码',
  `menu_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '菜单编码',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=768 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('685', '123', 'role', '600', '管理员', '2023-04-18 20:45:58');
INSERT INTO `sys_role_menu` VALUES ('686', '123', 'user', '600', '管理员', '2023-04-18 20:45:58');
INSERT INTO `sys_role_menu` VALUES ('687', '123', 'code', '600', '管理员', '2023-04-18 20:45:58');
INSERT INTO `sys_role_menu` VALUES ('688', '123', 'root', '600', '管理员', '2023-04-18 20:45:58');
INSERT INTO `sys_role_menu` VALUES ('689', '123', 'system', '600', '管理员', '2023-04-18 20:45:58');
INSERT INTO `sys_role_menu` VALUES ('753', 'admin', 'system', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('754', 'admin', 'menu', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('755', 'admin', 'org', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('756', 'admin', 'role', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('757', 'admin', 'user', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('758', 'admin', 'dict', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('759', 'admin', 'log', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('760', 'admin', 'root', '604', '杨乐', '2023-04-21 14:21:06');
INSERT INTO `sys_role_menu` VALUES ('761', 'pm', 'menu', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('762', 'pm', 'org', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('763', 'pm', 'role', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('764', 'pm', 'user', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('765', 'pm', 'dict', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('766', 'pm', 'root', '604', '杨乐', '2023-04-21 14:22:11');
INSERT INTO `sys_role_menu` VALUES ('767', 'pm', 'system', '604', '杨乐', '2023-04-21 14:22:11');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_code` varchar(32) NOT NULL COMMENT '用户编号默认手机号',
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `password` varchar(80) NOT NULL COMMENT '密码',
  `org_code` varchar(32) NOT NULL COMMENT '部门编号',
  `job_no` varchar(18) NOT NULL DEFAULT '' COMMENT '工号',
  `post` varchar(50) NOT NULL DEFAULT '' COMMENT '职务 字典',
  `sex` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别 字典',
  `mobile` varchar(15) NOT NULL DEFAULT '' COMMENT '手机号码',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT 'EMAIL',
  `address` varchar(80) NOT NULL DEFAULT '' COMMENT '家庭地址',
  `fail_num` int(8) NOT NULL DEFAULT '0' COMMENT '登录错误次数',
  `allow_login` tinyint(1) NOT NULL DEFAULT '0' COMMENT '允许登录',
  `allow_login_time` datetime DEFAULT NULL COMMENT '允许登录时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人ID',
  `update_user` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=605 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('600', 'admin', '管理员', '5D720E9D590870706665B664427D1AE1', 'sysadmin', '', '', '1', '', '', '', '1', '1', null, '2023-04-24 17:00:08', '0', '', '2023-04-10 10:08:59', '600', '管理员', '2023-04-24 17:00:08');
INSERT INTO `sys_user` VALUES ('602', 'wudi', '无敌', '5B477617971766039DEC1533D4673B5B', 'sysadmin', '', '', '1', '15659899211', 'qq@sina.com', '金山大道', '0', '1', null, null, '600', '管理员', '2023-04-17 19:15:23', '0', '', '2023-04-17 19:20:42');
INSERT INTO `sys_user` VALUES ('604', '13559939681', '杨乐', 'C18E864938FA8BC286180FBF4D77CB41', 'develop', '', '', '1', '13559939681', '', '', '0', '1', null, '2023-04-24 16:59:39', '600', '管理员', '2023-04-20 15:30:36', '0', '', '2023-04-24 16:59:39');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(32) NOT NULL COMMENT '角色编码',
  `user_code` varchar(32) NOT NULL COMMENT '用户编码',
  `create_user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人ID',
  `create_user` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=619 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('606', 'admin', '13566666666', '600', '管理员', '2023-04-18 13:59:32');
INSERT INTO `sys_user_role` VALUES ('608', 'root', 'wudi', '600', '管理员', '2023-04-19 16:27:59');
INSERT INTO `sys_user_role` VALUES ('609', '123', 'wudi', '600', '管理员', '2023-04-19 16:27:59');
INSERT INTO `sys_user_role` VALUES ('614', 'admin', 'admin', '600', '管理员', '2023-04-20 23:43:53');
INSERT INTO `sys_user_role` VALUES ('618', 'admin', '13559939681', '604', '杨乐', '2023-04-21 14:22:45');
