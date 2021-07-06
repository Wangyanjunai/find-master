package com.potato369.find.dynamic.config.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.user.config.props
 * @ClassName AliyunProps
 * @Desc 阿里云IP定位接口请求参数配置
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/07 09:57
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
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
public class AliyunProps {

    @Value("${aliyun.appcode}")
    private String appcode;

    @Value("${aliyun.url}")
    private String url;
}
