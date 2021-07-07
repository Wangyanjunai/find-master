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

-- 查询点赞发送者和接收者列表
SELECT
	a.`id`,
	a.`send_user_id`,
	a.`recipient_user_id`,
	a.`content`,
	a.`send_mode`,
	a.`status`,
	a.`create_time`
FROM
	(
	SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
	FROM
		`message` 
	WHERE
		`reserve_column01` = 'applications' 
		AND `send_user_id` = 29 
UNION
	SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
	FROM
		`message` 
	WHERE
		`reserve_column01` = 'applications' 
		AND `recipient_user_id` = 29 
		) AS a 
	GROUP BY
		a.`send_user_id`,
		a.`recipient_user_id` 
	ORDER BY
		a.`create_time` DESC;
	
-- 查询点赞消息记录	
SELECT
	a.`id`,
	a.`send_user_id`,
	a.`recipient_user_id`,
	a.`content`,
	a.`send_mode`,
	a.`status`,
	a.`create_time` 
FROM
	(
SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
FROM
	`message` 
WHERE
	`reserve_column01` = 'applications' 
	AND `send_user_id` = 60 
	AND `recipient_user_id` = 29 UNION
SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
FROM
	`message` 
WHERE
	`reserve_column01` = 'applications' 
	AND `send_user_id` = 29 
	AND `recipient_user_id` = 60 
	) AS a 
ORDER BY
	a.`create_time` DESC;
		
-- 查询点赞消息记录条数	
SELECT
	COUNT( 1 ) 
FROM
	(
SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
FROM
	`message` 
WHERE
	`reserve_column01` = 'applications' 
	AND `send_user_id` = 60 
	AND `recipient_user_id` = 29 UNION
SELECT
	`id`,
	`send_user_id`,
	`recipient_user_id`,
	`content`,
	`send_mode`,
	`status`,
	`create_time` 
FROM
	`message` 
WHERE
	`reserve_column01` = 'applications' 
	AND `send_user_id` = 29 
	AND `recipient_user_id` = 60 
	) AS a 
WHERE
	a.`status` = '0' 
ORDER BY
	a.`create_time` DESC;
	
	
SELECT
	count( 1 ) 
FROM
	(
SELECT
	`user_id` 
FROM
	`dynamic_info` 
WHERE
	`id` IN (
SELECT
	`dynamic_info_id` 
FROM
	`application_record` 
WHERE
	`create_time` >= STR_TO_DATE( DATE_FORMAT( NOW( ), '%Y-%m-%d' ), '%Y-%m-%d %H:%i:%s' ) 
	AND `create_time` <= DATE_ADD( DATE_ADD( STR_TO_DATE( DATE_FORMAT( NOW( ), '%Y-%m-%d' ), '%Y-%m-%d %H:%i:%s' ), INTERVAL 1 DAY ), INTERVAL - 1 SECOND ) 
GROUP BY
	`dynamic_info_id`
	) 
GROUP BY `user_id`
    ) AS a
WHERE a.`user_id` = 8;

select now()
from dual;

select count(1)
from `user`;

select *
from `user`
where `id` = 3;

select *
from `screen_setting`;



select now()
from dual;
select *
from user;

select count(1)
from message
where `send_user_id` = 70
  and `recipient_user_id` = 20
  and `reserve_column01` = 'applications'
  and `reserve_column02` = '1'
  and `send_mode` = '0';

SELECT DISTINCT me.`content`,
                me.`send_user_id`,
                u.`head_icon`,
                u.`nick_name`
FROM `message` me,
     `user` u
WHERE me.`send_user_id` = u.`id`
  AND me.`reserve_column01` != 'likes'
  AND me.`recipient_user_id` = 20
ORDER BY me.`create_time` desc, me.`update_time` desc;

SELECT DISTINCT me.`id`                     AS `message_id`,
                u.`id`                      AS `id`,
                u.`head_icon`               AS `head_icon`,
                me.`content`                AS `content`,
                dynamic_info.`attache_type` AS `attache_type`,
                attache_info.`file_name`    AS `file_name`
FROM `like_record` AS record,
     `user` AS u,
     `dynamic_info`,
     `attache_info`,
     `message` AS me
