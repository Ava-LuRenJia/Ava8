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

 Date: 26/12/2024 15:13:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for enrollment_info
-- ----------------------------
DROP TABLE IF EXISTS `enrollment_info`;
CREATE TABLE `enrollment_info`  (
  `enrollment_info_id` int NOT NULL AUTO_INCREMENT COMMENT '招考信息唯一标识，自增主键',
  `enrollment_admin_id` int NOT NULL COMMENT '发布招考信息的招生管理员标识，与 enrollment_admin 表 enrollment_admin_id 关联（外键）',
  `exam_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试名称',
  `start_time` datetime NOT NULL COMMENT '报名开始时间',
  `end_time` datetime NOT NULL COMMENT '报名结束时间',
  `exam_date` datetime NOT NULL COMMENT '考试日期',
  `admission_line` decimal(5, 2) NULL DEFAULT NULL COMMENT '录取分数线（初始设置值，后续可调整）',
  PRIMARY KEY (`enrollment_info_id`) USING BTREE,
  INDEX `enrollment_admin_id`(`enrollment_admin_id` ASC) USING BTREE,
  CONSTRAINT `enrollment_info_ibfk_1` FOREIGN KEY (`enrollment_admin_id`) REFERENCES `enrollment_admin` (`enrollment_admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of enrollment_info
-- ----------------------------
INSERT INTO `enrollment_info` VALUES (1, 2025, '操作系统', '2024-12-08 15:05:15', '2024-12-09 15:05:23', '2024-12-11 15:05:27', 85.00);

SET FOREIGN_KEY_CHECKS = 1;
