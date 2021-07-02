-- ----------------------------
-- Table structure for user
-- ----------------------------
ALTER TABLE `user`
    ADD COLUMN `longitude`        double(20, 6) NULL     DEFAULT NULL COMMENT '经度' AFTER `other`,
    ADD COLUMN `latitude`         double(20, 6) NULL     DEFAULT NULL COMMENT '纬度' AFTER `longitude`,
    ADD COLUMN `profession_id`    bigint(20)    NOT NULL DEFAULT 1 COMMENT '职业编号' AFTER `reserve_column04`,
    ADD COLUMN `tag1`             bigint(20)    NULL     DEFAULT NULL COMMENT '标签1编号，对应标签表id' AFTER `profession_id`,
    ADD COLUMN `tag2`             bigint(20)    NULL     DEFAULT NULL COMMENT '标签2编号，对应标签表id' AFTER `tag1`,
    ADD COLUMN `tag3`             bigint(20)    NULL     DEFAULT NULL COMMENT '标签3编号，对应标签表id' AFTER `tag2`,
    ADD COLUMN `tag4`             bigint(20)    NULL     DEFAULT NULL COMMENT '标签4编号，对应标签表id' AFTER `tag3`,
    ADD COLUMN `tag5`             bigint(20)    NULL     DEFAULT NULL COMMENT '标签5编号，对应标签表id' AFTER `tag4`,
    ADD COLUMN `reserve_column05` varchar(256)  NULL     DEFAULT NULL COMMENT '预留字段05' AFTER `tag5`,
    ADD COLUMN `reserve_column06` varchar(256)  NULL     DEFAULT NULL COMMENT '预留字段06' AFTER `reserve_column05`,
    ADD COLUMN `reserve_column07` varchar(256)  NULL     DEFAULT NULL COMMENT '预留字段07' AFTER `reserve_column06`,
    ADD COLUMN `reserve_column08` varchar(256)  NULL     DEFAULT NULL COMMENT '预留字段08' AFTER `reserve_column07`;
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
    ADD COLUMN `longitude` double(12, 6) NULL     DEFAULT NULL COMMENT '经度' AFTER `attache_number`,
    ADD COLUMN `latitude`  double(12, 6) NULL     DEFAULT NULL COMMENT '纬度' AFTER `longitude`,
    ADD COLUMN `country`   varchar(16)   NOT NULL DEFAULT '中国' COMMENT '国' AFTER `latitude`,
    ADD COLUMN `province`  varchar(32)   NULL     DEFAULT NULL COMMENT '省' AFTER `country`,
    ADD COLUMN `city`      varchar(32)   NULL     DEFAULT NULL COMMENT '市' AFTER `province`,
    ADD COLUMN `district`  varchar(32)   NULL     DEFAULT NULL COMMENT '区/县' AFTER `city`,
    ADD COLUMN `other`     varchar(64)   NULL     DEFAULT NULL COMMENT '其它地址' AFTER `district`;
-- ----------------------------
-- Records of dynamic_info
-- ----------------------------

-- ----------------------------
-- Table structure for dynamic_info
-- ----------------------------
ALTER TABLE `dynamic_info`
    ADD COLUMN `comments`     int(11)         NOT NULL DEFAULT 0 COMMENT '评论数' AFTER `likes`,
    ADD COLUMN `is_topic`     enum ('0', '1') NOT NULL DEFAULT '0' COMMENT '是否话题，0->否；1->是，默认：0->否' AFTER `public_status`,
    ADD COLUMN `is_anonymous` enum ('0', '1') NOT NULL DEFAULT '0' COMMENT '是否匿名，0->否；1->是，默认：0->否' AFTER `is_topic`,
    ADD COLUMN `topic_title`  varchar(256)    NULL     DEFAULT NULL COMMENT '话题标题' AFTER `is_topic`;
