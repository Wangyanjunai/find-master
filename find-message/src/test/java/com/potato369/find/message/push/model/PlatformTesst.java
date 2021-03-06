package com.potato369.find.message.push.model;

import cn.jiguang.common.DeviceType;
import cn.jpush.api.push.model.Platform;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.potato369.find.message.FastTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@Category(FastTests.class)
public class PlatformTesst {

    @Test
    public void testAll() {
        Platform all = Platform.all();
        Assert.assertEquals("test", new JsonPrimitive("all"), all.toJSON());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotAll() {
        Platform all = Platform.newBuilder().setAll(false).build();
        
        assertThat(all.toJSON(), is((JsonElement) new JsonPrimitive("all")));
    }

    @Test
    public void testAndroid() {
        Platform android = Platform.newBuilder().addDeviceType(DeviceType.Android).build();
        JsonArray array = new JsonArray();
        array.add(new JsonPrimitive("android"));
        
        assertThat(android.toJSON(), is((JsonElement) array));
    }
    
}



