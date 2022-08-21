/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mhl

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 21/08/2022 22:46:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `billId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `menuId` int(11) NOT NULL DEFAULT 0,
  `nums` int(11) NOT NULL DEFAULT 0,
  `money` double NOT NULL DEFAULT 0,
  `diningTableId` int(11) NOT NULL DEFAULT 0,
  `billDate` datetime(0) NOT NULL,
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for diningtable
-- ----------------------------
DROP TABLE IF EXISTS `diningtable`;
CREATE TABLE `diningtable`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderTel` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of diningtable
-- ----------------------------
INSERT INTO `diningtable` VALUES (1, '空', '', '');
INSERT INTO `diningtable` VALUES (2, '空', '', '');
INSERT INTO `diningtable` VALUES (3, '空', '', '');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empId` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `pwd` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `job` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `empId`(`empId`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, '6668612', 'e10adc3949ba59abbe56e057f20f883e', '张三丰', '经理');
INSERT INTO `employee` VALUES (2, '6668622', 'e10adc3949ba59abbe56e057f20f883e', '小龙女', '服务员');
INSERT INTO `employee` VALUES (3, '6668633', 'e10adc3949ba59abbe56e057f20f883e', '张无忌', '收银员');
INSERT INTO `employee` VALUES (4, '666666', 'e10adc3949ba59abbe56e057f20f883e', '老韩', '经理');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `TYPE` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `price` double NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '八宝饭', '主食', 10);
INSERT INTO `menu` VALUES (2, '叉烧包', '主食', 20);
INSERT INTO `menu` VALUES (3, '保鸡丁', '热菜', 30);
INSERT INTO `menu` VALUES (4, '山药拨鱼', '凉菜', 14);
INSERT INTO `menu` VALUES (5, '银丝卷', '甜食', 9);
INSERT INTO `menu` VALUES (6, '水煮鱼', '热菜', 26);
INSERT INTO `menu` VALUES (7, '甲鱼汤', '汤类', 100);
INSERT INTO `menu` VALUES (8, '鸡蛋汤', '汤类', 16);

SET FOREIGN_KEY_CHECKS = 1;
