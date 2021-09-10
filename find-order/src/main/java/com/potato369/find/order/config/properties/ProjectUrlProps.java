package com.potato369.find.order.config.properties;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.order.config.properties
 * @ClassName ProjectUrlProps
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/14 14:52
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
@Scope("request")
public class ProjectUrlProps {
    /**
     * <pre>
     * projectName：项目名字
     * </pre>
     */
    @Value(value = "${project-url.project-name}")
    private String projectName;

    /**
     * <pre>
     * resAppImg：项目前端图片资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-app-product-img}")
    private String resAppProductImg;

    /**
     * <pre>
     * domain：项目网关域名前缀
     * </pre>
     */
    @Value(value = "${project-url.domain}")
    private String domain;
}
