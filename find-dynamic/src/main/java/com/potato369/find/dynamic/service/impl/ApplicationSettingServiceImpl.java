package com.potato369.find.dynamic.service.impl;

import com.potato369.find.common.enums.ApplicationSettingStatusEnum;
import com.potato369.find.dynamic.service.ApplicationSettingService;
import com.potato369.find.mbg.mapper.ApplicationSettingMapper;
import com.potato369.find.mbg.model.ApplicationSetting;
import com.potato369.find.mbg.model.ApplicationSettingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service.impl
 * @ClassName ApplicationSettingServiceImpl
 * @Desc 申请加微信次数配置Service类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 10:23
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class ApplicationSettingServiceImpl implements ApplicationSettingService {

    private ApplicationSettingMapper applicationSettingMapperReader;

    @Autowired
    @Transactional(readOnly = true)
    public void setApplicationSettingMapperReader(ApplicationSettingMapper applicationSettingMapperReader) {
        this.applicationSettingMapperReader = applicationSettingMapperReader;
    }

    /**
     * 获取申请加微信次数配置
     *
     * @return
     */
    @Override
    public ApplicationSetting findApplication() {
        ApplicationSettingExample applicationSettingExample = new ApplicationSettingExample();
        applicationSettingExample.setDistinct(true);
        applicationSettingExample.setOrderByClause("create_time DESC, update_time DESC");
        applicationSettingExample.createCriteria().andStatusEqualTo(ApplicationSettingStatusEnum.ENABLE.getStatus());
        List<ApplicationSetting> applicationSettingList = this.applicationSettingMapperReader.selectByExample(applicationSettingExample);
        if (applicationSettingList != null && !applicationSettingList.isEmpty()) {
            return applicationSettingList.get(0);
        }
        return null;
    }
}
