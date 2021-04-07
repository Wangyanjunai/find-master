package com.potato369.find.dynamic.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.enums.*;
import com.potato369.find.dynamic.service.ApplicationRecordService;
import com.potato369.find.mbg.mapper.ApplicationRecordMapper;
import com.potato369.find.mbg.mapper.DynamicInfoMapper;
import com.potato369.find.mbg.mapper.MessageMapper;
import com.potato369.find.mbg.model.ApplicationRecord;
import com.potato369.find.mbg.model.DynamicInfo;
import com.potato369.find.mbg.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.service.impl
 * @ClassName ApplicationRecordServiceImpl
 * @Desc 申请加微信Service类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/02/01 10:19
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class ApplicationRecordServiceImpl implements ApplicationRecordService {

    private ApplicationRecordMapper applicationRecordMapperReader;

    private ApplicationRecordMapper applicationRecordMapperWriter;

    private MessageMapper messageMapperWriter;

    private DynamicInfoMapper dynamicInfoMapperWriter;

    @Autowired
    public void setApplicationRecordMapperReader(ApplicationRecordMapper applicationRecordMapperReader) {
        this.applicationRecordMapperReader = applicationRecordMapperReader;
    }

    @Autowired
    public void setApplicationRecordMapperWriter(ApplicationRecordMapper applicationRecordMapperWriter) {
        this.applicationRecordMapperWriter = applicationRecordMapperWriter;
    }

    @Autowired
    public void setMessageMapperWriter(MessageMapper messageMapperWriter) {
        this.messageMapperWriter = messageMapperWriter;
    }

    @Autowired
    public void setDynamicInfoMapperWriter(DynamicInfoMapper dynamicInfoMapperWriter) {
        this.dynamicInfoMapperWriter = dynamicInfoMapperWriter;
    }

    /**
     * 根据用户id查询用户当天的申请加微信次数
     *
     * @param userId 用户id
     * @return 当天的申请加微信次数
     */
    @Override
    @Transactional(readOnly = true)
    public Integer findApplicationRecordCountByUserId(Long userId) {
        return applicationRecordMapperReader.selectCountDataByUserId(userId);
    }

    /**
     * 保存申请加微信记录
     *
     * @param applicationRecord 申请加微信记录
     * @return 保存记录条数
     */
    @Override
    @Transactional
    public Integer saveApplicationRecord(DynamicInfo dynamicInfo, ApplicationRecord applicationRecord, String message) {
        int a = 0, b = 0, c = 0;
        if (StrUtil.isNotEmpty(message) && dynamicInfo != null && applicationRecord != null) {
            Long sendUserId = applicationRecord.getUserId();//申请加微信者用户id
            Long recipientUserId = dynamicInfo.getUserId();//被申请加微信者用户id
            
            Message messageRecord = new Message();//消息记录
            messageRecord.setSendMode(MessageSendModeEnum.ACTIVE.getStatus());//消息发送方式，用户主动
            messageRecord.setSendUserId(sendUserId);//消息发送者用户id
            messageRecord.setRecipientUserId(recipientUserId);//消息接收者用户id
            messageRecord.setContent(message);//消息内容
            messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());//消息状态，未读
            messageRecord.setReserveColumn01(MessageTypeEnum.Applications.getMessage());//消息类型，申请加微信
            messageRecord.setReserveColumn02(MessageType2Enum.SEND.getCodeStr());//消息发送还是回复类型
            messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());//消息是否删除
            
            dynamicInfo.setApplications(dynamicInfo.getApplications() + 1);//动态内容信息申请加微信数量+1
            dynamicInfo.setUpdateTime(new Date());//动态内容信息更新时间
            a = this.dynamicInfoMapperWriter.updateByPrimaryKeySelective(dynamicInfo);
            b = this.messageMapperWriter.insertSelective(messageRecord);
            c = this.applicationRecordMapperWriter.insertSelective(applicationRecord);
        }
        return a + b + c;
    }
}
