package com.potato369.find.message.push.remote;

import static org.junit.Assert.assertTrue;

import cn.jiguang.common.resp.APIRequestException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.potato369.find.message.SlowTests;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.WinphoneNotification;
import lombok.extern.slf4j.Slf4j;

@Category(SlowTests.class)
@Slf4j
public class AlertOverrideTest extends BaseRemotePushTest {
    
    @Test
    public void sendAlert_all() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert")
                        .addPlatformNotification(AndroidNotification.alert("android alert"))
                        .addPlatformNotification(IosNotification.alert("ios alert"))
                        .addPlatformNotification(WinphoneNotification.alert("winphone alert"))
                        .build())
                .build();
        PushResult result = _client.sendPush(payload);
        assertTrue(result.isResultOK());
    }
    
    @Test
    public void sendAlert_android() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert")
                        .addPlatformNotification(AndroidNotification.alert("android alert"))
                        .build())
                .build();
        PushResult result = _client.sendPush(payload);
        assertTrue(result.isResultOK());
    }
    
    @Test
    public void sendAlert_ios() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert")
                        .addPlatformNotification(IosNotification.alert("ios alert"))
                        .build())
                .build();
        try {
            PushResult result = _client.sendPush(payload);
            log.info("result={}", result);
        } catch (APIRequestException e) {
            log.error("APIRequestException={}", e);
        }
    }
    
    @Test
    public void sendAlert_wp() throws Exception {
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.winphone())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert("alert")
                        .addPlatformNotification(WinphoneNotification.alert("winphone alert"))
                        .build())
                .build();
        try {
            PushResult result = _client.sendPush(payload);
            log.info("result={}", result);
        } catch (APIRequestException e) {
        	log.error("APIRequestException={}", e);
        }
    }
}

