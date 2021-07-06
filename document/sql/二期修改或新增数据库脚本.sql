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


-- ----------------------------
-- Table structure for sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `sensitive_words`;
CREATE TABLE `sensitive_words`
(
    `id`               bigint(20)                                                NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
    `type_name`        varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci     NULL     DEFAULT NULL COMMENT '类型名称',
    `content`          text CHARACTER SET utf8 COLLATE utf8_general_ci           NULL COMMENT '内容',
    `delete_status`    enum ('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是，默认：0->否',
    `created_time`     timestamp(0)                                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_time`     timestamp(0)                                              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `deleted_time`     timestamp(0)                                              NULL     DEFAULT NULL COMMENT '删除时间',
    `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '预留字段01',
    `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '预留字段02',
    `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '预留字段03',
    `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci   NULL     DEFAULT NULL COMMENT '预留字段04',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4038
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci COMMENT = '敏感词汇表'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sensitive_words
-- ----------------------------
INSERT INTO `sensitive_words`
VALUES (1, '色情', '爱液', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2, '色情', '按摩棒', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3, '色情', '拔出来', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4, '色情', '爆草', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (5, '色情', '包二奶', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (6, '色情', '暴干', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (7, '色情', '暴奸', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (8, '色情', '暴乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (9, '色情', '爆乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (10, '色情', '暴淫', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (11, '色情', '屄', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (12, '色情', '被操', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (13, '色情', '被插', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (14, '色情', '被干', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (15, '色情', '逼奸', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (16, '色情', '仓井空', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (17, '色情', '插暴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (18, '色情', '操逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (19, '色情', '操黑', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (20, '色情', '操烂', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (21, '色情', '肏你', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (22, '色情', '肏死', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (23, '色情', '操死', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (24, '色情', '操我', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (25, '色情', '厕奴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (26, '色情', '插比', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (27, '色情', '插b', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (28, '色情', '插逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (29, '色情', '插进', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (30, '色情', '插你', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (31, '色情', '插我', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (32, '色情', '插阴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (33, '色情', '潮吹', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (34, '色情', '潮喷', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (35, '色情', '成人dv', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (36, '色情', '成人电影', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (37, '色情', '成人论坛', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (38, '色情', '成人小说', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (39, '色情', '成人电', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (40, '色情', '成人电影', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (41, '色情', '成人卡通', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (42, '色情', '成人聊', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (43, '色情', '成人片', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (44, '色情', '成人视', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (45, '色情', '成人图', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (46, '色情', '成人文', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (47, '色情', '成人小', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (48, '色情', '成人电影', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (49, '色情', '成人论坛', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (50, '色情', '成人色情', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (51, '色情', '成人网站', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (52, '色情', '成人文学', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (53, '色情', '成人小说', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (54, '色情', '艳情小说', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (55, '色情', '成人游戏', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (56, '色情', '吃精', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (57, '色情', '赤裸', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (58, '色情', '抽插', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (59, '色情', '扌由插', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (60, '色情', '抽一插', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (61, '色情', '春药', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (62, '色情', '大波', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (63, '色情', '大力抽送', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (64, '色情', '大乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (65, '色情', '荡妇', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (66, '色情', '荡女', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (67, '色情', '盗撮', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (68, '色情', '多人轮', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (69, '色情', '发浪', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (70, '色情', '放尿', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (71, '色情', '肥逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (72, '色情', '粉穴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (73, '色情', '封面女郎', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (74, '色情', '风月大陆', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (75, '色情', '干死你', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (76, '色情', '干穴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (77, '色情', '肛交', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (78, '色情', '肛门', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (79, '色情', '龟头', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (80, '色情', '裹本', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (81, '色情', '国产av', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (82, '色情', '好嫩', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (83, '色情', '豪乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (84, '色情', '黑逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (85, '色情', '后庭', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (86, '色情', '后穴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (87, '色情', '虎骑', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (88, '色情', '花花公子', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (89, '色情', '换妻俱乐部', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (90, '色情', '黄片', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (91, '色情', '几吧', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (92, '色情', '鸡吧', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (93, '色情', '鸡巴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (94, '色情', '鸡奸', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (95, '色情', '寂寞男', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (96, '色情', '寂寞女', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (97, '色情', '妓女', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (98, '色情', '激情', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (99, '色情', '集体淫', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (100, '色情', '奸情', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (101, '色情', '叫床', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (102, '色情', '脚交', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (103, '色情', '金鳞岂是池中物', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (104, '色情', '金麟岂是池中物', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (105, '色情', '精液', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (106, '色情', '就去日', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (107, '色情', '巨屌', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (108, '色情', '菊花洞', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (109, '色情', '菊门', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (110, '色情', '巨奶', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (111, '色情', '巨乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (112, '色情', '菊穴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (113, '色情', '开苞', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (114, '色情', '口爆', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (115, '色情', '口活', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (116, '色情', '口交', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (117, '色情', '口射', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (118, '色情', '口淫', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (119, '色情', '裤袜', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (120, '色情', '狂操', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (121, '色情', '狂插', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (122, '色情', '浪逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (123, '色情', '浪妇', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (124, '色情', '浪叫', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (125, '色情', '浪女', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (126, '色情', '狼友', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (127, '色情', '聊性', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (128, '色情', '流淫', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (129, '色情', '铃木麻', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (130, '色情', '凌辱', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (131, '色情', '漏乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (132, '色情', '露b', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (133, '色情', '乱交', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (134, '色情', '乱伦', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (135, '色情', '轮暴', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (136, '色情', '轮操', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (137, '色情', '轮奸', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (138, '色情', '裸陪', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (139, '色情', '买春', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (140, '色情', '美逼', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (141, '色情', '美少妇', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (142, '色情', '美乳', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (143, '色情', '美腿', '0', '2021-07-06 13:51:37', '2021-07-06 13:51:37', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (144, '色情', '美穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (145, '色情', '美幼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (146, '色情', '秘唇', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (147, '色情', '迷奸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (148, '色情', '密穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (149, '色情', '蜜穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (150, '色情', '蜜液', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (151, '色情', '摸奶', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (152, '色情', '摸胸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (153, '色情', '母奸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (154, '色情', '奈美', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (155, '色情', '奶子', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (156, '色情', '男奴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (157, '色情', '内射', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (158, '色情', '嫩逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (159, '色情', '嫩女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (160, '色情', '嫩穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (161, '色情', '捏弄', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (162, '色情', '女优', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (163, '色情', '炮友', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (164, '色情', '砲友', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (165, '色情', '喷精', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (166, '色情', '屁眼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (167, '色情', '品香堂', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (168, '色情', '前凸后翘', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (169, '色情', '强jian', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (170, '色情', '强暴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (171, '色情', '强奸处女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (172, '色情', '情趣用品', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (173, '色情', '情色', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (174, '色情', '拳交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (175, '色情', '全裸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (176, '色情', '群交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (177, '色情', '惹火身材', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (178, '色情', '人妻', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (179, '色情', '人兽', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (180, '色情', '日逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (181, '色情', '日烂', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (182, '色情', '肉棒', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (183, '色情', '肉逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (184, '色情', '肉唇', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (185, '色情', '肉洞', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (186, '色情', '肉缝', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (187, '色情', '肉棍', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (188, '色情', '肉茎', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (189, '色情', '肉具', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (190, '色情', '揉乳', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (191, '色情', '肉穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (192, '色情', '肉欲', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (193, '色情', '乳爆', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (194, '色情', '乳房', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (195, '色情', '乳沟', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (196, '色情', '乳交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (197, '色情', '乳头', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (198, '色情', '三级片', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (199, '色情', '骚逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (200, '色情', '骚比', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (201, '色情', '骚女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (202, '色情', '骚水', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (203, '色情', '骚穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (204, '色情', '色逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (205, '色情', '色界', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (206, '色情', '色猫', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (207, '色情', '色盟', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (208, '色情', '色情网站', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (209, '色情', '色区', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (210, '色情', '色色', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (211, '色情', '色诱', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (212, '色情', '色欲', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (213, '色情', '色b', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (214, '色情', '少年阿宾', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (215, '色情', '少修正', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (216, '色情', '射爽', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (217, '色情', '射颜', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (218, '色情', '食精', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (219, '色情', '释欲', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (220, '色情', '兽奸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (221, '色情', '兽交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (222, '色情', '手淫', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (223, '色情', '兽欲', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (224, '色情', '熟妇', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (225, '色情', '熟母', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (226, '色情', '熟女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (227, '色情', '爽片', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (228, '色情', '爽死我了', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (229, '色情', '双臀', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (230, '色情', '死逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (231, '色情', '丝袜', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (232, '色情', '丝诱', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (233, '色情', '松岛枫', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (234, '色情', '酥痒', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (235, '色情', '汤加丽', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (236, '色情', '套弄', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (237, '色情', '体奸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (238, '色情', '体位', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (239, '色情', '舔脚', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (240, '色情', '舔阴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (241, '色情', '调教', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (242, '色情', '偷欢', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (243, '色情', '偷拍', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (244, '色情', '推油', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (245, '色情', '脱内裤', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (246, '色情', '文做', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (247, '色情', '我就色', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (248, '色情', '无码', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (249, '色情', '舞女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (250, '色情', '无修正', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (251, '色情', '吸精', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (252, '色情', '夏川纯', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (253, '色情', '相奸', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (254, '色情', '小逼', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (255, '色情', '校鸡', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (256, '色情', '小穴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (257, '色情', '小xue', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (258, '色情', '写真', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (259, '色情', '性感妖娆', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (260, '色情', '性感诱惑', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (261, '色情', '性虎', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (262, '色情', '性饥渴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (263, '色情', '性技巧', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (264, '色情', '性交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (265, '色情', '性奴', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (266, '色情', '性虐', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (267, '色情', '性息', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (268, '色情', '性欲', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (269, '色情', '胸推', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (270, '色情', '穴口', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (271, '色情', '学生妹', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (272, '色情', '穴图', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (273, '色情', '亚情', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (274, '色情', '颜射', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (275, '色情', '阳具', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (276, '色情', '杨思敏', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (277, '色情', '要射了', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (278, '色情', '夜勤病栋', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (279, '色情', '一本道', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (280, '色情', '一夜欢', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (281, '色情', '一夜情', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (282, '色情', '一ye情', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (283, '色情', '阴部', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (284, '色情', '淫虫', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (285, '色情', '阴唇', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (286, '色情', '淫荡', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (287, '色情', '阴道', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (288, '色情', '淫电影', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (289, '色情', '阴阜', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (290, '色情', '淫妇', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (291, '色情', '淫河', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (292, '色情', '阴核', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (293, '色情', '阴户', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (294, '色情', '淫贱', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (295, '色情', '淫叫', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (296, '色情', '淫教师', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (297, '色情', '阴茎', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (298, '色情', '阴精', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (299, '色情', '淫浪', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (300, '色情', '淫媚', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (301, '色情', '淫糜', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (302, '色情', '淫魔', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (303, '色情', '淫母', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (304, '色情', '淫女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (305, '色情', '淫虐', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (306, '色情', '淫妻', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (307, '色情', '淫情', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (308, '色情', '淫色', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (309, '色情', '淫声浪语', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (310, '色情', '淫兽学园', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (311, '色情', '淫书', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (312, '色情', '淫术炼金士', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (313, '色情', '淫水', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (314, '色情', '淫娃', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (315, '色情', '淫威', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (316, '色情', '淫亵', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (317, '色情', '淫样', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (318, '色情', '淫液', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (319, '色情', '淫照', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (320, '色情', '阴b', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (321, '色情', '应召', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (322, '色情', '幼交', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (323, '色情', '幼男', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (324, '色情', '幼女', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (325, '色情', '欲火', '0', '2021-07-06 13:51:38', '2021-07-06 13:51:38', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (326, '色情', '欲女', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (327, '色情', '玉女心经', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (328, '色情', '玉蒲团', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (329, '色情', '玉乳', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (330, '色情', '欲仙欲死', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (331, '色情', '玉穴', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (332, '色情', '援交', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (333, '色情', '原味内衣', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (334, '色情', '援助交际', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (335, '色情', '张筱雨', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (336, '色情', '招鸡', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (337, '色情', '招妓', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (338, '色情', '中年美妇', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (339, '色情', '抓胸', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (340, '色情', '自拍', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (341, '色情', '自慰', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (342, '色情', '作爱', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (343, '色情', '18禁', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (344, '色情', '99bb', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (345, '色情', 'a4u', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (346, '色情', 'a4y', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (347, '色情', 'adult', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (348, '色情', 'amateur', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (349, '色情', 'anal', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (350, '色情', 'a片', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (351, '色情', 'fuck', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (352, '色情', 'gay片', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (353, '色情', 'g点', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (354, '色情', 'g片', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (355, '色情', 'hardcore', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (356, '色情', 'h动画', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (357, '色情', 'h动漫', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (358, '色情', 'incest', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (359, '色情', 'porn', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (360, '色情', 'secom', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (361, '色情', 'sexinsex', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (362, '色情', 'sm女王', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (363, '色情', 'xiao77', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (364, '色情', 'xing伴侣', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (365, '色情', 'tokyohot', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (366, '色情', 'yin荡', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (367, '色情', '贱人', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (368, '色情', '装b', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (369, '色情', '大sb', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (370, '色情', '傻逼', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (371, '色情', '傻b', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (372, '色情', '煞逼', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (373, '色情', '煞笔', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (374, '色情', '刹笔', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (375, '色情', '傻比', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (376, '色情', '沙比', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (377, '色情', '欠干', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (378, '色情', '婊子养的', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (379, '色情', '我日你', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (380, '色情', '我操', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (381, '色情', '我草', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (382, '色情', '卧艹', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (383, '色情', '卧槽', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (384, '色情', '爆你菊', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (385, '色情', '艹你', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (386, '色情', 'cao你', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (387, '色情', '你他妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (388, '色情', '真他妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (389, '色情', '别他吗', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (390, '色情', '草你吗', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (391, '色情', '草你丫', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (392, '色情', '操你妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (393, '色情', '擦你妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (394, '色情', '操你娘', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (395, '色情', '操他妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (396, '色情', '日你妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (397, '色情', '干你妈', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (398, '色情', '干你娘', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (399, '色情', '娘西皮', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (400, '色情', '狗操', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (401, '色情', '狗草', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (402, '色情', '狗杂种', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (403, '色情', '狗日的', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (404, '色情', '操你祖宗', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (405, '色情', '操你全家', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (406, '色情', '操你大爷', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (407, '色情', '妈逼', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (408, '色情', '你麻痹', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (409, '色情', '麻痹的', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (410, '色情', '妈了个逼', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (411, '色情', '马勒', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (412, '色情', '狗娘养', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (413, '色情', '贱比', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (414, '色情', '贱b', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (415, '色情', '下贱', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (416, '色情', '死全家', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (417, '色情', '全家死光', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (418, '色情', '全家不得好死', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (419, '色情', '全家死绝', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (420, '色情', '白痴', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (421, '色情', '无耻', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (422, '色情', 'sb', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (423, '色情', '杀b', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (424, '色情', '你吗b', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (425, '色情', '你妈的', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (426, '色情', '婊子', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (427, '色情', '贱货', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (428, '色情', '人渣', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (429, '色情', '混蛋', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (430, '色情', '媚外', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (431, '色情', '和弦', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (432, '色情', '兼职', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (433, '色情', '限量', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (434, '色情', '铃声', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (435, '色情', '性伴侣', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (436, '色情', '男公关', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (437, '色情', '火辣', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (438, '色情', '精子', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (439, '色情', '射精', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (440, '色情', '诱奸', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (441, '色情', '强奸', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (442, '色情', '做爱', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (443, '色情', '性爱', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (444, '色情', '发生关系', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (445, '色情', '按摩', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (446, '色情', '快感', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (447, '色情', '处男', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (448, '色情', '猛男', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (449, '色情', '少妇', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (450, '色情', '屌', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (451, '色情', '屁股', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (452, '色情', '下体', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (453, '色情', 'a片', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (454, '色情', '内裤', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (455, '色情', '浑圆', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (456, '色情', '咪咪', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (457, '色情', '发情', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (458, '色情', '刺激', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (459, '色情', '白嫩', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (460, '色情', '粉嫩', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (461, '色情', '兽性', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (462, '色情', '风骚', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (463, '色情', '呻吟', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (464, '色情', 'sm', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (465, '色情', '阉割', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (466, '色情', '高潮', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (467, '色情', '裸露', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (468, '色情', '不穿', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (469, '色情', '一丝不挂', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (470, '色情', '脱光', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (471, '色情', '干你', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (472, '色情', '干死', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (473, '色情', '我干', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (474, '色情', '裙中性运动', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (475, '色情', '乱奸', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (476, '色情', '乱伦', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (477, '色情', '乱伦类', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (478, '色情', '乱伦小', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (479, '色情', '伦理大', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (480, '色情', '伦理电影', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (481, '色情', '伦理毛', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (482, '色情', '伦理片', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (483, '色情', '裸聊', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (484, '色情', '裸聊网', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (485, '色情', '裸体写真', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (486, '色情', '裸舞视', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (487, '色情', '裸照', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (488, '色情', '美女裸体', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (489, '色情', '美女写真', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (490, '色情', '美女上门', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (491, '色情', '美艳少妇', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (492, '色情', '妹按摩', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (493, '色情', '妹上门', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (494, '色情', '迷幻药', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (495, '色情', '迷幻藥', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (496, '色情', '迷昏口', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (497, '色情', '迷昏药', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (498, '色情', '迷昏藥', '0', '2021-07-06 13:51:39', '2021-07-06 13:51:39', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (499, '色情', '迷魂香', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (500, '色情', '迷魂药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (501, '色情', '迷魂藥', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (502, '色情', '迷奸粉', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (503, '色情', '迷奸药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (504, '色情', '迷情粉', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (505, '色情', '迷情水', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (506, '色情', '迷情药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (507, '色情', '迷药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (508, '色情', '迷藥', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (509, '色情', '谜奸药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (510, '色情', '骚妇', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (511, '色情', '骚货', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (512, '色情', '骚浪', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (513, '色情', '骚女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (514, '色情', '骚嘴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (515, '色情', '色电影', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (516, '色情', '色妹妹', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (517, '色情', '色情表演', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (518, '色情', '色情电影', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (519, '色情', '色情服务', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (520, '色情', '色情图片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (521, '色情', '色情小说', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (522, '色情', '色情影片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (523, '色情', '色情表演', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (524, '色情', '色情电影', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (525, '色情', '色情服务', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (526, '色情', '色情片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (527, '色情', '色视频', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (528, '色情', '色小说', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (529, '色情', '性伴侣', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (530, '色情', '性服务', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (531, '色情', '性福情', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (532, '色情', '性感少', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (533, '色情', '性伙伴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (534, '色情', '性交', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (535, '色情', '性交视频', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (536, '色情', '性交图片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (537, '色情', '性奴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (538, '色情', '性奴集中营', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (539, '色情', '性虐', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (540, '色情', '阴唇', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (541, '色情', '阴道', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (542, '色情', '阴蒂', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (543, '色情', '阴户', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (544, '色情', '阴间来电', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (545, '色情', '阴茎', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (546, '色情', '阴茎增大', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (547, '色情', '阴茎助勃', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (548, '色情', '阴毛', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (549, '色情', '陰唇', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (550, '色情', '陰道', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (551, '色情', '陰戶', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (552, '色情', '淫荡', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (553, '色情', '淫荡美女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (554, '色情', '淫荡视频', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (555, '色情', '淫荡照片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (556, '色情', '淫乱', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (557, '色情', '淫靡', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (558, '色情', '淫魔', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (559, '色情', '淫魔舞', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (560, '色情', '淫女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (561, '色情', '淫情女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (562, '色情', '淫肉', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (563, '色情', '淫騷妹', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (564, '色情', '淫兽', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (565, '色情', '淫兽学', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (566, '色情', '淫水', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (567, '色情', '淫穴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (568, '色情', 'morphine', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (569, '色情', '摇头丸', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (570, '色情', '迷药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (571, '色情', '乖乖粉', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (572, '色情', 'narcotic', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (573, '色情', '麻醉药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (574, '色情', '精神药品', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (575, '色情', '爱女人', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (576, '色情', '爱液', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (577, '色情', '按摩棒', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (578, '色情', '拔出来', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (579, '色情', '爆草', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (580, '色情', '包二奶', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (581, '色情', '暴干', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (582, '色情', '暴奸', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (583, '色情', '暴乳', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (584, '色情', '爆乳', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (585, '色情', '暴淫', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (586, '色情', '屄', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (587, '色情', '被操', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (588, '色情', '被插', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (589, '色情', '被干', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (590, '色情', '逼奸', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (591, '色情', '仓井空', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (592, '色情', '插暴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (593, '色情', '操逼', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (594, '色情', '操黑', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (595, '色情', '操烂', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (596, '色情', '肏你', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (597, '色情', '肏死', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (598, '色情', '操死', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (599, '色情', '操我', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (600, '色情', '厕奴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (601, '色情', '插比', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (602, '色情', '插b', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (603, '色情', '插逼', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (604, '色情', '插进', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (605, '色情', '插你', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (606, '色情', '插我', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (607, '色情', '插阴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (608, '色情', '潮吹', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (609, '色情', '潮喷', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (610, '色情', '成人电影', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (611, '色情', '成人论坛', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (612, '色情', '成人色情', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (613, '色情', '成人网站', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (614, '色情', '成人文学', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (615, '色情', '成人小说', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (616, '色情', '艳情小说', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (617, '色情', '成人游戏', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (618, '色情', '吃精', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (619, '色情', '赤裸', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (620, '色情', '抽插', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (621, '色情', '扌由插', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (622, '色情', '抽一插', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (623, '色情', '春药', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (624, '色情', '大波', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (625, '色情', '大力抽送', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (626, '色情', '大乳', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (627, '色情', '荡妇', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (628, '色情', '荡女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (629, '色情', '盗撮', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (630, '色情', '多人轮', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (631, '色情', '发浪', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (632, '色情', '放尿', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (633, '色情', '肥逼', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (634, '色情', '粉穴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (635, '色情', '封面女郎', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (636, '色情', '风月大陆', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (637, '色情', '干死你', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (638, '色情', '干穴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (639, '色情', '肛交', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (640, '色情', '肛门', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (641, '色情', '龟头', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (642, '色情', '裹本', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (643, '色情', '国产av', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (644, '色情', '好嫩', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (645, '色情', '豪乳', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (646, '色情', '黑逼', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (647, '色情', '后庭', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (648, '色情', '后穴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (649, '色情', '虎骑', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (650, '色情', '花花公子', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (651, '色情', '换妻俱乐部', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (652, '色情', '黄片', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (653, '色情', '几吧', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (654, '色情', '鸡吧', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (655, '色情', '鸡巴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (656, '色情', '鸡奸', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (657, '色情', '寂寞男', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (658, '色情', '寂寞女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (659, '色情', '妓女', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (660, '色情', '激情', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (661, '色情', '集体淫', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (662, '色情', '奸情', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (663, '色情', '叫床', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (664, '色情', '脚交', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (665, '色情', '金鳞岂是池中物', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (666, '色情', '金麟岂是池中物', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (667, '色情', '精液', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (668, '色情', '就去日', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (669, '色情', '巨屌', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (670, '色情', '菊花洞', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (671, '色情', '菊门', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (672, '色情', '巨奶', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (673, '色情', '巨乳', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (674, '色情', '菊穴', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (675, '色情', '开苞', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (676, '色情', '口爆', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (677, '色情', '口活', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (678, '色情', '口交', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (679, '色情', '口射', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (680, '色情', '口淫', '0', '2021-07-06 13:51:40', '2021-07-06 13:51:40', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (681, '色情', '裤袜', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (682, '色情', '狂操', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (683, '色情', '狂插', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (684, '色情', '浪逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (685, '色情', '浪妇', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (686, '色情', '浪叫', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (687, '色情', '浪女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (688, '色情', '狼友', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (689, '色情', '聊性', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (690, '色情', '流淫', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (691, '色情', '铃木麻', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (692, '色情', '凌辱', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (693, '色情', '漏乳', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (694, '色情', '露b', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (695, '色情', '乱交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (696, '色情', '乱伦', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (697, '色情', '轮暴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (698, '色情', '轮操', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (699, '色情', '轮奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (700, '色情', '裸陪', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (701, '色情', '买春', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (702, '色情', '美逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (703, '色情', '美少妇', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (704, '色情', '美乳', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (705, '色情', '美腿', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (706, '色情', '美穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (707, '色情', '美幼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (708, '色情', '秘唇', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (709, '色情', '迷奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (710, '色情', '密穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (711, '色情', '蜜穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (712, '色情', '蜜液', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (713, '色情', '摸奶', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (714, '色情', '摸胸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (715, '色情', '母奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (716, '色情', '奈美', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (717, '色情', '奶子', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (718, '色情', '男奴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (719, '色情', '内射', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (720, '色情', '嫩逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (721, '色情', '嫩女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (722, '色情', '嫩穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (723, '色情', '捏弄', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (724, '色情', '女优', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (725, '色情', '炮友', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (726, '色情', '砲友', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (727, '色情', '喷精', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (728, '色情', '屁眼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (729, '色情', '品香堂', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (730, '色情', '前凸后翘', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (731, '色情', '强jian', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (732, '色情', '强暴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (733, '色情', '强奸处女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (734, '色情', '情趣用品', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (735, '色情', '情色', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (736, '色情', '拳交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (737, '色情', '全裸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (738, '色情', '群交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (739, '色情', '惹火身材', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (740, '色情', '人妻', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (741, '色情', '人兽', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (742, '色情', '日逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (743, '色情', '日烂', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (744, '色情', '肉棒', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (745, '色情', '肉逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (746, '色情', '肉唇', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (747, '色情', '肉洞', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (748, '色情', '肉缝', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (749, '色情', '肉棍', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (750, '色情', '肉茎', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (751, '色情', '肉具', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (752, '色情', '揉乳', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (753, '色情', '肉穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (754, '色情', '肉欲', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (755, '色情', '乳爆', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (756, '色情', '乳房', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (757, '色情', '乳沟', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (758, '色情', '乳交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (759, '色情', '乳头', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (760, '色情', '三级片', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (761, '色情', '骚逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (762, '色情', '骚比', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (763, '色情', '骚女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (764, '色情', '骚水', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (765, '色情', '骚穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (766, '色情', '色逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (767, '色情', '色界', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (768, '色情', '色猫', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (769, '色情', '色盟', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (770, '色情', '色情网站', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (771, '色情', '色区', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (772, '色情', '色色', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (773, '色情', '色诱', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (774, '色情', '色欲', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (775, '色情', '色b', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (776, '色情', '少年阿宾', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (777, '色情', '少修正', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (778, '色情', '射爽', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (779, '色情', '射颜', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (780, '色情', '食精', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (781, '色情', '释欲', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (782, '色情', '兽奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (783, '色情', '兽交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (784, '色情', '手淫', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (785, '色情', '兽欲', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (786, '色情', '熟妇', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (787, '色情', '熟母', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (788, '色情', '熟女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (789, '色情', '爽片', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (790, '色情', '爽死我了', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (791, '色情', '双臀', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (792, '色情', '死逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (793, '色情', '丝袜', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (794, '色情', '丝诱', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (795, '色情', '松岛枫', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (796, '色情', '酥痒', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (797, '色情', '汤加丽', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (798, '色情', '套弄', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (799, '色情', '体奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (800, '色情', '体位', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (801, '色情', '舔脚', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (802, '色情', '舔阴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (803, '色情', '调教', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (804, '色情', '偷欢', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (805, '色情', '偷拍', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (806, '色情', '推油', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (807, '色情', '脱内裤', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (808, '色情', '文做', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (809, '色情', '我就色', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (810, '色情', '无码', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (811, '色情', '舞女', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (812, '色情', '无修正', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (813, '色情', '吸精', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (814, '色情', '夏川纯', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (815, '色情', '相奸', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (816, '色情', '小逼', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (817, '色情', '校鸡', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (818, '色情', '小穴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (819, '色情', '小xue', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (820, '色情', '写真', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (821, '色情', '性感妖娆', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (822, '色情', '性感诱惑', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (823, '色情', '性虎', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (824, '色情', '性饥渴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (825, '色情', '性技巧', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (826, '色情', '性交', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (827, '色情', '性奴', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (828, '色情', '性虐', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (829, '色情', '性息', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (830, '色情', '性欲', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (831, '色情', '胸推', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (832, '色情', '穴口', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (833, '色情', '学生妹', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (834, '色情', '穴图', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (835, '色情', '亚情', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (836, '色情', '颜射', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (837, '色情', '阳具', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (838, '色情', '杨思敏', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (839, '色情', '要射了', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (840, '色情', '夜勤病栋', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (841, '色情', '一本道', '0', '2021-07-06 13:51:41', '2021-07-06 13:51:41', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (842, '色情', '一夜欢', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (843, '色情', '一夜情', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (844, '色情', '一ye情', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (845, '色情', '阴部', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (846, '色情', '淫虫', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (847, '色情', '阴唇', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (848, '色情', '淫荡', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (849, '色情', '阴道', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (850, '色情', '淫电影', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (851, '色情', '阴阜', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (852, '色情', '淫妇', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (853, '色情', '淫河', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (854, '色情', '阴核', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (855, '色情', '阴户', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (856, '色情', '淫贱', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (857, '色情', '淫叫', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (858, '色情', '淫教师', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (859, '色情', '阴茎', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (860, '色情', '阴精', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (861, '色情', '淫浪', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (862, '色情', '淫媚', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (863, '色情', '淫糜', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (864, '色情', '淫魔', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (865, '色情', '淫母', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (866, '色情', '淫女', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (867, '色情', '淫虐', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (868, '色情', '淫妻', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (869, '色情', '淫情', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (870, '色情', '淫色', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (871, '色情', '淫声浪语', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (872, '色情', '淫兽学园', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (873, '色情', '淫书', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (874, '色情', '淫术炼金士', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (875, '色情', '淫水', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (876, '色情', '淫娃', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (877, '色情', '淫威', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (878, '色情', '淫亵', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (879, '色情', '淫样', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (880, '色情', '淫液', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (881, '色情', '淫照', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (882, '色情', '阴b', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (883, '色情', '应召', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (884, '色情', '幼交', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (885, '色情', '幼男', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (886, '色情', '幼女', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (887, '色情', '欲火', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (888, '色情', '欲女', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (889, '色情', '玉女心经', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (890, '色情', '玉蒲团', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (891, '色情', '玉乳', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (892, '色情', '欲仙欲死', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (893, '色情', '玉穴', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (894, '色情', '援交', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (895, '色情', '原味内衣', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (896, '色情', '援助交际', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (897, '色情', '张筱雨', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (898, '色情', '招鸡', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (899, '色情', '招妓', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (900, '色情', '中年美妇', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (901, '色情', '抓胸', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (902, '色情', '自拍', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (903, '色情', '自慰', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (904, '色情', '作爱', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (905, '色情', '18禁', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (906, '色情', '99bb', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (907, '色情', 'a4u', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (908, '色情', 'a4y', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (909, '色情', 'adult', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (910, '色情', 'amateur', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (911, '色情', 'anal', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (912, '色情', 'a片', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (913, '色情', 'fuck', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (914, '色情', 'gay片', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (915, '色情', 'g点', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (916, '色情', 'g片', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (917, '色情', 'hardcore', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (918, '色情', 'h动画', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (919, '色情', 'h动漫', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (920, '色情', 'incest', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (921, '色情', 'porn', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (922, '色情', 'secom', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (923, '色情', 'sexinsex', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (924, '色情', 'sm女王', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (925, '色情', 'xiao77', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (926, '色情', 'xing伴侣', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (927, '色情', 'tokyohot', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (928, '色情', 'yin荡', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (929, '反动', '腐败中国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (930, '反动', '三个呆婊', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (931, '反动', '你办事我放心', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (932, '反动', '社会主义灭亡', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (933, '反动', '打倒中国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (934, '反动', '打倒共产党', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (935, '反动', '打倒共产主义', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (936, '反动', '打倒胡锦涛', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (937, '反动', '打倒江泽民', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (938, '反动', '打倒江主席', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (939, '反动', '打倒李鹏', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (940, '反动', '打倒罗干', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (941, '反动', '打倒温家宝', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (942, '反动', '打倒中共', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (943, '反动', '打倒朱镕', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (944, '反动', '抵制共产党', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (945, '反动', '抵制共产主义', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (946, '反动', '抵制胡锦涛', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (947, '反动', '抵制江泽民', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (948, '反动', '抵制江主席', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (949, '反动', '抵制李鹏', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (950, '反动', '抵制罗干', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (951, '反动', '抵制温家宝', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (952, '反动', '抵制中共', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (953, '反动', '抵制朱镕基', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (954, '反动', '灭亡中国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (955, '反动', '亡党亡国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (956, '反动', '粉碎四人帮', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (957, '反动', '激流中国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (958, '反动', '特供', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (959, '反动', '特贡', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (960, '反动', '特共', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (961, '反动', 'zf大楼', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (962, '反动', '殃视', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (963, '反动', '贪污腐败', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (964, '反动', '强制拆除', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (965, '反动', '形式主义', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (966, '反动', '政治风波', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (967, '反动', '太子党', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (968, '反动', '上海帮', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (969, '反动', '北京帮', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (970, '反动', '清华帮', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (971, '反动', '红色贵族', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (972, '反动', '权贵集团', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (973, '反动', '河蟹社会', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (974, '反动', '喝血社会', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (975, '反动', '九风', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (976, '反动', '9风', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (977, '反动', '十七大', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (978, '反动', '十7大', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (979, '反动', '17da', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (980, '反动', '九学', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (981, '反动', '9学', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (982, '反动', '四风', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (983, '反动', '4风', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (984, '反动', '双规', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (985, '反动', '南街村', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (986, '反动', '最淫官员', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (987, '反动', '警匪', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (988, '反动', '官匪', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (989, '反动', '独夫民贼', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (990, '反动', '官商勾结', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (991, '反动', '城管暴力执法', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (992, '反动', '强制捐款', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (993, '反动', '毒豺', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (994, '反动', '一党执政', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (995, '反动', '一党专制', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (996, '反动', '一党专政', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (997, '反动', '专制政权', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (998, '反动', '宪法法院', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (999, '反动', '胡平', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1000, '反动', '苏晓康', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1001, '反动', '贺卫方', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1002, '反动', '谭作人', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1003, '反动', '焦国标', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1004, '反动', '万润南', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1005, '反动', '张志新', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1006, '反动', '辛灝年', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1007, '反动', '高勤荣', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1008, '反动', '王炳章', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1009, '反动', '高智晟', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1010, '反动', '司马璐', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1011, '反动', '刘晓竹', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1012, '反动', '刘宾雁', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1013, '反动', '魏京生', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1014, '反动', '寻找林昭的灵魂', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1015, '反动', '别梦成灰', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1016, '反动', '谁是新中国', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1017, '反动', '讨伐中宣部', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1018, '反动', '异议人士', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1019, '反动', '民运人士', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1020, '反动', '启蒙派', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1021, '反动', '选国家主席', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1022, '反动', '民一主', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1023, '反动', 'min主', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1024, '反动', '民竹', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1025, '反动', '民珠', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1026, '反动', '民猪', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1027, '反动', 'chinesedemocracy', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL,
        NULL);
INSERT INTO `sensitive_words`
VALUES (1028, '反动', '大赦国际', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1029, '反动', '国际特赦', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1030, '反动', 'da选', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1031, '反动', '投公', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1032, '反动', '公头', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1033, '反动', '宪政', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1034, '反动', '平反', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1035, '反动', '党章', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1036, '反动', '维权', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1037, '反动', '昝爱宗', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1038, '反动', '宪章', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1039, '反动', '08宪', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1040, '反动', '08xz', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1041, '反动', '抿主', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1042, '反动', '敏主', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1043, '反动', '人拳', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1044, '反动', '人木又', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1045, '反动', '人quan', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1046, '反动', 'renquan', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1047, '反动', '中国人权', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1048, '反动', '中国新民党', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1049, '反动', '群体事件', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1050, '反动', '群体性事件', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1051, '反动', '上中央', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1052, '反动', '去中央', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1053, '反动', '讨说法', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1054, '反动', '请愿', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1055, '反动', '请命', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1056, '反动', '公开信', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1057, '反动', '联名上书', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1058, '反动', '万人大签名', '0', '2021-07-06 13:51:42', '2021-07-06 13:51:42', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1059, '反动', '万人骚动', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1060, '反动', '截访', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1061, '反动', '上访', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1062, '反动', 'shangfang', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1063, '反动', '信访', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1064, '反动', '访民', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1065, '反动', '集合', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1066, '反动', '集会', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1067, '反动', '组织集体', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1068, '反动', '静坐', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1069, '反动', '静zuo', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1070, '反动', 'jing坐', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1071, '反动', '示威', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1072, '反动', '示wei', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1073, '反动', '游行', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1074, '反动', 'you行', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1075, '反动', '油行', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1076, '反动', '游xing', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1077, '反动', 'youxing', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1078, '反动', '官逼民反', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1079, '反动', '反party', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1080, '反动', '反共', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1081, '反动', '抗议', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1082, '反动', '亢议', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1083, '反动', '抵制', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1084, '反动', '低制', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1085, '反动', '底制', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1086, '反动', 'di制', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1087, '反动', '抵zhi', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1088, '反动', 'dizhi', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1089, '反动', 'boycott', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1090, '反动', '血书', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1091, '反动', '焚烧中国国旗', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1092, '反动', 'baoluan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1093, '反动', '流血冲突', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1094, '反动', '出现暴动', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1095, '反动', '发生暴动', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1096, '反动', '引起暴动', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1097, '反动', 'baodong', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1098, '反动', '灭共', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1099, '反动', '杀毙', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1100, '反动', '罢工', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1101, '反动', '霸工', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1102, '反动', '罢考', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1103, '反动', '罢餐', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1104, '反动', '霸餐', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1105, '反动', '罢参', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1106, '反动', '罢饭', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1107, '反动', '罢吃', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1108, '反动', '罢食', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1109, '反动', '罢课', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1110, '反动', '罢ke', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1111, '反动', '霸课', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1112, '反动', 'ba课', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1113, '反动', '罢教', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1114, '反动', '罢学', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1115, '反动', '罢运', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1116, '反动', '网特', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1117, '反动', '网评员', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1118, '反动', '网络评论员', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1119, '反动', '五毛党', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1120, '反动', '五毛们', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1121, '反动', '5毛党', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1122, '反动', '戒严', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1123, '反动', 'jieyan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1124, '反动', 'jie严', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1125, '反动', '戒yan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1126, '反动', '8的平方事件', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1127, '反动', '知道64', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1128, '反动', '八九年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1129, '反动', '贰拾年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1130, '反动', '2o年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1131, '反动', '20和谐年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1132, '反动', '贰拾周年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1133, '反动', '六四', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1134, '反动', '六河蟹四', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1135, '反动', '六百度四', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1136, '反动', '六和谐四', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1137, '反动', '陆四', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1138, '反动', '陆肆', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1139, '反动', '198964', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1140, '反动', '5月35', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1141, '反动', '89年春夏之交', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1142, '反动', '64惨案', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1143, '反动', '64时期', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1144, '反动', '64运动', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1145, '反动', '4事件', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1146, '反动', '四事件', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1147, '反动', '北京风波', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1148, '反动', '学潮', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1149, '反动', '学chao', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1150, '反动', 'xuechao', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1151, '反动', '学百度潮', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1152, '反动', '门安天', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1153, '反动', '天按门', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1154, '反动', '坦克压大学生', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1155, '反动', '民主女神', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1156, '反动', '历史的伤口', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1157, '反动', '高自联', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1158, '反动', '北高联', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1159, '反动', '血洗京城', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1160, '反动', '四二六社论', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1161, '反动', '王丹', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1162, '反动', '柴玲', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1163, '反动', '沈彤', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1164, '反动', '封从德', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1165, '反动', '王超华', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1166, '反动', '王维林', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1167, '反动', '吾尔开希', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1168, '反动', '吾尔开西', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1169, '反动', '侯德健', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1170, '反动', '阎明复', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1171, '反动', '方励之', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1172, '反动', '蒋捷连', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1173, '反动', '丁子霖', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1174, '反动', '辛灏年', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1175, '反动', '蒋彦永', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1176, '反动', '严家其', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1177, '反动', '陈一咨', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1178, '反动', '中华局域网', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1179, '反动', '党的喉舌', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1180, '反动', '互联网审查', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1181, '反动', '当局严密封锁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1182, '反动', '新闻封锁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1183, '反动', '封锁消息', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1184, '反动', '爱国者同盟', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1185, '反动', '关闭所有论坛', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1186, '反动', '网络封锁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1187, '反动', '金盾工程', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1188, '反动', 'gfw', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1189, '反动', '无界浏览', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1190, '反动', '无界网络', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1191, '反动', '自由门', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1192, '反动', '何清涟', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1193, '反动', '中国的陷阱', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1194, '反动', '汪兆钧', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1195, '反动', '记者无疆界', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1196, '反动', '境外媒体', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1197, '反动', '维基百科', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1198, '反动', '纽约时报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1199, '反动', 'bbc中文网', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1200, '反动', '华盛顿邮报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1201, '反动', '世界日报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1202, '反动', '东森新闻网', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1203, '反动', '东森电视', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1204, '反动', '星岛日报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1205, '反动', 'wikipedia', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1206, '反动', 'youtube', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1207, '反动', 'googleblogger', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1208, '反动', '美国广播公司', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1209, '反动', '英国金融时报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1210, '反动', '自由亚洲', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1211, '反动', '自由时报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1212, '反动', '中国时报', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1213, '反动', '反分裂', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1214, '反动', '威胁论', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1215, '反动', '左翼联盟', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1216, '反动', '钓鱼岛', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1217, '反动', '保钓组织', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1218, '反动', '主权', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1219, '反动', '弓单', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1220, '反动', '火乍', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1221, '反动', '木仓', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1222, '反动', '石肖', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1223, '反动', '核蛋', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1224, '反动', '步qiang', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1225, '反动', 'bao炸', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1226, '反动', '爆zha', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1227, '反动', 'baozha', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1228, '反动', 'zha药', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1229, '反动', 'zha弹', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1230, '反动', '炸dan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1231, '反动', '炸yao', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1232, '反动', 'zhadan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1233, '反动', 'zhayao', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1234, '反动', 'hmtd', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1235, '反动', '三硝基甲苯', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1236, '反动', '六氟化铀', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1237, '反动', '炸药配方', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1238, '反动', '弹药配方', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1239, '反动', '炸弹配方', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1240, '反动', '皮箱炸弹', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1241, '反动', '火药配方', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1242, '反动', '人体炸弹', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1243, '反动', '人肉炸弹', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1244, '反动', '解放军', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1245, '反动', '兵力部署', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1246, '反动', '军转', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1247, '反动', '军事社', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1248, '反动', '8341部队', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1249, '反动', '第21集团军', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1250, '反动', '七大军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1251, '反动', '7大军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1252, '反动', '北京军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1253, '反动', '沈阳军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1254, '反动', '济南军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1255, '反动', '成都军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1256, '反动', '广州军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1257, '反动', '南京军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1258, '反动', '兰州军区', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1259, '反动', '颜色革命', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1260, '反动', '规模冲突', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1261, '反动', '塔利班', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1262, '反动', '基地组织', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1263, '反动', '恐怖分子', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1264, '反动', '恐怖份子', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1265, '反动', '三股势力', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1266, '反动', '印尼屠华', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1267, '反动', '印尼事件', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1268, '反动', '蒋公纪念歌', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1269, '反动', '马英九', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1270, '反动', 'mayingjiu', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1271, '反动', '李天羽', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1272, '反动', '苏贞昌', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1273, '反动', '林文漪', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1274, '反动', '陈水扁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1275, '反动', '陈s扁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1276, '反动', '陈随便', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1277, '反动', '阿扁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1278, '反动', 'a扁', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1279, '反动', '告全国同胞书', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1280, '反动', '台百度湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1281, '反动', '台完', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1282, '反动', '台wan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1283, '反动', 'taiwan', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1284, '反动', '台弯', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1285, '反动', '湾台', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1286, '反动', '台湾国', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1287, '反动', '台湾共和国', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1288, '反动', '台军', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1289, '反动', '台独', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1290, '反动', '台毒', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1291, '反动', '台du', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1292, '反动', 'taidu', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1293, '反动', 'twdl', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1294, '反动', '一中一台', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1295, '反动', '打台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1296, '反动', '两岸战争', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1297, '反动', '攻占台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1298, '反动', '支持台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1299, '反动', '进攻台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1300, '反动', '占领台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1301, '反动', '统一台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1302, '反动', '收复台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1303, '反动', '登陆台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1304, '反动', '解放台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1305, '反动', '解放tw', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1306, '反动', '解决台湾', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1307, '反动', '光复民国', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1308, '反动', '台湾独立', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1309, '反动', '台湾问题', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1310, '反动', '台海问题', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1311, '反动', '台海危机', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1312, '反动', '台海统一', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1313, '反动', '台海大战', '0', '2021-07-06 13:51:43', '2021-07-06 13:51:43', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1314, '反动', '台海战争', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1315, '反动', '台海局势', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1316, '反动', '入联', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1317, '反动', '入耳关', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1318, '反动', '中华联邦', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1319, '反动', '国民党', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1320, '反动', 'x民党', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1321, '反动', '民进党', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1322, '反动', '青天白日', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1323, '反动', '闹独立', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1324, '反动', 'duli', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1325, '反动', 'fenlie', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1326, '反动', '日本万岁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1327, '反动', '小泽一郎', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1328, '反动', '劣等民族', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1329, '反动', '汉人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1330, '反动', '汉维', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1331, '反动', '维汉', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1332, '反动', '维吾', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1333, '反动', '吾尔', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1334, '反动', '热比娅', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1335, '反动', '伊力哈木', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1336, '反动', '疆独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1337, '反动', '东突厥斯坦解放组织', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1338, '反动', '东突解放组织', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1339, '反动', '蒙古分裂分子', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1340, '反动', '列确', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1341, '反动', '阿旺晋美', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1342, '反动', '藏人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1343, '反动', '臧人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1344, '反动', 'zang人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1345, '反动', '藏民', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1346, '反动', '藏m', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1347, '反动', '达赖', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1348, '反动', '赖达', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1349, '反动', 'dalai', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1350, '反动', '哒赖', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1351, '反动', 'dl喇嘛', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1352, '反动', '丹增嘉措', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1353, '反动', '打砸抢', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1354, '反动', '西独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1355, '反动', '藏独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1356, '反动', '葬独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1357, '反动', '臧独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1358, '反动', '藏毒', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1359, '反动', '藏du', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1360, '反动', 'zangdu', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1361, '反动', '支持zd', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1362, '反动', '藏暴乱', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1363, '反动', '藏青会', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1364, '反动', '雪山狮子旗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1365, '反动', '拉萨', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1366, '反动', '啦萨', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1367, '反动', '啦沙', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1368, '反动', '啦撒', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1369, '反动', '拉sa', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1370, '反动', 'lasa', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1371, '反动', 'la萨', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1372, '反动', '西藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1373, '反动', '藏西', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1374, '反动', '藏春阁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1375, '反动', '藏獨', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1376, '反动', '藏独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1377, '反动', '藏独立', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1378, '反动', '藏妇会', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1379, '反动', '藏青会', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1380, '反动', '藏字石', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1381, '反动', 'xizang', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1382, '反动', 'xi藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1383, '反动', 'x藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1384, '反动', '西z', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1385, '反动', 'tibet', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1386, '反动', '希葬', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1387, '反动', '希藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1388, '反动', '硒藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1389, '反动', '稀藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1390, '反动', '西脏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1391, '反动', '西奘', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1392, '反动', '西葬', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1393, '反动', '西臧', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1394, '反动', '援藏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1395, '反动', 'bjork', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1396, '反动', '王千源', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1397, '反动', '安拉', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1398, '反动', '回教', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1399, '反动', '回族', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1400, '反动', '回回', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1401, '反动', '回民', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1402, '反动', '穆斯林', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1403, '反动', '穆罕穆德', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1404, '反动', '穆罕默德', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1405, '反动', '默罕默德', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1406, '反动', '伊斯兰', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1407, '反动', '圣战组织', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1408, '反动', '清真', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1409, '反动', '清zhen', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1410, '反动', 'qingzhen', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1411, '反动', '真主', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1412, '反动', '阿拉伯', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1413, '反动', '高丽棒子', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1414, '反动', '韩国狗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1415, '反动', '满洲第三帝国', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1416, '反动', '满狗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1417, '反动', '鞑子', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1418, '反动', '江丑闻', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1419, '反动', '江嫡系', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1420, '反动', '江毒', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1421, '反动', '江独裁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1422, '反动', '江蛤蟆', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1423, '反动', '江核心', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1424, '反动', '江黑心', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1425, '反动', '江胡内斗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1426, '反动', '江祸心', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1427, '反动', '江家帮', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1428, '反动', '江绵恒', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1429, '反动', '江派和胡派', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1430, '反动', '江派人马', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1431, '反动', '江泉集团', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1432, '反动', '江人马', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1433, '反动', '江三条腿', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1434, '反动', '江氏集团', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1435, '反动', '江氏家族', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1436, '反动', '江氏政治局', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1437, '反动', '江氏政治委员', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1438, '反动', '江梳头', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1439, '反动', '江太上', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1440, '反动', '江戏子', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1441, '反动', '江系人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1442, '反动', '江系人马', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1443, '反动', '江宰民', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1444, '反动', '江贼', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1445, '反动', '江贼民', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1446, '反动', '江主席', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1447, '反动', '麻果丸', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1448, '反动', '麻将透', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1449, '反动', '麻醉弹', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1450, '反动', '麻醉狗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1451, '反动', '麻醉枪', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1452, '反动', '麻醉槍', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1453, '反动', '麻醉药', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1454, '反动', '麻醉藥', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1455, '反动', '台独', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1456, '反动', '台湾版假币', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1457, '反动', '台湾独立', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1458, '反动', '台湾国', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1459, '反动', '台湾应该独立', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1460, '反动', '台湾有权独立', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1461, '反动', '天灭中共', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1462, '反动', '中共帮凶', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1463, '反动', '中共保命', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1464, '反动', '中共裁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1465, '反动', '中共党文化', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1466, '反动', '中共腐败', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1467, '反动', '中共的血旗', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1468, '反动', '中共的罪恶', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1469, '反动', '中共帝国', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1470, '反动', '中共独裁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1471, '反动', '中共封锁', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1472, '反动', '中共封网', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1473, '反动', '中共腐败', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1474, '反动', '中共黑', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1475, '反动', '中共黑帮', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1476, '反动', '中共解体', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1477, '反动', '中共近期权力斗争', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1478, '反动', '中共恐惧', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1479, '反动', '中共权力斗争', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1480, '反动', '中共任用', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1481, '反动', '中共退党', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1482, '反动', '中共洗脑', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1483, '反动', '中共邪教', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1484, '反动', '中共邪毒素', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1485, '反动', '中共政治游戏', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1486, '暴恐', '福音会', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1487, '暴恐', '中国教徒', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1488, '暴恐', '统一教', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1489, '暴恐', '观音法门', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1490, '暴恐', '清海无上师', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1491, '暴恐', '盘古', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1492, '暴恐', '李洪志', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1493, '暴恐', '志洪李', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1494, '暴恐', '李宏志', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1495, '暴恐', '轮功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1496, '暴恐', '法轮', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1497, '暴恐', '轮法功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1498, '暴恐', '三去车仑', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1499, '暴恐', '氵去车仑', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1500, '暴恐', '发论工', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1501, '暴恐', '法x功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1502, '暴恐', '法o功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1503, '暴恐', '法0功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1504, '暴恐', '法一轮一功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1505, '暴恐', '轮子功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1506, '暴恐', '车仑工力', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1507, '暴恐', '法lun', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1508, '暴恐', 'fa轮', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1509, '暴恐', '法lg', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1510, '暴恐', 'flg', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1511, '暴恐', 'fl功', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1512, '暴恐', 'falungong', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1513, '暴恐', '大法弟子', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1514, '暴恐', '大纪元', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1515, '暴恐', 'dajiyuan', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1516, '暴恐', '明慧网', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1517, '暴恐', '明慧周报', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1518, '暴恐', '正见网', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1519, '暴恐', '新唐人', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1520, '暴恐', '伪火', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1521, '暴恐', '退党', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1522, '暴恐', 'tuidang', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1523, '暴恐', '退dang', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1524, '暴恐', '超越红墙', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1525, '暴恐', '自fen', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1526, '暴恐', '真善忍', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1527, '暴恐', '九评', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1528, '暴恐', '9评', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1529, '暴恐', '9ping', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1530, '暴恐', '九ping', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1531, '暴恐', 'jiuping', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1532, '暴恐', '藏字石', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1533, '暴恐', '集体自杀', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1534, '暴恐', '自sha', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1535, '暴恐', 'zi杀', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1536, '暴恐', 'suicide', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1537, '暴恐', 'titor', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1538, '暴恐', '逢8必灾', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1539, '暴恐', '逢八必灾', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1540, '暴恐', '逢9必乱', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1541, '暴恐', '逢九必乱', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1542, '暴恐', '朱瑟里诺', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1543, '暴恐', '根达亚文明', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1544, '暴恐', '诺查丹玛斯', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1545, '暴恐', '人类灭亡进程表', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1546, '暴恐', '按照马雅历法', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1547, '暴恐', '推背图', '0', '2021-07-06 13:51:44', '2021-07-06 13:51:44', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1548, '暴恐', '推bei图', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1549, '暴恐', '济世灵文', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1550, '暴恐', '诸世纪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1551, '暴恐', '电狗', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1552, '暴恐', '电话定位器', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1553, '暴恐', '电话拦截器', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1554, '暴恐', '电话窃听', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1555, '暴恐', '电话监', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1556, '暴恐', '电话交友', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1557, '暴恐', '电话追杀系统', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1558, '暴恐', '电击枪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1559, '暴恐', '电鸡', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1560, '暴恐', '电警棒', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1561, '暴恐', '枪出售', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1562, '暴恐', '枪的制', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1563, '暴恐', '枪货到', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1564, '暴恐', '枪决女犯', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1565, '暴恐', '枪模', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1566, '暴恐', '枪手', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1567, '暴恐', '枪销售', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1568, '暴恐', '枪械制', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1569, '暴恐', '枪子弹', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1570, '暴恐', '售步枪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1571, '暴恐', '售纯度', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1572, '暴恐', '售单管', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1573, '暴恐', '售弹簧刀', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1574, '暴恐', '售防身', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1575, '暴恐', '售狗子', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1576, '暴恐', '售虎头', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1577, '暴恐', '售火药', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1578, '暴恐', '售假币', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1579, '暴恐', '售健卫', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1580, '暴恐', '售军用', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1581, '暴恐', '售猎枪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1582, '暴恐', '售氯胺', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1583, '暴恐', '售麻醉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1584, '暴恐', '售枪支', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1585, '暴恐', '售热武', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1586, '暴恐', '售三棱', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1587, '暴恐', '售手枪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1588, '暴恐', '售五四', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1589, '暴恐', '售一元硬', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1590, '暴恐', '售子弹', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1591, '暴恐', '售左轮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1592, '暴恐', '亚砷（酸）酐', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1593, '暴恐', '亚砷酸钾', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1594, '暴恐', '亚砷酸钠', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1595, '暴恐', '亚硒酸', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1596, '暴恐', '亚硒酸二钠', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1597, '暴恐', '亚硒酸镁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1598, '暴恐', '亚硒酸钠', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1599, '暴恐', '亚硒酸氢钠', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1600, '暴恐', '亚硝酸乙酯', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1601, '暴恐', '亚硝酰乙氧', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1602, '暴恐', '氧化二丁基锡', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1603, '暴恐', '氧化汞', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1604, '暴恐', '氧化铊', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1605, '暴恐', '氧化亚铊', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1606, '暴恐', '氧氯化磷', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1607, '暴恐', '原装弹', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1608, '暴恐', '原子弹方法', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1609, '暴恐', '原子弹清单', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1610, '暴恐', '安眠酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1611, '暴恐', '代血浆', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1612, '暴恐', '普萘洛尔', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1613, '暴恐', '呋塞米', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1614, '暴恐', '西布曲明', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1615, '暴恐', 'testosterone', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1616, '暴恐', '胰岛素样生长因子', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1617, '暴恐', '促红细胞生成素', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1618, '暴恐', '地西泮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1619, '暴恐', '尼可刹米', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1620, '暴恐', '甲睾酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1621, '暴恐', 'adrenaline', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1622, '暴恐', 'erythropoietin', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1623, '暴恐', '地奈德', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1624, '暴恐', '莫达非尼', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1625, '暴恐', '氯噻嗪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1626, '暴恐', '苯巴比妥', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1627, '暴恐', '促性腺激素', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1628, '暴恐', '泼尼松', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1629, '暴恐', '麻黄草', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1630, '暴恐', '雄烯二醇', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1631, '暴恐', '地塞米松', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1632, '暴恐', 'tamoxifen', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1633, '暴恐', 'strychnine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1634, '暴恐', 'androst', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1635, '暴恐', '新型毒品', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1636, '暴恐', '杜冷丁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1637, '暴恐', '兴奋剂', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1638, '暴恐', 'mdma', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1639, '暴恐', '海洛因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1640, '暴恐', '海luo因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1641, '暴恐', 'heroin', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1642, '暴恐', 'diamorphine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1643, '暴恐', 'diacetylmorphine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL,
        NULL);
INSERT INTO `sensitive_words`
VALUES (1644, '暴恐', '鸦片', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1645, '暴恐', '阿芙蓉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1646, '暴恐', '咖啡因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1647, '暴恐', 'cocain', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1648, '暴恐', '三唑仑', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1649, '暴恐', '美沙酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1650, '暴恐', '麻古', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1651, '暴恐', 'k粉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1652, '暴恐', '凯他敏', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1653, '暴恐', 'ketamine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1654, '暴恐', '冰毒', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1655, '暴恐', '苯丙胺', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1656, '暴恐', 'cannabis', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1657, '暴恐', '大麻', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1658, '暴恐', '爱他死', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1659, '暴恐', '氯胺酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1660, '暴恐', 'benzodiazepines', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1661, '暴恐', '甲基安非他明', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1662, '暴恐', '安非他命', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1663, '暴恐', '吗啡', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1664, '民生', '打人', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1665, '民生', '打人', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1666, '民生', '拆迁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1667, '民生', '拆迁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1668, '民生', '纠纷', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1669, '民生', '纠纷', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1670, '民生', '盗窃', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1671, '民生', '盗窃', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1672, '民生', '安眠酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1673, '民生', '代药物毒品类：血浆', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1674, '民生', '普萘洛尔', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1675, '民生', '呋塞米', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1676, '民生', '西布曲明', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1677, '民生', 'testosterone', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1678, '民生', '胰岛素样生长因子', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1679, '民生', '促红细胞生成素', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1680, '民生', '地西泮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1681, '民生', '尼可刹米', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1682, '民生', '甲睾酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1683, '民生', 'adrenaline', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1684, '民生', 'erythropoietin', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1685, '民生', '地奈德', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1686, '民生', '莫达非尼', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1687, '民生', '氯噻嗪', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1688, '民生', '苯巴比妥', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1689, '民生', '促性腺激素', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1690, '民生', '泼尼松', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1691, '民生', '麻黄草', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1692, '民生', '雄烯二醇', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1693, '民生', '地塞米松', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1694, '民生', 'tamoxifen', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1695, '民生', 'strychnine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1696, '民生', 'androst', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1697, '民生', '新型毒品', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1698, '民生', '杜冷丁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1699, '民生', '兴奋剂', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1700, '民生', 'mdma', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1701, '民生', '海洛因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1702, '民生', '海luo因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1703, '民生', 'heroin', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1704, '民生', 'diamorphine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1705, '民生', 'diacetylmorphine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL,
        NULL);
INSERT INTO `sensitive_words`
VALUES (1706, '民生', '鸦片', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1707, '民生', '阿芙蓉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1708, '民生', '咖啡因', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1709, '民生', 'cocain', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1710, '民生', '三唑仑', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1711, '民生', '美沙酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1712, '民生', '麻古', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1713, '民生', 'k粉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1714, '民生', '凯他敏', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1715, '民生', 'ketamine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1716, '民生', '冰毒', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1717, '民生', '苯丙胺', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1718, '民生', 'cannabis', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1719, '民生', '大麻', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1720, '民生', '爱他死', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1721, '民生', '氯胺酮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1722, '民生', 'benzodiazepines', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1723, '民生', '甲基安非他明', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1724, '民生', '安非他命', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1725, '民生', '吗啡', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1726, '民生', 'morphine', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1727, '民生', '摇头丸', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1728, '民生', '迷药', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1729, '民生', '乖乖粉', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1730, '民生', 'narcotic', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1731, '民生', '麻醉药', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1732, '民生', '精神药品', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1733, '民生', '专业代理', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1734, '民生', '帮忙点一下', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1735, '民生', '帮忙点下', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1736, '民生', '请点击进入', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1737, '民生', '详情请进入', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1738, '民生', '私人侦探', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1739, '民生', '私家侦探', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1740, '民生', '针孔摄象', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1741, '民生', '调查婚外情', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1742, '民生', '信用卡提现', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1743, '民生', '无抵押贷款', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1744, '民生', '广告代理', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1745, '民生', '原音铃声', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1746, '民生', '借腹生子', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1747, '民生', '找个妈妈', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1748, '民生', '找个爸爸', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1749, '民生', '代孕妈妈', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1750, '民生', '代生孩子', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1751, '民生', '代开发票', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1752, '民生', '腾讯客服电话', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1753, '民生', '销售热线', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1754, '民生', '免费订购热线', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1755, '民生', '低价出售', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1756, '民生', '款到发货', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1757, '民生', '回复可见', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1758, '民生', '连锁加盟', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1759, '民生', '加盟连锁', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1760, '民生', '免费二级域名', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1761, '民生', '免费使用', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1762, '民生', '免费索取', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1763, '民生', '蚁力神', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1764, '民生', '婴儿汤', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1765, '民生', '售肾', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1766, '民生', '刻章办', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1767, '民生', '买小车', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1768, '民生', '套牌车', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1769, '民生', '玛雅网', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1770, '民生', '电脑传讯', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1771, '民生', '视频来源', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1772, '民生', '下载速度', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1773, '民生', '高清在线', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1774, '民生', '全集在线', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1775, '民生', '在线播放', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1776, '民生', 'txt下载', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1777, '民生', '六位qq', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1778, '民生', '6位qq', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1779, '民生', '位的qq', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1780, '民生', '个qb', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1781, '民生', '送qb', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1782, '民生', '用刀横向切腹', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1783, '民生', '完全自杀手册', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1784, '民生', '四海帮', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1785, '民生', '足球投注', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1786, '民生', '地下钱庄', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1787, '民生', '中国复兴党', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1788, '民生', '阿波罗网', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1789, '民生', '曾道人', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1790, '民生', '六合彩', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1791, '民生', '改卷内幕', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1792, '民生', '替考试', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1793, '民生', '隐形耳机', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1794, '民生', '出售答案', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1795, '民生', '考中答案', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1796, '民生', '答an', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1797, '民生', 'da案', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1798, '民生', '资金周转', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1799, '民生', '救市', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1800, '民生', '股市圈钱', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1801, '民生', '崩盘', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1802, '民生', '资金短缺', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1803, '民生', '证监会', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1804, '民生', '质押贷款', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1805, '民生', '小额贷款', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1806, '民生', '周小川', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1807, '民生', '刘明康', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1808, '民生', '尚福林', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1809, '民生', '孔丹', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1810, '民生', '汉芯造假', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1811, '民生', '杨树宽', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1812, '民生', '中印边界谈判结果', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1813, '民生', '喂奶门', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1814, '民生', '摸nai门', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1815, '民生', '酒瓶门', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1816, '民生', '脱裤门', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1817, '民生', '75事件', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1818, '民生', '乌鲁木齐', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1819, '民生', '新疆骚乱', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1820, '民生', '针刺', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1821, '民生', '打针', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1822, '民生', '食堂涨价', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1823, '民生', '饭菜涨价', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1824, '民生', 'h1n1', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1825, '民生', '瘟疫爆发', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1826, '民生', 'yangjia', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1827, '民生', 'y佳', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1828, '民生', 'yang佳', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1829, '民生', '杨佳', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1830, '民生', '杨j', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1831, '民生', '袭警', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1832, '民生', '杀警', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1833, '民生', '武侯祠', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1834, '民生', '川b26931', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1835, '民生', '贺立旗', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1836, '民生', '周正毅', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1837, '民生', 'px项目', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1838, '民生', '骂四川', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1839, '民生', '家l福', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1840, '民生', '家le福', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1841, '民生', '加了服', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1842, '民生', '麦当劳被砸', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1843, '民生', '豆腐渣', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1844, '民生', '这不是天灾', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1845, '民生', '龙小霞', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1846, '民生', '震其国土', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1847, '民生', 'yuce', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1848, '民生', '提前预测', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1849, '民生', '地震预测', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1850, '民生', '隐瞒地震', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1851, '民生', '李四光预测', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1852, '民生', '蟾蜍迁徙', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1853, '民生', '地震来得更猛烈', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1854, '民生', '八级地震毫无预报', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1855, '民生', '踩踏事故', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1856, '民生', '聂树斌', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1857, '民生', '万里大造林', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1858, '民生', '陈相贵', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1859, '民生', '张丹红', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1860, '民生', '尹方明', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1861, '民生', '李树菲', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1862, '民生', '王奉友', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1863, '民生', '零八奥运艰', '0', '2021-07-06 13:51:45', '2021-07-06 13:51:45', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1864, '民生', '惨奥', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1865, '民生', '奥晕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1866, '民生', '凹晕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1867, '民生', '懊运', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1868, '民生', '懊孕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1869, '民生', '奥孕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1870, '民生', '奥你妈的运', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1871, '民生', '反奥', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1872, '民生', '628事件', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1873, '民生', 'weng安', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1874, '民生', 'wengan', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1875, '民生', '翁安', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1876, '民生', '瓮安事件', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1877, '民生', '化工厂爆炸', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1878, '民生', '讨回工资', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1879, '民生', '代办发票', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1880, '民生', '代办各', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1881, '民生', '代办文', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1882, '民生', '代办学', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1883, '民生', '代办制', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1884, '民生', '代辦', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1885, '民生', '代表烦', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1886, '民生', '代开发票', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1887, '民生', '代開', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1888, '民生', '代考', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1889, '民生', '代理发票', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1890, '民生', '代理票据', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1891, '民生', '代您考', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1892, '民生', '代讨债', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1893, '民生', '代写毕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1894, '民生', '代写论文', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1895, '民生', '代孕', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1896, '民生', '代追债', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1897, '民生', '考后付款', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1898, '民生', '考机构', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1899, '民生', '考考邓', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1900, '民生', '考联盟', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1901, '民生', '考前答案', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1902, '民生', '考前付', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1903, '民生', '考前密卷', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1904, '民生', '考前预测', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1905, '民生', '考试,答案', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1906, '民生', '考试,作弊器', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1907, '民生', '考试包过', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1908, '民生', '考试保', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1909, '民生', '考试答案', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1910, '民生', '考试机构', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1911, '民生', '考试联盟', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1912, '民生', '考试枪', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1913, '民生', '考试作弊', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1914, '民生', '考试作弊器', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1915, '民生', '考研考中', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1916, '民生', '考中答案', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1917, '民生', '透视功能', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1918, '民生', '透视镜', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1919, '民生', '透视扑', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1920, '民生', '透视器', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1921, '民生', '透视眼睛', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1922, '民生', '透视眼镜', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1923, '民生', '透视药', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1924, '民生', '透视仪', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1925, '民生', '打死经过', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1926, '民生', '打死人', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1927, '民生', '打砸办公', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1928, '民生', '打砸抢', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1929, '民生', '安眠酮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1930, '民生', '代血浆', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1931, '民生', '普萘洛尔', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1932, '民生', '呋塞米', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1933, '民生', '西布曲明', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1934, '民生', 'testosterone', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1935, '民生', '胰岛素样生长因子', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1936, '民生', '促红细胞生成素', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1937, '民生', '地西泮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1938, '民生', '尼可刹米', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1939, '民生', '甲睾酮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1940, '民生', 'adrenaline', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1941, '民生', 'erythropoietin', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1942, '民生', '地奈德', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1943, '民生', '莫达非尼', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1944, '民生', '氯噻嗪', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1945, '民生', '苯巴比妥', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1946, '民生', '促性腺激素', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1947, '民生', '泼尼松', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1948, '民生', '麻黄草', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1949, '民生', '雄烯二醇', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1950, '民生', '地塞米松', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1951, '民生', 'tamoxifen', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1952, '民生', 'strychnine', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1953, '民生', 'androst', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1954, '民生', '新型毒品', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1955, '民生', '杜冷丁', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1956, '民生', '兴奋剂', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1957, '民生', 'mdma', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1958, '民生', '海洛因', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1959, '民生', '海luo因', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1960, '民生', 'heroin', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1961, '民生', 'diamorphine', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1962, '民生', 'diacetylmorphine', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL,
        NULL);
INSERT INTO `sensitive_words`
VALUES (1963, '民生', '鸦片', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1964, '民生', '阿芙蓉', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1965, '民生', '咖啡因', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1966, '民生', 'cocain', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1967, '民生', '三唑仑', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1968, '民生', '美沙酮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1969, '民生', '麻古', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1970, '民生', 'k粉', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1971, '民生', '凯他敏', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1972, '民生', 'ketamine', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1973, '民生', '冰毒', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1974, '民生', '苯丙胺', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1975, '民生', 'cannabis', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1976, '民生', '大麻', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1977, '民生', '爱他死', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1978, '民生', '氯胺酮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1979, '民生', 'benzodiazepines', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1980, '民生', '甲基安非他明', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1981, '民生', '安非他命', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1982, '民生', '吗啡', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1983, '民生', 'KC短信', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1984, '民生', 'KC嘉年华', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1985, '民生', '短信广告', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1986, '民生', '短信群发', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1987, '民生', '短信群发器', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1988, '民生', '小6灵通', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1989, '民生', '短信商务广告', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1990, '民生', '段录定', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1991, '民生', '无界浏览', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1992, '民生', '无界浏览器', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1993, '民生', '无界', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1994, '民生', '无网界', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1995, '民生', '无网界浏览', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1996, '民生', '无帮国', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1997, '民生', 'KC提示', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1998, '民生', 'KC网站', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (1999, '民生', 'UP8新势力', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2000, '民生', '白皮书', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2001, '民生', 'UP新势力', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2002, '民生', '移民', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2003, '民生', '易达网络卡', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2004, '民生', '安魂网', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2005, '民生', '罢工', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2006, '民生', '罢课', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2007, '民生', '纽崔莱七折', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2008, '民生', '手机复制', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2009, '民生', '手机铃声', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2010, '民生', '网关', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2011, '民生', '神通加持法', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2012, '民生', '全1球通', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2013, '民生', '如6意通', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2014, '民生', '清仓', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2015, '民生', '灵动卡', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2016, '民生', '答案卫星接收机', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2017, '民生', '高薪养廉', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2018, '民生', '考后付款', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2019, '民生', '佳静安定片', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2020, '民生', '航空母舰', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2021, '民生', '航空售票', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2022, '民生', '号码百事通', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2023, '民生', '考前发放', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2024, '民生', '成本价', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2025, '民生', '诚信通手机商城', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2026, '民生', '高利贷', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2027, '民生', '联4通', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2028, '民生', '黑庄', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2029, '民生', '黑手党', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2030, '民生', '黑车', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2031, '民生', '联通贵宾卡', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2032, '民生', '联总', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2033, '民生', '联总这声传单', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2034, '民生', '联总之声传单', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2035, '民生', '高息贷款', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2036, '民生', '高干子弟', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2037, '民生', '恭喜你的号码', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2038, '民生', '恭喜您的号码', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2039, '民生', '高干子女', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2040, '民生', '各个银行全称', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2041, '民生', '各种发票', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2042, '民生', '高官', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2043, '民生', '高官互调', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2044, '民生', '高官子女', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2045, '民生', '喝一送一', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2046, '民生', '卡号', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2047, '民生', '复制', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2048, '民生', '监听王', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2049, '民生', '传单', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2050, '民生', '旦科', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2051, '民生', '钓鱼岛', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2052, '民生', '钓鱼台', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2053, '民生', '当官靠后台', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2054, '民生', '党校安插亲信', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2055, '民生', '传九促三', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2056, '民生', '客户端非法字符', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2057, '民生', '刻章', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2058, '民生', '大麻树脂', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2059, '民生', '大麻油', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2060, '民生', '大法', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2061, '民生', '大法弟子', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2062, '民生', 'dpp大法', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2063, '民生', 'fa lun', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2064, '民生', 'falu', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2065, '民生', '发抡', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2066, '民生', '发抡功', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2067, '民生', '洗脑', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2068, '民生', '下法轮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2069, '民生', '发轮', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2070, '民生', '发伦', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2071, '民生', '发伦功', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2072, '民生', '发仑', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2073, '民生', '发沦', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2074, '民生', '发纶', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2075, '民生', '发论', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2076, '民生', '发论功', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2077, '民生', '发论公', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2078, '民生', '发正念', '0', '2021-07-06 13:51:46', '2021-07-06 13:51:46', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2079, '民生', '发囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2080, '民生', '发愣', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2081, '民生', '发瞟', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2082, '民生', '罚抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2083, '民生', '罚伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2084, '民生', '罚仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2085, '民生', '罚沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2086, '民生', '罚纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2087, '民生', '罚囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2088, '民生', '筏抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2089, '民生', '筏轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2090, '民生', '筏伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2091, '民生', '筏仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2092, '民生', '筏沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2093, '民生', '筏纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2094, '民生', '筏论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2095, '民生', '筏囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2096, '民生', '伐抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2097, '民生', '伐轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2098, '民生', '伐伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2099, '民生', '伐仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2100, '民生', '伐沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2101, '民生', '伐论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2102, '民生', '伐囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2103, '民生', '乏抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2104, '民生', '乏轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2105, '民生', '乏伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2106, '民生', '乏仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2107, '民生', '乏沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2108, '民生', '乏纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2109, '民生', '乏论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2110, '民生', '乏囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2111, '民生', '阀抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2112, '民生', '阀伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2113, '民生', '阀仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2114, '民生', '阀沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2115, '民生', '阀纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2116, '民生', '阀论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2117, '民生', '阀囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2118, '民生', '法 轮 功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2119, '民生', '法*功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2120, '民生', '法.轮.功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2121, '民生', '法L功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2122, '民生', '法lun功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2123, '民生', '法功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2124, '民生', '法会', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2125, '民生', '法抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2126, '民生', '法抡功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2127, '民生', '法轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2128, '民生', '法轮大法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2129, '民生', '法轮佛法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2130, '民生', '法轮功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2131, '民生', '法伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2132, '民生', '法仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2133, '民生', '法沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2134, '民生', '法纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2135, '民生', '法论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2136, '民生', '法十轮十功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2137, '民生', '法西斯', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2138, '民生', '法院', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2139, '民生', '法正', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2140, '民生', '法谪', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2141, '民生', '法谪功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2142, '民生', '法輪', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2143, '民生', '法囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2144, '民生', '法愣', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2145, '民生', '珐.輪功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2146, '民生', '珐抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2147, '民生', '珐轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2148, '民生', '珐伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2149, '民生', '珐仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2150, '民生', '珐沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2151, '民生', '五不', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2152, '民生', '五不争鸣论坛', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2153, '民生', '五出三进', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2154, '民生', '五套功法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2155, '民生', '邝锦文', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2156, '民生', '垡抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2157, '民生', '垡轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2158, '民生', '垡伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2159, '民生', '垡仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2160, '民生', '垡沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2161, '民生', '垡纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2162, '民生', '垡论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2163, '民生', '垡囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2164, '民生', '茳澤民', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2165, '民生', '荭志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2166, '民生', '闳志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2167, '民生', '闵维方', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2168, '民生', '氵去', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2169, '民生', '氵去车仑工力', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2170, '民生', '转法轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2171, '民生', '砝抡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2172, '民生', '砝轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2173, '民生', '砝伦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2174, '民生', '砝仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2175, '民生', '砝沦', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2176, '民生', '砝纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2177, '民生', '真、善、忍', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2178, '民生', '真理教', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2179, '民生', '真善美', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2180, '民生', '真善忍', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2181, '民生', '砝论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2182, '民生', '砝囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2183, '民生', '泓志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2184, '民生', '屙民', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2185, '民生', '珐纶', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2186, '民生', '珐论', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2187, '民生', '珐囵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2188, '民生', 'falun', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2189, '民生', 'Falundafa', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2190, '民生', 'fa轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2191, '民生', 'Flg', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2192, '民生', '弟子', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2193, '民生', '地下教会', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2194, '民生', '炼功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2195, '民生', '梦网洪志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2196, '民生', '轮大', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2197, '民生', '抡功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2198, '民生', '轮功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2199, '民生', '伦功', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2200, '民生', '摩门教', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2201, '民生', '三水法轮', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2202, '民生', '三唑仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2203, '民生', '天皇', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2204, '民生', '天怒', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2205, '民生', '天葬', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2206, '民生', '车臣', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2207, '民生', '达赖', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2208, '民生', '功法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2209, '民生', '讲法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2210, '民生', '基督', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2211, '民生', '基督教', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2212, '民生', '护法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2213, '民生', '回教', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2214, '民生', '教徒', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2215, '民生', '功友', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2216, '民生', '大师', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2217, '民生', '达赖喇嘛', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2218, '民生', '虹志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2219, '民生', '鸿志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2220, '民生', '洪传', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2221, '民生', '贯通两极法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2222, '民生', '光祖', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2223, '民生', '洪吟', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2224, '民生', '洪哲胜', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2225, '民生', '洪志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2226, '民生', '宏法', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2227, '民生', '观世音', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2228, '民生', '宏志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2229, '民生', '弘志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2230, '民生', '古兰经', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2231, '民生', '红志', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2232, '民生', '车库', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2233, '民生', '车仑', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2234, '民生', '经文', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2235, '贪腐', '腐败', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2236, '贪腐', '贪污', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2237, '贪腐', 'gcd', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2238, '贪腐', '共贪党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2239, '贪腐', 'gongchandang', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2240, '贪腐', '阿共', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2241, '贪腐', '共一产一党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2242, '贪腐', '产党共', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2243, '贪腐', '公产党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2244, '贪腐', '工产党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2245, '贪腐', '共c党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2246, '贪腐', '共x党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2247, '贪腐', '共铲', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2248, '贪腐', '供产', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2249, '贪腐', '共惨', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2250, '贪腐', '供铲党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2251, '贪腐', '供铲谠', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2252, '贪腐', '供铲裆', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2253, '贪腐', '共残党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2254, '贪腐', '共残主义', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2255, '贪腐', '共产主义的幽灵', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2256, '贪腐', '拱铲', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2257, '贪腐', '老共', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2258, '贪腐', '中珙', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2259, '贪腐', '中gong', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2260, '贪腐', 'gc党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2261, '贪腐', '贡挡', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2262, '贪腐', 'gong党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2263, '贪腐', 'g产', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2264, '贪腐', '狗产蛋', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2265, '贪腐', '共残裆', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2266, '贪腐', '恶党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2267, '贪腐', '邪党', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2268, '贪腐', '共产专制', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2269, '贪腐', '共产王朝', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2270, '贪腐', '裆中央', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2271, '贪腐', '土共', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2272, '贪腐', '土g', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2273, '贪腐', '共狗', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2274, '贪腐', 'g匪', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2275, '贪腐', '共匪', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2276, '贪腐', '仇共', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2277, '贪腐', '共产党腐败', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2278, '贪腐', '共产党专制', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2279, '贪腐', '共产党的报应', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2280, '贪腐', '共产党的末日', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2281, '贪腐', '共产党专制', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2282, '贪腐', 'communistparty', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2283, '贪腐', '症腐', '0', '2021-07-06 13:51:47', '2021-07-06 13:51:47', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2284, '贪腐', '政腐', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2285, '贪腐', '政付', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2286, '贪腐', '正府', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2287, '贪腐', '政俯', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2288, '贪腐', '政f', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2289, '贪腐', 'zhengfu', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2290, '贪腐', '政zhi', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2291, '贪腐', '挡中央', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2292, '贪腐', '档中央', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2293, '贪腐', '中国zf', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2294, '贪腐', '中央zf', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2295, '贪腐', '国wu院', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2296, '贪腐', '中华帝国', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2297, '贪腐', 'gong和', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2298, '贪腐', '大陆官方', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2299, '贪腐', '北京政权', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2300, '贪腐', '刘志军', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2301, '贪腐', '张曙', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2302, '贪腐', '刘志军', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2303, '贪腐', '买别墅', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2304, '贪腐', '玩女人', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2305, '贪腐', '贪20亿', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2306, '贪腐', '许宗衡', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2307, '贪腐', '贪财物', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2308, '贪腐', '李启红', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2309, '贪腐', '贪腐财富', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2310, '贪腐', '落马', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2311, '贪腐', '高官名单', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2312, '贪腐', '陈希同', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2313, '贪腐', '贪污', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2314, '贪腐', '玩忽职守', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2315, '贪腐', '有期徒刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2316, '贪腐', '陈良宇', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2317, '贪腐', '受贿罪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2318, '贪腐', '滥用职权', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2319, '贪腐', '有期徒刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2320, '贪腐', '没收个人财产', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2321, '贪腐', '成克杰', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2322, '贪腐', '死刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2323, '贪腐', '程维高', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2324, '贪腐', '严重违纪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2325, '贪腐', '开除党籍', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2326, '贪腐', '撤销职务', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2327, '贪腐', '刘方仁', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2328, '贪腐', '无期徒刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2329, '贪腐', '倪献策', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2330, '贪腐', '徇私舞弊', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2331, '贪腐', '梁湘', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2332, '贪腐', '以权谋私', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2333, '贪腐', '撤职。', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2334, '贪腐', '李嘉廷', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2335, '贪腐', '死刑缓期', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2336, '贪腐', '张国光', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2337, '贪腐', '韩桂芝', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2338, '贪腐', '宋平顺', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2339, '贪腐', '自杀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2340, '贪腐', '黄瑶', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2341, '贪腐', '双规', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2342, '贪腐', '陈绍基', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2343, '贪腐', '判处死刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2344, '贪腐', '剥夺政治权利终身', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2345, '贪腐', '没收个人全部财产', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2346, '贪腐', '石兆彬', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2347, '贪腐', '侯伍杰', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2348, '贪腐', '王昭耀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2349, '贪腐', '剥夺政治权利', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2350, '贪腐', '杜世成', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2351, '贪腐', '沈图', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2352, '贪腐', '叛逃美国', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2353, '贪腐', '罗云光', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2354, '贪腐', '起诉', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2355, '贪腐', '张辛泰', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2356, '贪腐', '李效时', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2357, '贪腐', '边少斌', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2358, '贪腐', '徐鹏航', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2359, '贪腐', '违纪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2360, '贪腐', '收受股票', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2361, '贪腐', '王乐毅', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2362, '贪腐', '李纪周', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2363, '贪腐', '郑光迪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2364, '贪腐', '田凤山。', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2365, '贪腐', '邱晓华', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2366, '贪腐', '郑筱萸', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2367, '贪腐', '孙鹤龄', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2368, '贪腐', '蓝田造假案', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2369, '贪腐', '于幼军', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2370, '贪腐', '留党察看', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2371, '贪腐', '何洪达', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2372, '贪腐', '朱志刚', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2373, '贪腐', '杨汇泉', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2374, '贪腐', '官僚主义', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2375, '贪腐', '徐炳松', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2376, '贪腐', '托乎提沙比尔', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2377, '贪腐', '王宝森', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2378, '贪腐', '经济犯罪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2379, '贪腐', '畏罪自杀。', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2380, '贪腐', '陈水文', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2381, '贪腐', '孟庆平', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2382, '贪腐', '胡长清', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2383, '贪腐', '朱川', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2384, '贪腐', '许运鸿', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2385, '贪腐', '丘广钟', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2386, '贪腐', '刘知炳', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2387, '贪腐', '丛福奎', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2388, '贪腐', '王怀忠', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2389, '贪腐', '巨额财产', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2390, '贪腐', '来源不明罪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2391, '贪腐', '李达昌', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2392, '贪腐', '刘长贵', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2393, '贪腐', '王钟麓', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2394, '贪腐', '阿曼哈吉', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2395, '贪腐', '付晓光', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2396, '贪腐', '自动辞', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2397, '贪腐', '刘克田', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2398, '贪腐', '吕德彬', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2399, '贪腐', '刘维明', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2400, '贪腐', '双开', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2401, '贪腐', '刘志华', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2402, '贪腐', '孙瑜', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2403, '贪腐', '李堂堂', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2404, '贪腐', '韩福才 青海', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2405, '贪腐', '欧阳德 广东', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2406, '贪腐', '韦泽芳 海南', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2407, '贪腐', '铁英 北京', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2408, '贪腐', '辛业江 海南', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2409, '贪腐', '于飞 广东', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2410, '贪腐', '姜殿武 河北', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2411, '贪腐', '秦昌典 重庆', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2412, '贪腐', '范广举 黑龙江', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2413, '贪腐', '张凯广东', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2414, '贪腐', '王厚宏海南', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2415, '贪腐', '陈维席安徽', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2416, '贪腐', '王有杰河南', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2417, '贪腐', '王武龙江苏', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2418, '贪腐', '米凤君吉林', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2419, '贪腐', '宋勇辽宁', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2420, '贪腐', '张家盟浙江', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2421, '贪腐', '马烈孙宁夏', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2422, '贪腐', '黄纪诚北京', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2423, '贪腐', '常征贵州', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2424, '贪腐', '王式惠重庆', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2425, '贪腐', '周文吉', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2426, '贪腐', '王庆录广西', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2427, '贪腐', '潘广田山东', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2428, '贪腐', '朱作勇甘肃', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2429, '贪腐', '孙善武河南', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2430, '贪腐', '宋晨光江西', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2431, '贪腐', '梁春禄广西政协', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2432, '贪腐', '鲁家善 中国交通', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2433, '贪腐', '金德琴 中信', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2434, '贪腐', '李大强 神华', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2435, '贪腐', '吴文英 纺织', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2436, '贪腐', '查克明 华能', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2437, '贪腐', '朱小华光大', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2438, '贪腐', '高严 国家电力', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2439, '贪腐', '王雪冰', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2440, '贪腐', '林孔兴', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2441, '贪腐', '刘金宝', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2442, '贪腐', '张恩照', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2443, '贪腐', '陈同海', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2444, '贪腐', '康日新', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2445, '贪腐', '王益', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2446, '贪腐', '张春江', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2447, '贪腐', '洪清源', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2448, '贪腐', '平义杰', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2449, '贪腐', '李恩潮', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2450, '贪腐', '孙小虹', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2451, '贪腐', '陈忠', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2452, '贪腐', '慕绥新', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2453, '贪腐', '田凤岐', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2454, '贪腐', '麦崇楷', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2455, '贪腐', '柴王群', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2456, '贪腐', '吴振汉', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2457, '贪腐', '张秋阳', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2458, '贪腐', '徐衍东', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2459, '贪腐', '徐发 黑龙江', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2460, '贪腐', '张宗海', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2461, '贪腐', '丁鑫发', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2462, '贪腐', '徐国健', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2463, '贪腐', '李宝金', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2464, '贪腐', '单平', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2465, '贪腐', '段义和', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2466, '贪腐', '荆福生', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2467, '贪腐', '陈少勇', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2468, '贪腐', '黄松有', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2469, '贪腐', '皮黔生', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2470, '贪腐', '王华元', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2471, '贪腐', '王守业', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2472, '贪腐', '刘连昆', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2473, '贪腐', '孙晋美', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2474, '贪腐', '邵松高', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2475, '贪腐', '肖怀枢', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2476, '贪腐', '刘广智 空军', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2477, '贪腐', '姬胜德 总参', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2478, '贪腐', '廖伯年 北京', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2479, '其他', '《动向》', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2480, '其他', '《争鸣》', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2481, '其他', '《中国民主》', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2482, '其他', '322攻台作战计划', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2483, '其他', '38集团军', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2484, '其他', '3D轮盘', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2485, '其他', '64大屠杀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2486, '其他', '6合彩', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2487, '其他', '70天大事记', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2488, '其他', '89学潮大屠杀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2489, '其他', '89学潮血腥屠杀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2490, '其他', 'BB弹', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2491, '其他', 'BB枪', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2492, '其他', 'fa轮', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2493, '其他', 'FL大法', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2494, '其他', 'jiuping', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2495, '其他', 'SIM卡复制器', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2496, '其他', 'SM用品', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2497, '其他', 't牌车', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2498, '其他', '阿宾', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2499, '其他', '阿凡提机', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2500, '其他', '挨了一炮', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2501, '其他', '爱国运动正名', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2502, '其他', '爱国者同盟网站', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2503, '其他', '爱液横流', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2504, '其他', '安眠藥', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2505, '其他', '案的准确', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2506, '其他', '暗访包', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2507, '其他', '八九民', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2508, '其他', '八九学', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2509, '其他', '八九政治', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2510, '其他', '把病人整', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2511, '其他', '把邓小平', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2512, '其他', '把学生整', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2513, '其他', '罢工门', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2514, '其他', '白黄牙签', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2515, '其他', '白小姐', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2516, '其他', '百家乐', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2517, '其他', '百乐二呓', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2518, '其他', '败培训', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2519, '其他', '拜大哥', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2520, '其他', '斑蝥', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2521, '其他', '办本科', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2522, '其他', '办理本科', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2523, '其他', '办理各种', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2524, '其他', '办理票据', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2525, '其他', '办理文凭', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2526, '其他', '办理真实', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2527, '其他', '办理证件', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2528, '其他', '办理证书', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2529, '其他', '办理资格', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2530, '其他', '办文凭', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2531, '其他', '办怔', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2532, '其他', '办证', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2533, '其他', '半刺刀', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2534, '其他', '辦毕业', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2535, '其他', '辦證', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2536, '其他', '帮人怀孕', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2537, '其他', '谤罪获刑', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2538, '其他', '磅解码器', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2539, '其他', '磅遥控器', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2540, '其他', '包办色情娱乐服务', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2541, '其他', '包青天机', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2542, '其他', '包养', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2543, '其他', '宝在甘肃修', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2544, '其他', '保过答案', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2545, '其他', '报复执法', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2546, '其他', '报码', '0', '2021-07-06 13:51:48', '2021-07-06 13:51:48', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2547, '其他', '暴菊', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2548, '其他', '暴力袭警', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2549, '其他', '暴力执法', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2550, '其他', '爆发骚', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2551, '其他', '爆菊', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2552, '其他', '北姑', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2553, '其他', '北京帮', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2554, '其他', '北京独立', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2555, '其他', '北京风波', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2556, '其他', '北京黑幕', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2557, '其他', '北京市委黑幕', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2558, '其他', '北京政坛清华名人', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2559, '其他', '北美巡回讲法', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2560, '其他', '北省委门', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2561, '其他', '被指抄袭', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2562, '其他', '被中共', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2563, '其他', '本无码', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2564, '其他', '避孕膜', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2565, '其他', '鞭满', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2566, '其他', '变牌', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2567, '其他', '变牌绝', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2568, '其他', '辩词与梦', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2569, '其他', '冰毒', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2570, '其他', '冰火毒', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2571, '其他', '冰火佳', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2572, '其他', '冰火九重', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2573, '其他', '冰火漫', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2574, '其他', '冰淫传', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2575, '其他', '冰在火上', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2576, '其他', '波胆', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2577, '其他', '波推龙', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2578, '其他', '博彩', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2579, '其他', '博彩娱', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2580, '其他', '博会暂停', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2581, '其他', '博园区伪', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2582, '其他', '不查都', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2583, '其他', '不查全', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2584, '其他', '布卖淫女', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2585, '其他', '部忙组阁', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2586, '其他', '部是这样', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2587, '其他', '才知道只生', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2588, '其他', '财众科技', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2589, '其他', '采花堂', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2590, '其他', '彩宝', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2591, '其他', '彩票', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2592, '其他', '彩票选号机', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2593, '其他', '彩票预测', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2594, '其他', '踩踏事', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2595, '其他', '苍山兰', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2596, '其他', '苍蝇水', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2597, '其他', '操了嫂', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2598, '其他', '操你妈', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2599, '其他', '操嫂子', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2600, '其他', '厕奴', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2601, '其他', '策没有不', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2602, '其他', '插屁屁', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2603, '其他', '察象蚂', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2604, '其他', '拆迁灭', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2605, '其他', '禅密功', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2606, '其他', '长狗', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2607, '其他', '车仑大法', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2608, '其他', '车牌隐', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2609, '其他', '车用电子狗', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2610, '其他', '成佛做主', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2611, '其他', '城管灭', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2612, '其他', '惩公安', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2613, '其他', '惩贪难', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2614, '其他', '持枪证', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2615, '其他', '充气娃', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2616, '其他', '冲凉死', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2617, '其他', '抽着大中', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2618, '其他', '抽着芙蓉', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2619, '其他', '出成绩付', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2620, '其他', '出售发票', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2621, '其他', '出售假币', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2622, '其他', '出售军', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2623, '其他', '出售器官', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2624, '其他', '出售肾', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2625, '其他', '穿透仪器', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2626, '其他', '传九退三', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2627, '其他', '传送答案', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2628, '其他', '吹箭', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2629, '其他', '春宫', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2630, '其他', '春宫图', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2631, '其他', '春水横溢', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2632, '其他', '春药', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2633, '其他', '纯度白', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2634, '其他', '纯度黄', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2635, '其他', '慈悲功', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2636, '其他', '次通过考', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2637, '其他', '催眠水', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2638, '其他', '催情粉', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2639, '其他', '催情药', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2640, '其他', '催情藥', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2641, '其他', '挫仑', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2642, '其他', '达毕业证', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2643, '其他', '达赖', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2644, '其他', '达米宣教会', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2645, '其他', '答案包', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2646, '其他', '答案提供', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2647, '其他', '打标语', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2648, '其他', '打错门', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2649, '其他', '打飞机专', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2650, '其他', '大法弟子', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2651, '其他', '大法轮', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2652, '其他', '大法修炼者', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2653, '其他', '大鸡巴', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2654, '其他', '大雞巴', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2655, '其他', '大纪元', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2656, '其他', '大妓院', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2657, '其他', '大揭露', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2658, '其他', '大奶子', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2659, '其他', '大批贪官', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2660, '其他', '大肉棒', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2661, '其他', '大学暴动', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2662, '其他', '大圆满法', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2663, '其他', '大庄', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2664, '其他', '大嘴歌', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2665, '其他', '贷借款', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2666, '其他', '贷开', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2667, '其他', '戴海静', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2668, '其他', '弹种', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2669, '其他', '当代七整', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2670, '其他', '当官要精', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2671, '其他', '当官在于', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2672, '其他', '党风日下', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2673, '其他', '党棍', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2674, '其他', '党后萎', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2675, '其他', '党禁', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2676, '其他', '党内权力', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2677, '其他', '党内权争', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2678, '其他', '党内危机', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2679, '其他', '党内言事潮', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2680, '其他', '党前干劲', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2681, '其他', '荡妇', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2682, '其他', '刀架保安', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2683, '其他', '导爆索', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2684, '其他', '导的情人', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2685, '其他', '导叫失', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2686, '其他', '导人的最', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2687, '其他', '导人最', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2688, '其他', '导小商', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2689, '其他', '到花心', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2690, '其他', '盗号', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2691, '其他', '盗取qq', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2692, '其他', '盗取密码', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2693, '其他', '得财兼', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2694, '其他', '的同修', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2695, '其他', '灯草和', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2696, '其他', '等级證', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2697, '其他', '等人老百', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2698, '其他', '等人是老', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2699, '其他', '等人手术', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2700, '其他', '邓爷爷转', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2701, '其他', '邓颖超日记', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2702, '其他', '邓玉娇', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2703, '其他', '抵制北京奥运', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2704, '其他', '地磅解码器', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2705, '其他', '地磅遥控器', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2706, '其他', '地产之歌', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2707, '其他', '地下先烈', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2708, '其他', '帝国之梦', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2709, '其他', '递进民主', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2710, '其他', '递纸死', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2711, '其他', '第二首都', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2712, '其他', '第三次世界大战', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2713, '其他', '第五代红人', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2714, '其他', '第五代接班梯队', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2715, '其他', '颠覆,共产党', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2716, '其他', '颠覆,共产主义', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2717, '其他', '颠覆,胡锦涛', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2718, '其他', '颠覆,温家宝', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2719, '其他', '颠覆,中共', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2720, '其他', '颠覆,朱镕基', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2721, '其他', '点金商务', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2722, '其他', '点数优惠', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2723, '其他', '电子狗导航手机', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2724, '其他', '甸果敢', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2725, '其他', '蝶舞按', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2726, '其他', '丁香社', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2727, '其他', '丁子霖', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2728, '其他', '顶花心', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2729, '其他', '顶贴机', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2730, '其他', '顶帖器', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2731, '其他', '东北独立', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2732, '其他', '东方闪电', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2733, '其他', '东复活', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2734, '其他', '东京热', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2735, '其他', '东突', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2736, '其他', '东伊运', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2737, '其他', '東京熱', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2738, '其他', '董元辰', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2739, '其他', '洞小口紧', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2740, '其他', '都当警', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2741, '其他', '都当小姐', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2742, '其他', '都进中央', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2743, '其他', '毒蛇钻', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2744, '其他', '独夫民贼', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2745, '其他', '独立台湾', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2746, '其他', '赌博机', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2747, '其他', '赌恒指', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2748, '其他', '赌具', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2749, '其他', '赌球', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2750, '其他', '赌球网', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2751, '其他', '短信截', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2752, '其他', '短信猫', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2753, '其他', '短信群发', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2754, '其他', '短信投票业务', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2755, '其他', '段桂清', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2756, '其他', '对共产党清算', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2757, '其他', '对日强硬', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2758, '其他', '多党执政', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2759, '其他', '多美康', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2760, '其他', '躲猫猫', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2761, '其他', '俄罗斯轮盘', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2762, '其他', '俄羅斯', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2763, '其他', '恶党', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2764, '其他', '恶搞人民币', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2765, '其他', '恶警', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2766, '其他', '恶势力操', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2767, '其他', '恶势力插', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2768, '其他', '恩氟烷', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2769, '其他', '儿园惨', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2770, '其他', '儿园砍', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2771, '其他', '儿园杀', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2772, '其他', '儿园凶', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2773, '其他', '二奶大', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2774, '其他', '二十四事件', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2775, '其他', '发仑da发', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2776, '其他', '发伦工', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2777, '其他', '发轮功', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2778, '其他', '发轮功陈果', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2779, '其他', '发牌绝', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2780, '其他', '发票出', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2781, '其他', '发票代', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2782, '其他', '发票代开', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2783, '其他', '发票销', '0', '2021-07-06 13:51:49', '2021-07-06 13:51:49', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2784, '其他', '发贴工具', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2785, '其他', '发贴机', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2786, '其他', '發票', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2787, '其他', '法0功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2788, '其他', '法O功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2789, '其他', '法车仑', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2790, '其他', '法功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2791, '其他', '法拉盛', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2792, '其他', '法拉盛缅街', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2793, '其他', '法力像佛', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2794, '其他', '法仑功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2795, '其他', '法伦', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2796, '其他', '法伦功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2797, '其他', '法轮', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2798, '其他', '法论功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2799, '其他', '法能功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2800, '其他', '法维权', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2801, '其他', '法西藏主义', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2802, '其他', '法一轮', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2803, '其他', '法院给废', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2804, '其他', '法正乾', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2805, '其他', '珐(工力)学', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2806, '其他', '反测速雷', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2807, '其他', '反对共产党', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2808, '其他', '反对共产主义', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2809, '其他', '反腐总攻', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2810, '其他', '反攻大陆', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2811, '其他', '反共传单', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2812, '其他', '反共言论', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2813, '其他', '反雷达测', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2814, '其他', '反雷达测速', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2815, '其他', '反屏蔽', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2816, '其他', '反社会主义', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2817, '其他', '反中共黑色暴力', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2818, '其他', '范燕琼', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2819, '其他', '方迷香', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2820, '其他', '芳香型智悟气功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2821, '其他', '防电子眼', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2822, '其他', '防身枪', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2823, '其他', '防身药水', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2824, '其他', '房贷给废', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2825, '其他', '仿真枪', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2826, '其他', '仿真手枪', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2827, '其他', '仿真证', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2828, '其他', '费私服', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2829, '其他', '封锁消', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2830, '其他', '佛怀煽仇录', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2831, '其他', '佛同修', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2832, '其他', '夫妻交换', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2833, '其他', '福尔马林', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2834, '其他', '福娃的預', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2835, '其他', '福娃頭上', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2836, '其他', '福香巴', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2837, '其他', '府包庇', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2838, '其他', '府集中领', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2839, '其他', '妇销魂', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2840, '其他', '附送枪', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2841, '其他', '复印件生', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2842, '其他', '复印件制', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2843, '其他', '赴港生子', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2844, '其他', '富民穷', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2845, '其他', '富婆给废', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2846, '其他', '改号软件', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2847, '其他', '干扰器', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2848, '其他', '感扑克', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2849, '其他', '冈本真', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2850, '其他', '肛交', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2851, '其他', '肛门是邻', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2852, '其他', '岡本真', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2853, '其他', '钢针狗', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2854, '其他', '钢珠枪', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2855, '其他', '港澳博球', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2856, '其他', '港馬會', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2857, '其他', '港鑫華', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2858, '其他', '高就在政', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2859, '其他', '高考,答案', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2860, '其他', '高考黑', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2861, '其他', '高莺莺', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2862, '其他', '搞媛交', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2863, '其他', '告长期', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2864, '其他', '告洋状', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2865, '其他', '格证考试', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2866, '其他', '蛤蟆转世', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2867, '其他', '个人圆满说', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2868, '其他', '个四小码', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2869, '其他', '个邪的党', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2870, '其他', '各类考试', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2871, '其他', '各类文凭', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2872, '其他', '根浴', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2873, '其他', '跟帖器', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2874, '其他', '跟踪器', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2875, '其他', '工程吞得', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2876, '其他', '工力人', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2877, '其他', '弓弩', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2878, '其他', '公安错打', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2879, '其他', '公开小姐', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2880, '其他', '攻官小姐', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2881, '其他', '供应发票', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2882, '其他', '共产主义黑皮书', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2883, '其他', '共狗', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2884, '其他', '共和国2049', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2885, '其他', '共青背景', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2886, '其他', '共青团派', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2887, '其他', '共王储', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2888, '其他', '共字玄机', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2889, '其他', '狗粮', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2890, '其他', '狗娘养的', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2891, '其他', '狗屁专家', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2892, '其他', '狗日的', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2893, '其他', '狗友', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2894, '其他', '古方迷香', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2895, '其他', '鼓动一些', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2896, '其他', '乖乖粉', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2897, '其他', '观音法门', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2898, '其他', '官匪一家', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2899, '其他', '官商勾', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2900, '其他', '官也不容', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2901, '其他', '官因发帖', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2902, '其他', '光学真题', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2903, '其他', '广安事件', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2904, '其他', '广场事件', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2905, '其他', '跪真相', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2906, '其他', '滚圆大乳', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2907, '其他', '国办发', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2908, '其他', '国际投注', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2909, '其他', '国家妓', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2910, '其他', '国家软弱', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2911, '其他', '国家吞得', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2912, '其他', '国库折', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2913, '其他', '国统纲领', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2914, '其他', '国统会', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2915, '其他', '国一九五七', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2916, '其他', '國內美', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2917, '其他', '哈狗帮', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2918, '其他', '哈药直销', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2919, '其他', '海访民', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2920, '其他', '海洛因', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2921, '其他', '豪圈钱', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2922, '其他', '号屏蔽器', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2923, '其他', '合彩', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2924, '其他', '和狗交', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2925, '其他', '和狗性', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2926, '其他', '和狗做', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2927, '其他', '黑火药的', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2928, '其他', '黑社会', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2929, '其他', '红病历', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2930, '其他', '红床', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2931, '其他', '红客联盟', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2932, '其他', '红色恐怖', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2933, '其他', '红外,透视', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2934, '其他', '红外透视', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2935, '其他', '洪志', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2936, '其他', '紅色恐', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2937, '其他', '胡的接班人', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2938, '其他', '胡江风云', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2939, '其他', '胡江关系', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2940, '其他', '胡江内斗', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2941, '其他', '胡江曾', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2942, '其他', '胡江争斗', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2943, '其他', '胡紧掏', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2944, '其他', '胡紧套', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2945, '其他', '胡锦涛,腐败', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2946, '其他', '胡錦濤', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2947, '其他', '胡进涛', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2948, '其他', '胡派军委', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2949, '其他', '胡派人马', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2950, '其他', '胡适眼', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2951, '其他', '胡下台', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2952, '其他', '胡耀邦', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2953, '其他', '湖淫娘', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2954, '其他', '虎头猎', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2955, '其他', '护卫团', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2956, '其他', '华藏功', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2957, '其他', '华国锋', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2958, '其他', '华门开', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2959, '其他', '华闻', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2960, '其他', '化学扫盲', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2961, '其他', '划老公', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2962, '其他', '话在肉身显现', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2963, '其他', '还会吹萧', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2964, '其他', '还看锦涛', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2965, '其他', '环球证件', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2966, '其他', '换妻', '0', '2021-07-06 13:51:50', '2021-07-06 13:51:50', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2967, '其他', '皇冠投注', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2968, '其他', '皇家轮盘', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2969, '其他', '黄冰', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2970, '其他', '黄菊遗孀', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2971, '其他', '黄色,电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2972, '其他', '黄色电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2973, '其他', '黄色小电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2974, '其他', '回汉冲突', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2975, '其他', '回民暴动', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2976, '其他', '回民猪', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2977, '其他', '回忆六四', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2978, '其他', '昏药', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2979, '其他', '浑圆豪乳', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2980, '其他', '活不起', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2981, '其他', '活体取肾', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2982, '其他', '活摘器官', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2983, '其他', '火车也疯', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2984, '其他', '机定位器', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2985, '其他', '机号定', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2986, '其他', '机号卫', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2987, '其他', '机卡密', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2988, '其他', '机屏蔽器', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2989, '其他', '鸡巴', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2990, '其他', '积克馆', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2991, '其他', '基本靠吼', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2992, '其他', '基督灵恩布道团', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2993, '其他', '绩过后付', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2994, '其他', '激光气', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2995, '其他', '激光汽', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2996, '其他', '激流中国', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2997, '其他', '激情,电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2998, '其他', '激情,图片', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (2999, '其他', '激情电', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3000, '其他', '激情电话', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3001, '其他', '激情电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3002, '其他', '激情短', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3003, '其他', '激情交友', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3004, '其他', '激情妹', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3005, '其他', '激情炮', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3006, '其他', '激情视频', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3007, '其他', '激情小电影', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3008, '其他', '级办理', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3009, '其他', '级答案', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3010, '其他', '急需嫖', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3011, '其他', '疾病业债说', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3012, '其他', '集体打砸', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3013, '其他', '集体腐', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3014, '其他', '集体抗议', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3015, '其他', '挤乳汁', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3016, '其他', '擠乳汁', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3017, '其他', '记号扑克', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3018, '其他', '纪念达赖喇嘛流亡49周年', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3019, '其他', '纪念文革', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3020, '其他', '妓女', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3021, '其他', '妓女的口号', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3022, '其他', '寂寞少妇', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3023, '其他', '加油机干扰器', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3024, '其他', '佳静安定', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3025, '其他', '佳静安定片', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3026, '其他', '家一样饱', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3027, '其他', '家属被打', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3028, '其他', '甲虫跳', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3029, '其他', '甲流了', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3030, '其他', '假币出售', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3031, '其他', '假发票', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3032, '其他', '假文凭', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3033, '其他', '假证件', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3034, '其他', '奸成瘾', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3035, '其他', '奸杀', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3036, '其他', '兼职上门', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3037, '其他', '监听器', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3038, '其他', '监听头', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3039, '其他', '监听王', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3040, '其他', '监狱管理局', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3041, '其他', '监狱里的斗争', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3042, '其他', '简易炸', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3043, '其他', '贱货', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3044, '其他', '贱人', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3045, '其他', '江z民', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3046, '其他', '疆獨', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3047, '其他', '疆独', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3048, '其他', '讲法传功', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3049, '其他', '蒋彦永', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3050, '其他', '叫鸡', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3051, '其他', '叫自慰', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3052, '其他', '揭贪难', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3053, '其他', '姐包夜', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3054, '其他', '姐服务', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3055, '其他', '姐兼职', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3056, '其他', '姐上门', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3057, '其他', '解码开锁', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3058, '其他', '解密软件', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3059, '其他', '解体的命运', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3060, '其他', '解体中共', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3061, '其他', '金扎金', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3062, '其他', '金钟气', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3063, '其他', '津大地震', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3064, '其他', '津地震', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3065, '其他', '津人治津', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3066, '其他', '进来的罪', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3067, '其他', '禁书', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3068, '其他', '禁网禁片', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3069, '其他', '京地震', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3070, '其他', '京夫子', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3071, '其他', '京要地震', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3072, '其他', '经典谎言', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3073, '其他', '精子射在', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3074, '其他', '警察被', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3075, '其他', '警察的幌', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3076, '其他', '警察殴打', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3077, '其他', '警察说保', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3078, '其他', '警车雷达', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3079, '其他', '警方包庇', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3080, '其他', '警匪一家', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3081, '其他', '警徽', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3082, '其他', '警民冲突', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3083, '其他', '警用品', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3084, '其他', '径步枪', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3085, '其他', '敬请忍', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3086, '其他', '靖国神社', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3087, '其他', '究生答案', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3088, '其他', '九龙论坛', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3089, '其他', '九评共', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3090, '其他', '九十三运动', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3091, '其他', '酒象喝汤', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3092, '其他', '酒像喝汤', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3093, '其他', '救度众生说', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3094, '其他', '就爱插', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3095, '其他', '就要色', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3096, '其他', '菊暴', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3097, '其他', '菊爆', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3098, '其他', '菊花洞', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3099, '其他', '举国体', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3100, '其他', '巨乳', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3101, '其他', '据说全民', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3102, '其他', '绝食声', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3103, '其他', '军长发威', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3104, '其他', '军刺', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3105, '其他', '军品特', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3106, '其他', '军用手', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3107, '其他', '军转', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3108, '其他', '卡辛纳大道和三福大道交界处', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3109, '其他', '开苞', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3110, '其他', '开邓选', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3111, '其他', '开平,轮奸', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3112, '其他', '开平,受辱', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3113, '其他', '开锁工具', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3114, '其他', '开天目', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3115, '其他', '開碼', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3116, '其他', '開票', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3117, '其他', '砍杀幼', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3118, '其他', '砍伤儿', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3119, '其他', '看JJ', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3120, '其他', '康没有不', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3121, '其他', '康生丹', '0', '2021-07-06 13:51:51', '2021-07-06 13:51:51', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3122, '其他', '康跳楼', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3123, '其他', '抗议磁悬浮', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3124, '其他', '抗议中共当局', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3125, '其他', '磕彰', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3126, '其他', '克分析', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3127, '其他', '克千术', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3128, '其他', '克透视', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3129, '其他', '嗑药', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3130, '其他', '空和雅典', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3131, '其他', '空中民主墙', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3132, '其他', '孔摄像', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3133, '其他', '恐共', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3134, '其他', '控诉世博', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3135, '其他', '控制媒', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3136, '其他', '口交', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3137, '其他', '口手枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3138, '其他', '口淫', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3139, '其他', '骷髅死', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3140, '其他', '矿难不公', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3141, '其他', '昆仑女神功', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3142, '其他', '拉帮游说', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3143, '其他', '拉登说', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3144, '其他', '拉开水晶', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3145, '其他', '拉票贿选', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3146, '其他', '拉萨僧人接连抗议', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3147, '其他', '拉线飞机', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3148, '其他', '来福猎', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3149, '其他', '拦截器', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3150, '其他', '狼全部跪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3151, '其他', '浪穴', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3152, '其他', '老虎机', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3153, '其他', '乐透码', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3154, '其他', '雷管', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3155, '其他', '雷人女官', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3156, '其他', '类准确答', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3157, '其他', '黎阳平', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3158, '其他', '李大轮子', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3159, '其他', '李红痔', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3160, '其他', '李洪X', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3161, '其他', '李洪志', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3162, '其他', '李鹏', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3163, '其他', '李伟信的笔供', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3164, '其他', '李晓英', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3165, '其他', '李咏曰', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3166, '其他', '理各种证', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3167, '其他', '理是影帝', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3168, '其他', '理证件', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3169, '其他', '理做帐报', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3170, '其他', '力骗中央', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3171, '其他', '力月西', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3172, '其他', '历史的伤口', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3173, '其他', '丽媛离', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3174, '其他', '利他林', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3175, '其他', '连发手', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3176, '其他', '联盟党', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3177, '其他', '聯繫電', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3178, '其他', '练功群众', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3179, '其他', '炼大法', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3180, '其他', '两岸才子', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3181, '其他', '两会代', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3182, '其他', '两会又三', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3183, '其他', '聊斋艳', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3184, '其他', '了件渔袍', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3185, '其他', '猎好帮手', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3186, '其他', '猎枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3187, '其他', '猎枪销', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3188, '其他', '临震预报', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3189, '其他', '领导忽悠百姓叫号召', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3190, '其他', '领土拿', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3191, '其他', '流亡藏人', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3192, '其他', '流血事件', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3193, '其他', '留四进三', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3194, '其他', '六HE彩', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3195, '其他', '六代接班人', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3196, '其他', '六合采', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3197, '其他', '六合彩', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3198, '其他', '六死', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3199, '其他', '六四内部日记', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3200, '其他', '六四事', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3201, '其他', '六四事件', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3202, '其他', '六四受难者家属证辞', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3203, '其他', '六四信息', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3204, '其他', '六四资料馆', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3205, '其他', '六月联盟', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3206, '其他', '龙虎斗', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3207, '其他', '龙湾事件', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3208, '其他', '隆手指', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3209, '其他', '漏题', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3210, '其他', '陆封锁', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3211, '其他', '陆同修', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3212, '其他', '旅游新报', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3213, '其他', '氯胺酮', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3214, '其他', '轮手枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3215, '其他', '轮子小报', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3216, '其他', '论文代', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3217, '其他', '罗干', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3218, '其他', '罗斯小姐', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3219, '其他', '落霞缀', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3220, '其他', '妈了个逼', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3221, '其他', '麻古', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3222, '其他', '麻果配', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3223, '其他', '麻醉乙醚', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3224, '其他', '蟆叫专家', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3225, '其他', '买春', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3226, '其他', '买断', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3227, '其他', '买官卖官', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3228, '其他', '买枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3229, '其他', '买肾', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3230, '其他', '买真枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3231, '其他', '卖QQ号码', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3232, '其他', '卖春', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3233, '其他', '卖地财政', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3234, '其他', '卖发票', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3235, '其他', '卖枪', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3236, '其他', '卖身', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3237, '其他', '卖肾', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3238, '其他', '卖血', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3239, '其他', '卖银行卡', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3240, '其他', '卖自考', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3241, '其他', '漫步丝', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3242, '其他', '忙爱国', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3243, '其他', '猫眼工具', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3244, '其他', '毛时代大饥荒揭秘', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3245, '其他', '毛一鲜', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3246, '其他', '毛泽东复活', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3247, '其他', '毛泽东侄子', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3248, '其他', '毛贼', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3249, '其他', '毛主席的嫡孙', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3250, '其他', '媒体封锁', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3251, '其他', '每周一死', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3252, '其他', '门按摩', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3253, '其他', '门保健', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3254, '其他', '门徒会', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3255, '其他', '門服務', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3256, '其他', '氓培训', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3257, '其他', '蒙古回归', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3258, '其他', '蒙汗药', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3259, '其他', '蒙汗药粉', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3260, '其他', '蜜穴', '0', '2021-07-06 13:51:52', '2021-07-06 13:51:52', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3261, '其他', '免电灯', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3262, '其他', '免费电影', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3263, '其他', '灭共', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3264, '其他', '灭绝罪', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3265, '其他', '灭中共', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3266, '其他', '民储害', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3267, '其他', '民九亿商', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3268, '其他', '民抗议', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3269, '其他', '民殇', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3270, '其他', '民运', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3271, '其他', '民运分子', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3272, '其他', '民主不能等待', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3273, '其他', '民主还专政', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3274, '其他', '民主女神', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3275, '其他', '民族问题', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3276, '其他', '明hui', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3277, '其他', '明慧网', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3278, '其他', '明镜出版社', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3279, '其他', '铭记印尼', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3280, '其他', '摩小姐', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3281, '其他', '魔难论', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3282, '其他', '末世劫难', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3283, '其他', '末世论', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3284, '其他', '牟新生', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3285, '其他', '母乳家', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3286, '其他', '母子乱伦', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3287, '其他', '木齐针', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3288, '其他', '幕没有不', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3289, '其他', '幕前戲', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3290, '其他', '内射', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3291, '其他', '内争人权', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3292, '其他', '男妓', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3293, '其他', '男奴', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3294, '其他', '南充针', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3295, '其他', '嫩逼', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3296, '其他', '嫩穴', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3297, '其他', '嫩阴', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3298, '其他', '泥马之歌', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3299, '其他', '你的西域', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3300, '其他', '拟涛哥', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3301, '其他', '娘两腿之间', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3302, '其他', '鸟巢最少死', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3303, '其他', '妞上门', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3304, '其他', '纽扣摄像机', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3305, '其他', '浓精', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3306, '其他', '怒的志愿', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3307, '其他', '女被人家搞', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3308, '其他', '女激情', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3309, '其他', '女技师', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3310, '其他', '女奴', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3311, '其他', '女人费', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3312, '其他', '女人和狗', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3313, '其他', '女任职名', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3314, '其他', '女上门', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3315, '其他', '女神教', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3316, '其他', '女士服务', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3317, '其他', '女伟哥', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3318, '其他', '女優', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3319, '其他', '鸥之歌', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3320, '其他', '拍肩,药', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3321, '其他', '拍肩神', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3322, '其他', '拍肩神药', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3323, '其他', '拍肩型', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3324, '其他', '拍肩醉迷药', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3325, '其他', '牌技网', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3326, '其他', '派系斗争', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3327, '其他', '盘古乐队', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3328, '其他', '盘口', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3329, '其他', '炮的小蜜', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3330, '其他', '跑官要官', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3331, '其他', '泡友', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3332, '其他', '陪考枪', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3333, '其他', '陪聊', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3334, '其他', '赔率', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3335, '其他', '配有消', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3336, '其他', '喷尿', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3337, '其他', '嫖俄罗', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3338, '其他', '嫖鸡', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3339, '其他', '嫖妓', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3340, '其他', '平惨案', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3341, '其他', '平反六四', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3342, '其他', '平叫到床', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3343, '其他', '仆不怕饮', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3344, '其他', '普提功', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3345, '其他', '普通嘌', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3346, '其他', '期货配', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3347, '其他', '奇迹的黄', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3348, '其他', '奇淫散', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3349, '其他', '骑单车出', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3350, '其他', '气狗', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3351, '其他', '气枪', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3352, '其他', '汽车解码器', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3353, '其他', '汽车走表器', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3354, '其他', '汽狗', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3355, '其他', '汽枪', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3356, '其他', '氣槍', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3357, '其他', '器官贩卖', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3358, '其他', '千禧弘法', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3359, '其他', '铅弹', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3360, '其他', '钱三字经', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3361, '其他', '强权政府', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3362, '其他', '强效失忆药', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3363, '其他', '强硬发言', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3364, '其他', '抢其火炬', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3365, '其他', '切听器', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3366, '其他', '窃听', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3367, '其他', '窃听器', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3368, '其他', '亲共', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3369, '其他', '亲共分子', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3370, '其他', '亲共媒体', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3371, '其他', '侵犯国外专利', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3372, '其他', '钦点接班人', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3373, '其他', '禽流感了', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3374, '其他', '勤捞致', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3375, '其他', '沁园春血', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3376, '其他', '青海无上师', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3377, '其他', '氢弹手', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3378, '其他', '清场内幕', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3379, '其他', '清除负面', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3380, '其他', '清純壆', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3381, '其他', '清官团', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3382, '其他', '清海师父', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3383, '其他', '清海无上师', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3384, '其他', '清华帮', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3385, '其他', '情聊天室', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3386, '其他', '情妹妹', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3387, '其他', '情色,论坛', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3388, '其他', '情色论坛', '0', '2021-07-06 13:51:53', '2021-07-06 13:51:53', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3389, '其他', '情杀', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3390, '其他', '情视频', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3391, '其他', '情自拍', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3392, '其他', '氰化钾', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3393, '其他', '氰化钠', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3394, '其他', '请集会', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3395, '其他', '请示威', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3396, '其他', '琼花问', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3397, '其他', '区的雷人', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3398, '其他', '娶韩国', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3399, '其他', '全范围教会', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3400, '其他', '全真证', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3401, '其他', '全自动开锁器', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3402, '其他', '犬交', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3403, '其他', '群发广告机', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3404, '其他', '群发软件', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3405, '其他', '群奸暴', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3406, '其他', '群交', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3407, '其他', '群起抗暴', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3408, '其他', '群体灭绝', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3409, '其他', '群体性事', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3410, '其他', '群众冲击', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3411, '其他', '绕过封锁', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3412, '其他', '惹的国', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3413, '其他', '人弹', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3414, '其他', '人祸', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3415, '其他', '人类灭亡时间表', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3416, '其他', '人类罪恶论', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3417, '其他', '人民币恶搞', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3418, '其他', '人权保护', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3419, '其他', '人宇特能功', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3420, '其他', '人在云上', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3421, '其他', '人真钱', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3422, '其他', '认牌绝', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3423, '其他', '任于斯国', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3424, '其他', '日你妈', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3425, '其他', '日月气功', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3426, '其他', '容弹量', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3427, '其他', '柔胸粉', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3428, '其他', '肉棒', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3429, '其他', '肉洞', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3430, '其他', '肉棍', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3431, '其他', '如厕死', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3432, '其他', '乳交', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3433, '其他', '软弱的国', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3434, '其他', '软弱外交', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3435, '其他', '瑞安事件', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3436, '其他', '萨斯病', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3437, '其他', '赛后骚', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3438, '其他', '赛克网', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3439, '其他', '三班仆人派', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3440, '其他', '三挫', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3441, '其他', '三股势力', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3442, '其他', '三级,电影', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3443, '其他', '三级,影片', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3444, '其他', '三级电影', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3445, '其他', '三级片', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3446, '其他', '三陪', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3447, '其他', '三三九乘元功', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3448, '其他', '三网友', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3449, '其他', '三唑', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3450, '其他', '三唑仑', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3451, '其他', '扫了爷爷', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3452, '其他', '杀害学生', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3453, '其他', '杀指南', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3454, '其他', '沙皇李长春', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3455, '其他', '傻逼', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3456, '其他', '山涉黑', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3457, '其他', '煽动不明', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3458, '其他', '煽动群众', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3459, '其他', '商务短信', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3460, '其他', '商务快车', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3461, '其他', '上海帮', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3462, '其他', '上海独立', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3463, '其他', '上门激', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3464, '其他', '上网文凭', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3465, '其他', '烧公安局', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3466, '其他', '烧瓶的', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3467, '其他', '韶关斗', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3468, '其他', '韶关玩', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3469, '其他', '韶关旭', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3470, '其他', '少妇自拍', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3471, '其他', '社会混', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3472, '其他', '社会主义灭亡', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3473, '其他', '射网枪', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3474, '其他', '涉台政局', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3475, '其他', '涉嫌抄袭', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3476, '其他', '身份证生成器', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3477, '其他', '深喉冰', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3478, '其他', '神的教会', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3479, '其他', '神七假', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3480, '其他', '神韵艺术', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3481, '其他', '神州忏悔录', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3482, '其他', '沈昌功', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3483, '其他', '沈昌人体科技', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3484, '其他', '肾源', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3485, '其他', '升达毕业证', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3486, '其他', '生被砍', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3487, '其他', '生踩踏', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3488, '其他', '生孩子没屁眼', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3489, '其他', '生命树的分叉', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3490, '其他', '生肖中特', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3491, '其他', '生意宝', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3492, '其他', '圣殿教', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3493, '其他', '圣火护卫', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3494, '其他', '圣灵重建教会', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3495, '其他', '圣战不息', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3496, '其他', '盛行在舞', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3497, '其他', '剩火', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3498, '其他', '尸博', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3499, '其他', '失身水', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3500, '其他', '失意药', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3501, '其他', '师涛', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3502, '其他', '狮子旗', '0', '2021-07-06 13:51:54', '2021-07-06 13:51:54', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3503, '其他', '十八大接班人', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3504, '其他', '十八等', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3505, '其他', '十大独裁', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3506, '其他', '十大谎', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3507, '其他', '十大禁', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3508, '其他', '十个预言', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3509, '其他', '十类人不', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3510, '其他', '十七大幕', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3511, '其他', '十七大权力争霸战', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3512, '其他', '十七大人事安排', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3513, '其他', '十七位老部长', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3514, '其他', '实毕业证', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3515, '其他', '实际神', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3516, '其他', '实体娃', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3517, '其他', '实学历文', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3518, '其他', '士的年', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3519, '其他', '士的宁', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3520, '其他', '士康事件', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3521, '其他', '独裁者', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3522, '其他', '世界之门', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3523, '其他', '式粉推', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3524, '其他', '视解密', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3525, '其他', '手变牌', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3526, '其他', '手狗', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3527, '其他', '手机,定位器', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3528, '其他', '手机,窃听', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3529, '其他', '手机复制', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3530, '其他', '手机跟', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3531, '其他', '手机跟踪定位器', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3532, '其他', '手机监', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3533, '其他', '手机监听', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3534, '其他', '手机监听器', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3535, '其他', '手机卡复制器', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3536, '其他', '手机魔卡', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3537, '其他', '手机窃听器', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3538, '其他', '手机追', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3539, '其他', '手木仓', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3540, '其他', '手枪', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3541, '其他', '守所死法', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3542, '其他', '兽交', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3543, '其他', '书办理', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3544, '其他', '熟妇', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3545, '其他', '术牌具', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3546, '其他', '双管立', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3547, '其他', '双管平', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3548, '其他', '双筒', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3549, '其他', '谁是胡的接班人', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3550, '其他', '谁是新中国', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3551, '其他', '谁为腐败晚餐买单', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3552, '其他', '水阎王', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3553, '其他', '税务总局致歉', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3554, '其他', '丝护士', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3555, '其他', '丝情侣', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3556, '其他', '丝袜保', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3557, '其他', '丝袜恋', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3558, '其他', '丝袜美', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3559, '其他', '丝袜妹', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3560, '其他', '丝袜网', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3561, '其他', '丝足按', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3562, '其他', '司长期有', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3563, '其他', '司法黑', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3564, '其他', '司考答案', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3565, '其他', '司马璐回忆录', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3566, '其他', '私房写真', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3567, '其他', '私服', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3568, '其他', '私家侦探服务', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3569, '其他', '死法分布', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3570, '其他', '死刑现场', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3571, '其他', '死要见毛', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3572, '其他', '四博会', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3573, '其他', '四川大地震异象揭密', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3574, '其他', '四川朱昱', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3575, '其他', '四大扯', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3576, '其他', '四二六社论', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3577, '其他', '四六级,答案', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3578, '其他', '饲养基地', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3579, '其他', '苏家屯', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3580, '其他', '苏家屯集', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3581, '其他', '诉讼集团', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3582, '其他', '素女,自拍', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3583, '其他', '素女心', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3584, '其他', '速代办', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3585, '其他', '速取证', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3586, '其他', '酸羟亚胺', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3587, '其他', '缩阴', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3588, '其他', '蹋纳税', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3589, '其他', '太王四神', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3590, '其他', '太王四神记', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3591, '其他', '泰兴幼', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3592, '其他', '泰兴镇中', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3593, '其他', '泰州幼', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3594, '其他', '贪官也辛', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3595, '其他', '探测狗', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3596, '其他', '涛共产', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3597, '其他', '涛一样胡', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3598, '其他', '讨厌中国', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3599, '其他', '套牌车', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3600, '其他', '特别党费', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3601, '其他', '特工资', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3602, '其他', '特码', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3603, '其他', '特上门', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3604, '其他', '特务机构', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3605, '其他', '体透视镜', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3606, '其他', '替考', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3607, '其他', '替人体', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3608, '其他', '天安门事件', '0', '2021-07-06 13:51:55', '2021-07-06 13:51:55', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3609, '其他', '天朝特', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3610, '其他', '天鹅之旅', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3611, '其他', '天推广歌', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3612, '其他', '天要灭', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3613, '其他', '天音功', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3614, '其他', '田罢工', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3615, '其他', '田田桑', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3616, '其他', '田停工', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3617, '其他', '铁血师', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3618, '其他', '庭审直播', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3619, '其他', '通钢总经', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3620, '其他', '同盟党', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3621, '其他', '统一教', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3622, '其他', '统治术', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3623, '其他', '偷電器', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3624, '其他', '偷啪,dv', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3625, '其他', '偷肃贪', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3626, '其他', '偷听器', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3627, '其他', '偷偷贪', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3628, '其他', '头双管', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3629, '其他', '投毒杀人', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3630, '其他', '投注站', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3631, '其他', '骰子', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3632, '其他', '秃鹰汽', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3633, '其他', '突破封锁', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3634, '其他', '突破网路', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3635, '其他', '屠杀学生', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3636, '其他', '土炮', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3637, '其他', '土枪', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3638, '其他', '推翻独裁', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3639, '其他', '推油按', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3640, '其他', '脱衣舞', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3641, '其他', '脱衣艳', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3642, '其他', '瓦斯手', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3643, '其他', '袜按摩', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3644, '其他', '外蒙回归', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3645, '其他', '外透视镜', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3646, '其他', '外围赌球', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3647, '其他', '万法归一功', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3648, '其他', '万能钥匙', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3649, '其他', '万人骚动', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3650, '其他', '王立军', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3651, '其他', '王益案', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3652, '其他', '网络封锁', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3653, '其他', '网络警察', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3654, '其他', '网民案', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3655, '其他', '网民获刑', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3656, '其他', '网民诬', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3657, '其他', '网站推广软件', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3658, '其他', '微型,监控', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3659, '其他', '微型摄像', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3660, '其他', '为党不为国', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3661, '其他', '围攻上海', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3662, '其他', '维汉员', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3663, '其他', '维权基', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3664, '其他', '维权人', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3665, '其他', '维权谈', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3666, '其他', '伪基百科', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3667, '其他', '伪政府', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3668, '其他', '委坐船', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3669, '其他', '卫星高频头', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3670, '其他', '卫星广播器材', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3671, '其他', '谓的和谐', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3672, '其他', '魏京生', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3673, '其他', '温加饱', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3674, '其他', '温家宝,腐败', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3675, '其他', '温家堡', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3676, '其他', '温切斯特', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3677, '其他', '温休曾退', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3678, '其他', '温影帝', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3679, '其他', '溫家寶', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3680, '其他', '瘟加饱', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3681, '其他', '瘟家宝', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3682, '其他', '瘟假饱', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3683, '其他', '文凭证', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3684, '其他', '纹了毛', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3685, '其他', '闻被控制', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3686, '其他', '闻封锁', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3687, '其他', '瓮安', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3688, '其他', '我的西域', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3689, '其他', '我搞台独', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3690, '其他', '我们自杀吧', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3691, '其他', '我虽死去', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3692, '其他', '乌蝇水', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3693, '其他', '乌云其木格', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3694, '其他', '无帮国', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3695, '其他', '无耻语录', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3696, '其他', '无官正', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3697, '其他', '无界浏览器', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3698, '其他', '无码', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3699, '其他', '无人性的政权', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3700, '其他', '无网界', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3701, '其他', '无线,监听', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3702, '其他', '吾爾開希', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3703, '其他', '五套功', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3704, '其他', '五月天', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3705, '其他', '午夜电', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3706, '其他', '午夜极', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3707, '其他', '武警暴', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3708, '其他', '武警殴', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3709, '其他', '武警已增', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3710, '其他', '武力镇压', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3711, '其他', '武装镇压', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3712, '其他', '务员答案', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3713, '其他', '务员考试', '0', '2021-07-06 13:51:56', '2021-07-06 13:51:56', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3714, '其他', '雾型迷', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3715, '其他', '西藏,独立', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3716, '其他', '西藏独立', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3717, '其他', '西藏国家民主党', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3718, '其他', '西藏人民大起义', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3719, '其他', '西藏限', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3720, '其他', '西藏作家组织', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3721, '其他', '西服进去', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3722, '其他', '希脏', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3723, '其他', '习进平', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3724, '其他', '习晋平', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3725, '其他', '席复活', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3726, '其他', '席临终前', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3727, '其他', '席指着护', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3728, '其他', '洗澡死', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3729, '其他', '喜贪赃', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3730, '其他', '先烈纷纷', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3731, '其他', '现大地震', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3732, '其他', '现金投注', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3733, '其他', '现在的党', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3734, '其他', '现在的公安', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3735, '其他', '现在的共产党', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3736, '其他', '现在的警察', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3737, '其他', '现在的社会', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3738, '其他', '现在的政府', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3739, '其他', '线透视镜', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3740, '其他', '限制言', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3741, '其他', '陷害案', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3742, '其他', '陷害罪', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3743, '其他', '霰弹', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3744, '其他', '香港彩', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3745, '其他', '香港独立', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3746, '其他', '香港马会', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3747, '其他', '香港一类', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3748, '其他', '香港总彩', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3749, '其他', '向巴平措', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3750, '其他', '消防灭火枪', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3751, '其他', '消防枪', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3752, '其他', '消业之说', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3753, '其他', '硝化甘', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3754, '其他', '小电影', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3755, '其他', '小活佛', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3756, '其他', '小姐按摩', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3757, '其他', '小姐上门', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3758, '其他', '小穴', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3759, '其他', '肖中特', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3760, '其他', '校骚乱', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3761, '其他', '写两会', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3762, '其他', '泄漏的内', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3763, '其他', '新疆暴乱', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3764, '其他', '新疆独立', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3765, '其他', '新疆叛', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3766, '其他', '新疆限', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3767, '其他', '新金瓶', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3768, '其他', '新生网', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3769, '其他', '新唐人', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3770, '其他', '新搪人', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3771, '其他', '态度蛮横', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3772, '其他', '新中华战记', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3773, '其他', '信访专班', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3774, '其他', '信用卡套现', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3775, '其他', '兴华论谈', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3776, '其他', '兴中心幼', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3777, '其他', '行长王益', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3778, '其他', '形透视镜', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3779, '其他', '性推广歌', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3780, '其他', '性息', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3781, '其他', '胸主席', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3782, '其他', '修炼大法', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3783, '其他', '徐玉元', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3784, '其他', '学骚乱', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3785, '其他', '学生领袖', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3786, '其他', '学位證', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3787, '其他', '血溅人民天堂', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3788, '其他', '血染的风采', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3789, '其他', '血色京机', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3790, '其他', '血色京畿', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3791, '其他', '血腥清场', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3792, '其他', '循环轮回论', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3793, '其他', '丫与王益', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3794, '其他', '严晓玲', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3795, '其他', '言被劳教', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3796, '其他', '言论罪', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3797, '其他', '盐酸曲', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3798, '其他', '颜射', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3799, '其他', '眼镜,透视', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3800, '其他', '燕玲论坛', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3801, '其他', '恙虫病', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3802, '其他', '摇头丸', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3803, '其他', '遥控信号拦截器', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3804, '其他', '要射精了', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3805, '其他', '要射了', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3806, '其他', '要泄了', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3807, '其他', '业力回报', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3808, '其他', '业力轮', '0', '2021-07-06 13:51:57', '2021-07-06 13:51:57', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3809, '其他', '夜半加税', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3810, '其他', '夜激情', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3811, '其他', '液体炸', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3812, '其他', '一党独裁', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3813, '其他', '一党私利', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3814, '其他', '一党执政', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3815, '其他', '一党专政', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3816, '其他', '一卡多号', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3817, '其他', '一氯丙酮', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3818, '其他', '一氯乙醛', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3819, '其他', '一码中特', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3820, '其他', '一通功', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3821, '其他', '一通健康法', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3822, '其他', '一小撮别', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3823, '其他', '一肖', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3824, '其他', '一氧化二氟', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3825, '其他', '一氧化汞', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3826, '其他', '一夜激情', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3827, '其他', '伊皮恩', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3828, '其他', '遗情书', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3829, '其他', '乙酰替硫脲', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3830, '其他', '乙酰亚砷酸铜', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3831, '其他', '异硫氰酸烯丙酯', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3832, '其他', '异氰酸苯酯', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3833, '其他', '异氰酸甲酯', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3834, '其他', '因毒磷', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3835, '其他', '因毒硫磷', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3836, '其他', '银氰化钾', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3837, '其他', '银行卡复制设备', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3838, '其他', '隐蔽式摄像机', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3839, '其他', '隐形,耳机', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3840, '其他', '隐形,摄像机', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3841, '其他', '隐形耳', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3842, '其他', '隐形耳机', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3843, '其他', '隐形喷剂', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3844, '其他', '隐形摄像机', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3845, '其他', '应子弹', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3846, '其他', '婴儿命', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3847, '其他', '婴儿汤', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3848, '其他', '罂粟壳', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3849, '其他', '罂粟籽', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3850, '其他', '蝇毒', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3851, '其他', '影子政府', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3852, '其他', '雍战胜', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3853, '其他', '永伏虫', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3854, '其他', '咏妓', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3855, '其他', '用手枪', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3856, '其他', '优化官员', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3857, '其他', '幽谷三', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3858, '其他', '游精佑', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3859, '其他', '游戏机破解', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3860, '其他', '有偿服务', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3861, '其他', '有偿捐献', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3862, '其他', '有偿肾', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3863, '其他', '有码', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3864, '其他', '有奶不一', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3865, '其他', '右转是政', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3866, '其他', '幼齿类', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3867, '其他', '幼交', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3868, '其他', '娱乐透视', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3869, '其他', '愚民同', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3870, '其他', '愚民政', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3871, '其他', '与狗性', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3872, '其他', '宇宙大法', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3873, '其他', '宇宙毁灭', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3874, '其他', '宇宙主佛', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3875, '其他', '玉蒲团', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3876, '其他', '育部女官', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3877, '其他', '预测答案', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3878, '其他', '冤民大', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3879, '其他', '鸳鸯洗', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3880, '其他', '渊盖苏文', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3881, '其他', '元极功', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3882, '其他', '原砷酸', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3883, '其他', '原一九五七', '0', '2021-07-06 13:52:00', '2021-07-06 13:52:00', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3884, '其他', '袁伟民', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3885, '其他', '援藏网', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3886, '其他', '援交', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3887, '其他', '晕倒型', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3888, '其他', '韵徐娘', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3889, '其他', '赞成,西藏,独立', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3890, '其他', '脏毒', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3891, '其他', '脏独', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3892, '其他', '遭便衣', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3893, '其他', '遭到警', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3894, '其他', '遭警察', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3895, '其他', '遭武警', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3896, '其他', '择油录', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3897, '其他', '曾道人', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3898, '其他', '炸弹教', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3899, '其他', '炸弹遥控', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3900, '其他', '炸广州', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3901, '其他', '炸立交', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3902, '其他', '炸药的制', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3903, '其他', '炸药配', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3904, '其他', '炸药制', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3905, '其他', '粘氯酸', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3906, '其他', '张春桥', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3907, '其他', '张宏宝', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3908, '其他', '张宏堡', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3909, '其他', '张文中', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3910, '其他', '张志新', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3911, '其他', '找枪手', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3912, '其他', '找援交', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3913, '其他', '找政法委副', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3914, '其他', '赵紫阳', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3915, '其他', '针刺案', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3916, '其他', '针刺伤', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3917, '其他', '针刺事', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3918, '其他', '针刺死', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3919, '其他', '针孔摄象机', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3920, '其他', '针孔摄像机', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3921, '其他', '侦探设备', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3922, '其他', '真钱,百家乐', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3923, '其他', '真钱斗地', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3924, '其他', '真钱投注', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3925, '其他', '真善忍', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3926, '其他', '真实文凭', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3927, '其他', '震惊一个民', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3928, '其他', '震其国土', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3929, '其他', '证到付款', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3930, '其他', '证件公司', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3931, '其他', '证件集团', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3932, '其他', '证生成器', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3933, '其他', '证书办', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3934, '其他', '政府无能', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3935, '其他', '政论区', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3936, '其他', '政治风波', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3937, '其他', '政治局十七', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3938, '其他', '政治人祸的源头', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3939, '其他', '支那', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3940, '其他', '支那猪', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3941, '其他', '植物冰', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3942, '其他', '指纹考勤', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3943, '其他', '指纹膜', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3944, '其他', '指纹套', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3945, '其他', '制服诱', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3946, '其他', '制手枪', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3947, '其他', '制证定金', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3948, '其他', '制作证件', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3949, '其他', '治疗红斑狼疮', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3950, '其他', '治疗性病', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3951, '其他', '治疗乙肝', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3952, '其他', '治疗肿瘤', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3953, '其他', '中办发', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3954, '其他', '中的班禅', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3955, '其他', '中国不强', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3956, '其他', '中国复兴党', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3957, '其他', '中国高层权力斗争', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3958, '其他', '中国共和党', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3959, '其他', '中国官场情杀案', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3960, '其他', '中国过渡政府', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3961, '其他', '中国海外腐败兵团', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3962, '其他', '中国没有自由', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3963, '其他', '中国人民党', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3964, '其他', '中国实行血腥教育', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3965, '其他', '中国贪官在海外', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3966, '其他', '中国网络审查', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3967, '其他', '中国舆论监督网周洪', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3968, '其他', '中国正义党', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3969, '其他', '中国政府封锁消息', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3970, '其他', '中国支配下的朝鲜经济', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3971, '其他', '中国猪', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3972, '其他', '中华昆仑女神功', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3973, '其他', '中华养生益智功', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3974, '其他', '中华养生益智气', '0', '2021-07-06 13:52:01', '2021-07-06 13:52:01', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3975, '其他', '中南海的权力游戏', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3976, '其他', '中南海斗争', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3977, '其他', '中南海恩仇录', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3978, '其他', '中南海黑幕', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3979, '其他', '中南海权力斗争', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3980, '其他', '中石油国家电网倒数', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3981, '其他', '中特', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3982, '其他', '中央黑幕', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3983, '其他', '中正纪念歌', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3984, '其他', '中组部前部长直言', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3985, '其他', '种公务员', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3986, '其他', '种学历证', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3987, '其他', '众像羔', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3988, '其他', '重亚硒酸钠', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3989, '其他', '重阳兵变', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3990, '其他', '州惨案', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3991, '其他', '州大批贪', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3992, '其他', '州三箭', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3993, '其他', '宙最高法', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3994, '其他', '昼将近', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3995, '其他', '朱镕基,腐败', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3996, '其他', '主神教', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3997, '其他', '主席忏', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3998, '其他', '属灵教', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (3999, '其他', '住英国房', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4000, '其他', '助考', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4001, '其他', '助考网', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4002, '其他', '转法轮', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4003, '其他', '转法论', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4004, '其他', '转是政府', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4005, '其他', '赚钱资料', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4006, '其他', '庄家', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4007, '其他', '装弹甲', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4008, '其他', '装枪套', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4009, '其他', '装消音', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4010, '其他', '追债公司', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4011, '其他', '追踪,定位', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4012, '其他', '梓健特药', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4013, '其他', '自动群发', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4014, '其他', '自己找枪', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4015, '其他', '自杀手册', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4016, '其他', '自杀指南', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4017, '其他', '自慰用', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4018, '其他', '自由门', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4019, '其他', '自由圣', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4020, '其他', '自由西藏', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4021, '其他', '自由西藏学生运动', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4022, '其他', '总会美女', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4023, '其他', '走私车', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4024, '其他', '足交', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4025, '其他', '足球,博彩', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4026, '其他', '足球玩法', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4027, '其他', '最后圆满', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4028, '其他', '醉钢枪', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4029, '其他', '醉迷药', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4030, '其他', '醉乙醚', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4031, '其他', '尊爵粉', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4032, '其他', '左棍', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4033, '其他', '左转是政', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4034, '其他', '作弊器', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4035, '其他', '作各种证', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4036, '其他', '作硝化甘', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sensitive_words`
VALUES (4037, '其他', '唑仑', '0', '2021-07-06 13:52:02', '2021-07-06 13:52:02', NULL, NULL, NULL, NULL, NULL);