WHERE record.`user_id` = u.`id`
  AND dynamic_info.`id` = record.`dynamic_info_id`
  AND attache_info.`dynamic_info_by` = dynamic_info.`id`
  AND me.`send_user_id` = u.`id`
  AND me.`recipient_user_id` = dynamic_info.`user_id`
  AND me.send_user_id = record.`user_id`
  AND me.`reserve_column01` = 'likes'
  AND me.`reserve_column03` = '0'
  AND record.`status` = '0'
  AND dynamic_info.user_id = 20
GROUP BY u.`id`,
         me.`id`,
         me.`create_time`
ORDER BY me.`create_time` DESC;


select *
from dynamic_info
where id = 92;

-- 查询动态内容拥有者用户信息
select *
from user
where id = 29;

-- 查询申请加微信者用户信息

select *
from user
where id = 60;

-- 查询今天申请者加动态内容拥有者几次微信
SELECT COUNT(a.`user_id`)
FROM (
         SELECT `user_id`
         FROM `dynamic_info`
         WHERE `id` IN (
             SELECT `dynamic_info_id`
             FROM `application_record`
             WHERE `create_time` >= STR_TO_DATE(DATE_FORMAT(NOW(), '%Y-%m-%d'), '%Y-%m-%d %H:%i:%s')
               AND `create_time` <=
                   DATE_ADD(DATE_ADD(STR_TO_DATE(DATE_FORMAT(NOW(), '%Y-%m-%d'), '%Y-%m-%d %H:%i:%s'), INTERVAL 1 DAY),
                            INTERVAL - 1 SECOND)
               AND `user_id` = 60)) AS a;

-- 查询申请者加微信是否已经回复了的次数
select *
from `message`
where `send_user_id` = 60
  and `recipient_user_id` = 29
  and `reserve_column01` = 'applications'
  and `reserve_column02` = '0';


select count(1)
from `message`
where `send_user_id` = 60
  and `recipient_user_id` = 29
  and `reserve_column01` = 'applications'
  and `reserve_column02` = '1'
  and `send_mode` = '0'
  and `reserve_column04` = 42;


select *
from dynamic_info
where user_id = 83;


select *
from message
where send_user_id = 139
  and recipient_user_id = 138
union
select *
from message
where send_user_id = 138
  and recipient_user_id = 139
order by create_time;

select *
from user;


select distinct id,
                send_user_id,
                recipient_user_id,
                send_mode,
                status,
                create_time,
                update_time,
                reserve_column01,
                reserve_column02,
                reserve_column03,
                reserve_column04
from message
WHERE (send_user_id = 139 and recipient_user_id = 138 and reserve_column02 = 1 and reserve_column04 = 18)
order by id, create_time;


SELECT `id`,
       `send_user_id`,
       `recipient_user_id`,
       `send_mode`,
       `status`,
       `create_time`
FROM `message`
WHERE `reserve_column01` != 'likes'
  AND `recipient_user_id` = 137
  AND `reserve_column03` = '0'
GROUP BY `recipient_user_id`;



select me.*
from `application_record` record,
     `message` me
where record.`user_id` = me.`send_user_id`
  and record.`reserve_column01` = me.`recipient_user_id`
  and me.`send_user_id` = 54
group by me.`send_user_id`
union all
select me.*
from `application_record` record,
     `message` me
where record.`user_id` = me.`send_user_id`
  and record.`reserve_column01` = me.`recipient_user_id`
  and me.`recipient_user_id` = 54
order by `create_time` desc;

select *
from `message`;


select me.*
from `application_record` record,
     `message` me
where record.`user_id` = me.`send_user_id`
  and record.`reserve_column01` = me.`recipient_user_id`
  and me.`recipient_user_id` = 54;


select me.*
from `application_record` record,
     `message` me
where record.`user_id` = me.`send_user_id`
  and record.`reserve_column01` = me.`recipient_user_id`
  and me.`send_user_id` = 54
group by me.`send_user_id`;



select `user_id`,
       `reserve_column01`,
       `create_time`
from `application_record`
where `user_id` = 137
union all
select `user_id`,
       `reserve_column01`,
       `create_time`
from `application_record`
where `reserve_column01` = 137
group by `user_id`
order by `create_time` desc;


select *
from `message`
where `send_user_id` = 137
  and recipient_user_id = 53
  and `reserve_column01` != 'likes'
  and `reserve_column03` = '0'
union all
select *
from message
where `send_user_id` = 53
  and `recipient_user_id` = 137
  and `reserve_column01` != 'likes'
  and `reserve_column03` = '0'
order by `create_time` desc;


