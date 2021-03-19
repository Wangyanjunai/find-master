package com.potato369.find.user.dao;

import com.potato369.find.mbg.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * @PackageName com.potato369.find.user.dao
 * @ClassName UserDaoUseJdbcTemplate
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2020/11/21 22:20
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Repository("userDao")
public class UserDaoUseJdbcTemplate {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByPhone(String phone) {
        String sql = "select `id`, `ip`, `country`, `province`, `city`, `gender`, `nick_name`, `head_icon`, `background_icon`, `autograph` from `user` where `phone` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = null;
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, phone);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return user;
    }
    
    public User getById(Long id) {
        String sql = "select `id`, `ip`, `country`, `province`, `city`, `gender`, `nick_name`, `weixin_id`, `phone`, `status`, `head_icon`, `background_icon`, `autograph` from `user` where `id` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = null;
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return user;
    }
    
    public User getUserInfoById(Long id) {
        String sql = "select `id`, `gender`, `grade`, `year`, `month`, `date`,`nick_name`, `head_icon`, `background_icon`, `autograph` from `user` where `id` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = null;
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return user;
    }
    
    public User getweixinIdById(Long id) {
        String sql = "select `weixin_id` from `user` where `id` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        User user = null;
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return user;
    }
}
