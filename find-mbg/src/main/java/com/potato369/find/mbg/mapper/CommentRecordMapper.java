package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.CommentRecord;
import com.potato369.find.mbg.model.CommentRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentRecordMapper {
    long countByExample(CommentRecordExample example);

    int deleteByExample(CommentRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommentRecord record);

    int insertSelective(CommentRecord record);

    List<CommentRecord> selectByExample(CommentRecordExample example);

    CommentRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommentRecord record, @Param("example") CommentRecordExample example);

    int updateByExample(@Param("record") CommentRecord record, @Param("example") CommentRecordExample example);

    int updateByPrimaryKeySelective(CommentRecord record);

    int updateByPrimaryKey(CommentRecord record);
}