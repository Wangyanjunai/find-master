package com.potato369.find.message.service;

import com.potato369.find.common.vo.LikesMessageVO;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;

/**
 * 消息服务
 * 封装业务功能相关
 */
public interface MessageService {
	/**
	 * 根据用户id查询某个用户最新一条被点赞的消息记录
	 * @param userId 消息接收者用户id
	 * @return
	 */
	LikesMessageVO selectLikesMessage(Long userId);
	
	/**
	 * 根据用户id分页查询某个用户不是被点赞的消息记录
	 * @param userId 消息接收者用户id
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 * @return
	 */
	MessageVO selectNotLikesMessage(Long userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据用户id分页查询某个用户被点赞的消息记录
	 * @param userId 消息接收者用户id
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 * @return
	 */
	MessageVO2 selectLikesMessage(Long userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据用户id分页查询某个用户收到的申请加微信消息记录
	 * @param userId 消息接收者用户id
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 * @return
	 */
	MessageVO selectApplicationsMessage(Long userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 分页查询某个用户发送接收消息记录列表
	 * @param sendUserId 消息发送者用户id
	 * @param recipientUserId 消息接收者用户id
	 * @param pageNum 当前页码
	 * @param pageSize 每页数量
	 */
	MessageVO3 selectMessageRecord(Long sendUserId, Long recipientUserId, Integer pageNum, Integer pageSize);
}
