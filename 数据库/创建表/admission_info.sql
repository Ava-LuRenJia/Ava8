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

 Date: 26/12/2024 15:13:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admission_info
-- ----------------------------
DROP TABLE IF EXISTS `admission_info`;
CREATE TABLE `admission_info`  (
  `admission_id` int NOT NULL AUTO_INCREMENT COMMENT '录取信息唯一标识，自增主键',
  `examinee_id` int NOT NULL COMMENT '被录取考生标识，与 examinee 表 examinee_id 关联（外键）',
  `is_admitted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否被录取（0 表示否，1 表示是）',
  `admission_time` datetime NULL DEFAULT NULL COMMENT '录取时间',
  PRIMARY KEY (`admission_id`) USING BTREE,
  INDEX `examinee_id`(`examinee_id` ASC) USING BTREE,
  CONSTRAINT `admission_info_ibfk_1` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admission_info
-- ----------------------------
INSERT INTO `admission_info` VALUES (1, 2023, 1, '2024-12-20 00:00:00');

-- ----------------------------
-- Triggers structure for table admission_info
-- ----------------------------
DROP TRIGGER IF EXISTS `admission_info_insert_trigger`;
delimiter ;;
CREATE TRIGGER `admission_info_insert_trigger` AFTER INSERT ON `admission_info` FOR EACH ROW BEGIN
    -- 假设更新对应考生在examinee表中的记录（这里简单示例，实际可能根据业务规则操作）
    UPDATE examinee SET name = '已录取考生更新姓名' WHERE examinee_id = NEW.examinee_id AND NEW.is_admitted = 1;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admission_info
-- ----------------------------
DROP TRIGGER IF EXISTS `admission_info_update_trigger`;
delimiter ;;
CREATE TRIGGER `admission_info_update_trigger` AFTER UPDATE ON `admission_info` FOR EACH ROW BEGIN
    -- 根据录取情况更新examinee表中的记录（这里简单示例）
    IF NEW.is_admitted = 1 AND OLD.is_admitted = 0 THEN
        UPDATE examinee SET name = '新录取考生更新姓名' WHERE examinee_id = NEW.examinee_id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admission_info
-- ----------------------------
DROP TRIGGER IF EXISTS `admission_info_delete_trigger`;
delimiter ;;
CREATE TRIGGER `admission_info_delete_trigger` AFTER DELETE ON `admission_info` FOR EACH ROW BEGIN
    -- 假设删除对应考生在examinee表中的某些记录（这里简单示例，实际可能根据业务规则操作）
    UPDATE examinee SET name = '未录取考生更新姓名' WHERE examinee_id = OLD.examinee_id AND OLD.is_admitted = 1;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
