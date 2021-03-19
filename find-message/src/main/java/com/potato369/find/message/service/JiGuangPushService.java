package com.potato369.find.message.service;

import com.potato369.find.message.config.bean.PushBean;

/**
 * 推送服务
 * 封装业务功能相关
 */
public interface JiGuangPushService {

	boolean pushAll(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean, String... registids);

	String[] checkRegistids(String[] registids);
}
