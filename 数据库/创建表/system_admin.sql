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

 Date: 26/12/2024 15:12:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for system_admin
-- ----------------------------
DROP TABLE IF EXISTS `system_admin`;
CREATE TABLE `system_admin`  (
  `admin_id` int NOT NULL,
  `admin_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE,
  CONSTRAINT `system_admin_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_admin
-- ----------------------------
INSERT INTO `system_admin` VALUES (2024, '系统管理员1', '计算机系');

-- ----------------------------
-- Triggers structure for table system_admin
-- ----------------------------
DROP TRIGGER IF EXISTS `system_admin_insert_trigger`;
delimiter ;;
CREATE TRIGGER `system_admin_insert_trigger` AFTER INSERT ON `system_admin` FOR EACH ROW BEGIN
    -- 假设user表中插入对应系统管理员记录（这里假设user表的identity为1代表系统管理员）
    INSERT INTO `user` (user_id, username, password, role)
    VALUES (NEW.admin_id, '未填写用户名', '未填写密码', '系统管理员');
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table system_admin
-- ----------------------------
DROP TRIGGER IF EXISTS `system_admin_update_trigger`;
delimiter ;;
CREATE TRIGGER `system_admin_update_trigger` AFTER UPDATE ON `system_admin` FOR EACH ROW BEGIN
    -- 将需要更新的数据插入临时表
    INSERT INTO temp_user_password_update (user_id, new_password)
    VALUES (OLD.admin_id, NEW.admin_name);
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
