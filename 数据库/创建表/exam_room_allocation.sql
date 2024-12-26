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

 Date: 26/12/2024 15:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for exam_room_allocation
-- ----------------------------
DROP TABLE IF EXISTS `exam_room_allocation`;
CREATE TABLE `exam_room_allocation`  (
  `allocation_id` int NOT NULL AUTO_INCREMENT COMMENT '考场分配记录唯一标识，自增主键',
  `examinee_id` int NOT NULL COMMENT '考生标识，与 examinee 表 examinee_id 关联（外键）',
  `exam_room_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考场编号',
  `seat_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '座位号',
  PRIMARY KEY (`allocation_id`) USING BTREE,
  INDEX `examinee_id`(`examinee_id` ASC) USING BTREE,
  CONSTRAINT `exam_room_allocation_ibfk_1` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of exam_room_allocation
-- ----------------------------
INSERT INTO `exam_room_allocation` VALUES (1, 2023, '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
