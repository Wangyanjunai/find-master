package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.LikesMessageRecord;
import com.potato369.find.mbg.model.LikesRecord;
import com.potato369.find.mbg.model.Message;
import com.potato369.find.mbg.model.MessageExample;
import com.potato369.find.mbg.model.NotLikesMessageRecord;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Long id);
    
    int delectApplicationMessageRecordByUserId(@Param("userId") Long userId, @Param("messageId") Long messageId);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Long id);
    
    List<LikesRecord> selectLikesRecordByUserId(@Param("userId") Long userId);
    
    List<NotLikesMessageRecord> selectUnLikesRecordByUserId(@Param("userId") Long userId);
    
    List<LikesMessageRecord> selectLikesMessageRecordByUserId(@Param("userId") Long userId);
    
    List<Message> selectApplicationMessageRecordByUserId(@Param("userId") Long userId);
    
    long selectMessageRecordCount(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);
    
    List<Message> selectMessageRecord(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
    
    int updateApplicationMessage(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);
    
    int updateLikesMessage(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);
    
    int updateAllByUserId(@Param("recipientUserId") Long recipientUserId);
}