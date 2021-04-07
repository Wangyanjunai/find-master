package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ReportCategory;
import com.potato369.find.mbg.model.ReportCategoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportCategoryMapper {
    long countByExample(ReportCategoryExample example);

    int deleteByExample(ReportCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportCategory record);

    int insertSelective(ReportCategory record);

    List<ReportCategory> selectByExample(ReportCategoryExample example);

    ReportCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReportCategory record, @Param("example") ReportCategoryExample example);

    int updateByExample(@Param("record") ReportCategory record, @Param("example") ReportCategoryExample example);

    int updateByPrimaryKeySelective(ReportCategory record);

    int updateByPrimaryKey(ReportCategory record);
}