/*
 Navicat Premium Data Transfer

 Source Server         : localhost-find
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : find_dev_test01

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 28/07/2021 16:39:44
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `alipay_config`;
CREATE TABLE `alipay_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用ID',
  `status` enum('1','0') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态：0->启用，1->禁用，默认：0->启用',
  `charset` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '编码',
  `format` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型 固定格式json',
  `gateway_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关地址',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异步回调',
  `private_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '私钥',
  `public_key` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公钥',
  `return_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '回调地址',
  `sign_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名方式',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_app_id`(`app_id`) USING BTREE,
  UNIQUE INDEX `u_idx_sys_service_provider_id`(`sys_service_provider_id`) USING BTREE,
  INDEX `n_idx_charset`(`charset`) USING BTREE,
  INDEX `n_idx_format`(`format`) USING BTREE,
  INDEX `n_idx_sign_type`(`sign_type`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付宝配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for application_record
-- ----------------------------
DROP TABLE IF EXISTS `application_record`;
CREATE TABLE `application_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '申请者id',
  `dynamic_info_id` bigint(20) NOT NULL COMMENT '动态内容id',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_dynamic_info_id`(`dynamic_info_id`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申请加微信记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for application_setting
-- ----------------------------
DROP TABLE IF EXISTS `application_setting`;
CREATE TABLE `application_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置id，主键',
  `times` int(2) NOT NULL COMMENT '次数',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态，0->禁用，1->启用，默认：0->禁用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_times`(`times`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申请加微信次数设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attache_info
-- ----------------------------
DROP TABLE IF EXISTS `attache_info`;
CREATE TABLE `attache_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件id，主键',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '状态，0->隐藏，1->显示，默认：1->显示',
  `dynamic_info_by` bigint(20) NOT NULL COMMENT '动态内容id，对应动态信息表主键id',
  `file_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `data_type` enum('1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '附件类型，1->图片；2->音频，默认：1->图片',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_dynamic_info_by`(`dynamic_info_by`) USING BTREE,
  INDEX `n_idx_file_name`(`file_name`) USING BTREE,
  INDEX `n_idx_data_type`(`data_type`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for blacklist_record
-- ----------------------------
DROP TABLE IF EXISTS `blacklist_record`;
CREATE TABLE `blacklist_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id，主键',
  `owner_user_id` bigint(20) NOT NULL COMMENT '拥有者id',
  `black_user_id` bigint(20) NOT NULL COMMENT '黑名单用户id',
  `status` int(8) NOT NULL COMMENT '黑名单状态，5表示拉入黑名单，6表示移出黑名单',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_owner_user_id`(`owner_user_id`) USING BTREE,
  INDEX `n_idx_black_user_id`(`black_user_id`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '黑名单记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `dynamic_info_id` bigint(20) NOT NULL COMMENT '动态内容id',
  `likes` int(11) NOT NULL DEFAULT 0 COMMENT '评论的点赞数',
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
-- Table structure for dynamic
-- ----------------------------
DROP TABLE IF EXISTS `dynamic`;
CREATE TABLE `dynamic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动态id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id，对应用户信息表主键id',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `imei` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备串码',
  `model` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `sys_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称',
  `sys_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统版本',
  `network_mode` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网络方式',
  `country` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `other` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它地址',
  `longitude` double(12, 6) NULL DEFAULT NULL COMMENT '经度',
  `latitude` double(12, 6) NULL DEFAULT NULL COMMENT '纬度',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_nick_name`(`nick_name`) USING BTREE,
  INDEX `n_idx_imei`(`imei`) USING BTREE,
  INDEX `n_idx_model`(`model`) USING BTREE,
  INDEX `n_idx_sys_name`(`sys_name`) USING BTREE,
  INDEX `n_idx_sys_code`(`sys_code`) USING BTREE,
  INDEX `n_idx_network_mode`(`network_mode`) USING BTREE,
  INDEX `n_idx_country`(`country`) USING BTREE,
  INDEX `n_idx_province`(`province`) USING BTREE,
  INDEX `n_idx_city`(`city`) USING BTREE,
  INDEX `n_idx_district`(`district`) USING BTREE,
  INDEX `n_idx_other`(`other`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '动态信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dynamic_info
-- ----------------------------
DROP TABLE IF EXISTS `dynamic_info`;
CREATE TABLE `dynamic_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动态内容id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '发布者id',
  `dynamic_id` bigint(20) NOT NULL COMMENT '动态信息id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `dynamic_status` enum('0','1','2','3') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '动态内容状态，0->显示；1->隐藏；2->小推；3->大推，默认：0->显示',
  `public_status` enum('0','1') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否公开定位状态，0->未公开；1->公开，默认：0->未公开',
  `is_topic` enum('0','1') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否是话题，0->否；1->是，默认：0->否',
  `is_anonymous` enum('0','1') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '是否匿名，0->否；1->是，默认：0->否',
  `topic_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '话题标题',
  `likes` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `comments` int(11) NOT NULL DEFAULT 0 COMMENT '评论数',
  `applications` int(11) NOT NULL DEFAULT 0 COMMENT '申请加微信数',
  `shares` int(11) NOT NULL DEFAULT 0 COMMENT '分享数',
  `attache_type` enum('0','1','2') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '附件类型，0->文本；1->图片；2->音频，默认：0->文本',
  `attache_number` int(4) NULL DEFAULT NULL COMMENT '附件数量',
  `longitude` double(12, 6) NULL DEFAULT NULL COMMENT '经度',
  `latitude` double(12, 6) NULL DEFAULT NULL COMMENT '纬度',
  `country` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '中国' COMMENT '国',
  `province` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `other` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它地址',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column02` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_dynamic_id`(`dynamic_id`) USING BTREE,
  INDEX `n_idx_dynamic_status`(`dynamic_status`) USING BTREE,
  INDEX `n_idx_public_status`(`public_status`) USING BTREE,
  INDEX `n_idx_likes`(`likes`) USING BTREE,
  INDEX `n_idx_applications`(`applications`) USING BTREE,
  INDEX `n_idx_shares`(`shares`) USING BTREE,
  INDEX `n_idx_attache_type`(`attache_type`) USING BTREE,
  INDEX `n_idx_attache_number`(`attache_number`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '动态内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for industrys
-- ----------------------------
DROP TABLE IF EXISTS `industrys`;
CREATE TABLE `industrys`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '行业名称',
  `parent_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '父级编号',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted_time` timestamp(0) NULL DEFAULT NULL COMMENT '删除时间',
  `reserve_column01` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '行业信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for is_debug
-- ----------------------------
DROP TABLE IF EXISTS `is_debug`;
CREATE TABLE `is_debug`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `is_debug` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否支付测试，0->否；1->是，默认：0->否',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `price` decimal(4, 2) NOT NULL DEFAULT 0.01 COMMENT '测试金额',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_is_debug`(`is_debug`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_price`(`price`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付调测表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for like_record
-- ----------------------------
DROP TABLE IF EXISTS `like_record`;
CREATE TABLE `like_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '点赞者id',
  `dynamic_info_id` bigint(20) NOT NULL COMMENT '动态内容id',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '状态，0->取消；1->点赞，默认：1->点赞',
  `type` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '类型，0->动态，1->评论，2->其它，默认：0，动态',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_dynamic_info_id`(`dynamic_info_id`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '点赞记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息id，主键',
  `send_user_id` bigint(20) NOT NULL COMMENT '发送者id',
  `recipient_user_id` bigint(20) NOT NULL COMMENT '接收者id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `send_mode` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '发送方式：0->系统自动；1->用户主动，默认：0->系统自动',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态，0->未读；1->已读，默认：0->未读',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型，likes->点赞，applications->申请加微信，commons->普通消息',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02，消息发送或者回复，0->发送，1->回复',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03，是否删除，0->否,1-删除，默认：0',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04，消息id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_send_user_id`(`send_user_id`) USING BTREE,
  INDEX `n_idx_recipient_user_id`(`recipient_user_id`) USING BTREE,
  INDEX `n_idx_send_mode`(`send_mode`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for operate_record
-- ----------------------------
DROP TABLE IF EXISTS `operate_record`;
CREATE TABLE `operate_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '状态：0->失败，1->成功，默认：1->成功',
  `type` int(2) NOT NULL COMMENT '类型：0->发布动态，1->删除动态，2->点赞动态，3->申请加微信，4->分享动态，5->拉黑用户，6->举报动态，7-创建用户，8->修改资料',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户操作记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `detail_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详情id',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `product_id` bigint(20) NOT NULL COMMENT '商品id',
  `buyer_user_id` bigint(20) NOT NULL COMMENT '用户id',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL DEFAULT 0.01 COMMENT '商品价格',
  `product_quantity` int(8) NOT NULL DEFAULT 1 COMMENT '购买数量',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_detail_id`(`detail_id`) USING BTREE,
  UNIQUE INDEX `u_idx_order_id`(`order_id`) USING BTREE,
  INDEX `u_idx_product_id`(`product_id`) USING BTREE,
  INDEX `n_idx_buyer_user_id`(`buyer_user_id`) USING BTREE,
  INDEX `n_idx_product_name`(`product_name`) USING BTREE,
  INDEX `n_idx_product_quantity`(`product_quantity`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id，主键',
  `order_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `transaction_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单支付交易流水号',
  `product_id` bigint(20) NOT NULL COMMENT '商品id',
  `buyer_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '买家昵称',
  `buyer_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家手机号码',
  `buyer_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `order_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单名称',
  `order_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单金额',
  `order_status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '订单状态：0->新订单；1->已完结；2->已取消，默认：0->新订单',
  `pay_status` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '支付状态：0->等待支付；1->支付成功；2->关闭支付，默认：0->等待支付',
  `pay_mode` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '支付方式：0->微信支付；1->支付宝支付，默认：0->微信支付',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_order_id`(`order_id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_transaction_id`(`transaction_id`) USING BTREE,
  INDEX `n_idx_product_id`(`product_id`) USING BTREE,
  INDEX `n_idx_buyer_name`(`buyer_name`) USING BTREE,
  INDEX `n_idx_buyer_phone`(`buyer_phone`) USING BTREE,
  INDEX `n_idx_buyer_ip`(`buyer_ip`) USING BTREE,
  INDEX `n_idx_order_name`(`order_name`) USING BTREE,
  INDEX `n_idx_order_amount`(`order_amount`) USING BTREE,
  INDEX `n_idx_order_status`(`order_status`) USING BTREE,
  INDEX `n_idx_pay_status`(`pay_status`) USING BTREE,
  INDEX `n_idx_pay_mode`(`pay_mode`) USING BTREE,
  INDEX `n_idx_pay_time`(`pay_time`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_setting
-- ----------------------------
DROP TABLE IF EXISTS `order_setting`;
CREATE TABLE `order_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '设置id，主键',
  `normal_order_overtime` int(8) NULL DEFAULT NULL COMMENT '正常订单超时时间(分)',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态，0->启用，1->禁用，默认：0->启用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_normal_order_overtime`(`normal_order_overtime`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，主键',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_description` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `product_icon` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品小图',
  `product_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '状态，0->下架，1->上架，默认：1->上架',
  `product_unit_price` decimal(8, 2) NOT NULL DEFAULT 0.01 COMMENT '商品单价',
  `vip_charge_time` int(2) NOT NULL DEFAULT 2 COMMENT 'VIP时长(月)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_product_name`(`product_name`) USING BTREE,
  INDEX `n_idx_product_description`(`product_description`) USING BTREE,
  INDEX `n_idx_product_status`(`product_status`) USING BTREE,
  INDEX `n_idx_product_unit_price`(`product_unit_price`) USING BTREE,
  INDEX `n_idx_vip_charge_time`(`vip_charge_time`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for professions
-- ----------------------------
DROP TABLE IF EXISTS `professions`;
CREATE TABLE `professions`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '职业名称',
  `industry_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '所属行业编号',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是',
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
-- Table structure for report_category
-- ----------------------------
DROP TABLE IF EXISTS `report_category`;
CREATE TABLE `report_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目id，主键',
  `content` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态，0->启用，1->禁用，默认：0->启用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_content`(`content`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '举报类目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for report_info
-- ----------------------------
DROP TABLE IF EXISTS `report_info`;
CREATE TABLE `report_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '举报信息id，主键',
  `category_type` bigint(20) NOT NULL COMMENT '类目id，对应举报类目表主键id',
  `report_type` enum('1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '举报类型，1->动态，2->用户，默认：1-动态',
  `suggest_type` enum('0','1','2','3','4') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '处理建议类型，0->默认，1->已忽略，2->已隐藏（针对动态），3—>已禁用（针对用户。亦可做其他方式处理）），默认：0->默认',
  `report_user_id` bigint(20) NOT NULL COMMENT '举报用户id',
  `being_report_user_id` bigint(20) NOT NULL COMMENT '被举报动态id',
  `report_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '举报内容',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_category_type`(`category_type`) USING BTREE,
  INDEX `n_idx_report_type`(`report_type`) USING BTREE,
  INDEX `n_idx_suggest_type`(`suggest_type`) USING BTREE,
  INDEX `n_idx_report_user_id`(`report_user_id`) USING BTREE,
  INDEX `n_idx_being_report_user_id`(`being_report_user_id`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '举报信息表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '筛选条件配置信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sensitive_words
-- ----------------------------
DROP TABLE IF EXISTS `sensitive_words`;
CREATE TABLE `sensitive_words`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `type_name` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '敏感词汇表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for share_record
-- ----------------------------
DROP TABLE IF EXISTS `share_record`;
CREATE TABLE `share_record`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id，主键',
  `user_id` bigint(20) NOT NULL COMMENT '分享者id',
  `dynamic_info_id` bigint(20) NOT NULL COMMENT '动态内容id',
  `mode` enum('0','1','2','3','4') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '分享方式，0->微信好友；1->QQ好友；2->微信朋友圈；3->QQ空间，4->微信收藏；',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `n_idx_user_id`(`user_id`) USING BTREE,
  INDEX `n_idx_dynamic_info_id`(`dynamic_info_id`) USING BTREE,
  INDEX `n_idx_mode`(`mode`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分享记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号，主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '标签名称',
  `delete_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除状态，0->否，1->是',
  `hot_value` int(11) NOT NULL DEFAULT 0 COMMENT '热门值',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id，主键',
  `phone` char(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '账号状态，0->正常；1->异常，默认：0->正常',
  `is_test` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否测试用户，0->否；1->是，默认：0->否',
  `nick_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `weixin_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号码',
  `imei` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备串码',
  `model` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备型号',
  `sys_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称',
  `sys_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统版本',
  `network_mode` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网络方式',
  `gender` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '性别，0->女生；1->男生，默认：0->女生',
  `year` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生年代',
  `month` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生月份',
  `date` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `constellation` char(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '星座',
  `head_icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像小图',
  `country` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '国',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '区/县',
  `other` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '其它地址',
  `longitude` double(20, 6) NULL DEFAULT NULL COMMENT '经度',
  `latitude` double(20, 6) NULL DEFAULT NULL COMMENT '纬度',
  `platform` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '平台',
  `ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP',
  `grade` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT 'VIP等级，0->VIP0，普通用户；1->VIP1，VIP用户；2->SVIP，超级VIP；默认：0->VIP0，普通用户',
  `autograph` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '这位靓妹/靓仔还未填写签名。' COMMENT '签名，默认：这位靓妹/靓仔还未填写签名。',
  `vip_start_time` datetime(0) NULL DEFAULT NULL COMMENT 'VIP开始时间',
  `vip_end_time` datetime(0) NULL DEFAULT NULL COMMENT 'VIP结束时间',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `background_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景图片',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '极光推送唯一设备的标识',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  `profession_id` bigint(20) NOT NULL DEFAULT 1 COMMENT '职业编号',
  `tag1` bigint(20) NULL DEFAULT NULL COMMENT '标签1编号，对应标签表id',
  `tag2` bigint(20) NULL DEFAULT NULL COMMENT '标签2编号，对应标签表id',
  `tag3` bigint(20) NULL DEFAULT NULL COMMENT '标签3编号，对应标签表id',
  `tag4` bigint(20) NULL DEFAULT NULL COMMENT '标签4编号，对应标签表id',
  `tag5` bigint(20) NULL DEFAULT NULL COMMENT '标签5编号，对应标签表id',
  `reserve_column05` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段05',
  `reserve_column06` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段06',
  `reserve_column07` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段07',
  `reserve_column08` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段08',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_phone`(`phone`) USING BTREE,
  INDEX `n_idx_status`(`status`) USING BTREE,
  INDEX `n_idx_is_test`(`is_test`) USING BTREE,
  INDEX `n_idx_nick_name`(`nick_name`) USING BTREE,
  INDEX `n_idx_imei`(`imei`) USING BTREE,
  INDEX `n_idx_sys_code`(`sys_code`) USING BTREE,
  INDEX `n_idx_network_mode`(`network_mode`) USING BTREE,
  INDEX `n_idx_gender`(`gender`) USING BTREE,
  INDEX `n_idx_year`(`year`) USING BTREE,
  INDEX `n_idx_month`(`month`) USING BTREE,
  INDEX `n_idx_date`(`date`) USING BTREE,
  INDEX `n_idx_constellation`(`constellation`) USING BTREE,
  INDEX `n_idx_country`(`country`) USING BTREE,
  INDEX `n_idx_province`(`province`) USING BTREE,
  INDEX `n_idx_city`(`city`) USING BTREE,
  INDEX `n_idx_district`(`district`) USING BTREE,
  INDEX `n_idx_platform`(`platform`) USING BTREE,
  INDEX `n_idx_ip`(`ip`) USING BTREE,
  INDEX `n_idx_grade`(`grade`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE,
  INDEX `u_idx_weixin_id`(`weixin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for weixin_config
-- ----------------------------
DROP TABLE IF EXISTS `weixin_config`;
CREATE TABLE `weixin_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '配置id，主键',
  `app_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用ID',
  `status` enum('1','0') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态，1->禁用，0->启用，默认：0->启用',
  `app_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用secret',
  `sys_service_provider_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户号',
  `sys_service_provider_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商户Key',
  `api_v3_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'APIv3密钥',
  `key_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '证书路径',
  `notify_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '异步回调',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
  `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
  `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
  `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `u_idx_app_id`(`app_id`) USING BTREE,
  UNIQUE INDEX `u_idx_app_secret`(`app_secret`) USING BTREE,
  UNIQUE INDEX `u_idx_sys_service_provider_id`(`sys_service_provider_id`) USING BTREE,
  UNIQUE INDEX `u_idx_sys_service_provider_key`(`sys_service_provider_key`) USING BTREE,
  UNIQUE INDEX `u_idx_api_v3_key`(`api_v3_key`) USING BTREE,
  INDEX `n_idx_create_time`(`create_time`) USING BTREE,
  INDEX `n_idx_update_time`(`update_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信配置表' ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for feedback_record
-- ----------------------------
DROP TABLE IF EXISTS `feedback_record`;
CREATE TABLE `feedback_record`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id，主键',
   `user_id` bigint(20) NOT NULL COMMENT '反馈者id',
   `content` tinytext CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
   `file_path_list` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径列表',
   `data_type` enum('0','1','2') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '附件类型，0->文字；1->图片；2->音频，默认：0->文字',
   `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
   `reserve_column01` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段01',
   `reserve_column02` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段02',
   `reserve_column03` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段03',
   `reserve_column04` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段04',
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `n_idx_user_id`(`user_id`) USING BTREE,
   INDEX `n_idx_data_type`(`data_type`) USING BTREE,
   INDEX `n_idx_create_time`(`create_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '意见反馈记录表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
