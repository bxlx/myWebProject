/*
Navicat MySQL Data Transfer

Source Server         : localHost
Source Server Version : 50520
Source Host           : 127.0.0.1:3306
Source Database       : mymavenprojectdb

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2017-11-19 16:00:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` tinyint(11) NOT NULL,
  `name` char(32) CHARACTER SET utf8 NOT NULL,
  `url` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  `orderNum` int(11) NOT NULL,
  `parentId` tinyint(11) DEFAULT NULL,
  `icon` char(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PARENT` (`parentId`),
  CONSTRAINT `FK_PARENT` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '用户管理', null, '1', null, 'glyphicon glyphicon-user');
INSERT INTO `menu` VALUES ('2', '用户管理', '/users', '1', '1', 'glyphicon glyphicon-user');
INSERT INTO `menu` VALUES ('3', '角色管理', '/roles', '2', '1', 'glyphicon glyphicon-user');
INSERT INTO `menu` VALUES ('4', '测试菜单', null, '2', null, null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` tinyint(11) NOT NULL AUTO_INCREMENT,
  `name` char(32) CHARACTER SET utf8 NOT NULL,
  `description` varchar(256) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'superadmin', 'superadmin');

-- ----------------------------
-- Table structure for `rolemenurelation`
-- ----------------------------
DROP TABLE IF EXISTS `rolemenurelation`;
CREATE TABLE `rolemenurelation` (
  `roleId` tinyint(11) NOT NULL,
  `menuId` tinyint(11) NOT NULL,
  PRIMARY KEY (`menuId`),
  KEY `INDX_menuId` (`menuId`),
  KEY `INDX_roleId` (`roleId`),
  CONSTRAINT `FK_menuId` FOREIGN KEY (`menuId`) REFERENCES `menu` (`id`),
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of rolemenurelation
-- ----------------------------
INSERT INTO `rolemenurelation` VALUES ('1', '1');
INSERT INTO `rolemenurelation` VALUES ('1', '2');
INSERT INTO `rolemenurelation` VALUES ('1', '3');
INSERT INTO `rolemenurelation` VALUES ('1', '4');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` tinyint(11) NOT NULL,
  `username` char(16) CHARACTER SET utf8 NOT NULL,
  `password` char(128) CHARACTER SET utf8 NOT NULL,
  `firstname` char(32) CHARACTER SET utf8 NOT NULL,
  `lastname` char(32) CHARACTER SET utf8 NOT NULL,
  `gender` tinyint(2) NOT NULL,
  `roleId` tinyint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `INDX_USER_ROLE` (`roleId`) USING BTREE,
  CONSTRAINT `FK_USER_ROLE` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'xiaojiyun', 'e10adc3949ba59abbe56e057f20f883e', 'xiao', 'jiyun', '1', '1');
