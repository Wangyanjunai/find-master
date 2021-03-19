package com.potato369.find.dynamic.service;

import com.potato369.find.mbg.model.ApplicationRecord;
import com.potato369.find.mbg.model.DynamicInfo;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service
 * @InterfaceName ApplicationRecordService
 * @Desc 申请加微信Service接口实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 10:18
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
public interface ApplicationRecordService {

    /**
     * 根据用户id查询用户当天的申请加微信次数
     *
     * @param userId 用户id
     * @return 当天的申请加微信次数
     */
    Integer findApplicationRecordCountByUserId(Long userId);

    /**
     * 保存申请加微信记录
     *
     * @param applicationRecord 申请加微信记录
     * @return 保存记录条数
     */
    Integer saveApplicationRecord(DynamicInfo dynamicInfo, ApplicationRecord applicationRecord, String message);
}
