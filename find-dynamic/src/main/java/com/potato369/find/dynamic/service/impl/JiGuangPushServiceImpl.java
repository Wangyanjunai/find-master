package com.potato369.find.dynamic.service.impl;

import com.potato369.find.dynamic.config.bean.PushBean;
import com.potato369.find.dynamic.config.props.JiGuangConfig;
import com.potato369.find.dynamic.service.JiGuangPushService;
import com.potato369.find.dynamic.service.MyJiGuangPushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 推送服务 封装业务功能相关
 */
@Service
@Slf4j
public class JiGuangPushServiceImpl implements JiGuangPushService {

	private MyJiGuangPushService jPushService;
	
	private JiGuangConfig jPushConfig;
	
	@Autowired
	public void setjPushService(MyJiGuangPushService jPushService) {
		this.jPushService = jPushService;
	}

	@Autowired
	public void setjPushConfig(JiGuangConfig jPushConfig) {
		this.jPushConfig = jPushConfig;
	}

	/**
	 * 推送全部, 不支持附加信息
	 * 
	 * @return
	 */
	@Override
	public boolean pushAll(PushBean pushBean) {
		return this.jPushService.pushAll(pushBean);
	}

	/**
	 * 推送全部ios
	 * 
	 * @return
	 */
	@Override
	public boolean pushIos(PushBean pushBean) {
		return this.jPushService.pushIos(pushBean);
	}

	/**
	 * 推送ios 指定id
	 * 
	 * @return
	 */
	@Override
	public boolean pushIos(PushBean pushBean, String... registids) {
		registids = checkRegistids(registids); // 剔除无效registed
		while (registids.length > this.jPushConfig.getMaxSize()) { // 每次推送max_size个
			this.jPushService.pushIos(pushBean, Arrays.copyOfRange(registids, 0, this.jPushConfig.getMaxSize()));
			registids = Arrays.copyOfRange(registids, this.jPushConfig.getMaxSize(), registids.length);
			for (String registid : registids) {
				if (log.isDebugEnabled()) {
					log.debug("registid={}", registid);
				}
			}
		}
		return this.jPushService.pushIos(pushBean, registids);
	}

	/**
	 * 推送全部android
	 * 
	 * @return
	 */
	@Override
	public boolean pushAndroid(PushBean pushBean) {
		return this.jPushService.pushAndroid(pushBean);
	}

	/**
	 * 推送android 指定id
	 * 
	 * @return
	 */
	@Override
	public boolean pushAndroid(PushBean pushBean, String... registids) {
		registids = checkRegistids(registids); // 剔除无效registed
		while (registids.length > this.jPushConfig.getMaxSize()) { // 每次推送max_size个
			this.jPushService.pushAndroid(pushBean, Arrays.copyOfRange(registids, 0, this.jPushConfig.getMaxSize()));
			registids = Arrays.copyOfRange(registids, this.jPushConfig.getMaxSize(), registids.length);
			for (String registid : registids) {
				if (log.isDebugEnabled()) {
					log.debug("registid={}", registid);
				}
			}
		}
		return this.jPushService.pushAndroid(pushBean, registids);
	}

	/**
	 * 剔除无效registed
	 * 
	 * @param registids
	 * @return
	 */
	@Override
	public String[] checkRegistids(String[] registids) {
		List<String> regList = new ArrayList<>(registids.length);
		for (String registid : registids) {
			if (registid != null && !"".equals(registid.trim())) {
				regList.add(registid);
			}
			if (log.isDebugEnabled()) {
				log.debug("registid={}", registid.toString());
			}
		}
		return regList.toArray(new String[0]);
	}

	@Override
	public boolean pushAndroidAlais(PushBean pushBean, String alias, String content) {
		return this.jPushService.pushAndroidAlias(pushBean, alias, content);
	}

	@Override
	public boolean pushIosAlais(PushBean pushBean, String alias, String content) {
		return this.jPushService.pushIosAlias(pushBean, alias, content);
	}
}
