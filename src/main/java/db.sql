CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(11) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `password` varchar(255) default NULL,
  `sex` varchar(2) default NULL COMMENT '性别',
  `age` int(3) default NULL COMMENT '年龄',
  `phone` varchar(11) default '0' COMMENT '手机',
  `deliveryaddress` varchar(200) default NULL COMMENT '收货地址',
  `adddate` int(11) unsigned default NULL COMMENT '添加时间',
  `islock` tinyint(1) default NULL COMMENT '是否锁定1锁定 0未锁定',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `a` (`name`),
  UNIQUE KEY `n&p` (`name`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=100027 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `address` (
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS  `menu` (
  `id` bigint(20) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL COMMENT '菜单名',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 权限
CREATE TABLE IF NOT EXISTS `permission` (
  `id` bigint(11) NOT NULL auto_increment,
  `permission_name` varchar(50) NOT NULL COMMENT '权限名',
  `permission_url` varchar(100) NOT NULL COMMENT '权限url',
  `permission_code` varchar(30) NOT NULL,
  `permission_parentcode` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 角色
CREATE TABLE IF NOT EXISTS  `role` (
  `id` bigint(11) NOT NULL auto_increment,
  `role_name` varchar(50) NOT NULL COMMENT '角色名',
  `role_description` varchar(200) default NULL COMMENT '角色描述',
  PRIMARY KEY  (`id`),
) ENGINE=InnoDB AUTO_INCREMENT=100027 DEFAULT CHARSET=utf8;
-- 用户-角色
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` bigint(11) NOT NULL auto_increment,
  `user_id` bigint(50) NOT NULL COMMENT '用户id',
  `role_id` int(2) default NULL COMMENT '角色id',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 角色权限
CREATE TABLE IF NOT EXISTS `role_permission` (
  `id` bigint(11) NOT NULL auto_increment,
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `permission_id` bigint(2) NOT NULL COMMENT '权限id',
  PRIMARY KEY  (`id`),
  UNIQUE KEY `a` (`role_id`),
  UNIQUE KEY `n&p` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'menu', '0');
INSERT INTO `menu` VALUES ('2', 'submenu', '1');

DROP TABLE IF EXISTS `card`;
CREATE TABLE `card` (
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  `card_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of card
-- ----------------------------
INSERT INTO `card` VALUES ('1', 'this is cardno');

-- ----------------------------
-- Function structure for `fristPinyin`
-- ----------------------------
DROP FUNCTION IF EXISTS `fristPinyin`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fristPinyin`(P_NAME VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
    DECLARE V_RETURN VARCHAR(255);
    SET V_RETURN = ELT(INTERVAL(CONV(HEX(left(CONVERT(P_NAME USING gbk),1)),16,10),
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),   
    'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','W','X','Y','Z');
    RETURN V_RETURN;
END
;;
DELIMITER ;