select (select count(0)
        from `message`
        where `send_user_id` = 136
          and recipient_user_id = 53
          and `reserve_column01` != 'likes'
          and `reserve_column03` = '0'
          and `status` = '0'
          and `send_user_id` != 53) +
       (select count(0)
        from message
        where `send_user_id` = 53
          and `recipient_user_id` = 136
          and `reserve_column01` != 'likes'
          and `reserve_column03` = '0'
          and `status` = '0'
          and `send_user_id` != 53) as count;



select *
from dynamic_info
where user_id = 53;

select *
from message;

select *
from user;

select *
from `dynamic_info`
where `user_id` = 139;

SELECT *
FROM `dynamic_info`
WHERE `id` = 607;

SELECT *
FROM `like_record`
WHERE `dynamic_info_id` = 607;

SELECT *
FROM `message`
WHERE `send_user_id` = 144
  AND `recipient_user_id` = 138
  AND `reserve_column01` = 'likes';


select *
from `message`;
desc `tag`;
select now()
from dual;

select *
from `user`
order by `create_time` desc;
select *
from `dynamic`
order by `create_time` desc;
select *
from `dynamic_info`
order by `create_time` desc;
select *
from `attache_info`
order by `create_time` desc;


select *
from `user`
where `id` = 149;
select *
from `dynamic`
where `user_id` = 149;
select *
from `dynamic_info`
where `user_id` = 149;
select *
from `attache_info`
where `dynamic_info_by` = 709;


select *
from `user`
where `id` = 150;
select *
from `dynamic`
where `user_id` = 150;
select *
from `dynamic_info`
where `user_id` = 150;
select *
from `attache_info`
where `dynamic_info_by` = 710;

select `nick_name`
from `user`
where `id` = 70;

select `weixin_id`
from `user`
where `id` = 70;

select `autograph`
from user
where `id` = 70;

select `year`, `month`, `date`
from `user`
where `id` = 70;

select `month`, `date`, `constellation`
from user
where `id` = 70;

select `profession_id`, `tag1`, `tag2`, `tag3`, `tag4`, `tag5`
from `user`
where `id` = 70;

select *
from `tag`;

select *
from `user`
where `id` = 70;

