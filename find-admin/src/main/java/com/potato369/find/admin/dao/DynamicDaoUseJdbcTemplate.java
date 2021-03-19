package com.potato369.find.admin.dao;

//Dynamic，动态信息dao

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.potato369.find.mbg.model.Dynamic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("dynamicDao")
public class DynamicDaoUseJdbcTemplate {
	
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public Dynamic getById(Long id) {
    	Dynamic dynamic = null;
		String sql = "select `id`, `user_id`, `nick_name`, `imei`, `model`, `sys_name`, `sys_code`, `network_mode`, `country`, `province`, `city`, `district`, `other`, `create_time`, `update_time` from `dynamic` where `id` = ?";
		RowMapper<Dynamic> mapper = new BeanPropertyRowMapper<>(Dynamic.class);
		try {
			dynamic = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			log.error("根据用户id查询用户信息不存在");
		}
		return dynamic;
    }
}
