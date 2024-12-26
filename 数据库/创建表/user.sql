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

 Date: 26/12/2024 15:12:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `identity` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '判断是否是学生（0代表学生,1代表系统管理员,2代表招生管理员）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2023, '考生1', '1234', '0');
INSERT INTO `user` VALUES (2024, '系统管理员1', '1234', '1');
INSERT INTO `user` VALUES (2025, '招生管理员1', '1234', '2');

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `update_related_info`;
delimiter ;;
CREATE TRIGGER `update_related_info` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
    -- 判断被更新用户的角色（这里根据identity字段判断）
    CASE NEW.identity
        WHEN '0' THEN
            -- 更新admission_info表中对应考生的记录
            UPDATE admission_info SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新application_info表中对应考生的记录
            UPDATE application_info SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新exam_room_allocation表中对应考生的记录
            UPDATE exam_room_allocation SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新examinee表中对应考生的记录
            UPDATE examinee SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新score表中对应考生的记录
            UPDATE score SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
        WHEN '1' THEN
            -- 更新system_admin表中对应记录
            UPDATE system_admin SET admin_id = NEW.id WHERE admin_id = OLD.id;
        WHEN '2' THEN
            -- 更新enrollment_admin表中对应记录
            UPDATE enrollment_admin SET enrollment_admin_id = NEW.id WHERE enrollment_admin_id = OLD.id;
            -- 更新enrollment_info表中对应记录
            UPDATE enrollment_info SET enrollment_admin_id = NEW.id WHERE enrollment_admin_id = OLD.id;
    END CASE;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `user_update_trigger`;
delimiter ;;
CREATE TRIGGER `user_update_trigger` AFTER UPDATE ON `user` FOR EACH ROW BEGIN
    CASE NEW.identity
        WHEN '0' THEN
            -- 更新admission_info表中对应考生的记录
            UPDATE admission_info SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新application_info表中对应考生的记录
            UPDATE application_info SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新exam_room_allocation表中对应考生的记录
            UPDATE exam_room_allocation SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新examinee表中对应考生的记录
            UPDATE examinee SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
            -- 更新score表中对应考生的记录
            UPDATE score SET examinee_id = NEW.id WHERE examinee_id = OLD.id;
        WHEN '1' THEN
            -- 更新system_admin表中对应记录
            UPDATE system_admin SET admin_id = NEW.id WHERE admin_id = OLD.id;
        WHEN '2' THEN
            -- 更新enrollment_admin表中对应记录
            UPDATE enrollment_admin SET enrollment_admin_id = NEW.id WHERE enrollment_admin_id = OLD.id;
            -- 更新enrollment_info表中对应记录
            UPDATE enrollment_info SET enrollment_admin_id = NEW.id WHERE enrollment_admin_id = OLD.id;
    END CASE;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