-- ----------------------------
-- Records of dynamic_info
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`             bigint(20)                                              NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
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
-- Records of professions
-- ----------------------------
INSERT INTO `professions` VALUES (1, '农民', 1, '0', '2021-06-02 15:46:55', '2021-06-02 15:46:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (2, '牧民', 1, '0', '2021-06-02 15:46:55', '2021-06-02 15:46:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (3, '渔民', 1, '0', '2021-06-02 15:46:55', '2021-06-02 15:46:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (4, '林业员', 1, '0', '2021-06-02 15:46:55', '2021-06-02 15:46:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (5, '个体农业经营者', 1, '0', '2021-06-02 15:46:55', '2021-06-02 15:46:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (6, '农民2', 2, '0', '2021-06-02 15:48:00', '2021-06-02 15:48:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (7, '牧民2', 2, '0', '2021-06-02 15:48:00', '2021-06-02 15:48:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (8, '渔民2', 2, '0', '2021-06-02 15:48:00', '2021-06-02 15:48:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (9, '林业员2', 2, '0', '2021-06-02 15:48:00', '2021-06-02 15:48:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (10, '个体农业经营者2', 2, '0', '2021-06-02 15:48:00', '2021-06-02 15:48:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (11, '农民3', 3, '0', '2021-06-02 15:48:30', '2021-06-02 15:48:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (12, '牧民3', 3, '0', '2021-06-02 15:48:30', '2021-06-02 15:48:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (13, '渔民3', 3, '0', '2021-06-02 15:48:30', '2021-06-02 15:48:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (14, '林业员3', 3, '0', '2021-06-02 15:48:30', '2021-06-02 15:48:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (15, '个体农业经营者3', 3, '0', '2021-06-02 15:48:30', '2021-06-02 15:48:30', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (16, '农民4', 4, '0', '2021-06-02 15:49:01', '2021-06-02 15:49:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (17, '牧民4', 4, '0', '2021-06-02 15:49:01', '2021-06-02 15:49:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (18, '渔民4', 4, '0', '2021-06-02 15:49:01', '2021-06-02 15:49:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (19, '林业员4', 4, '0', '2021-06-02 15:49:01', '2021-06-02 15:49:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (20, '个体农业经营者4', 4, '0', '2021-06-02 15:49:01', '2021-06-02 15:49:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (21, '农民5', 5, '0', '2021-06-02 15:49:27', '2021-06-02 15:49:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (22, '牧民5', 5, '0', '2021-06-02 15:49:27', '2021-06-02 15:49:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (23, '渔民5', 5, '0', '2021-06-02 15:49:27', '2021-06-02 15:49:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (24, '林业员5', 5, '0', '2021-06-02 15:49:27', '2021-06-02 15:49:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (25, '个体农业经营者5', 5, '0', '2021-06-02 15:49:27', '2021-06-02 15:49:27', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (26, '农民6', 6, '0', '2021-06-02 15:49:55', '2021-06-02 15:49:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (27, '牧民6', 6, '0', '2021-06-02 15:49:55', '2021-06-02 15:49:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (28, '渔民6', 6, '0', '2021-06-02 15:49:55', '2021-06-02 15:49:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (29, '林业员6', 6, '0', '2021-06-02 15:49:55', '2021-06-02 15:49:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (30, '个体农业经营者6', 6, '0', '2021-06-02 15:49:55', '2021-06-02 15:49:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (31, '农民7', 7, '0', '2021-06-02 15:50:20', '2021-06-02 15:50:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (32, '牧民7', 7, '0', '2021-06-02 15:50:20', '2021-06-02 15:50:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (33, '渔民7', 7, '0', '2021-06-02 15:50:20', '2021-06-02 15:50:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (34, '林业员7', 7, '0', '2021-06-02 15:50:20', '2021-06-02 15:50:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (35, '个体农业经营者7', 7, '0', '2021-06-02 15:50:20', '2021-06-02 15:50:20', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (36, '农民8', 8, '0', '2021-06-02 15:50:50', '2021-06-02 15:50:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (37, '牧民8', 8, '0', '2021-06-02 15:50:50', '2021-06-02 15:50:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (38, '渔民8', 8, '0', '2021-06-02 15:50:50', '2021-06-02 15:50:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (39, '林业员8', 8, '0', '2021-06-02 15:50:50', '2021-06-02 15:50:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (40, '个体农业经营者8', 8, '0', '2021-06-02 15:50:50', '2021-06-02 15:50:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (41, '农民9', 9, '0', '2021-06-02 15:51:16', '2021-06-02 15:51:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (42, '牧民9', 9, '0', '2021-06-02 15:51:16', '2021-06-02 15:51:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (43, '渔民9', 9, '0', '2021-06-02 15:51:16', '2021-06-02 15:51:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (44, '林业员9', 9, '0', '2021-06-02 15:51:16', '2021-06-02 15:51:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (45, '个体农业经营者9', 9, '0', '2021-06-02 15:51:16', '2021-06-02 15:51:16', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (46, '农民10', 10, '0', '2021-06-02 15:51:52', '2021-06-02 15:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (47, '牧民10', 10, '0', '2021-06-02 15:51:52', '2021-06-02 15:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (48, '渔民10', 10, '0', '2021-06-02 15:51:52', '2021-06-02 15:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (49, '林业员10', 10, '0', '2021-06-02 15:51:52', '2021-06-02 15:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (50, '个体农业经营者10', 10, '0', '2021-06-02 15:51:52', '2021-06-02 15:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (51, '农民11', 11, '0', '2021-06-02 15:52:18', '2021-06-02 15:52:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (52, '牧民11', 11, '0', '2021-06-02 15:52:18', '2021-06-02 15:52:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (53, '渔民11', 11, '0', '2021-06-02 15:52:18', '2021-06-02 15:52:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (54, '林业员11', 11, '0', '2021-06-02 15:52:18', '2021-06-02 15:52:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (55, '个体农业经营者11', 11, '0', '2021-06-02 15:52:18', '2021-06-02 15:52:18', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (56, '农民12', 12, '0', '2021-06-02 15:52:44', '2021-06-02 15:52:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (57, '牧民12', 12, '0', '2021-06-02 15:52:44', '2021-06-02 15:52:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (58, '渔民12', 12, '0', '2021-06-02 15:52:44', '2021-06-02 15:52:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (59, '林业员12', 12, '0', '2021-06-02 15:52:44', '2021-06-02 15:52:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `professions` VALUES (60, '个体农业经营者12', 12, '0', '2021-06-02 15:52:44', '2021-06-02 15:52:44', NULL, NULL, NULL, NULL, NULL);

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
  `hot_value` int(11) NOT NULL DEFAULT 0 COMMENT '热门值，默认：0',
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
INSERT INTO `tag` VALUES (1, '颜值', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (2, '吃货', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (3, '篮球', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (4, '学习', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (5, '二次元', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (6, '音乐', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (7, '旅游', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (8, '足球', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (9, '游戏', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tag`
VALUES (10, '影视', '0', 100, '2021-05-28 10:54:28', '2021-05-28 10:54:28', NULL, NULL, NULL, NULL, NULL);