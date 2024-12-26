/*
 Navicat Premium Dump SQL

 Source Server         : mysql-1
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : bk_data

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 26/12/2024 15:13:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enrollment_admin
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_admin`;
CREATE TABLE `enrollment_admin`  (
  `enrollment_admin_id` int NOT NULL,
  `admin_real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `responsible_area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '负责招生区域',
  PRIMARY KEY (`enrollment_admin_id`) USING BTREE,
  CONSTRAINT `enrollment_admin_ibfk_1` FOREIGN KEY (`enrollment_admin_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enrollment_admin
-- ----------------------------
INSERT INTO `enrollment_admin` VALUES (2025, '招生管理员1', '天津市北辰区');

SET FOREIGN_KEY_CHECKS = 1;
