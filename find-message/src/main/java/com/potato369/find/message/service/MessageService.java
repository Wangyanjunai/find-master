package com.potato369.find.message.service;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.LikesMessageVO;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;

import java.util.Map;

/**
 * 消息服务
 * 封装业务功能相关
 */
public interface MessageService {
    /**
     * 根据用户id查询某个用户最新一条被点赞的消息记录
     *
     * @param userId 消息接收者用户id
     */
    LikesMessageVO selectLikesMessage(Long userId);

    /**
     * 根据用户id分页查询某个用户不是被点赞的消息记录
     *
     * @param userId   消息接收者用户id
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     */
    MessageVO selectNotLikesMessage(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id分页查询某个用户被点赞的消息记录
     *
     * @param userId   消息接收者用户id
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     */
    MessageVO2 selectLikesMessage(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 根据用户id分页查询某个用户收到的申请加微信消息记录
     *
     * @param userId   消息接收者用户id
     * @param pageNum  当前页码
     * @param pageSize 每页数量
     */
    MessageVO selectApplicationsMessage(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 分页查询某个用户发送接收消息记录列表
     *
     * @param sendUserId      消息发送者用户id
     * @param recipientUserId 消息接收者用户id
     * @param pageNum         当前页码
     * @param pageSize        每页数量
     */
    MessageVO3 selectMessageRecord(Long sendUserId, Long recipientUserId, Integer pageNum, Integer pageSize);

    /**
     * 发送消息
     *
     * @param sendUserId      消息发送者用户id
     * @param recipientUserId 消息接收者用户id
     * @param messageId       消息id
     * @param content         消息内容
     */
    CommonResult<Map<String, Object>> sendMessageAndPush(Long sendUserId, Long recipientUserId, Long messageId, String content);

    /**
     * 标记全部消息已读
     *
     * @param recipientUserId 消息接收者用户id
     */
    CommonResult<Map<String, Object>> allRead(Long recipientUserId);

    /**
     * 删除申请加微信消息记录
     *
     * @param recipientUserId 消息接收者用户id
     * @param messageId       消息记录id
     */
    CommonResult<Map<String, Object>> delete(Long recipientUserId, Long messageId);

    /**
     * @param recipientUserId 消息接收者用户id
     * @param messageId       消息id
     */

    CommonResult<Map<String, Object>> deleteLikes(Long recipientUserId, Long messageId);

    /**
     * 被申请者回复申请者申请加微信消息记录
     *
     * @param applicantsUserId 被申请加微信者用户id
     * @param applicantUserId  申请加微信者用户id
     * @param messageId        回复的消息id
     * @param type             回复类型，0->拒绝，1->同意
     * @param content          回复的消息内容
     * @param weChatId         回复的微信id
     */
    CommonResult<Map<String, Object>> replyApplications(Long applicantsUserId, Long applicantUserId, Long messageId, String type, String content, String weChatId);
}
