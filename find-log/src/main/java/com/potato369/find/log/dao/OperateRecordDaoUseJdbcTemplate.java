package com.potato369.find.log.dao;

import com.potato369.find.mbg.model.OperateRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("operateRecordDao")
public class OperateRecordDaoUseJdbcTemplate {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public OperateRecord getById(Long id) {
        String sql = "select `id`, `user_id`, `status`, `type` from `operate_record` where `id` = ?";
        RowMapper<OperateRecord> mapper = new BeanPropertyRowMapper<>(OperateRecord.class);
        OperateRecord operateRecord = null;
        try {
        	operateRecord = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return operateRecord;
    }
}
