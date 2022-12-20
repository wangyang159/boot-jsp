/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 20/12/2022 20:48:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blockmeg
-- ----------------------------
DROP TABLE IF EXISTS `blockmeg`;
CREATE TABLE `blockmeg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filemd5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原文件的md5',
  `fileblocksize` int(11) NULL DEFAULT NULL COMMENT '原文件被切分的总块数',
  `blockindex` int(11) NULL DEFAULT NULL COMMENT '该块数对比原文件分块的顺序',
  `filename` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原文件姓名',
  `blockpathname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '块数据保存路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 79 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '切片信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for filemeg
-- ----------------------------
DROP TABLE IF EXISTS `filemeg`;
CREATE TABLE `filemeg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `filemd5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件的md5',
  `fileblocksize` int(11) NULL DEFAULT NULL COMMENT '文件被切分的总块数',
  `filename` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `pathname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `filesize` bigint(20) NULL DEFAULT NULL COMMENT '文件总大小',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '切片信息表对应的原文件信息表' ROW_FORMAT = Dynamic;
