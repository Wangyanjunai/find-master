package com.potato369.find.user.dao;

import com.potato369.find.mbg.model.Dynamic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("dynamicDao")
public class DynamicDaoUseJdbcTemplate {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public Dynamic getById(Long id) {
        String sql = "select `id`, `user_id`, `nick_name`, `network_mode`, `country`, `province`, `city`, `longitude`, `latitude`, `district` from `dynamic` where `id` = ?";
        RowMapper<Dynamic> mapper = new BeanPropertyRowMapper<>(Dynamic.class);
        Dynamic dynamic = null;
        try {
        	dynamic = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return dynamic;
    }
	
	public Dynamic getByUserId(Long userId) {
        String sql = "select `id`, `user_id`, `nick_name`, `network_mode`, `country`, `province`, `city`, `longitude`, `latitude`, `district` from `dynamic` where `user_id` = ?";
        RowMapper<Dynamic> mapper = new BeanPropertyRowMapper<>(Dynamic.class);
        Dynamic dynamic = null;
        try {
        	dynamic = this.jdbcTemplate.queryForObject(sql, mapper, userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return dynamic;
    }
}
