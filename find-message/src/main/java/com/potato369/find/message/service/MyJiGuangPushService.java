package com.potato369.find.message.service;

import cn.jpush.api.push.model.PushPayload;
import com.potato369.find.message.config.bean.PushBean;

/**
 * 极光推送
 * 封装第三方api相关
 */
public interface MyJiGuangPushService {

	boolean pushAll(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean, String... registids);
	
	boolean sendPush(PushPayload pushPayload);
}
