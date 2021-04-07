package com.potato369.find.message.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.potato369.find.message.config.bean.PushBean;
import com.potato369.find.message.config.props.JiGuangConfig;
import com.potato369.find.message.service.MyJiGuangPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 极光推送 封装第三方api相关
 */
@Service
@Slf4j
public class MyJiGuangPushServiceImpl implements MyJiGuangPushService {

	private JiGuangConfig jPushConfig;
	
	@Autowired
	public void setjPushConfig(JiGuangConfig jPushConfig) {
		this.jPushConfig = jPushConfig;
	}

	/**
	 * 广播 (安卓平台，所有设备, 不支持附加信息)
	 * 
	 * @param pushBean 推送内容
	 * @return
	 */
	@Override
	public boolean pushAll(PushBean pushBean) {
		return sendPush(PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(Notification.alert(pushBean.getAlert()))
				.build());
	}

	/**
	 * android广播
	 * 
	 * @param pushBean 推送内容
	 * @return
	 */
	@Override
	public boolean pushAndroid(PushBean pushBean) {
		return sendPush(PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.all())
				.setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
				.build());
	}

	/**
	 * android通过registid推送 (一次推送最多 1000 个)
	 * 
	 * @param pushBean  推送内容
	 * @param registids 推送id多个
	 * @return
	 */
	@Override
	public boolean pushAndroid(PushBean pushBean, String... registids) {
		return sendPush(PushPayload.newBuilder()
				.setPlatform(Platform.android())
				.setAudience(Audience.registrationId(registids))
				.setNotification(Notification.android(pushBean.getAlert(), pushBean.getTitle(), pushBean.getExtras()))
				.build());
	}

	/**
	 * 调用api推送
	 * 
	 * @param pushPayload 推送实体
	 * @return
	 */
	@Override
	public boolean sendPush(PushPayload pushPayload) {
		log.info("发送极光推送请求: {}", pushPayload);
		PushResult result = null;
		try {
			result = this.jPushConfig.getJPushClient().sendPush(pushPayload);
		} catch (APIConnectionException e) {
			log.error("极光推送连接异常: ", e);
		} catch (APIRequestException e) {
			log.error("极光推送请求异常: ", e);
		}
		if (result != null && result.isResultOK()) {
			log.info("极光推送请求成功: {}", result);
			return true;
		} else {
			log.info("极光推送请求失败: {}", result);
			return false;
		}
	}
}
