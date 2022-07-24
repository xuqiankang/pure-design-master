/*
 Navicat Premium Data Transfer

 Source Server         : localhost3306
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost:3306
 Source Schema         : qing

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : 65001

 Date: 24/07/2022 21:03:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `company` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司名称',
  `apply` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申請單狀態',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '程序员青戈', 'admin@qq.com', '13988997788', '安徽合肥1', '2022-01-22 21:10:27', '0', NULL, NULL);
INSERT INTO `sys_user` VALUES (16, 'admin1', '123', '甄姬好大', '2', '2', '2', '2022-02-26 22:10:14', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (17, 'admin1', '123', '我是三三哦豁', '3', '3', '3', '2022-02-26 22:10:18', '1', NULL, NULL);
INSERT INTO `sys_user` VALUES (18, 'nzz', '123', '哪吒', '2', '2', '2', '2022-03-29 16:59:44', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (19, 'yss', '123', '亚瑟', '3', '3', '3', '2022-04-29 16:59:44', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (20, 'lxx', '123', '李信', '2', '2', '2', '2022-05-29 17:12:04', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (25, 'sir', '123', '安琪拉', NULL, NULL, NULL, '2022-06-08 17:00:47', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (26, 'err', NULL, '妲己', '11', '1', '1', '2022-07-08 17:20:01', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (28, 'ddd', '123', 'ddd', '', '', '', '2022-11-09 10:41:07', '2', NULL, NULL);
INSERT INTO `sys_user` VALUES (29, 'ffff', '123', NULL, NULL, NULL, NULL, '2022-12-10 11:53:31', '2', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
