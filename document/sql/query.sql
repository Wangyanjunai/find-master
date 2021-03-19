SELECT
	ur.`id` AS '用户id',
	ur.`head_icon` AS '头像',
	ur.`nick_name` AS '昵称',
	di.`create_time` AS '发布时间',
	di.`id` AS '动态内容id',
	di.`content` AS '动态内容',
	di.`public_status` AS '是否公开定位',
	dy.`country` AS '定位国家',
	CONCAT( dy.`province`, '省' ) AS '定位省份',
	CONCAT( dy.`city`, '市' ) AS '定位城市',
	di.`likes` AS '点赞数',
	di.`applications` AS '申请加微信数',
	di.`shares` AS '分享数',
	ai.id AS '附件id',
	ai.data_type AS '附件类型',
	ai.file_name AS '附件名称列表' 
FROM
	`dynamic_info` di,
	`dynamic` dy,
	`attache_info` ai,
	`user` ur 
WHERE
	dy.`id` = di.`dynamic_id` 
	AND di.`id` = ai.`dynamic_info_by` 
	AND dy.`user_id` = ur.`id` 
	AND ur.`id` IN ( 70 ) 
	AND di.`dynamic_status` != '1' 
	AND dy.`country` IN ( '中国' ) 
	AND dy.`province` IN ( '广东省', '广西省' ) 
	AND dy.`city` IN ( '广州市', '深圳市', '南宁市' ) 
	AND di.`create_time` >= DATE_SUB( NOW( ), INTERVAL 720 HOUR );	
	
SELECT date_sub( now( ), INTERVAL 720 HOUR );	
	
SELECT
	ur.`id` AS user_id,
	ur.`head_icon` AS head_icon,
	ur.`nick_name` AS nick_name,
	di.`create_time` AS create_time,
	di.`id` AS dynamic_info_id,
	di.`content` AS content,
	di.`public_status` AS public_status,
	dy.`country` AS country,
	CONCAT( dy.`province`, '省' ) AS province,
	CONCAT( dy.`city`, '市' ) AS city,
	di.`likes` AS likes,
	di.`applications` AS applications,
	di.`shares` AS shares,
	ai.id AS attache_file_id,
	ai.data_type AS attache_file_data_type,
	ai.file_name AS attache_file_name_list 
FROM
	`dynamic_info` di,
	`dynamic` dy,
	`attache_info` ai,
	`user` ur 
WHERE
	dy.`id` = di.`dynamic_id` 
	AND di.`id` = ai.`dynamic_info_by` 
	AND dy.`user_id` = ur.`id` 
	AND ur.`id` IN ( 70 ) 
	AND di.`dynamic_status` != '1' 
	AND dy.`country` IN ( '中国' ) 
	AND dy.`province` IN ( '广东省', '广西省' ) 
	AND dy.`city` IN ( '广州市', '深圳市', '南宁市' ) 
	AND di.`create_time` >= DATE_SUB( NOW( ), INTERVAL 720 HOUR );
	
	
SELECT
	NOW( ) 
FROM
	DUAL;
	
SELECT
	ur.`id` AS user_id,
	ur.`head_icon` AS head_icon,
	ur.`nick_name` AS nick_name,
	di.`create_time` AS create_time,
	di.`id` AS dynamic_info_id,
	di.`content` AS content,
	di.`public_status` AS public_status,
	dy.`country` AS country,
	CONCAT( dy.`province`, '省' ) AS province,
	CONCAT( dy.`city`, '市' ) AS city,
	di.`likes` AS likes,
	di.`applications` AS applications,
	di.`shares` AS shares,
	ai.id AS attache_file_id,
	ai.data_type AS attache_file_data_type,
	ai.file_name AS attache_file_name_list 
FROM
	`dynamic_info` di,
	`dynamic` dy,
	`attache_info` ai,
	`user` ur 
WHERE
	dy.`id` = di.`dynamic_id` 
	AND di.`id` = ai.`dynamic_info_by` 
	AND dy.`user_id` = ur.`id` 
	AND di.`create_time` >= DATE_SUB( NOW( ), INTERVAL 720 HOUR ) 
	AND di.`dynamic_status` != '1'
	AND ur.`id` = 70;
	
SELECT
	* 
FROM
	`dynamic_info` di 
WHERE
	di.`user_id` = 70;


