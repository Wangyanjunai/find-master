package com.potato369.find.message.push.model.audience;

import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.potato369.find.message.FastTests;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTests.class)
public class AudienceTest {

    @Test
    public void testAll() {
        Audience audience = Audience.all();
        Assert.assertEquals("test", new JsonPrimitive("all"), audience.toJSON());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAllMore() {
        AudienceTarget target = AudienceTarget.alias("aaaa");
        Audience audience = Audience.newBuilder()
                .setAll(true)
                .addAudienceTarget(target)
                .build();
        Assert.assertEquals("test", new JsonPrimitive("all"), audience.toJSON());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testNotAll() {
        Audience audience = Audience.newBuilder().setAll(false).build();
        Assert.assertEquals("test", new JsonPrimitive("all"), audience.toJSON());
    }
    
    @Test
    public void testAudience() {
        JsonObject json = new JsonObject();
        JsonArray arr = new JsonArray();
        arr.add(new JsonPrimitive("aaaa"));
        json.add("alias", arr);
        
        AudienceTarget target = AudienceTarget.alias("aaaa");
        Audience audience = Audience.newBuilder()
                .addAudienceTarget(target)
                .build();
        Assert.assertEquals("test", json, audience.toJSON());
        
    }
}
