package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.BlacklistRecord;
import com.potato369.find.mbg.model.BlacklistRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlacklistRecordMapper {
    long countByExample(BlacklistRecordExample example);

    int deleteByExample(BlacklistRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(BlacklistRecord record);

    int insertSelective(BlacklistRecord record);

    List<BlacklistRecord> selectByExample(BlacklistRecordExample example);

    BlacklistRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") BlacklistRecord record, @Param("example") BlacklistRecordExample example);

    int updateByExample(@Param("record") BlacklistRecord record, @Param("example") BlacklistRecordExample example);

    int updateByPrimaryKeySelective(BlacklistRecord record);

    int updateByPrimaryKey(BlacklistRecord record);
}