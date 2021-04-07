package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ReportInfo;
import com.potato369.find.mbg.model.ReportInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReportInfoMapper {
    long countByExample(ReportInfoExample example);

    int deleteByExample(ReportInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReportInfo record);

    int insertSelective(ReportInfo record);

    List<ReportInfo> selectByExampleWithBLOBs(ReportInfoExample example);

    List<ReportInfo> selectByExample(ReportInfoExample example);

    ReportInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReportInfo record, @Param("example") ReportInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") ReportInfo record, @Param("example") ReportInfoExample example);

    int updateByExample(@Param("record") ReportInfo record, @Param("example") ReportInfoExample example);

    int updateByPrimaryKeySelective(ReportInfo record);

    int updateByPrimaryKeyWithBLOBs(ReportInfo record);

    int updateByPrimaryKey(ReportInfo record);
}