/*
Source Database : kungfu_admin
Create Date: 2023-03-29 15:35:08
Update Date: 2023-04-07 18:35:08
*/

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_group` varchar(32) NOT NULL DEFAULT 'default_group' COMMENT '字典分组',
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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='字典数据表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统菜单表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `api_url` varchar(128) NOT NULL DEFAULT '接口地址',
  `api_type` varchar(6) NOT NULL DEFAULT 'GET' COMMENT '接口请求方式',
  `api_method` varchar(80) NOT NULL DEFAULT '' COMMENT '接口方法',
  `api_param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求参数',
  `api_cost` bigint(20) NOT NULL DEFAULT '0' COMMENT '接口请求耗时',
  `visitor_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '访问者ID',
  `visitor` varchar(80) NOT NULL DEFAULT '' COMMENT '访问者',
  `remark` varchar(80) DEFAULT NULL COMMENT '行为备注',
  `visit_ip` varchar(15) NOT NULL COMMENT '访问IP',
  `visit_port` int(6) NOT NULL DEFAULT '0' COMMENT '访问端口号',
  `address` varchar(80) NOT NULL DEFAULT '' COMMENT '访问地址',
  `create_day` int(8) NOT NULL DEFAULT '0' COMMENT '创建日期',
  `create_month` int(8) NOT NULL DEFAULT '0' COMMENT '创建月份',
  `create_year` int(8) NOT NULL DEFAULT '0' COMMENT '创建年份',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_visitor` (`visitor`)
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统日志';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单关系表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

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
) ENGINE=InnoDB AUTO_INCREMENT=600 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色关系表';
