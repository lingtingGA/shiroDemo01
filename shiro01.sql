SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456');
INSERT INTO `user` VALUES ('2', 'test', '123456');
-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', 'admin', 'super');
INSERT INTO `user_roles` VALUES ('2', 'admin', 'default');
INSERT INTO `user_roles` VALUES ('3', 'test', 'default');
-- ----------------------------
-- Table structure for roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of roles_permissions
-- ----------------------------
INSERT INTO `roles_permissions` VALUES ('1', 'super', 'user:delete');
INSERT INTO `roles_permissions` VALUES ('2', 'super', 'user:update');
INSERT INTO `roles_permissions` VALUES ('3', 'super', 'user:insert');
INSERT INTO `roles_permissions` VALUES ('4', 'default', 'user:select');