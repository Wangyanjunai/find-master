package com.potato369.find.message.service;

import com.potato369.find.common.vo.LikesMessageVO;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;

/**
 * 消息服务
 * 封装业务功能相关
 */
public interface MessageService {
	/**
	 * 根据用户id查询某个用户被点赞的消息记录
	 * @param userId	用户id
	 * @return
	 */
	LikesMessageVO selectLikesMessage(Long userId);
	
	/**
	 * 根据用户id查询某个用户不是被点赞的消息记录
	 * @param userId	用户id
	 * @return
	 */
	MessageVO selectNotLikesMessage(Long userId, Integer pageNum, Integer pageSize);
	
	/**
	 * 根据用户id查询某个用户被点赞的消息记录
	 * @param userId	用户id
	 * @return
	 */
	MessageVO2 selectLikesMessage(Long userId, Integer pageNum, Integer pageSize);
}
