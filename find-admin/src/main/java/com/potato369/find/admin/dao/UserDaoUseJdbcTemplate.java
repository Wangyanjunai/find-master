package com.potato369.find.admin.dao;

import com.potato369.find.mbg.model.User;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Repository("userDao")
public class UserDaoUseJdbcTemplate {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getByPhone(String phone) {
    	User user = null;
        String sql = "select `id`, `phone` from `user` where `phone` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, phone);
		} catch (EmptyResultDataAccessException e) {
			log.error("根据手机号码查询用户信息不存在");
		}
        return user;
    }
    
    public User getById(Long id) {
    	User user = null;
        String sql = "select `id`, `phone`, `head_icon`, `background_icon` from `user` where `id` = ?";
        RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
        try {
			user = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			log.error("根据用户id查询用户信息不存在");
		}
        return user;
    }    
}
