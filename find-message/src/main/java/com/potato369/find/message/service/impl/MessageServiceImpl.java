package com.potato369.find.message.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.vo.*;
import com.potato369.find.mbg.mapper.ApplicationRecordMapper;
import com.potato369.find.mbg.mapper.MessageMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.*;
import com.potato369.find.message.config.bean.PushBean;
import com.potato369.find.message.config.props.ProjectUrlProps;
import com.potato369.find.message.service.JiGuangPushService;
import com.potato369.find.message.service.MessageService;
import com.potato369.find.message.service.SensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapperReader;

    private MessageMapper messageMapperWriter;

    private UserMapper userMapperReader;

    private UserMapper userMapperWriter;

    private ApplicationRecordMapper applicationRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

    private JiGuangPushService jiGuangPushService;

    private SensitiveWordsService sensitiveWordsService;

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
    public void setApplicationRecordMapperReader(ApplicationRecordMapper applicationRecordMapperReader) {
        this.applicationRecordMapperReader = applicationRecordMapperReader;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Autowired
    public void setJiGuangPushService(JiGuangPushServiceImpl jiGuangPushService) {
        this.jiGuangPushService = jiGuangPushService;
    }

    @Autowired
    public void setSensitiveWordsService(SensitiveWordsService sensitiveWordsService) {
        this.sensitiveWordsService = sensitiveWordsService;
    }

    @Override
    @Transactional(readOnly = true)
    public LikesMessageVO selectAllLikesMessage(Long userId, int pageNum, int pageSize) {
        // 查询点赞消息
        LikesMessageVO likesMessageVO = LikesMessageVO.builder().build();
        final PageInfo<Message> messagePageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectAllLikesMessageRecord(userId));
        // 查询最后一条点赞消息记录
        if (!Objects.isNull(messagePageInfo) && messagePageInfo.getTotal() > 0) {
            List<Message> messageList = messagePageInfo.getList();
            if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
                List<Message> messageList2 = messageList.stream().filter(message -> Objects.equals(MessageStatusEnum.UNREAD.getStatus(), message.getStatus())).collect(Collectors.toList());
                likesMessageVO.setCount((long) messageList2.size());
                likesMessageVO.setContent(messageList.get(0).getContent());
            }
        } else {
            likesMessageVO.setCount(0L);
            likesMessageVO.setContent(null);
        }
        return likesMessageVO;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentsMessageVO selectAllCommentsMessage(Long userId, int pageNum, int pageSize) {
        // 查询点赞消息
        CommentsMessageVO commentsMessageVO = CommentsMessageVO.builder().build();
        final PageInfo<Message> messagePageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectAllCommentsMessageRecord(userId));
        // 查询最后一条点赞消息记录
        if (!Objects.isNull(messagePageInfo) && messagePageInfo.getTotal() > 0) {
            List<Message> messageList = messagePageInfo.getList();
            if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
                List<Message> messageList2 = messageList.stream().filter(message -> Objects.equals(MessageStatusEnum.UNREAD.getStatus(), message.getStatus())).collect(Collectors.toList());
                commentsMessageVO.setCount((long) messageList2.size());
                commentsMessageVO.setContent(messageList.get(0).getContent());
            }
        } else {
            commentsMessageVO.setCount(0L);
            commentsMessageVO.setContent(null);
        }
        return commentsMessageVO;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageVO selectNotLikesMessage(Long userId, int pageNum, int pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectAllLikesMessage(userId, pageNum, pageSize));
        final PageInfo<NotLikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> this.messageMapperReader.selectUnLikesRecordByUserId(userId));
        messageVO.setTotalCount(listPageInfo.getTotal());// 未读申请加微信总数量
        messageVO.setTotalPage(listPageInfo.getPages());
        List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
        List<NotLikesMessageRecord> notLikesMessageRecordList = listPageInfo.getList();
        for (NotLikesMessageRecord notLikesMessageRecord : notLikesMessageRecordList) {
            MessageInfoVO messageInfoVO = new MessageInfoVO();
            messageInfoVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) + notLikesMessageRecord.getSendUserId()
                    + "/" + notLikesMessageRecord.getSendUserHead());
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
    public MessageVO2 selectLikesMessage(Long userId, int pageNum, int pageSize) {
        MessageVO2 messageVO2 = MessageVO2.builder().build();
        final PageInfo<LikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectLikesMessageRecordByUserId(userId));
        messageVO2.setTotalCount(listPageInfo.getTotal());
        messageVO2.setTotalPage(listPageInfo.getPages());
        List<LikesInfoVO> likesInfoVOs = new ArrayList<>();
        List<LikesMessageRecord> likesMessageRecordList = listPageInfo.getList();
        for (LikesMessageRecord likesMessageRecord : likesMessageRecordList) {
            LikesInfoVO likesInfoVO = LikesInfoVO.builder().build();
            likesInfoVO.setMessageId(likesMessageRecord.getMessageId());
            likesInfoVO.setDynamicInfoId(likesMessageRecord.getDynamicInfoId());
            likesInfoVO.setUserId(likesMessageRecord.getUserId());
            likesInfoVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) + likesMessageRecord.getUserId() + "/"
                    + likesMessageRecord.getHeadIcon());
            likesInfoVO.setAttacheType(likesMessageRecord.getAttacheType());
            likesInfoVO.setContent(likesMessageRecord.getLikesContent());
            String[] fileNameList01 = StrUtil.split(likesMessageRecord.getAttacheFilename(), "||");
            List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
            List<String> fileNameList03 = new ArrayList<>();
            for (String fileName : fileNameList02) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
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
    @Transactional
    public CommentsVO2 selectCommentsMessage(Long userId, int pageNum, int pageSize) {
        CommentsVO2 commentsVO2 = CommentsVO2.builder().build();
        final PageInfo<LikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> this.messageMapperReader.selectLikesMessageRecordByUserId(userId));
        commentsVO2.setTotalCount(listPageInfo.getTotal());
        commentsVO2.setTotalPage(listPageInfo.getPages());
        List<LikesInfoVO> likesInfoVOs = new ArrayList<>();
        List<LikesMessageRecord> likesMessageRecordList = listPageInfo.getList();
        for (LikesMessageRecord likesMessageRecord : likesMessageRecordList) {
            LikesInfoVO likesInfoVO = LikesInfoVO.builder().build();
            likesInfoVO.setMessageId(likesMessageRecord.getMessageId());
            likesInfoVO.setUserId(likesMessageRecord.getUserId());
            likesInfoVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) + likesMessageRecord.getUserId() + "/"
                    + likesMessageRecord.getHeadIcon());
            likesInfoVO.setAttacheType(likesMessageRecord.getAttacheType());
            likesInfoVO.setContent(likesMessageRecord.getLikesContent());
            String[] fileNameList01 = StrUtil.split(likesMessageRecord.getAttacheFilename(), "||");
            List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
            List<String> fileNameList03 = new ArrayList<>();
            for (String fileName : fileNameList02) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain()))
                        .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                if (StrUtil.isNotEmpty(likesMessageRecord.getAttacheType()) && AttacheInfoDataTypeEnum.Image.getCode()
                        .toString().equals(likesMessageRecord.getAttacheType())) {
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                }
                if (StrUtil.isNotEmpty(likesMessageRecord.getAttacheType()) && AttacheInfoDataTypeEnum.Audio.getCode()
                        .toString().equals(likesMessageRecord.getAttacheType())) {
                    stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                }
                stringBuilder.append(fileName);
                fileNameList03.add(stringBuilder.toString());
            }
            likesInfoVO.setAttacheFilenameList(fileNameList03);
            likesInfoVOs.add(likesInfoVO);
            this.messageMapperWriter.updateLikesMessage(likesInfoVO.getUserId(), userId);
        }
