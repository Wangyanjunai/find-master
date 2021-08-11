package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.FeedbackRecord;
import com.potato369.find.mbg.model.FeedbackRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackRecordMapper {
    long countByExample(FeedbackRecordExample example);

    int deleteByExample(FeedbackRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FeedbackRecord record);

    int insertSelective(FeedbackRecord record);

    List<FeedbackRecord> selectByExample(FeedbackRecordExample example);

    FeedbackRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FeedbackRecord record, @Param("example") FeedbackRecordExample example);

    int updateByExample(@Param("record") FeedbackRecord record, @Param("example") FeedbackRecordExample example);

    int updateByPrimaryKeySelective(FeedbackRecord record);

    int updateByPrimaryKey(FeedbackRecord record);
}