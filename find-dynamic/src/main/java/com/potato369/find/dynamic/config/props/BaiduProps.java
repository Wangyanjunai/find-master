package com.potato369.find.dynamic.config.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.user.config.props
 * @ClassName BaiduProps
 * @Desc 百度普通ip定位服务属性配置
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 11:30
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@AllArgsConstructor
@Builder
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class BaiduProps {

    /**
     * <pre>
     * ak：访问应用（AK）
     * </pre>
     */
    @Value("${baidu.ak}")
    private String ak;

    /**
     * <pre>
     * url：百度应用定位获取url
     * </pre>
     */
    @Value("${baidu.url}")
    private String url;
}
