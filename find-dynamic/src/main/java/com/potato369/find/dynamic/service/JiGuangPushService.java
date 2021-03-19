package com.potato369.find.dynamic.service;

import com.potato369.find.dynamic.config.bean.PushBean;

/**
 * 推送服务
 * 封装业务功能相关
 */
public interface JiGuangPushService {

	boolean pushAll(PushBean pushBean);

	boolean pushIos(PushBean pushBean);

	boolean pushIos(PushBean pushBean, String... registids);

	boolean pushAndroid(PushBean pushBean);

	boolean pushAndroid(PushBean pushBean, String... registids);
	
	boolean pushAndroidAlais(PushBean pushBean, String alias, String content);

	boolean pushIosAlais(PushBean pushBean, String alias, String content);

	String[] checkRegistids(String[] registids);
}
