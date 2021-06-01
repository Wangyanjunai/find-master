package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.Professions;
import com.potato369.find.mbg.model.ProfessionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProfessionsMapper {
    long countByExample(ProfessionsExample example);

    int deleteByExample(ProfessionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Professions record);

    int insertSelective(Professions record);

    List<Professions> selectByExample(ProfessionsExample example);

    Professions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Professions record, @Param("example") ProfessionsExample example);

    int updateByExample(@Param("record") Professions record, @Param("example") ProfessionsExample example);

    int updateByPrimaryKeySelective(Professions record);

    int updateByPrimaryKey(Professions record);
}