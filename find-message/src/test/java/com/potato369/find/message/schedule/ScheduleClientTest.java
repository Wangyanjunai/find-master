package com.potato369.find.message.schedule;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.schedule.ScheduleClient;
import cn.jpush.api.schedule.ScheduleListResult;
import cn.jpush.api.schedule.ScheduleResult;
import cn.jpush.api.schedule.model.SchedulePayload;
import cn.jpush.api.schedule.model.TriggerPayload;
import com.potato369.find.message.BaseTest;
import com.potato369.find.message.SlowTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ScheduleClient Tester.
 *
 * @author liucy
 * @version 1.0
 */

@Category(SlowTests.class)
public class ScheduleClientTest extends BaseTest {

    protected static final Logger LOG = LoggerFactory.getLogger(ScheduleClientTest.class);

    public static final int NOT_EXIST = 8104;
    public static final int AUTH_FAILED = 8101;
    public static final int INVALID_PARAM = 8100;

    private ScheduleClient client;

    @Override
    public void before() {
        client = new ScheduleClient(MASTER_SECRET, APP_KEY);
    }

    /**
     * Method: getScheduleList(int page)
     */
    @Test
    public void testGetScheduleList() {
        try {
            ScheduleListResult result = client.getScheduleList(1);
            Assert.assertEquals("", 1, result.getPage());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            Assert.assertTrue(e.getErrorMessage(), false);
        }
    }


    /**
     * Method: createSchedule(SchedulePayload payload)
     * Method: getSchedule(String scheduleId)
     * Method: updateSchedule(String scheduleId, SchedulePayload payload)
     * Method: deleteSchedule(String scheduleId)
     */
    @Test
    public void testScheduleMethods() {
        String name = "test_schedule";
        TriggerPayload trigger = TriggerPayload.newBuilder()
                .setSingleTime("2105-07-30 12:00:00")
                .buildSingle();

        PushPayload push = PushPayload.alertAll("test schedule");

        SchedulePayload payload = SchedulePayload.newBuilder()
                .setName(name)
                .setEnabled(true)
                .setTrigger(trigger)
                .setPush(push)
                .build();

        SchedulePayload update = SchedulePayload.newBuilder()
                .setEnabled(false).build();

        ScheduleResult result = null;
        boolean success = false;
        try {
            result = client.createSchedule(payload);
            Assert.assertNotNull("test createSchedule failed.", result);

            ScheduleResult getResult = client.getSchedule(result.getSchedule_id());
            Assert.assertEquals("test getSchedule failed.", result.getName(), getResult.getName());

            ScheduleResult updateResult = client.updateSchedule(result.getSchedule_id(), update);
            Assert.assertEquals("test updateSchedule failed.", false, updateResult.getEnabled());

            client.deleteSchedule(result.getSchedule_id());
            Assert.assertTrue(true);
            success = true;

        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
        } finally {
            if(!success && null != result) {
                try {
                    client.deleteSchedule(result.getSchedule_id());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testGetNotExist() {
        try{
            client.getSchedule("test-not-exist");
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            Assert.assertEquals(NOT_EXIST, e.getErrorCode());
        }
    }

} 
