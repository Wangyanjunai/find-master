package com.potato369.find.admin.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Builder
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
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
    @Value(value = "${project-url.res-app-img}")
    private String resAppImg;

    /**
     * <pre>
     * resHeadImg：项目前端头像小图资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-head-icon}")
    private String resHeadIcon;

    /**
     * <pre>
     * resBackgroundIcon：项目前端背景小图资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-background-icon}")
    private String resBackgroundIcon;

    /**
     * <pre>
     * resVoiceIcon：项目前端语音资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-voices-file}")
    private String resDynamicVoiceFile;

    /**
     * <pre>
     * resVoiceIcon：项目前端动态图片资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-images-file}")
    private String resDynamicImageFile;

    /**
     * <pre>
     * domain：项目网关域名前缀
     * </pre>
     */
    @Value(value = "${project-url.domain}")
    private String domain;

    /**
     * <pre>
     * uploadRes：资源文件存放nginx服务器路径
     * </pre>
     */
    @Value(value = "${project-url.upload-res}")
    private String uploadRes;

    /**
     * <pre>
     * resDomain：资源文件存放nginx服务器路径
     * </pre>
     */
    @Value(value = "${project-url.res-domain}")
    private String resDomain;
}