-- 非VIP筛选
SELECT DISTINCT ur.`id`                    AS `user_id`,
                ur.`head_icon`             AS `head_icon`,
                ur.`nick_name`             AS `nick_name`,
                di.`create_time`           AS `create_time`,
                di.`id`                    AS `dynamic_info_id`,
                di.`content`               AS `content`,
                di.`public_status`         AS `public_status`,
                CONCAT(dy.`country`, '·')  AS `country`,
                CONCAT(dy.`province`, '·') AS `province`,
                CONCAT(dy.`city`)          AS `city`,
                di.`likes`                 AS `likes`,
                di.`applications`          AS `applications`,
                ai.`data_type`             AS `attache_file_data_type`,
                ai.`file_name`             AS `attache_file_name_list`,
                di.`dynamic_status`,
                di.`update_time`
FROM `dynamic_info` di,
     `dynamic` dy,
     `attache_info` ai,
     `user` ur
WHERE dy.`id` = di.`dynamic_id`
  AND di.`id` = ai.`dynamic_info_by`
  AND dy.`user_id` = ur.`id`
  AND di.`dynamic_status` != '1'
  AND ur.`gender`= '0'
  AND STR_TO_DATE(CONCAT_WS('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') <= '2005-03-04 00:00:00'
  AND STR_TO_DATE(CONCAT_WS('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') >= '1986-03-04 00:00:00'
  AND ur.`id` NOT IN (17, 19, 132)
  AND dy.`country` IN ('中国')
  AND dy.`province` IN ('浙江省')
ORDER BY di.`dynamic_status` DESC, di.`create_time` DESC, di.`update_time` DESC;

-- VIP筛选
SELECT DISTINCT ur.`id`                    AS `user_id`,
                ur.`head_icon`             AS `head_icon`,
                ur.`nick_name`             AS `nick_name`,
                di.`create_time`           AS `create_time`,
                di.`id`                    AS `dynamic_info_id`,
                di.`content`               AS `content`,
                di.`public_status`         AS `public_status`,
                CONCAT(dy.`country`, '·')  AS `country`,
                CONCAT(dy.`province`, '·') AS `province`,
                CONCAT(dy.`city`)          AS `city`,
                di.`likes`                 AS `likes`,
                di.`applications`          AS `applications`,
                ai.`data_type`             AS `attache_file_data_type`,
                ai.`file_name`             AS `attache_file_name_list`,
                di.`dynamic_status`,
                di.`update_time`
FROM `dynamic_info` di,
     `dynamic` dy,
     `attache_info` ai,
     `user` ur
WHERE dy.`id` = di.`dynamic_id`
  AND di.`id` = ai.`dynamic_info_by`
  AND dy.`user_id` = ur.`id`
  AND di.`dynamic_status` != '1'
  AND ur.`gender` = '0'
  AND STR_TO_DATE(CONCAT_WS('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') <= '2005-03-04 00:00:00'
  AND STR_TO_DATE(CONCAT_WS('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') >= '1986-03-04 00:00:00'
  AND ur.`constellation` = '巨蟹座'
  AND ur.`id` NOT IN (17, 19, 132)
  AND ai.`data_type` = '0'
  AND dy.`country` IN ('中国')
  AND dy.`province` IN ('浙江省')
  AND dy.`city` IN ('杭州市')
ORDER BY di.`dynamic_status` DESC, di.`create_time` DESC, di.`update_time` DESC;

UPDATE `user` set `reserve_column03` = '13065ffa4ead6656765' WHERE MOD(`id`, 2) = 0;
UPDATE `user` set `reserve_column03` = '1507bfd3f76139cd43a' WHERE MOD(`id`, 2) != 0;

-- 查询某个用户被点赞的动态列表信息
SELECT
	lr.`user_id` AS `likes_user_id`,
	lr.`create_time` AS `likes_create_time`,
	di.`id` AS `dyinfo_id`
FROM
	`like_record` lr,
	`dynamic_info` di,
	`user` ur
WHERE
	lr.`dynamic_info_id` = di.`id` 
	AND di.`user_id` = ur.`id`
	AND ur.`id` = 18
ORDER BY
	lr.`create_time` DESC;
	
ALTER TABLE `find_dev_test01`.`message` 
ADD COLUMN `status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '状态，0->未读；1->已读，默认：0->未读' AFTER `send_mode`;
	


SELECT
	me.`send_user_id`,
	me.`content`,
	ur.`head_icon`,
	ur.`nick_name`
FROM
	`message` me,
	`user` ur 
WHERE
	me.`send_user_id` = ur.`id` 
	AND me.`reserve_column01` != 'likes' 
	AND me.`recipient_user_id` = 29 
	AND me.`status` = '0' 
ORDER BY
	me.`create_time` DESC;