select *
from `user` ur
where ur.`gender` = '0'
  and str_to_date(concat_ws('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') <= '2005-06-18 00:00:00'
  and str_to_date(concat_ws('-', ur.`year`, ur.`month`, ur.`date`), '%Y-%m-%d %H:%M:%s') >= '1991-06-18 00:00:00'
order by ur.`create_time` desc, ur.`update_time` desc;


select ai.`file_name`
from `attache_info` ai
where ai.`dynamic_info_by` =
      (select dy.`id`
       from `dynamic_info` dy
       where dy.`user_id` = 70
         and dy.`attache_type` = '0'
         and dy.attache_number > 0
       order by dy.`create_time` desc
       limit 1)
  and ai.`data_type` = '0'
  and ai.`file_name` is not null
limit 1;

select *
from `user`
where `district` is null
order by `id`;

select *
from `user`
where `city` = '成都市'
  and `district` is null
order by `id`;
select *
from `user`
where `city` = '成都市'
  and `district` is not null
order by `id`;

select *
from `user`
where `district` = '宝安区';

select *
from `user`
where `city` = '深圳市'
  and `district` = '福田区';
/*
http://www.jsons.cn/lngcode
万州区	108.40869，30.80788
涪陵区	107.39007，29.70292
渝中区	106.56901，29.55279
大渡口区	106.48262，29.48447
江北区	106.57434，29.60658
沙坪坝区 106.45752，29.54113
九龙坡区	106.51107，29.50197
南岸区	106.56347，29.52311	查看详情
重庆重庆市	北碚区	106.39614，29.80574	查看详情
重庆重庆市	綦江区	106.926779，28.960656	查看详情
重庆重庆市	大足区	105.768121，29.484025	查看详情
重庆重庆市	渝北区	106.6307，29.7182	查看详情
重庆重庆市	巴南区	106.52365，29.38311	查看详情
重庆重庆市	黔江区	108.7709，29.5332	查看详情
重庆重庆市	长寿区	107.08166，29.85359	查看详情
重庆重庆市	江津区	106.25912，29.29008	查看详情
重庆重庆市	合川区	106.27633，29.97227	查看详情
重庆重庆市	永川区	105.927，29.35593	查看详情
重庆重庆市	南川区	107.09936，29.15751	查看详情
重庆重庆市	璧山区	106.231126，29.593581	查看详情
重庆重庆市	铜梁区	106.054948，29.839944	查看详情
重庆重庆市	潼南县	105.84005，30.1912	查看详情
重庆重庆市	荣昌县	105.59442，29.40488	查看详情
重庆重庆市	梁平县	107.79998，30.67545	查看详情
重庆重庆市	城口县	108.66513，31.94801	查看详情
重庆重庆市	丰都县	107.73098，29.86348	查看详情
重庆重庆市	垫江县	107.35446，30.33359	查看详情
重庆重庆市	武隆县	107.7601，29.32548	查看详情
重庆重庆市	忠县	108.03689，30.28898	查看详情
重庆重庆市	开县	108.39306，31.16095	查看详情
重庆重庆市	云阳县	108.69726，30.93062	查看详情
重庆重庆市	奉节县	109.46478，31.01825	查看详情
重庆重庆市	巫山县	109.87814，31.07458	查看详情
重庆重庆市	巫溪县	109.63128，31.39756	查看详情
重庆重庆市	石柱土家族自治县	108.11389，30.00054	查看详情
重庆重庆市	秀山土家族苗族自治县	108.98861，28.45062	查看详情
重庆重庆市	酉阳土家族苗族自治县	108.77212，28.8446	查看详情
重庆重庆市	彭水苗族土家族自治县	108.16638，29.29516	查看详情


http://www.jsons.cn/lngcode
省份/城市	    地区	        经度       纬度	    查看详情
广东省	    广州市	113.280637，23.125178   查看详情
广东省广州市	荔湾区	113.2442，23.12592	    查看详情
广东省广州市	越秀区	113.26683，23.12897	    查看详情
广东省广州市	海珠区	113.26197，23.10379	    查看详情
广东省广州市	天河区	113.36112，23.12467	    查看详情
广东省广州市	白云区	113.27307，23.15787	    查看详情
广东省广州市	黄埔区	113.45895，23.10642	    查看详情
广东省广州市	番禺区	113.38397，22.93599	    查看详情
广东省广州市	花都区	113.22033，23.40358	    查看详情
广东省广州市	南沙区	113.60845，22.77144	    查看详情
广东省广州市	从化区	113.587386，23.545283	查看详情
广东省广州市	增城区	113.829579，23.290497	查看详情


省份/城市	    地区	        经度        纬度	        查看详情
江西省	    南昌市	115.892151   28.676493	    查看详情
江西省南昌市	东湖区	115.8988     28.68505	    查看详情
江西省南昌市	西湖区	115.87728，  28.65688	    查看详情
江西省南昌市	青云谱区	115.915，    28.63199	    查看详情
江西省南昌市	湾里区	115.73104，  28.71529	    查看详情
江西省南昌市	青山湖区	115.9617，   28.68206	    查看详情
江西省南昌市	南昌县	115.94393，  28.54559	    查看详情
江西省南昌市	新建县	115.81546，  28.69248	    查看详情
江西省南昌市	安义县	115.54879，  28.84602	    查看详情
江西省南昌市	进贤县	116.24087，  28.37679	    查看详情


浙江省	杭州市	120.153576，30.287459	查看详情
浙江省杭州市	上城区	120.16922，30.24255	查看详情
浙江省杭州市	下城区	120.18096，30.28153	查看详情
浙江省杭州市	江干区	120.20517，30.2572	查看详情
浙江省杭州市	拱墅区	120.14209，30.31968	查看详情
浙江省杭州市	西湖区	120.12979，30.25949	查看详情
浙江省杭州市	滨江区	120.21194，30.20835	查看详情
浙江省杭州市	萧山区	120.26452，30.18505	查看详情
浙江省杭州市	余杭区	120.29986，30.41829	查看详情
浙江省杭州市	桐庐县	119.68853，29.79779	查看详情
浙江省杭州市	淳安县	119.04257，29.60988	查看详情
浙江省杭州市	建德市	119.28158，29.47603	查看详情
浙江省杭州市	富阳区	119.96041，30.04878	查看详情
浙江省杭州市	临安市	119.72473，30.23447	查看详情


浙江省温州市	鹿城区	120.65505，28.01489	查看详情
浙江省温州市	龙湾区	120.83053，27.91284	查看详情
浙江省温州市	瓯海区	120.63751，28.00714	查看详情
浙江省温州市	洞头县	121.15606，27.83634	查看详情
浙江省温州市	永嘉县	120.69317，28.15456	查看详情
浙江省温州市	平阳县	120.56506，27.66245	查看详情
浙江省温州市	苍南县	120.42608，27.51739	查看详情
浙江省温州市	文成县	120.09063，27.78678	查看详情
浙江省温州市	泰顺县	119.7182，27.55694	查看详情
浙江省温州市	瑞安市	120.65466，27.78041	查看详情
浙江省温州市	乐清市	120.9617，28.12404	查看详情

浙江省宁波市	海曙区	121.55106，29.85977	查看详情
浙江省宁波市	江东区	121.57028，29.86701	查看详情
浙江省宁波市	江北区	121.55681，29.88776	查看详情
浙江省宁波市	北仑区	121.84408，29.90069	查看详情
浙江省宁波市	镇海区	121.71615，29.94893	查看详情
浙江省宁波市	鄞州区	121.54754，29.81614	查看详情
浙江省宁波市	象山县	121.86917，29.47758	查看详情
浙江省宁波市	宁海县	121.43072，29.2889	查看详情
浙江省宁波市	余姚市	121.15341，30.03867	查看详情
浙江省宁波市	慈溪市	121.26641，30.16959	查看详情
浙江省宁波市	奉化市	121.41003，29.65537	查看详情

江西省九江市	庐山区	115.98904，29.67177	查看详情
江西省九江市	浔阳区	115.98986，29.72786	查看详情
江西省九江市	九江县	115.91128，29.60852	查看详情
江西省九江市	武宁县	115.10061，29.2584	查看详情
江西省九江市	修水县	114.54684，29.02539	查看详情
江西省九江市	永修县	115.80911，29.02093	查看详情
江西省九江市	德安县	115.75601，29.31341	查看详情
江西省九江市	星子县	116.04492，29.44608	查看详情
江西省九江市	都昌县	116.20401，29.27327	查看详情
江西省九江市	湖口县	116.21853，29.73818	查看详情
江西省九江市	彭泽县	116.55011，29.89589	查看详情
江西省九江市	瑞昌市	115.66705，29.67183	查看详情
江西省九江市	共青城市	115.801939，29.238785	查看详情

山东省济南市	历下区	117.0768，36.66661	查看详情
山东省济南市	市中区	116.99741，36.65101	查看详情
山东省济南市	槐荫区	116.90075，36.65136	查看详情
山东省济南市	天桥区	116.98749，36.67801	查看详情
山东省济南市	历城区	117.06509，36.67995	查看详情
山东省济南市	长清区	116.75192，36.55352	查看详情
山东省济南市	平阴县	116.45587，36.28955	查看详情
山东省济南市	济阳县	117.17327，36.97845	查看详情
山东省济南市	商河县	117.15722，37.31119	查看详情
山东省济南市	章丘市	117.53677，36.71392	查看详情


江苏省南京市	玄武区	118.79772，32.04856	查看详情
江苏省南京市	秦淮区	118.79815，32.01112	查看详情
江苏省南京市	建邺区	118.76641，32.03096	查看详情
江苏省南京市	鼓楼区	118.76974，32.06632	查看详情
江苏省南京市	浦口区	118.62802，32.05881	查看详情
江苏省南京市	栖霞区	118.88064，32.11352	查看详情
江苏省南京市	雨花台区	118.7799，31.99202	查看详情
江苏省南京市	江宁区	118.8399，31.95263	查看详情
江苏省南京市	六合区	118.8413，32.34222	查看详情
江苏省南京市	溧水区	119.028732，31.653061	查看详情
江苏省南京市	高淳区	118.87589，31.327132	查看详情


陕西省西安市	新城区	108.9608，34.26641	查看详情
陕西省西安市	碑林区	108.93426，34.2304	查看详情
陕西省西安市	莲湖区	108.9401，34.26709	查看详情
陕西省西安市	灞桥区	109.06451，34.27264	查看详情
陕西省西安市	未央区	108.94683，34.29296	查看详情
陕西省西安市	雁塔区	108.94866，34.22245	查看详情
陕西省西安市	阎良区	109.22616，34.66221	查看详情
陕西省西安市	临潼区	109.21417，34.36665	查看详情
陕西省西安市	长安区	108.94586，34.15559	查看详情
陕西省西安市	蓝田县	109.32339，34.15128	查看详情
陕西省西安市	周至县	108.22207，34.16337	查看详情
陕西省西安市	户县	108.60513，34.10856	查看详情
陕西省西安市	高陵区	109.08816，34.53483	查看详情

河南省郑州市	中原区	113.61333，34.74827	查看详情
河南省郑州市	二七区	113.63931，34.72336	查看详情
河南省郑州市	管城回族区	113.67734，34.75383	查看详情
河南省郑州市	金水区	113.66057，34.80028	查看详情
河南省郑州市	上街区	113.30897，34.80276	查看详情
河南省郑州市	惠济区	113.61688，34.86735	查看详情
河南省郑州市	中牟县	113.97619，34.71899	查看详情
河南省郑州市	巩义市	113.022，34.74794	查看详情
河南省郑州市	荥阳市	113.38345，34.78759	查看详情
河南省郑州市	新密市	113.3869，34.53704	查看详情
河南省郑州市	新郑市	113.73645，34.3955	查看详情
河南省郑州市	登封市	113.05023，34.45345	查看详情


安徽省合肥市	瑶海区	117.30947，31.85809	查看详情
安徽省合肥市	庐阳区	117.26452，31.87874	查看详情
安徽省合肥市	蜀山区	117.26104，31.85117	查看详情
安徽省合肥市	包河区	117.30984，31.79502	查看详情
安徽省合肥市	长丰县	117.16549，32.47959	查看详情
安徽省合肥市	肥东县	117.47128，31.88525	查看详情
安徽省合肥市	肥西县	117.16845，31.72143	查看详情
安徽省合肥市	庐江县	117.289844，31.251488	查看详情
安徽省合肥市	巢湖市	117.874155，31.600518	查看详情

湖北省武汉市	江岸区	114.30943，30.59982	查看详情
湖北省武汉市	江汉区	114.27093，30.60146	查看详情
湖北省武汉市	硚口区	114.26422，30.56945	查看详情
湖北省武汉市	汉阳区	114.27478，30.54915	查看详情
湖北省武汉市	武昌区	114.31589，30.55389	查看详情
湖北省武汉市	青山区	114.39117，30.63427	查看详情
湖北省武汉市	洪山区	114.34375，30.49989	查看详情
湖北省武汉市	东西湖区	114.13708，30.61989	查看详情
湖北省武汉市	汉南区	114.08462，30.30879	查看详情
湖北省武汉市	蔡甸区	114.02929，30.58197	查看详情
湖北省武汉市	江夏区	114.31301，30.34653	查看详情
湖北省武汉市	黄陂区	114.37512，30.88151	查看详情
湖北省武汉市	新洲区	114.80136，30.84145	查看详情


四川省成都市	锦江区	104.08347，30.65614	查看详情
四川省成都市	青羊区	104.06151，30.67387	查看详情
四川省成都市	金牛区	104.05114，30.69126	查看详情
四川省成都市	武侯区	104.04303，30.64235	查看详情
四川省成都市	成华区	104.10193，30.65993	查看详情
四川省成都市	龙泉驿区	104.27462，30.55658	查看详情
四川省成都市	青白江区	104.251，  30.87841	查看详情
四川省成都市	新都区	104.15921，30.82314	查看详情
四川省成都市	温江区	103.84881，30.68444	查看详情
四川省成都市	金堂县	104.41195，30.86195	查看详情
四川省成都市	双流县	103.92373，30.57444	查看详情
四川省成都市	郫县	    103.88717，30.81054	查看详情
四川省成都市	大邑县	103.52075，30.58738	查看详情
四川省成都市	蒲江县	103.50616，30.19667	查看详情
四川省成都市	新津县	103.8114，30.40983	查看详情
四川省成都市	都江堰市	103.61941，30.99825	查看详情
四川省成都市	彭州市	103.958，30.99011	查看详情
四川省成都市	邛崃市	103.46283，30.41489	查看详情
四川省成都市	崇州市	103.67285，30.63014	查看详情
*/
update `user`
set `district` = '南山区'
where `city` = '深圳市'
  and `id` < 16;
update `user`
set `district`  = '龙华新区',
    `longitude` = 114.036585,
    `latitude`  = 22.68695
where `id` >= 135
  and `id` <= 138;

select *
from `user`
where `district` is null
order by `id`;

select *
from `user`
where `city` = '常州市'
  and `district` is null
order by `id`;
select *
from `user`
where `city` = '常州市'
  and `district` is not null
order by `id`;

select *
from `user`
where `district` = '宝安区';

select *
from `user`
where `city` = '深圳市'
  and `district` = '福田区';
  
  
select * from `industrys`;  
select * from `professions`;  