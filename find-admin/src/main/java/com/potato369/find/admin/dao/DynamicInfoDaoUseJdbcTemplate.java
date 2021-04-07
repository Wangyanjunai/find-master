package com.potato369.find.admin.dao;

import com.potato369.find.mbg.model.DynamicInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//DynamicInfo，动态内容dao
@Slf4j
@Repository("dynamicInfoDao")
public class DynamicInfoDaoUseJdbcTemplate {
	
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    public DynamicInfo getById(Long id) {
    	DynamicInfo dynamicInfo = null;
		String sql = "select `id`, `user_id`, `content`, `dynamic_status`, `public_status`, `likes`, `applications`, `shares`, `attache_type`, `attache_number`, `create_time`, `update_time` from `dynamic_info` where `id` = ?";
		RowMapper<DynamicInfo> mapper = new BeanPropertyRowMapper<>(DynamicInfo.class);
		try {
			dynamicInfo = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			log.error("根据用户id查询用户信息不存在");
		}
		return dynamicInfo;
    }
}
