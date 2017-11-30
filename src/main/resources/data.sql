CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spring`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`)
) 
insert into `user` (`id`,`username`,`password`,`phone` ) 
values (1,'admin','admin','18292833834'),
(2,'test','test','183928338344'),
(3,'test2','test2','18293839484'),
(4,'test3','test3','18283948953')