/*
Navicat MySQL Data Transfer

Source Server         : WslUb
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : selfclocking

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2024-06-09 11:33:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `countdown`
-- ----------------------------
DROP TABLE IF EXISTS `countdown`;
CREATE TABLE `countdown` (
  `user_name` varchar(30) NOT NULL,
  `countdown_name` varchar(40) NOT NULL,
  `countdown_day` int(5) NOT NULL,
  `countdown_past` int(5) NOT NULL,
  `countdown_over` int(1) NOT NULL DEFAULT 0,
  `countdown_start` varchar(50) NOT NULL COMMENT '倒计时的起始日',
  `countdown_end` varchar(50) DEFAULT NULL COMMENT '倒计时的结束日',
  KEY `user_name` (`user_name`),
  CONSTRAINT `user_name` FOREIGN KEY (`user_name`) REFERENCES `user` (`name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of countdown
-- ----------------------------
INSERT INTO `countdown` VALUES ('szy', 'TEST', '19', '2', '0', '2024-06-07', '2024-06-26');

-- ----------------------------
-- Table structure for `relationship`
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship` (
  `r_id` int(18) NOT NULL AUTO_INCREMENT COMMENT '绑定id',
  `user_one` int(11) NOT NULL COMMENT '用户一的ID',
  `user_two` int(11) NOT NULL COMMENT '用户二的id',
  `relationship` varchar(30) NOT NULL COMMENT '用户一和用户二的关系',
  `day` date DEFAULT NULL COMMENT '绑定关系的时间',
  `time` time NOT NULL COMMENT '绑定了多久关系',
  PRIMARY KEY (`r_id`),
  KEY `user_two` (`user_two`),
  KEY `oid` (`user_one`),
  CONSTRAINT `oid` FOREIGN KEY (`user_one`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tid` FOREIGN KEY (`user_one`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of relationship
-- ----------------------------
INSERT INTO `relationship` VALUES ('6', '1051432623', '1086522222', 'Loving', null, '21:33:23');

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `user_id` int(18) DEFAULT NULL COMMENT '用户id',
  `schedule_id` int(50) NOT NULL AUTO_INCREMENT,
  `end_date` varchar(30) DEFAULT NULL,
  `date` varchar(30) DEFAULT NULL,
  `schedule_filed` varchar(30) DEFAULT '',
  `start_time` varchar(30) NOT NULL,
  `end_time` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`schedule_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES ('1086522222', '1', null, '2024-05-29', '打羽毛球', '', null);
INSERT INTO `schedule` VALUES ('1086522222', '8', null, '2024-05-28', '无敌', '', null);
INSERT INTO `schedule` VALUES ('1086522222', '9', null, '2024-05-29', '打排球', '', null);
INSERT INTO `schedule` VALUES ('1086522222', '16', null, '2024-05-12', '1231', '', null);
INSERT INTO `schedule` VALUES ('1086522222', '30', '2024-06-08', '2024-06-08', '111', '20:48:21', '20:48:21');
INSERT INTO `schedule` VALUES ('1086522222', '31', '2024-06-07', '2024-06-07', '222', '20:49:08', '20:49:08');
INSERT INTO `schedule` VALUES ('1086522222', '32', '2024-06-08', '2024-06-08', '333', '20:49:41', '20:49:41');
INSERT INTO `schedule` VALUES ('1086522222', '33', '2024-06-03', '2024-06-03', '2222', '20:51:32', '20:51:32');
INSERT INTO `schedule` VALUES ('1086522222', '34', '2024-06-10', '2024-06-10', '22', '20:55:06', '20:55:06');
INSERT INTO `schedule` VALUES ('1086522222', '35', '2024-06-01', '2024-06-01', '33', '21:06:33', '21:06:33');
INSERT INTO `schedule` VALUES ('1086522222', '36', '2024-06-01', '2024-06-01', 'test', '21:53:00', '21:53:00');
INSERT INTO `schedule` VALUES ('1086522222', '37', '2024-06-05', '2024-06-05', 'teste', '21:59:26', '21:59:26');
INSERT INTO `schedule` VALUES ('1086522222', '38', '2024-06-03', '2024-06-03', 'trest', '21:59:50', '21:59:50');
INSERT INTO `schedule` VALUES ('1086522222', '39', '2024-06-09', '2024-06-09', '出去玩', '13:22:10', '22:22:12');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(18) NOT NULL,
  `name` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `learn_date` int(11) DEFAULT NULL,
  `task` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('28706764', '', '', null, null);
INSERT INTO `user` VALUES ('406476519', 'bx4', '000000', null, null);
INSERT INTO `user` VALUES ('560588859', 'szy6666', '123456', null, null);
INSERT INTO `user` VALUES ('661839452', 'bx', '000000', null, null);
INSERT INTO `user` VALUES ('1051432623', 'tanc', '123456', null, null);
INSERT INTO `user` VALUES ('1086522222', 'szy', '123456', '1', null);
INSERT INTO `user` VALUES ('1223628934', 'baixie', '123456', null, null);
INSERT INTO `user` VALUES ('1280067652', 'szy666', 'szy666666.', null, null);
INSERT INTO `user` VALUES ('1302206754', 'tc7', '77777777', null, null);
INSERT INTO `user` VALUES ('1500745374', 'tc66', '000000', null, null);
INSERT INTO `user` VALUES ('1566267440', 'bx2', '000000', null, null);

-- ----------------------------
-- Table structure for `usertask`
-- ----------------------------
DROP TABLE IF EXISTS `usertask`;
CREATE TABLE `usertask` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `task_name` varchar(50) NOT NULL COMMENT '任务名',
  `task_field` varchar(255) NOT NULL DEFAULT '' COMMENT '任务内容',
  `level` int(11) DEFAULT NULL COMMENT '任务等级',
  `isover` int(1) DEFAULT NULL COMMENT '是否完成',
  PRIMARY KEY (`task_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of usertask
-- ----------------------------
INSERT INTO `usertask` VALUES ('1', '1051432623', 'learn', '学习', '4', null);
INSERT INTO `usertask` VALUES ('2', '1051432623', 'study', 'good good study study!', null, null);
INSERT INTO `usertask` VALUES ('4', '1086522222', 'Love', '哎哎哎', '4', '0');
INSERT INTO `usertask` VALUES ('5', '1086522222', '打羽毛球', '羽毛球冲冲冲', '3', '0');
INSERT INTO `usertask` VALUES ('6', '1086522222', 'Kubernets', '复习和增强K8s的学习', '1', '0');
INSERT INTO `usertask` VALUES ('7', '1086522222', '端午节出去玩', '还没想好', '3', '0');
