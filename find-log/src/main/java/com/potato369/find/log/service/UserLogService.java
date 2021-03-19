package com.potato369.find.log.service;

import com.potato369.find.mbg.model.OperateRecord;
public interface UserLogService {
	
	/**
	 * 查找用户个人资料
	 * @param id 用户id
	 * @param operateRecord
	 */
	int record(Long id, OperateRecord operateRecord);
}
