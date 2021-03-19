package com.potato369.find.dynamic.service;

import com.potato369.find.dynamic.config.bean.PushBean;

import cn.jpush.api.push.model.PushPayload;

/**
 * 极光推送
 * 封装第三方api相关
 */
public interface MyJiGuangPushService {

	boolean pushAll(PushBean pushBean);

	boolean pushIos(PushBean pushBean);

	boolean pushIos(PushBean pushBean, String... registids);

	boolean pushAndroid(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean, String... registids);
	
	boolean pushAndroidAlias(PushBean pushBean, String alias, String content);
	
	boolean pushIosAlias(PushBean pushBean, String alias, String content);
	
	boolean sendPush(PushPayload pushPayload);
}
