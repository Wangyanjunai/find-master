package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.HibernateSequence;
import com.potato369.find.mbg.model.HibernateSequenceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HibernateSequenceMapper {
    long countByExample(HibernateSequenceExample example);

    int deleteByExample(HibernateSequenceExample example);

    int insert(HibernateSequence record);

    int insertSelective(HibernateSequence record);

    List<HibernateSequence> selectByExample(HibernateSequenceExample example);

    int updateByExampleSelective(@Param("record") HibernateSequence record, @Param("example") HibernateSequenceExample example);

    int updateByExample(@Param("record") HibernateSequence record, @Param("example") HibernateSequenceExample example);
}