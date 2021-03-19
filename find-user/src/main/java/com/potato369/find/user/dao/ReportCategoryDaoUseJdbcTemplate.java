package com.potato369.find.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.potato369.find.mbg.model.ReportCategory;

@Repository("eportCategoryDao")
public class ReportCategoryDaoUseJdbcTemplate {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	public ReportCategory getById(Long id) {
        String sql = "select `id`, `content`, `status` from `report_category` where `id` = ?";
        RowMapper<ReportCategory> mapper = new BeanPropertyRowMapper<>(ReportCategory.class);
        ReportCategory reportCategory = null;
        try {
        	reportCategory = this.jdbcTemplate.queryForObject(sql, mapper, id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
        return reportCategory;
    }
}
