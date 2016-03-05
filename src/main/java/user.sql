CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '姓名',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `age` int(3) NOT NULL COMMENT '年龄',
  `phone` varchar(11) NOT NULL DEFAULT '0' COMMENT '手机',
  `deliveryaddress` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `adddate` int(11) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
