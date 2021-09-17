package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExampleWithBLOBs(MessageExample example);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Long id);

    List<LikesRecord> selectLikesRecordByUserId(@Param("userId") Long userId);

    List<NotLikesMessageRecord> selectUnLikesRecordByUserId(@Param("userId") Long userId);

    int countUnLikesRecordByUserId(@Param("userId") Long userId);

    List<LikesMessageRecord> selectLikesMessageRecordByUserId(@Param("userId") Long userId);

    List<Message> selectApplicationMessageRecordByUserId(@Param("userId") Long userId);

    List<Message> selectApplicationMessageRecordByUserId2(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    List<Message> selectAllLikesMessageRecord(@Param("userId") Long userId);

    List<Message> selectAllCommentsMessageRecord(@Param("userId") Long userId);

    List<Message> selectMessageRecordCount(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    long selectUnReadMessageCount(@Param("recipientUserId") Long recipientUserId);

    List<Message> selectMessageRecord(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    int countByUserId(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId, @Param("messageId") Long messageId);

    long countByUserId2(@Param("recipientUserId") Long recipientUserId, @Param("sendUserId") Long sendUserId);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExampleWithBLOBs(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);

    int updateApplicationMessage(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    int updateLikesMessage(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    int updateCommentsMessage(@Param("sendUserId") Long sendUserId, @Param("recipientUserId") Long recipientUserId);

    int updateAllByUserId(@Param("recipientUserId") Long recipientUserId);

    /**
     * 查询用户未读点赞条数
     *
     * @param recipientUserId
     * @return
     */
    int countUnreadLikesByUserId(@Param("recipientUserId") Long recipientUserId);
}