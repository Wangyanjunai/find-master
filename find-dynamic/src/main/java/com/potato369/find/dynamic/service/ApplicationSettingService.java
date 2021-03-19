package com.potato369.find.dynamic.service;

import com.potato369.find.mbg.model.ApplicationSetting;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service
 * @InterfaceName ApplicationSettingService
 * @Desc 申请加微信次数配置Service接口实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 10:23
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface ApplicationSettingService {

    /**
     * 获取申请加微信次数配置
     *
     * @return
     */
    ApplicationSetting findApplication();
}
