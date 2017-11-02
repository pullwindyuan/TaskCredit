/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : cvte

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-02 18:32:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `tid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `desc` varchar(255) NOT NULL COMMENT '任务描述',
  `type` int(11) NOT NULL COMMENT '周期类型：0新手任务，1一天，2一周，3一个月',
  `step` int(11) NOT NULL COMMENT '完成次数',
  `score` varchar(255) NOT NULL COMMENT '周期内可完成次数',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '新手徽章', '0', '1', '10');
INSERT INTO `task` VALUES ('2', '每日签到', '1', '1', '10');
INSERT INTO `task` VALUES ('3', '每周任务', '2', '3', '10,20,30');
INSERT INTO `task` VALUES ('4', '每月任务', '3', '4', '10,20,30,40');
INSERT INTO `task` VALUES ('5', '新手看看', '0', '3', '10,10,10');

-- ----------------------------
-- Table structure for `task_history`
-- ----------------------------
DROP TABLE IF EXISTS `task_history`;
CREATE TABLE `task_history` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(255) NOT NULL,
  `tid` int(11) NOT NULL COMMENT '任务id',
  `step` int(11) NOT NULL COMMENT '当前完成第几步',
  `score` int(11) NOT NULL COMMENT '获得积分',
  `createTime` datetime NOT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '记录描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_history
-- ----------------------------
INSERT INTO `task_history` VALUES ('1', 'testUid', '1', '0', '0', '2017-10-31 06:31:35', '领取任务');
INSERT INTO `task_history` VALUES ('2', 'testUid', '1', '0', '0', '2017-10-31 14:32:52', '领取任务');
INSERT INTO `task_history` VALUES ('3', '424e98ed688d9a8231ef9c7e10df572b', '4', '2', '30', '2017-11-02 14:43:40', '做任务');
INSERT INTO `task_history` VALUES ('4', '83abedd406bef1f2c684d6d91d080349', '4', '3', '60', '2017-11-02 15:14:14', '做任务');
INSERT INTO `task_history` VALUES ('5', '83abedd406bef1f2c684d6d91d080349', '3', '2', '30', '2017-11-02 16:53:55', '做任务');
INSERT INTO `task_history` VALUES ('6', '66c7e44220e83cb12974d5f271c709f9', '2', '0', '0', '2017-11-02 17:00:09', '领取任务');
INSERT INTO `task_history` VALUES ('7', '66c7e44220e83cb12974d5f271c709f9', '2', '1', '10', '2017-11-02 17:00:12', '做任务');

-- ----------------------------
-- Table structure for `task_record`
-- ----------------------------
DROP TABLE IF EXISTS `task_record`;
CREATE TABLE `task_record` (
  `uid` varchar(255) NOT NULL,
  `tid` int(255) NOT NULL,
  `history` int(11) NOT NULL COMMENT '历史完成次数',
  `step` int(11) NOT NULL COMMENT '当前完成第几步',
  `score` int(11) NOT NULL COMMENT '任务当前获得积分',
  `state` int(11) NOT NULL COMMENT '未领取 -1，进行中 0，已完成 1',
  `createTime` datetime NOT NULL COMMENT '记录创建时间',
  `updateTime` datetime NOT NULL COMMENT '上次更新时间',
  PRIMARY KEY (`uid`,`tid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_record
-- ----------------------------
INSERT INTO `task_record` VALUES ('424e98ed688d9a8231ef9c7e10df572b', '1', '0', '0', '0', '0', '2017-11-02 13:56:35', '2017-11-02 13:56:35');
INSERT INTO `task_record` VALUES ('424e98ed688d9a8231ef9c7e10df572b', '2', '0', '1', '10', '1', '2017-11-02 13:56:58', '2017-11-02 13:56:58');
INSERT INTO `task_record` VALUES ('424e98ed688d9a8231ef9c7e10df572b', '4', '0', '2', '30', '0', '2017-11-02 13:55:01', '2017-11-02 13:55:01');
INSERT INTO `task_record` VALUES ('66c7e44220e83cb12974d5f271c709f9', '2', '0', '1', '10', '1', '2017-11-02 17:00:09', '2017-11-02 17:00:09');

-- ----------------------------
-- Table structure for `user_score`
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score` (
  `uid` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_score
-- ----------------------------
INSERT INTO `user_score` VALUES ('309a45c6511d0acca47d5a9abd5191a4', 'testPhone', '0', '2017-11-02 17:04:05', '2017-11-02 17:04:05');
INSERT INTO `user_score` VALUES ('66c7e44220e83cb12974d5f271c709f9', '13531145222', '10', '2017-11-02 16:59:58', '2017-11-02 16:59:58');
