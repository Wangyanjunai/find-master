package com.potato369.find.message.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.vo.*;
import com.potato369.find.mbg.mapper.MessageMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.*;
import com.potato369.find.message.config.bean.PushBean;
import com.potato369.find.message.config.props.ProjectUrlProps;
import com.potato369.find.message.service.JiGuangPushService;
import com.potato369.find.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapperReader;

    private MessageMapper messageMapperWriter;

    private UserMapper userMapperReader;

    private UserMapper userMapperWriter;

    private ProjectUrlProps projectUrlProps;

    private JiGuangPushService jiGuangPushService;

    @Autowired
    public void setMessageMapperReader(MessageMapper messageMapperReader) {
        this.messageMapperReader = messageMapperReader;
    }

    @Autowired
    public void setMessageMapperWriter(MessageMapper messageMapperWriter) {
        this.messageMapperWriter = messageMapperWriter;
    }

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setUserMapperWriter(UserMapper userMapperWriter) {
        this.userMapperWriter = userMapperWriter;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setJiGuangPushService(JiGuangPushServiceImpl jiGuangPushService) {
        this.jiGuangPushService = jiGuangPushService;
    }

    @Override
    @Transactional(readOnly = true)
    public LikesMessageVO selectLikesMessage(Long userId) {
        //查询未读点赞消息总条数
        LikesMessageVO likesMessageVO = LikesMessageVO.builder().build();
        likesMessageVO.setCount(this.messageMapperReader.countUnreadLikesByUserId(userId));
        //查询最后一条点赞消息记录
        MessageExample messageExample = new MessageExample();
        messageExample.setDistinct(true);
        messageExample.setOrderByClause("create_time DESC");
        messageExample.createCriteria()
                .andRecipientUserIdEqualTo(userId)
                .andStatusEqualTo(MessageStatusEnum.UNREAD.getStatus())//点赞未读状态
                .andReserveColumn01EqualTo(MessageTypeEnum.Likes.getMessage())//点赞消息
                .andReserveColumn03EqualTo(MessageStatus2Enum.NO.getStatus());//是否删除，没有删除
        List<Message> messageList = this.messageMapperReader.selectByExampleWithBLOBs(messageExample);
        if (messageList != null && !messageList.isEmpty()) {
            Message message = messageList.get(0);
            likesMessageVO.setContent(message.getContent());
        } else {
            likesMessageVO.setContent(null);
        }
        return likesMessageVO;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageVO selectNotLikesMessage(Long userId, Integer pageNum, Integer pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectLikesMessage(userId));
        final PageInfo<NotLikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectUnLikesRecordByUserId(userId));
        messageVO.setTotalCount(listPageInfo.getTotal());//未读申请加微信总数量
        messageVO.setTotalPage(listPageInfo.getPages());
        List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
        List<NotLikesMessageRecord> notLikesMessageRecordList = listPageInfo.getList();
        for (NotLikesMessageRecord notLikesMessageRecord : notLikesMessageRecordList) {
            MessageInfoVO messageInfoVO = new MessageInfoVO();
            messageInfoVO.setHead(
                    StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                            + notLikesMessageRecord.getSendUserId()
                            + "/"
                            + notLikesMessageRecord.getSendUserHead()
            );
            messageInfoVO.setContent(notLikesMessageRecord.getMessageContent());
            messageInfoVO.setNickname(notLikesMessageRecord.getSendUserNickname());
            messageInfoVO.setUserId(notLikesMessageRecord.getSendUserId());
            messageInfoVOs.add(messageInfoVO);
        }
        messageVO.setMessageInfoVOs(messageInfoVOs);
        return messageVO;
    }

    @Override
    @Transactional
    public MessageVO2 selectLikesMessage(Long userId, Integer pageNum, Integer pageSize) {
        MessageVO2 messageVO2 = MessageVO2.builder().build();
        final PageInfo<LikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectLikesMessageRecordByUserId(userId));
        messageVO2.setTotalCount(listPageInfo.getTotal());
        messageVO2.setTotalPage(listPageInfo.getPages());
        List<LikesInfoVO> likesInfoVOs = new ArrayList<>();
        List<LikesMessageRecord> likesMessageRecordList = listPageInfo.getList();
        for (LikesMessageRecord likesMessageRecord : likesMessageRecordList) {
            LikesInfoVO likesInfoVO = LikesInfoVO.builder().build();
            likesInfoVO.setMessageId(likesMessageRecord.getMessageId());
            likesInfoVO.setUserId(likesMessageRecord.getUserId());
            likesInfoVO.setHead(
                    StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                            + likesMessageRecord.getUserId()
                            + "/"
                            + likesMessageRecord.getHeadIcon());
            likesInfoVO.setAttacheType(likesMessageRecord.getAttacheType());
            if (LikeStatusEnum.YES.getType().equals(likesMessageRecord.getStatus())) {
                likesInfoVO.setContent(likesMessageRecord.getNickname() + "点赞了你的动态" + likesMessageRecord.getLikesContent());
            }
            if (LikeStatusEnum.NO.getType().equals(likesMessageRecord.getStatus())) {
                likesInfoVO.setContent(likesMessageRecord.getNickname() + "取消点赞你的动态" + likesMessageRecord.getLikesContent());
            }
            String[] fileNameList01 = StrUtil.split(likesMessageRecord.getAttacheFilename(), "||");
            List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
            List<String> fileNameList03 = new ArrayList<>();
            for (String fileName : fileNameList02) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                        .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                if (StrUtil.isNotEmpty(likesMessageRecord.getAttacheType()) && AttacheInfoDataTypeEnum.Image.getCode().toString().equals(likesMessageRecord.getAttacheType())) {
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                }
                if (StrUtil.isNotEmpty(likesMessageRecord.getAttacheType()) && AttacheInfoDataTypeEnum.Audio.getCode().toString().equals(likesMessageRecord.getAttacheType())) {
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                }
                stringBuilder.append(fileName);
                fileNameList03.add(stringBuilder.toString());
            }
            likesInfoVO.setAttacheFilenameList(fileNameList03);
            likesInfoVOs.add(likesInfoVO);
            this.messageMapperWriter.updateLikesMessage(likesInfoVO.getUserId(), userId);
        }
        messageVO2.setLikesInfoVOs(likesInfoVOs);
        return messageVO2;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageVO selectApplicationsMessage(Long userId, Integer pageNum, Integer pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectLikesMessage(userId));
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectApplicationMessageRecordByUserId(userId));
        List<Message> messages = listPageInfo.getList();
        List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
        if (messages != null && !messages.isEmpty()) {
            for (Message message : messages) {
                MessageInfoVO messageInfoVO = MessageInfoVO.builder().build();
                messageInfoVO.setMessageId(message.getId());
                User user = this.userMapperReader.selectByPrimaryKey(message.getSendUserId());
                if (user != null) {
                    messageInfoVO.setUserId(user.getId());
                    messageInfoVO.setHead(
                            StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                                    + user.getId()
                                    + "/"
                                    + user.getHeadIcon());
                    messageInfoVO.setNickname(user.getNickName());
                }
                messageInfoVO.setContent(message.getContent());
                Long count = this.messageMapperReader.selectMessageRecordCount(message.getSendUserId(), message.getRecipientUserId());
                messageInfoVO.setCount(count);
                messageInfoVOs.add(messageInfoVO);
            }
        }
        messageVO.setMessageInfoVOs(messageInfoVOs);
        messageVO.setTotalCount(listPageInfo.getTotal());
        messageVO.setTotalPage(listPageInfo.getPages());
        return messageVO;
    }

    @Override
    @Transactional
    public MessageVO3 selectMessageRecord(Long sendUserId, Long recipientUserId, Integer pageNum, Integer pageSize) {
        MessageVO3 messageVO3 = MessageVO3.builder().build();
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectMessageRecord(sendUserId, recipientUserId));
        List<Message> messages = listPageInfo.getList();
        long totalCount = listPageInfo.getTotal();
        int totalPage = listPageInfo.getPages();
        List<MessageInfoVO2> messageInfoVO2s = new ArrayList<>();
        if (messages != null && !messages.isEmpty()) {
            for (Message message : messages) {
                long sendUserIdTmp = message.getSendUserId();
                User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserIdTmp);
                if (sendUser != null) {
                    MessageInfoVO2 messageInfoVO2 = MessageInfoVO2.builder().build();
                    messageInfoVO2.setMessageId(message.getId());
                    messageInfoVO2.setSendDateTime(DateUtil.fomateDate(message.getCreateTime(), DateUtil.sdfTimeCNFmt));
                    messageInfoVO2.setSendUserId(sendUserIdTmp);
                    messageInfoVO2.setSendUserNickname(sendUser.getNickName());
                    messageInfoVO2.setSendUserHeadIcon(
                            StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                                    + sendUser.getId()
                                    + "/"
                                    + sendUser.getHeadIcon());
                    messageInfoVO2.setContent(message.getContent());
                    messageInfoVO2s.add(messageInfoVO2);
                }
                this.messageMapperWriter.updateApplicationMessage(sendUserId, recipientUserId);
            }
        }
        messageVO3.setMessageInfoVO2s(messageInfoVO2s);
        messageVO3.setTotalCount(totalCount);
        messageVO3.setTotalPage(totalPage);
        return messageVO3;
    }

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> sendMessageAndPush(Long sendUserId, Long recipientUserId, Long messageId, String content) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("SEND", "ERROR");
        String msg;
        //判断回复的消息记录是否存在
        Message messageRecord2 = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (messageRecord2 == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        //判断回复的消息记录是否被删除状态
        if (MessageStatus2Enum.YES.getStatus().equals(messageRecord2.getReserveColumn03())) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_STATUS2_IS_VALID);
        }
        //判断这条消息记录是否已经回复了
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("id ASC, create_time ASC");
        messageExample.createCriteria().andReserveColumn04EqualTo(String.valueOf(messageRecord2.getId()));
        List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
        if (messageList != null && !messageList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_VALID);
        }

        Message messageRecord = new Message();
        messageRecord.setSendMode(MessageSendModeEnum.ACTIVE.getStatus());
        messageRecord.setStatus(MessageStatusEnum.UNREAD.getStatus());
        messageRecord.setSendUserId(sendUserId);
        messageRecord.setRecipientUserId(recipientUserId);
        messageRecord.setContent(content);
        messageRecord.setReserveColumn01(MessageTypeEnum.Commons.getMessage());
        messageRecord.setReserveColumn02(MessageType2Enum.REPLY.getCodeStr());
        messageRecord.setReserveColumn03(MessageStatus2Enum.NO.getStatus());
        messageRecord.setReserveColumn04(String.valueOf(messageId));
        User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
        if (sendUser == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        User recipientUser = this.userMapperReader.selectByPrimaryKey(recipientUserId);
        if (recipientUser == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        int b = this.messageMapperWriter.insertSelective(messageRecord);
        if (b > 0) {
            data.put("SEND", "OK");
            msg = "发送消息成功。";
            String title = sendUser.getNickName();//消息标题
            Map<String, String> extras = new HashMap<>();
            PushBean pushBean = new PushBean();
            pushBean.setAlert(content);
            pushBean.setTitle(title);
            pushBean.setExtras(extras);
            this.jiGuangPushService.pushAndroid(pushBean, recipientUser.getReserveColumn03());
            return CommonResult.success(data, msg);
        } else {
            msg = "发送消息失败。";
            return CommonResult.failed(data, ResultCode.FAILED);
        }
    }

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> allRead(Long recipientUserId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "UPDATE";
        String value = "ERROR";
        String msg = "标记全部消息已读失败。";
        int result = this.messageMapperWriter.updateAllByUserId(recipientUserId);
        if (result > 0) {
            value = "OK";
            msg = "标记全部消息已读成功。";
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }


    /**
     * <pre>
     * 描述该方法的实现功能：删除申请加微信消息记录
     * @see com.potato369.find.message.service.MessageService#delete(java.lang.Long, java.lang.Long)
     * </pre>
     */

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> delete(Long recipientUserId, Long sendUserId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "删除消息记录失败。";
        int count = this.messageMapperWriter.deleteApplicationMessageRecordByUserId(recipientUserId, sendUserId);
        if (count > 0) {
            value = "OK";
            msg = "删除消息记录成功。";
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }


    /**
     * <pre>
     * 描述该方法的实现功能：
     * @see com.potato369.find.message.service.MessageService#deleteLikes(java.lang.Long, java.lang.Long)
     * </pre>
     */
    @Override
    @Transactional
    public CommonResult<Map<String, Object>> deleteLikes(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "删除消息记录失败。";
        Message message = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (message != null) {
            message.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            message.setUpdateTime(new Date());
            message.setRecipientUserId(recipientUserId);
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(message);
            if (count > 0) {
                value = "OK";
                msg = "删除消息记录成功。";
            }
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> replyApplications(Long applicantsUserId, Long messageId, String type, String content, String weChatId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "REPLY";
        String value = "ERROR";
        //判断被申请加微信者用户信息是否存在，或者回复消息接收者用户信息是否存在
        User applicantsUser = this.userMapperReader.selectByPrimaryKey(applicantsUserId);
        if (applicantsUser == null) {
            data.put(key, value);
            return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
        }
        //判断回复的消息记录是否存在
        Message messageRecord = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (messageRecord == null) {
            data.put(key, value);
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        //判断回复的消息记录是否被删除状态
        if (MessageStatus2Enum.YES.getStatus().equals(messageRecord.getReserveColumn03())) {
            data.put(key, value);
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_STATUS2_IS_VALID);
        }
        //判断这条消息记录是否已经回复了
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("id ASC, create_time ASC");
        messageExample.createCriteria().andReserveColumn04EqualTo(String.valueOf(messageRecord.getId()));
        List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
        if (messageList != null && !messageList.isEmpty()) {
            data.put(key, value);
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_VALID);
        }
        //判断申请加微信者用户信息是否存在，或者回复消息发送者用户信息是否存在
        Long sendUserId = messageRecord.getSendUserId();
        User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
        if (sendUser == null) {
            data.put(key, value);
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        //如果是同意申请加微信
        String weixinId = applicantsUser.getWeixinId();//数据库获取到的被申请人的微信号
        if (MessageType3Enum.AGREE.getCodeStr().equals(type)) {
            if (StrUtil.isNotEmpty(weChatId)) {
                if (StrUtil.isEmpty(content)) {
                    content = "好的，我的微信号是：" + weChatId;
                } else {
                    content = content + "。好的，我的微信号是：" + weChatId;
                }
            } else {
                if (StrUtil.isNotEmpty(weixinId)) {
                    if (StrUtil.isEmpty(content)) {
                        content = "好的，我的微信号是：" + weixinId;
                    } else {
                        content = content + "。好的，我的微信号是：" + weixinId;
                    }
                }
            }
            if (StrUtil.isNotEmpty(weixinId) && !weixinId.equals(weChatId)) {
                applicantsUser.setWeixinId(weChatId);
                applicantsUser.setUpdateTime(new Date());
                this.userMapperWriter.updateByPrimaryKeySelective(applicantsUser);
            }
        }
        //如果是拒绝申请加微信
        if (MessageType3Enum.REFUSE.getCodeStr().equals(type)) {
            if (StrUtil.isEmpty(content)) {
                content = "非常抱歉，我不想加你！";
            }
        }
        Message message = new Message();
        message.setSendUserId(messageRecord.getRecipientUserId());
        message.setRecipientUserId(messageRecord.getSendUserId());
        message.setContent(content);
        message.setSendMode(MessageSendModeEnum.ACTIVE.getStatus());
        message.setStatus(MessageStatusEnum.UNREAD.getStatus());
        message.setReserveColumn01(MessageTypeEnum.Applications.getMessage());
        message.setReserveColumn02(MessageType2Enum.REPLY.getCodeStr());
        message.setReserveColumn03(MessageStatus2Enum.NO.getStatus());
        message.setReserveColumn04(String.valueOf(messageId));
        this.messageMapperWriter.insertSelective(message);
        String title = applicantsUser.getNickName();//消息标题
        PushBean pushBean = new PushBean();
        Map<String, String> extras = new HashMap<>();
        pushBean.setAlert(content);
        pushBean.setTitle(title);
        pushBean.setExtras(extras);
        this.jiGuangPushService.pushAndroid(pushBean, sendUser.getReserveColumn03());
        value = "OK";
        data.put(key, value);
        return CommonResult.success(data, ResultCode.SUCCESS.getMessage());
    }
}
