package com.potato369.find.message.config.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@Component("jpushConfig")
@Scope("request")
public class JPushConfig {
	
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
     * liveTime：消息生存时间
     */
	@Value(value = "${jpush.live-time}")
	private String liveTime;
	
	/**
     * apnsProduction：是否生产环境，true表示生产环境，false表示不是生产环境
     */
	@Value(value = "${jpush.apns-production}")
	private String apnsProduction;
}
