package com.potato369.find.user.dao;

import com.potato369.find.mbg.model.DynamicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("dynamicInfoDao")
public class DynamicInfoDaoUseJdbcTemplate {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DynamicInfo getById(Long id) {
        String sql = "select `id`, `user_id`, `dynamic_id`, `dynamic_status`, `public_status` from `dynamic_info` where `id` = ?";
        RowMapper<DynamicInfo> mapper = new BeanPropertyRowMapper<>(DynamicInfo.class);
        DynamicInfo dynamicInfo = null;
        try {
            dynamicInfo = this.jdbcTemplate.queryForObject(sql, mapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return dynamicInfo;
    }

    public List<Map<String, Object>> getByUserId(Long userId) {
        String sql = "select `id`, `dynamic_id`, `dynamic_status`, `public_status` from `dynamic_info` where `user_id` = ?";
        RowMapper<DynamicInfo> mapper = new BeanPropertyRowMapper<>(DynamicInfo.class);
        List<Map<String, Object>> dynamicInfoList;
        try {
            dynamicInfoList = this.jdbcTemplate.queryForList(sql, mapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        return dynamicInfoList;
    }

}
