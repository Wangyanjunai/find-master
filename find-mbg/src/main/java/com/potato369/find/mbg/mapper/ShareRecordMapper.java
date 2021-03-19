package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ShareRecord;
import com.potato369.find.mbg.model.ShareRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShareRecordMapper {
    long countByExample(ShareRecordExample example);

    int deleteByExample(ShareRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShareRecord record);

    int insertSelective(ShareRecord record);

    List<ShareRecord> selectByExample(ShareRecordExample example);

    ShareRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShareRecord record, @Param("example") ShareRecordExample example);

    int updateByExample(@Param("record") ShareRecord record, @Param("example") ShareRecordExample example);

    int updateByPrimaryKeySelective(ShareRecord record);

    int updateByPrimaryKey(ShareRecord record);
}