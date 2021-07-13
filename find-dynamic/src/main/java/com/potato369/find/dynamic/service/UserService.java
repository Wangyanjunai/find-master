package com.potato369.find.dynamic.service;

import org.springframework.transaction.annotation.Transactional;

import com.potato369.find.mbg.model.User;

@Transactional
public interface UserService {

	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 */
	User findUserById(Long userId);
}
