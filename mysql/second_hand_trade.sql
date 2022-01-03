/*
 Navicat Premium Data Transfer

 Source Server         : aaa
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : second_hand_trade

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/12/2021 09:50:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sht_admin
-- ----------------------------
DROP TABLE IF EXISTS `sht_admin`;
CREATE TABLE `sht_admin`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '管理员编号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_admin
-- ----------------------------
INSERT INTO `sht_admin` VALUES (2, 'lisi', '123');
INSERT INTO `sht_admin` VALUES (4, '张三', '333333');

-- ----------------------------
-- Table structure for sht_category
-- ----------------------------
DROP TABLE IF EXISTS `sht_category`;
CREATE TABLE `sht_category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `category` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_category
-- ----------------------------
INSERT INTO `sht_category` VALUES (2, '运动用品');

-- ----------------------------
-- Table structure for sht_collected
-- ----------------------------
DROP TABLE IF EXISTS `sht_collected`;
CREATE TABLE `sht_collected`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `post_id` bigint NOT NULL COMMENT '帖子编号',
  `collected_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '收藏日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_col_user`(`user_id`) USING BTREE,
  INDEX `fk_col_post`(`post_id`) USING BTREE,
  CONSTRAINT `fk_col_post` FOREIGN KEY (`post_id`) REFERENCES `sht_post` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_col_user` FOREIGN KEY (`user_id`) REFERENCES `sht_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_collected
-- ----------------------------
INSERT INTO `sht_collected` VALUES (1, 4, 2, '2021-10-25 16:45:46');

-- ----------------------------
-- Table structure for sht_message
-- ----------------------------
DROP TABLE IF EXISTS `sht_message`;
CREATE TABLE `sht_message`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_src_id` bigint NOT NULL COMMENT '发送用户的编号',
  `user_dst_id` bigint NOT NULL COMMENT '接受用户的编号',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '本条记录产生的时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '聊天内容',
  `picture_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `read` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '是否已读（已读1，未读0）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_msgsrc_user`(`user_src_id`) USING BTREE,
  INDEX `fk_msgdst_user`(`user_dst_id`) USING BTREE,
  CONSTRAINT `fk_msgdst_user` FOREIGN KEY (`user_dst_id`) REFERENCES `sht_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_msgsrc_user` FOREIGN KEY (`user_src_id`) REFERENCES `sht_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_message
-- ----------------------------
INSERT INTO `sht_message` VALUES (2, 2, 4, '2021-10-25 17:04:35', '你好', NULL, 0);
INSERT INTO `sht_message` VALUES (3, 4, 2, '2021-10-25 17:04:55', '你也好', NULL, 0);
INSERT INTO `sht_message` VALUES (4, 2, 5, '2021-11-04 16:49:21', '第一次见', NULL, 0);
INSERT INTO `sht_message` VALUES (5, 4, 2, '2021-11-04 16:50:44', '啊这', NULL, 0);

-- ----------------------------
-- Table structure for sht_post
-- ----------------------------
DROP TABLE IF EXISTS `sht_post`;
CREATE TABLE `sht_post`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子编号',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发帖日期',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '帖子内容',
  `scan_count` bigint(20) UNSIGNED ZEROFILL NOT NULL DEFAULT 00000000000000000000 COMMENT '帖子浏览量',
  `sold` int(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否已经卖出',
  `cover_picture_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面图',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `category_id` int NOT NULL COMMENT '商品分类',
  `quality_id` int NOT NULL COMMENT '成色，质量',
  `post_category` int NOT NULL COMMENT '帖子类型（挂卖0，求购1）',
  `keywords` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关键词|分割',
  `amount` int NOT NULL COMMENT '商品数量',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_post_user`(`user_id`) USING BTREE,
  INDEX `fk_post_category`(`category_id`) USING BTREE,
  INDEX `fk_post_quality`(`quality_id`) USING BTREE,
  CONSTRAINT `fk_post_category` FOREIGN KEY (`category_id`) REFERENCES `sht_category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_post_quality` FOREIGN KEY (`quality_id`) REFERENCES `sht_quality` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_post_user` FOREIGN KEY (`user_id`) REFERENCES `sht_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_post
-- ----------------------------
INSERT INTO `sht_post` VALUES (2, 2, '2021-12-02 16:33:53', '你好', '广药', 00000000000000000000, 0, 'H:\\study\\upload\\e7e2e97d-76c2-4383-ada5-951e9acb3c771.PNG', 333.30, 2, 1, 0, '', 0, '名字');
INSERT INTO `sht_post` VALUES (4, 10, '2021-12-02 16:35:56', '卖一只亿元狗', '可爱的亿元狗不来一只吗', 00000000000000000000, 0, 'H:\\study\\upload\\84b1b106-5ab0-4776-8fcd-4cb06ff10249e9a7971219a6f6d9ad641dad5019a7ddcef40d47.gif', 1000.00, 2, 1, 0, '亿元狗', 1, '亿元狗');
INSERT INTO `sht_post` VALUES (5, 10, '2021-12-02 20:54:31', '可爱的pp', '可爱的pp不来一只吗', 00000000000000000000, 0, 'H:\\study\\upload\\ade780dd-10aa-42d6-bdb2-7b7e9e5eaed4pp.PNG', 10.00, 2, 1, 0, '123', 1, 'pp');
INSERT INTO `sht_post` VALUES (6, 10, '2021-12-02 20:55:05', '蜜汁问号', '？？？', 00000000000000000000, 0, 'H:\\study\\upload\\8a85fdcd-fbe2-4627-8d6d-9b43ac416f0e问号_1.jpg', 10.00, 2, 1, 0, '？', 1, '？');
INSERT INTO `sht_post` VALUES (7, 10, '2021-12-02 20:56:13', '可爱的大河', '可爱的大河谁不想要呢', 00000000000000000000, 0, 'H:\\study\\upload\\c03ac745-011d-4ef7-8b60-dade8a9183a9wallhaven-0qdmzr.png', 120.00, 2, 1, 0, '可爱的大河', 1, '可爱的大河');
INSERT INTO `sht_post` VALUES (8, 10, '2021-12-02 20:57:40', '卡哇伊的馅子', '卡哇伊的馅子限价大甩卖', 00000000000000000000, 0, 'H:\\study\\upload\\e6a1dbb3-b753-4682-9d55-c5ab4837a187wallhaven-gjm7ml.png', 130.00, 2, 1, 0, '卡哇伊的馅子', 1, '卡哇伊的馅子');
INSERT INTO `sht_post` VALUES (10, 7, '2021-12-03 21:02:11', '123', '321', 00000000000000000000, 0, 'H:\\study\\upload\\0c10fb7f-ac37-4468-ae6d-47c579fdc129e9a7971219a6f6d9ad641dad5019a7ddcef40d47.gif', 11.00, 2, 1, 1, '123', 1, '123');

-- ----------------------------
-- Table structure for sht_quality
-- ----------------------------
DROP TABLE IF EXISTS `sht_quality`;
CREATE TABLE `sht_quality`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '成色编号',
  `quality` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '成色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_quality
-- ----------------------------
INSERT INTO `sht_quality` VALUES (1, '久经沙场');
INSERT INTO `sht_quality` VALUES (2, '全新已拆封');

-- ----------------------------
-- Table structure for sht_user
-- ----------------------------
DROP TABLE IF EXISTS `sht_user`;
CREATE TABLE `sht_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` int NULL DEFAULT NULL COMMENT '用户电话号码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户个人描述',
  `avatar_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像资源路径',
  `gender` int NULL DEFAULT NULL COMMENT '用户性别',
  `like_count` bigint UNSIGNED NULL DEFAULT 0 COMMENT '用户被点赞数',
  `collected_count` bigint UNSIGNED NULL DEFAULT 0 COMMENT '用户收藏数',
  `follow_count` bigint UNSIGNED NULL DEFAULT 0 COMMENT '用户关注数',
  `followed_count` bigint UNSIGNED NULL DEFAULT 0 COMMENT '用户被关注数',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_user
-- ----------------------------
INSERT INTO `sht_user` VALUES (2, '李四', '222222', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (4, '张三', '111111', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (5, '王五', '333333', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (6, '111111', '123456', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (7, 'zhangsan', '123456', NULL, NULL, '？？？？', 'H:\\study\\upload\\2470f357-c9c5-411a-8053-18292652dbc6问号_1.jpg', NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (8, 'user1', '123', NULL, NULL, NULL, NULL, NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (9, 'admin', 'admin', NULL, NULL, NULL, 'H:\\study\\upload\\6237f71f-165d-483e-a877-eeab4296863fwallhaven-01k3d9.jpg', NULL, 0, 0, 0, 0);
INSERT INTO `sht_user` VALUES (10, 'admin1', 'admin', 123, '1321132', '你好', 'H:\\study\\upload\\24cc67b4-f539-45ff-a5ee-fb949dad4d031.PNG', NULL, 0, 0, 0, 0);

-- ----------------------------
-- Table structure for sht_user_address
-- ----------------------------
DROP TABLE IF EXISTS `sht_user_address`;
CREATE TABLE `sht_user_address`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址',
  `default_sign` int(1) UNSIGNED ZEROFILL NOT NULL COMMENT '默认地址标记符',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_addr_user`(`user_id`) USING BTREE,
  CONSTRAINT `fk_addr_user` FOREIGN KEY (`user_id`) REFERENCES `sht_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sht_user_address
-- ----------------------------
INSERT INTO `sht_user_address` VALUES (1, 2, '江门', 1);
INSERT INTO `sht_user_address` VALUES (2, 2, '云浮', 0);

SET FOREIGN_KEY_CHECKS = 1;
