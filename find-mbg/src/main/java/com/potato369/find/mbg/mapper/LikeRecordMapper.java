package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.LikeRecord;
import com.potato369.find.mbg.model.LikeRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LikeRecordMapper {
    long countByExample(LikeRecordExample example);

    int deleteByExample(LikeRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LikeRecord record);

    int insertSelective(LikeRecord record);

    List<LikeRecord> selectByExample(LikeRecordExample example);

    LikeRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LikeRecord record, @Param("example") LikeRecordExample example);

    int updateByExample(@Param("record") LikeRecord record, @Param("example") LikeRecordExample example);

    int updateByPrimaryKeySelective(LikeRecord record);

    int updateByPrimaryKey(LikeRecord record);
}