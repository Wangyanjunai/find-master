package com.potato369.find.dynamic.service.impl;

import com.potato369.find.common.enums.*;
import com.potato369.find.dynamic.service.LikeRecordService;
import com.potato369.find.mbg.mapper.CommentMapper;
import com.potato369.find.mbg.mapper.DynamicInfoMapper;
import com.potato369.find.mbg.mapper.LikeRecordMapper;
import com.potato369.find.mbg.mapper.MessageMapper;
import com.potato369.find.mbg.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service.impl
 * @ClassName LikeRecordServiceImpl
 * @Desc 点赞记录Service类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 09:20
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class LikeRecordServiceImpl implements LikeRecordService {

    private LikeRecordMapper likeRecordMapperReader;

    private LikeRecordMapper likeRecordMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperWriter;

    private MessageMapper messageMapperWriter;

    private CommentMapper commentMapperWriter;

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setLikeRecordMapperWriter(LikeRecordMapper likeRecordMapperWriter) {
        this.likeRecordMapperWriter = likeRecordMapperWriter;
    }

    @Autowired
    public void setDynamicInfoMapperWriter(DynamicInfoMapper dynamicInfoMapperWriter) {
        this.dynamicInfoMapperWriter = dynamicInfoMapperWriter;
    }

    @Autowired
    public void setMessageMapperWriter(MessageMapper messageMapperWriter) {
        this.messageMapperWriter = messageMapperWriter;
    }

    @Autowired
    public void setCommentMapperWriter(CommentMapper commentMapperWriter) {
        this.commentMapperWriter = commentMapperWriter;
    }

    /**
     * 根据用户id和动态内容id查询用户对该条动态内容的点赞记录
     *
     * @param userId        用户id
     * @param dynamicInfoId 动态内容id
     * @return 点赞记录
     */
    @Override
    @Transactional(readOnly = true)
    public LikeRecord findByUserIdAndDynamicInfoId(Long userId, Long dynamicInfoId, String type) {
        LikeRecordExample likeRecordExample = new LikeRecordExample();
        likeRecordExample.setDistinct(true);
        likeRecordExample.setOrderByClause("create_time DESC, id DESC");
        likeRecordExample.createCriteria().andUserIdEqualTo(userId).andDynamicInfoIdEqualTo(dynamicInfoId).andTypeEqualTo(type);
        List<LikeRecord> likeRecordList = this.likeRecordMapperReader.selectByExample(likeRecordExample);
        if (likeRecordList != null && !likeRecordList.isEmpty()) {
            return likeRecordList.get(0);
        }
        return null;
    }

    /**
     * 根据用户id和动态内容id删除或者取消用户对该条动态内容的点赞记录
     *
     * @param userId      用户id
     * @param dynamicInfo 动态内容
     * @return 点赞记录条数
     */
    @Override
    @Transactional
    public int deleteByUserIdAndDynamicInfoId(Long userId, DynamicInfo dynamicInfo) {
        if (dynamicInfo != null) {
            int likes = dynamicInfo.getLikes();
            dynamicInfo.setLikes(likes - 1);
            dynamicInfo.setUpdateTime(new Date());
            this.dynamicInfoMapperWriter.updateByPrimaryKeySelective(dynamicInfo);
        }
        LikeRecordExample likeRecordExample = new LikeRecordExample();
        likeRecordExample.setDistinct(true);
        assert dynamicInfo != null;
        likeRecordExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andDynamicInfoIdEqualTo(dynamicInfo.getId());
        return this.likeRecordMapperWriter.deleteByExample(likeRecordExample);
    }

    /**
     * 根据用户id和动态内容id点赞用户对该条动态内容
     *
     * @param userId      用户id
     * @param dynamicInfo 动态内容
     * @return 点赞记录条数
     */
    @Override
    @Transactional
    public int createByUserIdAndDynamicInfoId(String content, Long userId, DynamicInfo dynamicInfo, LikeRecord likeRecord) {
        int a = 0, b = 0, c = 0;
        if (dynamicInfo != null) {
            int likes = dynamicInfo.getLikes();
            dynamicInfo.setLikes(likes + 1);
            dynamicInfo.setUpdateTime(new Date());
            a = this.dynamicInfoMapperWriter.updateByPrimaryKeySelective(dynamicInfo);
            if (likeRecord == null) {
                likeRecord = new LikeRecord();
                likeRecord.setDynamicInfoId(dynamicInfo.getId());
                likeRecord.setUserId(userId);
                likeRecord.setStatus(LikeStatusEnum.YES.getStatus());
                likeRecord.setType(LikeRecordTypeEnum.Dynamic.getType());
                b = this.likeRecordMapperWriter.insertSelective(likeRecord);
            } else {
                likeRecord.setStatus(LikeStatusEnum.YES.getStatus());
                likeRecord.setUpdateTime(new Date());
                b = this.likeRecordMapperWriter.updateByPrimaryKeySelective(likeRecord);
            }
            //消息记录
            Message messageRecord = new Message();
            messageRecord.setContent(content);//消息内容
            messageRecord.setSendMode(MessageSendModeEnum.PASSIVE.getStatus());//发送方式
            messageRecord.setRecipientUserId(dynamicInfo.getUserId());//接收者用户id
            messageRecord.setSendUserId(userId);//发送者用户id
            messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());//未读
            messageRecord.setReserveColumn01(MessageTypeEnum.Likes.getMessage());//消息类型，点赞->likes
            messageRecord.setReserveColumn02(MessageType2Enum.SEND.getCodeStr());//发送
            messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());//是否删除
            messageRecord.setReserveColumn04(String.valueOf(likeRecord.getId()));//点赞记录id
            c = this.messageMapperWriter.insertSelective(messageRecord);
        }
        return a + b + c;
    }

    /**
     * 根据用户id和评论id点赞用户对该条评论内容
     *
     * @param userId  用户id
     * @param comment 评论
     * @return 点赞记录条数
     */
    @Override
    @Transactional
    public int createByUserIdAndCommentId(String content, Long userId, Comment comment, LikeRecord likeRecord) {
        int a = this.commentMapperWriter.updateByPrimaryKeySelective(comment);
        int b;
        if (Objects.isNull(likeRecord)) {
            likeRecord = new LikeRecord();
            likeRecord.setDynamicInfoId(comment.getId());
            likeRecord.setUserId(userId);
            likeRecord.setStatus(LikeStatusEnum.YES.getStatus());
            likeRecord.setType(LikeRecordTypeEnum.Comment.getType());
            b = this.likeRecordMapperWriter.insertSelective(likeRecord);
        } else {
            likeRecord.setStatus(LikeStatusEnum.YES.getStatus());
            likeRecord.setUpdateTime(new Date());
            b = this.likeRecordMapperWriter.updateByPrimaryKeySelective(likeRecord);
        }
        //消息记录
        Message messageRecord = new Message();
        messageRecord.setContent(content);//消息内容
        messageRecord.setSendMode(MessageSendModeEnum.PASSIVE.getStatus());//发送方式
        messageRecord.setRecipientUserId(comment.getUserId());//接收者用户id
        messageRecord.setSendUserId(userId);//发送者用户id
        messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());//未读
        messageRecord.setReserveColumn01(MessageTypeEnum.Likes.getMessage());//消息类型，点赞->likes
        messageRecord.setReserveColumn02(MessageType2Enum.SEND.getCodeStr());//发送
        messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());//是否删除
        messageRecord.setReserveColumn04(String.valueOf(likeRecord.getId()));//点赞记录id
        int c = this.messageMapperWriter.insertSelective(messageRecord);
        return a + b + c;
    }

    /**
     * 更新动态内容点赞记录状态
     *
     * @param likeRecord  点赞记录
     * @param dynamicInfo 动态内容
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int update(LikeRecord likeRecord, DynamicInfo dynamicInfo) {
        int a = this.dynamicInfoMapperWriter.updateByPrimaryKeySelective(dynamicInfo);
        int b = this.likeRecordMapperWriter.updateByPrimaryKeySelective(likeRecord);
        return a + b;
    }

    /**
     * 更新动态内容点赞记录状态
     *
     * @param likeRecord 点赞记录
     * @param comment    评论
     * @return int
     */
    @Override
    @Transactional(readOnly = false)
    public int updateComment(LikeRecord likeRecord, Comment comment) {
        int a = this.commentMapperWriter.updateByPrimaryKeySelective(comment);
        int b = this.likeRecordMapperWriter.updateByPrimaryKeySelective(likeRecord);
        return a + b;
    }
}
