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

 Date: 26/12/2024 15:13:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for application_info
-- ----------------------------
DROP TABLE IF EXISTS `application_info`;
CREATE TABLE `application_info`  (
  `application_id` int NOT NULL AUTO_INCREMENT COMMENT '报考信息唯一标识，自增主键',
  `examinee_id` int NOT NULL COMMENT '报考考生的标识，与 examinee 表 examinee_id 关联（外键）',
  `application_time` datetime NOT NULL COMMENT '报考时间',
  `exam_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报考考试类型（比如自主招生的具体专业类别等）',
  `confirmed` int NULL DEFAULT NULL COMMENT '0代表未确认，1代表已确认',
  PRIMARY KEY (`application_id`) USING BTREE,
  INDEX `examinee_id`(`examinee_id` ASC) USING BTREE,
  CONSTRAINT `application_info_ibfk_1` FOREIGN KEY (`examinee_id`) REFERENCES `examinee` (`examinee_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of application_info
-- ----------------------------
INSERT INTO `application_info` VALUES (1, 2023, '2024-12-01 00:00:00', '操作系统', 0);

-- ----------------------------
-- Triggers structure for table application_info
-- ----------------------------
DROP TRIGGER IF EXISTS `application_info_insert_trigger`;
delimiter ;;
CREATE TRIGGER `application_info_insert_trigger` AFTER INSERT ON `application_info` FOR EACH ROW BEGIN
    -- 在exam_room_allocation表中为该考生插入初始考场分配记录（假设初始为未分配）
    INSERT INTO exam_room_allocation (examinee_id, exam_room_number, seat_number)
    VALUES (NEW.examinee_id, '未分配', '未分配');
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table application_info
-- ----------------------------
DROP TRIGGER IF EXISTS `application_info_update_trigger`;
delimiter ;;
CREATE TRIGGER `application_info_update_trigger` AFTER UPDATE ON `application_info` FOR EACH ROW BEGIN
    -- 更新exam_room_allocation表中对应考生的记录（这里简单关联更新，实际可能更复杂）
    UPDATE exam_room_allocation SET examinee_id = NEW.examinee_id WHERE examinee_id = OLD.examinee_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table application_info
-- ----------------------------
DROP TRIGGER IF EXISTS `application_info_delete_trigger`;
delimiter ;;
CREATE TRIGGER `application_info_delete_trigger` AFTER DELETE ON `application_info` FOR EACH ROW BEGIN
    -- 删除exam_room_allocation表中对应考生的记录
    DELETE FROM exam_room_allocation WHERE examinee_id = OLD.examinee_id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
