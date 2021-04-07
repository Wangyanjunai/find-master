package com.potato369.find.admin.dao;

import com.potato369.find.mbg.model.AttacheInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

//AttacheInfo，附件内容dao
@Slf4j
@Repository("attacheInfoDao")
public class AttacheInfoDaoUseJdbcTemplate {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public AttacheInfo getById(Long id) {
		AttacheInfo attacheInfo = null;
		String sql = "select `id`, `status`, `dynamic_info_by`, `file_name`, `data_type`, `create_time`, `update_time` from `attache_info` where `id` = ?";
		RowMapper<AttacheInfo> mapper = new BeanPropertyRowMapper<>(AttacheInfo.class);
		try {
			attacheInfo = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (Exception e) {
			log.error("根据id查询附件信息出错", e);
		}
		return attacheInfo;
	}
}
