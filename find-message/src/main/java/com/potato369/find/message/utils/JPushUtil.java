package com.potato369.find.message.utils;

import cn.jiguang.common.TimeUnit;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.ReceivedsResult.Received;
import cn.jpush.api.report.UsersResult;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JPushUtil {

    private static final String APP_KEY = "eb67142502ec9f5556875b9a";

    private static final String MASTER_SECRET = "186aa57bc697c94f8698bbbf";

    public static JPushClient jpushClient;
    
    /**
     * 根据别名和通知推送
     * @param registrationId 注册ID
     * @param title 
     * @param alias 别名
     * @param alert 推送内容
     * @return
     */
    public static PushPayload buldPushObject_all_all_alias(String registrationId, String title, String alert, String content, Map<String, String> map) {
        return PushPayload.newBuilder()
        		.setPlatform(Platform.all())
        		.setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
                        .addExtra("content", content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert)
                        .addExtra("content", content).setTitle(title).addExtras(map).build())
                        .addPlatformNotification(WinphoneNotification.newBuilder().setAlert(alert)
                        .addExtra("content", content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据tag和通知推送
     *
     * @param tags tag数组
     * @param title 推送标题
     * @param alert 通知
     * @param content 推送内容
     * @param map
     * @return
     */
    public static PushPayload buildPushObject_all_all_tag(String[] tags, String title, String alert, String content, Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.tag(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).setTitle(title).addExtras(map).build())
                        .addPlatformNotification(WinphoneNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据tag通知推送
     *
     * @param registrationId	设备标识
     * @param tags 			 	tag数组
     * @param alert 			通知
     * @param title 			推送标题
     * @param content 			推送内容
     * @param map
     * @return
     */
    public static PushPayload buildPushObject_all_all_aliasAndTag(String registrationId, String[] tags, String alert, String title, String content, Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.registrationId(registrationId))
                .setAudience(Audience.tag(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).setTitle(title).addExtras(map).build())
                        .addPlatformNotification(WinphoneNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 根据通知推送
     *
     * @param title
     * @param alert
     * @param content
     * @param map
     * @return
     */
    public static PushPayload buildPushObject_all_all(String title, String alert, String content, Map<String, String> map) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).setTitle(title).addExtras(map).build())
                        .addPlatformNotification(WinphoneNotification.newBuilder().setAlert(alert)
                                .addExtra("content", content).addExtras(map).build())
                        .build())
                .build();
    }

    /**
     * 推送通知接口
     *
     * @param registrationId 设备标识
     * @param tags           tag数组
     * @param title          推送标题
     * @param type           推送类型
     * @param content        推送内容
     */
    public static void sendPushNotice(String registrationId, String[] tags, String title, String type, String alert, String content) {
        jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = null;
        // 生成推送的内容，这里我们先测试全部推送
        // 通知提示信息
        if (content != null) {
            Map<String, String> map = new HashMap<>();
            map.put("type", type);
            if (registrationId != null && tags == null) {// 根据别名推送
                payload = buldPushObject_all_all_alias(registrationId, title, alert, content, map);
            } else if (registrationId == null && tags != null) {// 根据tag[]推送
                payload = buildPushObject_all_all_tag(tags, title, alert, content, map);
            } else if (registrationId != null) {// 根据别名和tags[]推送通知
                payload = buildPushObject_all_all_aliasAndTag(registrationId, tags, title, alert, content, map);
            } else {
                payload = buildPushObject_all_all(title, alert, content, map);
            }
        } else {
            log.info("No notification: {}", content);
        }
        try {
            assert payload != null;
            log.info("payload: {}", payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            log.info("推送结果: {}", result.getResponseCode());
            log.info("Got result: {}", result);
        } catch (APIConnectionException e) {
            log.error("Connection error.Should retry later.", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server.Should review and fix it.", e);
            log.error("HTTP Status: {}", e.getStatus());
            log.error("Error Code: {}", e.getErrorCode());
            log.error("Error Message: {}", e.getErrorMessage());
            log.error("Msg ID: {}", e.getMsgId());
        }
    }

    /**
     * 推送自定义消息接口
     * 根据别名修改标签（tag）
     * @param alias   别名
     * @param content 推送内容
     */
    public static void sendPushMessage(String alias, String content) {
        jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload payload = null;
        // For push, all you need do is to build PushPayload object.
        // PushPayload payload = buildPushObject_all_all_alert();
        // 判断用户别名和tag不为空的情况下才推送修改标签（tag）
        if (content != null && alias != null) {
            payload = PushPayload.newBuilder()
                    .setAudience(Audience.alias(alias))
                    .setPlatform(Platform.all())
                    .setMessage(Message.content(content))
                    .build();
        } else {
            log.info("No notification: {}", content);
        }
        try {
            assert payload != null;
            log.info("payload.toString(): {}", payload.toString());
            PushResult result = jpushClient.sendPush(payload);
            log.info("result: {}", result);
            log.info("Got result: {}", result);
        } catch (APIConnectionException e) {
            log.error("Connection error.Should retry later.", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server.Should review and fix it.", e);
            log.error("HTTP Status: {}", e.getStatus());
            log.error("Error Code: {}", e.getErrorCode());
            log.error("Error Message: {}", e.getErrorMessage());
            log.error("Msg ID: {}", e.getMsgId());
        }
    }

    /**
     * 查询记录推送成功条数
     *
     * @param mid
     */
    public static void countPush(String mid) {
        jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            ReceivedsResult result = jpushClient.getReportReceiveds(mid);
            Received received = result.received_list.get(0);
            log.info("android_received: {}\nios:{}", received.android_received, received.ios_apns_sent);
            log.info("Got result: {}", result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later.
            log.error("Connection error, should retry later.", e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request.
            log.error("Should review the error, and fix the request", e);
            log.error("HTTP Status: {}", e.getStatus());
            log.error("Error Code: {}", e.getErrorCode());
            log.error("Error Message: {}", e.getErrorMessage());
        }
    }

    /**
     * 统计用户数据
     * 需要vip用户才能访问
     */
    public static void getReportUser() {
        jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            UsersResult result = jpushClient.getReportUsers(TimeUnit.DAY, "2015-04-28", 8);
         // Received received =result
         // System.out.println("android_received:"+received.android_received+"\nios:"+received.ios_apns_sent);
            log.debug("Got result: {}", result);
        } catch (APIConnectionException e) {
            // Connection error, should retry later.
            log.error("Connection error, should retry later.", e);
        } catch (APIRequestException e) {
            // Should review the error, and fix the request.
            log.error("Should review the error, and fix the request.", e);
            log.error("HTTP Status: {}", e.getStatus());
            log.error("Error Code: {}", e.getErrorCode());
            log.error("Error Message: {}", e.getErrorMessage());
        }
    }

    public static void main(String[] args) {
    	// vivo
        sendPushNotice("1507bfd3f76139cd43a", null, "系统通知88", "类型", "觅鹿通知88", "content");
        // xiaomi
        sendPushNotice("13065ffa4ead6656765", null, "系统通知88", "类型", "觅鹿通知88", "content");
    }
}