//        commentsVO2.getCommentVOList(new ArrayList<>());
        return commentsVO2;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageVO selectApplicationsMessage(Long userId, int pageNum, int pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectAllLikesMessage(userId, pageNum, pageSize));
        messageVO.setCommentsMessageVO(this.selectAllCommentsMessage(userId, pageNum, pageSize));
        List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByUserId(userId);
        List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
        List<MessageInfoVO> messageInfoVOs2 = new ArrayList<>();
        if (!Objects.isNull(applicationRecordList) && !applicationRecordList.isEmpty()) {
            for (ApplicationRecord applicationRecord : applicationRecordList) {
                long sendUserId = applicationRecord.getUserId();//申请加微信者
                long recipientUserId = Long.parseLong(applicationRecord.getReserveColumn01());//被申请加微信者
                final PageInfo<Message> messagePageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectApplicationMessageRecordByUserId2(sendUserId, recipientUserId));
                if (!Objects.isNull(messagePageInfo) && messagePageInfo.getTotal() > 0) {
                    List<Message> messageList = messagePageInfo.getList();
                    if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
                        Message message = messageList.get(0);//获取最新的一条消息
                        if (!Objects.isNull(message)) {
                            MessageInfoVO messageInfoVO = MessageInfoVO.builder().build();
                            // 消息发送者（申请加微信者）
                            User user1 = this.userMapperReader.selectByPrimaryKey(sendUserId);
                            // 消息接收者（被申请加微信者）
                            User user2 = this.userMapperReader.selectByPrimaryKey(recipientUserId);
                            if (Objects.equals(sendUserId, userId)) {
                                getUserInfo(user2, messageInfoVO);
                            } else {
                                getUserInfo(user1, messageInfoVO);
                            }
                            messageInfoVO.setIsOrNotApplication(Objects.equals(applicationRecord.getUserId(), userId));
                            messageInfoVO.setMessageId(message.getId());
                            if (MessageType2Enum.REPLY.getCodeStr().equals(message.getReserveColumn02()) && MessageTypeEnum.Applications.getMessage().equals(message.getReserveColumn01())) {
                                messageInfoVO.setFlag(1);
                                String contentString = message.getContent();
                                if (StrUtil.contains(contentString, "|")) {
                                    String[] strings = StrUtil.split(contentString, "|");
                                    messageInfoVO.setContent(strings[0]);
                                    if (StrUtil.isEmpty(strings[1])) {
                                        messageInfoVO.setWeixinId(user1.getWeixinId());
                                    } else {
                                        messageInfoVO.setWeixinId(strings[1]);
                                    }
                                } else {
                                    messageInfoVO.setContent(contentString);
                                    messageInfoVO.setWeixinId(user1.getWeixinId());
                                }
                            } else {
                                messageInfoVO.setFlag(0);
                                messageInfoVO.setContent(message.getContent());
                            }
                            if (MessageTypeEnum.Applications.getMessage().equals(message.getReserveColumn01())) {
                                messageInfoVO.setType("1");
                            }
                            if (MessageTypeEnum.Commons.getMessage().equals(message.getReserveColumn01())) {
                                messageInfoVO.setType("0");
                            }
                            messageInfoVO.setCreateTime(DateUtil.fomateDate(message.getCreateTime(), DateUtil.sdfTimeCNFmt));
                            messageInfoVO.setCount(this.messageMapperReader.countByUserId2(sendUserId, recipientUserId, userId));
                            messageInfoVOs.add(messageInfoVO);
                            messageInfoVOs2 = messageInfoVOs.stream().sorted(Comparator.comparing(MessageInfoVO::getCreateTime).reversed()).collect(Collectors.toList());
                        }
                    }
                    messageVO.setMessageInfoVOs(messageInfoVOs2);
                    messageVO.setTotalCount(messagePageInfo.getTotal());
                    messageVO.setTotalPage(messagePageInfo.getPages());
                }
            }
        }
        return messageVO;
    }

    private void getUserInfo(User user, MessageInfoVO messageInfoVO) {
        if (user != null) {
            messageInfoVO.setUserId(user.getId());
            messageInfoVO.setHead(
                    StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                            + user.getId() + "/"
                            + user.getHeadIcon());
            messageInfoVO.setNickname(user.getNickName());
        }
    }

    @Override
    @Transactional
    public MessageVO3 selectMessageRecord(Long sendUserId, Long recipientUserId, int pageNum, int pageSize) {
        MessageVO3 messageVO3 = MessageVO3.builder().build();
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> this.messageMapperReader.selectMessageRecord(sendUserId, recipientUserId));
        List<Message> messages = listPageInfo.getList();
        long totalCount = listPageInfo.getTotal();
        int totalPage = listPageInfo.getPages();
        ArrayList<MessageInfoVO2> messageInfoVO2s = new ArrayList<>();
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
                    messageInfoVO2.setSendUserHeadIcon(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                            + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                            + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon()) + sendUser.getId() + "/"
                            + sendUser.getHeadIcon());
                    String content = message.getContent();
                    if (StrUtil.isNotEmpty(content) && StrUtil.contains(content, "|") && StrUtil.contains(content, sendUser.getWeixinId())) {
                        content = StrUtil.removeAll(content, "|" + sendUser.getWeixinId()) + "，我的微信号是：" + sendUser.getWeixinId();
                    }
                    messageInfoVO2.setContent(content);
                    messageInfoVO2s.add(messageInfoVO2);
                }
                if (message.getSendUserId().equals(sendUserId) && message.getRecipientUserId().equals(recipientUserId)) {
                    message.setUpdateTime(new Date());
                    message.setStatus(MessageStatusEnum.READ.getStatus());
                    this.messageMapperWriter.updateByPrimaryKeySelective(message);
                }
            }
        }
        messageVO3.setMessageInfoVO2s(messageInfoVO2s);
        messageVO3.setTotalCount(totalCount);
        messageVO3.setTotalPage(totalPage);
        return messageVO3;
    }

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> sendMessageAndPush(Long sendUserId, Long messageId, String content) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("SEND", "ERROR");
        String msg;
        // 判断回复的消息记录是否存在
        Message messageRecord2 = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (Objects.isNull(messageRecord2)) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        // 判断回复的消息记录是否被删除状态
        if (MessageStatus2Enum.YES.getStatus().equals(messageRecord2.getReserveColumn03())) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_STATUS2_IS_VALID);
        }
        // 判断这条消息记录是否已经被回复了
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("id ASC, create_time ASC");
        messageExample.createCriteria().andReserveColumn04EqualTo(String.valueOf(messageRecord2.getId()));
        List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
        if (messageList != null && !messageList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_VALID);
        }
        User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
        if (sendUser == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        User recipientUser = this.userMapperReader.selectByPrimaryKey(messageRecord2.getRecipientUserId());
        if (recipientUser == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        long recipientUserId = 0L;
        if (recipientUser.getId().equals(sendUser.getId())) {
            recipientUserId = messageRecord2.getSendUserId();
        } else {
            recipientUserId = messageRecord2.getRecipientUserId();
        }
        User recipientUser1 = this.userMapperReader.selectByPrimaryKey(recipientUserId);
        if (recipientUser1 == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        //校验发布的消息内容是否包含敏感词汇
        SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(content);
        if (!Objects.isNull(sensitiveWords)) {
            return CommonResult.validateFailed("发消息，消息内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，不允许发布。");
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
        int b = this.messageMapperWriter.insertSelective(messageRecord);
        if (b > 0 && !sendUserId.equals(recipientUser1.getId())) {
            data.put("SEND", "OK");
            msg = "发送消息成功。";
            String title = sendUser.getNickName();// 消息标题
            Map<String, String> extras = new HashMap<>();
            PushBean pushBean = new PushBean();
            pushBean.setAlert(content);
            pushBean.setTitle(title);
            pushBean.setExtras(extras);
            this.jiGuangPushService.pushAndroid(pushBean, recipientUser1.getReserveColumn03());
        } else {
            msg = "发送消息失败。";
        }
        return CommonResult.success(data, msg);
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
     * </pre>
     */

    @Override
    @Transactional
    public CommonResult<Map<String, Object>> delete(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "删除消息记录失败。";
        Message message = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (!Objects.isNull(message)) {
            message.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            message.setUpdateTime(new Date());
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(message);
            if (count > 0) {
                value = "OK";
                msg = "删除消息记录成功。";
            }
            data.put(key, value);
        }
        return CommonResult.success(data, msg);
    }

    /**
     * <pre>
     * 描述该方法的实现功能：
     * </pre>
     */
    @Override
    @Transactional
    public CommonResult<Map<String, Object>> deleteLikes(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "删除消息记录失败。";
        Message messageRecord = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (messageRecord != null) {
            messageRecord.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            messageRecord.setUpdateTime(new Date());
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(messageRecord);
            if (count > 0) {
                value = "OK";
                msg = "删除消息记录成功。";
            }
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }

    @Override
    @Transactional(readOnly = false)
    public CommonResult<Map<String, Object>> replyApplications(Long applicantsUserId, Long messageId, String type, String content, String weChatId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("REPLY", "ERROR");
        // 申请加微信消息记录
        Message messageOld = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (Objects.isNull(messageOld)) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        // 消息发送者用户id,申请者
        long sendUserId = messageOld.getSendUserId();
        // 判断被申请加微信者用户信息是否存在，或者回复消息接收者用户信息是否存在
        User applicantsUser = this.userMapperReader.selectByPrimaryKey(applicantsUserId);
        if (Objects.isNull(applicantsUser)) {
            return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
        }
        // 判断申请加微信者用户信息是否存在，或者回复消息发送者用户信息是否存在
        User applicantUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
        if (Objects.isNull(applicantUser)) {
            return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
        }
        ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
        applicationRecordExample.createCriteria()
                .andUserIdEqualTo(sendUserId)
                .andReserveColumn01EqualTo(String.valueOf(applicantsUserId));
        List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
        if (Objects.isNull(applicationRecordList) || applicationRecordList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID);
        }
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andRecipientUserIdEqualTo(applicantsUserId).andSendUserIdEqualTo(sendUserId).andReserveColumn01EqualTo(MessageTypeEnum.Applications.getMessage());
        List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
        if (Objects.isNull(messageList) || messageList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID);
        }
        ApplicationRecord applicationRecord = applicationRecordList.get(0);
        if (!Objects.isNull(applicationRecord)) {
            // 如果是同意申请加微信
            String welkinId = applicantsUser.getWeixinId();// 数据库获取到的被申请人的微信号
            if (StrUtil.isNotEmpty(content)) {
                //校验发布的内容是否包含敏感词汇
                SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(content);
                if (!Objects.isNull(sensitiveWords)) {
                    return CommonResult.validateFailed("发消息，消息内容包含" + sensitiveWords.getTypeName() + "类型敏感词汇，禁止发送。");
                }
            }
            if (MessageType3Enum.AGREE.getCodeStr().equals(type)) {
                if (StrUtil.isNotEmpty(weChatId)) {
                    if (StrUtil.isEmpty(content)) {
                        content = "已同意添加微信，我的微信号是：|" + weChatId;
                    } else {
                        if (content.contains(weChatId)) {
                            content = content + "|";
                        } else {
                            content = content + "|" + weChatId;
                        }
                    }
                } else {
                    if (StrUtil.isNotEmpty(welkinId)) {
                        if (StrUtil.isEmpty(content)) {
                            content = "已同意添加微信，我的微信号是：|" + welkinId;
                        } else {
                            if (content.contains(welkinId)) {
                                content = content + "|";
                            } else {
                                content = content + "|" + weChatId;
                            }
                        }
                    }
                }
                if (StrUtil.isNotEmpty(weChatId) && !weChatId.equals(welkinId)) {
                    applicantsUser.setWeixinId(weChatId);
                    applicantsUser.setUpdateTime(new Date());
                    this.userMapperWriter.updateByPrimaryKeySelective(applicantsUser);
                }
            }
            // 如果是拒绝申请加微信
            if (MessageType3Enum.REFUSE.getCodeStr().equals(type)) {
                if (StrUtil.isEmpty(content)) {
                    content = "非常抱歉，我不想加你！";
                }
            }
            Message message = new Message();
            message.setSendUserId(applicantsUserId);
            message.setRecipientUserId(sendUserId);
            message.setContent(content);
            message.setSendMode(MessageSendModeEnum.ACTIVE.getStatus());
            message.setStatus(MessageStatusEnum.UNREAD.getStatus());
            message.setReserveColumn01(MessageTypeEnum.Applications.getMessage());
            message.setReserveColumn02(MessageType2Enum.REPLY.getCodeStr());
            message.setReserveColumn03(MessageStatus2Enum.NO.getStatus());
            message.setReserveColumn04(String.valueOf(messageOld.getId()));
            this.messageMapperWriter.insertSelective(message);
            String title = applicantsUser.getNickName();// 消息标题
            PushBean pushBean = new PushBean();
            pushBean.setAlert(content);
            pushBean.setTitle(title);
            this.jiGuangPushService.pushAndroid(pushBean, applicantUser.getReserveColumn03());
            data.put("REPLY", "OK");
            return CommonResult.success(data, ResultCode.SUCCESS.getMessage());
        } else {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID);
        }
    }
}
