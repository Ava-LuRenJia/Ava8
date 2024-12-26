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

 Date: 26/12/2024 15:13:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `score_id` int NOT NULL AUTO_INCREMENT COMMENT '成绩记录唯一标识，自增主键',
  `examinee_id` int NOT NULL COMMENT '对应考生标识，与 examinee 表 examinee_id 关联（外键）',
  `subject` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '考试科目',
  `score_value` int NOT NULL COMMENT '成绩分数',
  `exam_time` datetime NOT NULL COMMENT '考试时间',
  PRIMARY KEY (`score_id`) USING BTREE,
  INDEX `examinee_id`(`examinee_id` ASC) USING BTREE,
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, 2023, '操作系统', 99, '2024-12-15 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
