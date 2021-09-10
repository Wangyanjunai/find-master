package com.potato369.find.message.config.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * @PackageName com.potato369.find.message.config.props
 * @ClassName ProjectUrlProps
 * @Desc 项目路径配置公共参数配置
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2020/11/21 16:16
 * @CreateBy IntelliJ IDEA 2020.2.3
 * @Copyright Copyright (c) 2016 ~ 2020 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
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
     * resVoices：项目前端语音资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-voices-file}")
    private String resVoices;
    
    /**
     * <pre>
     * resImages：项目前端动态图片资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.res-images-file}")
    private String resImages;
    
    /**
     * <pre>
     * defaultHeadIcon：项目前端默认头像资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.default-head-icon}")
    private String defaultHeadIcon;
    
    /**
     * <pre>
     * defaultBgIcon：项目前端默认背景资源存放路径
     * </pre>
     */
    @Value(value = "${project-url.default-bg-icon}")
    private String defaultBgIcon;
    
    /**
     * <pre>
     * defaultMaleContent：注册时男生默认发布动态内容，包括头像
     * </pre>
     */
    @Value(value = "${project-url.default-male-content}")
    private String defaultMaleContent;
    
    /**
     * <pre>
     * defaultFemaleContent：注册时男生默认发布动态内容，包括头像
     * </pre>
     */
    @Value(value = "${project-url.default-female-content}")
    private String defaultFemaleContent;
    
    /**
     * <pre>
     * domain：项目网关域名前缀
     * </pre>
     */
    @Value(value = "${project-url.domain}")
    private String domian;
    
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
}
