package com.potato369.find.dynamic.config.props;
//极光配置类
import cn.jpush.api.JPushClient;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Data
@Configuration
@ConfigurationProperties
public class JiGuangConfig {
	/**
     * appKey：极光平台应用的唯一标识，或者说是极光推送的用户名
     */
	@Value(value = "${jpush.app-key}")
	private String appKey; 
	
	/**
     * masterSecret：用于服务器端API调用时与AppKey配合使用达到鉴权的目的，
     * 				请保管好Master Secret防止外泄
     * 				或者说是极光推送的密码
     */
	@Value(value = "${jpush.master-secret}")
	private String masterSecret;
	
	/**
     * liveTime：消息生存时间，极光推送设置过期时间，离线消息保留时长(秒)
     */
	@Value(value = "${jpush.live-time}")
	private int liveTime;
	
	/**
     * maxSize：一次推送最多多少个
     */
	@Value(value = "${jpush.max-size}")
	private int maxSize;
	
	/**
     * apnsProduction：是否生产环境，true表示生产环境，false表示不是生产环境
     */
	@Value(value = "${jpush.apns-production}")
	private boolean apnsProduction;
    
    private JPushClient jPushClient;
    
    // 推送客户端
    @PostConstruct
    public void initJPushClient() {
        jPushClient = new JPushClient(masterSecret, appKey);
    }
    // 获取推送客户端
    public JPushClient getJPushClient() {
        return jPushClient;
    }
}
