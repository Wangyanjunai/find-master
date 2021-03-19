package com.potato369.find.message;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * curl --insecure -X POST -v https://api.jpush.cn/v3/push -H "Content-Type: application/json" -u "2ca10b44a1a6a77c278cbf0a:c165704661401d94f6c7a7d8" -d '{"platform":"all","audience":"all","notification":{"alert":"Hi,JPush !","android":{"extras":{"android-key1":"android-value1"}},"ios":{"sound":"sound.caf","badge":"+1","extras":{"ios-key1":"ios-value1"}}}}'
 * @author admin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestOrder {
    int order();
}