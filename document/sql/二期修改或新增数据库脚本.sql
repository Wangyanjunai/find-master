-- ----------------------------
-- Table structure for user
-- ----------------------------
ALTER TABLE `user`
ADD COLUMN `longitude` double(12, 6) NULL DEFAULT NULL COMMENT '经度' AFTER `other`,
ADD COLUMN `latitude` double(12, 6) NULL DEFAULT NULL  COMMENT '纬度' AFTER `longitude`,
ADD COLUMN `profession_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '职业编号' AFTER `reserve_column04`,
ADD COLUMN `tag1` bigint(20) NULL DEFAULT NULL COMMENT '标签1编号，对应标签表id' AFTER `profession_id`,
ADD COLUMN `tag2` bigint(20) NULL DEFAULT NULL COMMENT '标签2编号，对应标签表id' AFTER `tag1`,
ADD COLUMN `tag3` bigint(20) NULL DEFAULT NULL COMMENT '标签3编号，对应标签表id' AFTER `tag2`,
ADD COLUMN `tag4` bigint(20) NULL DEFAULT NULL COMMENT '标签4编号，对应标签表id' AFTER `tag3`,
ADD COLUMN `tag5` bigint(20) NULL DEFAULT NULL COMMENT '标签5编号，对应标签表id' AFTER `tag4`,
ADD COLUMN `reserve_column05` varchar(256) NULL DEFAULT NULL COMMENT '预留字段05' AFTER `tag5`,
ADD COLUMN `reserve_column06` varchar(256) NULL DEFAULT NULL COMMENT '预留字段06' AFTER `reserve_column05`,
ADD COLUMN `reserve_column07` varchar(256) NULL DEFAULT NULL COMMENT '预留字段07' AFTER `reserve_column06`,
ADD COLUMN `reserve_column08` varchar(256) NULL DEFAULT NULL COMMENT '预留字段08' AFTER `reserve_column07`;
-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic
-- ----------------------------
ALTER TABLE `dynamic`
ADD COLUMN `longitude` double(12, 6) NULL DEFAULT NULL COMMENT '经度' AFTER `other`,
ADD COLUMN `latitude` double(12, 6) NULL DEFAULT NULL  COMMENT '纬度' AFTER `longitude`;
-- ----------------------------
-- Records of dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic_info
-- ----------------------------
ALTER TABLE `dynamic_info` 
ADD COLUMN `comments` int(11) NOT NULL DEFAULT 0 COMMENT '评论数' AFTER `likes`,
ADD COLUMN `is_topic` enum('0', '1') NOT NULL DEFAULT '0' COMMENT '是否是话题，0->否；1->是，默认：0->否' AFTER `public_status`,
ADD COLUMN `topic_title` varchar(256) NULL DEFAULT NULL COMMENT '话题标题' AFTER `is_topic`;
-- ----------------------------
-- Records of dynamic_info
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dynamic_info_id` bigint(20) NOT NULL COMMENT '动态内容id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for industrys
-- ----------------------------
DROP TABLE IF EXISTS `industrys`;
CREATE TABLE `industrys`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '行业名称',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级编号',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of industrys
-- ----------------------------
INSERT INTO `industrys` VALUES (1, '农业', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (2, '食品、饮料', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (3, '服装', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (4, '纺织、皮革', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (5, '电工电气', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (6, '家用电器', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (7, '数码、电脑', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (8, '化工', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (9, '冶金矿产', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (10, '能源', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (11, '环保', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `industrys` VALUES (12, '交通运输', 0, '0', '2021-03-31 17:57:47', '2021-03-31 17:57:47', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for professions
-- ----------------------------
DROP TABLE IF EXISTS `professions`;
CREATE TABLE `professions`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '职业名称',
  `industry_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属行业编号',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for screen_setting
-- ----------------------------
DROP TABLE IF EXISTS `screen_setting`;
CREATE TABLE `screen_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `type` enum('0','1','2','3') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '类型，0->鹿可女生年龄筛选条件；1->鹿可男生年龄筛选条件；2->动态年龄范围筛选条件最小值；3->动态年龄范围筛选条件最大值，默认：0->鹿可女生年龄筛选条件',
  `value` int(4) NOT NULL DEFAULT 0 COMMENT '值',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '筛选条件配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of screen_setting
-- ----------------------------
INSERT INTO `screen_setting` VALUES (1, '0', 3, '0', '2021-05-28 10:57:44', '2021-05-28 10:57:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `screen_setting` VALUES (2, '1', 10, '0', '2021-05-28 10:57:44', '2021-05-28 10:57:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `screen_setting` VALUES (3, '2', 16, '0', '2021-05-28 10:57:44', '2021-05-28 10:57:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `screen_setting` VALUES (4, '3', 50, '0', '2021-05-28 10:57:44', '2021-05-28 10:57:44', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_name`(`name`) USING BTREE,
  UNIQUE INDEX `uq_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tag
-- ----------------------------
INSERT INTO `tag` VALUES (1, '颜值', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (2, '吃货', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (3, '篮球', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (4, '学习', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (5, '二次元', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (6, '音乐', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (7, '旅游', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (8, '足球', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (9, '游戏', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag` VALUES (10, '影视', '0', '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);

select * from `user`;
select * from `tag`;