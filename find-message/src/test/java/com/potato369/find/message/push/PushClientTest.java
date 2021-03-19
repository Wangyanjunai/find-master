package com.potato369.find.message.push;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.ServiceHelper;
import cn.jiguang.common.connection.NettyHttpClient;
import cn.jiguang.common.resp.DefaultResult;
import cn.jiguang.common.resp.ResponseWrapper;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.CIDResult;
import cn.jpush.api.push.PushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.*;
import io.netty.handler.codec.http.HttpMethod;
import lombok.extern.slf4j.Slf4j;

import org.junit.Test;

import cn.jiguang.common.connection.HttpProxy;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import com.potato369.find.message.BaseTest;
import cn.jpush.api.push.model.PushPayload;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class PushClientTest extends BaseTest {


    @Test
    public void testSendPush() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            int status = result.getResponseCode();
            log.info("status - " + status);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later.", e);
            log.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it.", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
            log.info("Msg ID: " + e.getMsgId());
            log.error("Sendno: " + payload.getSendno());
        }
    }

    @Test
    public void testSendPushWithCallback() {
        ClientConfig clientConfig = ClientConfig.getInstance();
        String host = (String) clientConfig.get(ClientConfig.PUSH_HOST_NAME);
        final NettyHttpClient client = new NettyHttpClient(ServiceHelper.getBasicAuthorization(APP_KEY, MASTER_SECRET),
                null, clientConfig);
        try {
            URI uri = new URI(host + clientConfig.get(ClientConfig.PUSH_PATH));
            PushPayload payload = buildPushObject_all_alias_alert();
            client.sendRequest(HttpMethod.POST, payload.toString(), uri, new NettyHttpClient.BaseCallback() {
                @Override
                public void onSucceed(ResponseWrapper responseWrapper) {
                    log.info("Got result: " + responseWrapper.responseContent);
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(REGISTRATION_ID3))
                .setNotification(Notification.alert(ALERT))
                .setOptions(Options.newBuilder().setApnsProduction(false).setTimeToLive(86000).build())
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_invalid_json() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            pushClient.sendPush("{aaa:'a}");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_string() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            pushClient.sendPush("");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_empty_password() {
        new HttpProxy("127.0.0.1", 8080, "", null);
    }

    @Test
    public void test_validate() {
        PushClient pushClient = new PushClient(MASTER_SECRET, APP_KEY);

        try {
            PushResult result = pushClient.sendPushValidate(PushPayload.alertAll("alert"));
            assertTrue("", result.isResultOK());
        } catch (APIRequestException e) {
            fail("Should not fail");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCidList() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            CIDResult result = jPushClient.getCidList(3, "push");
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    @Test
    public void testSendPushWithCid() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload pushPayload = buildPushObject_android_cid();
        try {
            PushResult result = jPushClient.sendPush(pushPayload);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    @Test
    public void testSendFilePush() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        PushPayload filePushPayload = buildFilePushPayload();
        try {

            PushResult result = jPushClient.sendFilePush(filePushPayload);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    @Test
    public void testDeletePush() {
        JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        try {
            String msgId = "58546877793854733";
            DefaultResult result = jPushClient.deletePush(msgId);
            log.info("Got result - " + result);
        } catch (APIConnectionException e) {
            log.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            log.error("Error response from JPush server. Should review and fix it. ", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    private static PushPayload buildFilePushPayload() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.file("file-id-test"))
                .setNotification(Notification.alert(ALERT))
                .build();
    }

    private static PushPayload buildPushObject_android_cid() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.registrationId("18071adc030dcba91c0"))
                .setNotification(Notification.alert(ALERT))
                .setCid("d4ee2375846bc30fa51334f5-21f305e0-4a52-42f3-a3dd-d2e49bdf0499")
                .build();
    }

}
