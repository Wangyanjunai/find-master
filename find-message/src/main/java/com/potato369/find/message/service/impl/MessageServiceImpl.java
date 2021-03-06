package com.potato369.find.message.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.vo.*;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import com.potato369.find.message.config.bean.PushBean;
import com.potato369.find.message.config.props.ProjectUrlProps;
import com.potato369.find.message.service.JiGuangPushService;
import com.potato369.find.message.service.MessageService;
import com.potato369.find.message.service.SensitiveWordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapperReader;

    private MessageMapper messageMapperWriter;

    private UserMapper userMapperReader;

    private UserMapper userMapperWriter;

    private ApplicationRecordMapper applicationRecordMapperReader;

    private ProjectUrlProps projectUrlProps;

    private JiGuangPushService jiGuangPushService;

    private SensitiveWordsService sensitiveWordsService;

    private LikeRecordMapper likeRecordMapperReader;

    private DynamicInfoMapper dynamicInfoMapperReader;

    private AttacheInfoMapper attacheInfoMapperReader;

    private CommentRecordMapper commentRecordMapperReader;

    private CommentMapper commentMapperReader;

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
    public void setJiGuangPushService(JiGuangPushService jiGuangPushService) {
        this.jiGuangPushService = jiGuangPushService;
    }

    @Autowired
    public void setSensitiveWordsService(SensitiveWordsService sensitiveWordsService) {
        this.sensitiveWordsService = sensitiveWordsService;
    }

    @Autowired
    public void setLikeRecordMapperReader(LikeRecordMapper likeRecordMapperReader) {
        this.likeRecordMapperReader = likeRecordMapperReader;
    }

    @Autowired
    public void setDynamicInfoMapperReader(DynamicInfoMapper dynamicInfoMapperReader) {
        this.dynamicInfoMapperReader = dynamicInfoMapperReader;
    }

    @Autowired
    public void setAttacheInfoMapperReader(AttacheInfoMapper attacheInfoMapperReader) {
        this.attacheInfoMapperReader = attacheInfoMapperReader;
    }

    @Autowired
    public void setCommentRecordMapperReader(CommentRecordMapper commentRecordMapperReader) {
        this.commentRecordMapperReader = commentRecordMapperReader;
    }

    @Autowired
    public void setCommentMapperReader(CommentMapper commentMapperReader) {
        this.commentMapperReader = commentMapperReader;
    }

    //???????????????????????????????????????????????????????????????????????????????????????
    @Override
    public LikesMessageVO selectInteractionMessage(Long userId) {
        // ???????????????????????????????????????????????????????????????????????????
        LikesMessageVO likesMessageVO = LikesMessageVO.builder().build();
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("create_time DESC");
        messageExample.createCriteria().andRecipientUserIdEqualTo(userId)
                .andReserveColumn01In(Arrays.asList(MessageTypeEnum.Likes.getMessage(), MessageTypeEnum.Comments.getMessage()))
                .andReserveColumn03EqualTo(MessageStatus2Enum.NO.getStatus());
        List<Message> messageList = this.messageMapperReader.selectByExampleWithBLOBs(messageExample);
        if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
            List<Message> messageList2 = messageList.stream().filter(message -> Objects.equals(MessageStatusEnum.UNREAD.getStatus(), message.getStatus())).collect(Collectors.toList());
            likesMessageVO.setCount(messageList2.size());//???????????????????????????????????????
            likesMessageVO.setContent(messageList.get(0).getContent());//??????????????????????????????????????????
        }
        return likesMessageVO;
    }

    @Override
    public CommentsMessageVO selectAllCommentsMessage(Long userId) {
        // ??????????????????
        List<Message> messageList = this.messageMapperReader.selectAllCommentsMessageRecord(userId);
        CommentsMessageVO commentsMessageVO = CommentsMessageVO.builder().build();
        if (!Objects.isNull(messageList) && !messageList.isEmpty()) {
            List<Message> messageList2 = messageList.stream().filter(message -> Objects.equals(MessageStatusEnum.UNREAD.getStatus(), message.getStatus())).collect(Collectors.toList());
            commentsMessageVO.setCount(messageList2.size());
            commentsMessageVO.setContent(messageList.get(0).getContent());
        }
        return commentsMessageVO;
    }

    @Override
    public MessageVO selectNotLikesMessage(Long userId, int pageNum, int pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectInteractionMessage(userId));
        final PageInfo<NotLikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectUnLikesRecordByUserId(userId));
        messageVO.setTotalCount(listPageInfo.getTotal());// ??????????????????????????????
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
    public MessageVO2 selectLikesMessage(Long userId, int pageNum, int pageSize) {
        MessageVO2 messageVO2 = MessageVO2.builder().build();
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("create_time DESC");
        messageExample.createCriteria()
                .andRecipientUserIdEqualTo(userId)
                .andReserveColumn01In(Arrays.asList(MessageTypeEnum.Likes.getMessage(), MessageTypeEnum.Comments.getMessage()))
                .andReserveColumn03EqualTo(MessageStatus2Enum.NO.getStatus());
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectByExampleWithBLOBs(messageExample));
        List<LikesInfoVO> likesInfoVOs = new ArrayList<>();
        List<Message> likesMessageRecordList = new ArrayList<>();
        if (!Objects.isNull(listPageInfo)) {
            messageVO2.setTotalCount(listPageInfo.getTotal());
            messageVO2.setTotalPage(listPageInfo.getPages());
            likesMessageRecordList = listPageInfo.getList();
        }
        for (Message message : likesMessageRecordList) {
            LikesInfoVO likesInfoVO = LikesInfoVO.builder().build();
            likesInfoVO.setMessageId(message.getId());
            String type = message.getReserveColumn01();
            //??????
            if (Objects.equals(MessageTypeEnum.Likes.getMessage(), type)) {
                Long likeRecordId = Long.valueOf(message.getReserveColumn04());
                LikeRecord likeRecord = this.likeRecordMapperReader.selectByPrimaryKey(likeRecordId);
                if (!Objects.isNull(likeRecord) && Objects.equals(LikeRecordTypeEnum.Dynamic.getType(), likeRecord.getType())) {
                    Long dynamicInfoId = likeRecord.getDynamicInfoId();
                    likesInfoVO.setDynamicInfoId(dynamicInfoId);
                    likesInfoVO.setType(LikeRecordTypeEnum.Dynamic.getType());
                    DynamicInfo dynamicInfo = this.dynamicInfoMapperReader.selectByPrimaryKey(dynamicInfoId);
                    if (!Objects.isNull(dynamicInfo)) {
                        likesInfoVO.setAttacheType(dynamicInfo.getAttacheType());
                        AttacheInfo attacheInfo = new AttacheInfo();
                        AttacheInfoExample attacheInfoExample = new AttacheInfoExample();
                        attacheInfoExample.createCriteria().andDynamicInfoByEqualTo(dynamicInfoId);
                        List<AttacheInfo> attacheInfoList = this.attacheInfoMapperReader.selectByExample(attacheInfoExample);
                        if (!Objects.isNull(attacheInfoList) && !attacheInfoList.isEmpty()) {
                            attacheInfo = attacheInfoList.get(0);
                        }
                        String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                        List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                        List<String> fileNameList03 = new ArrayList<>();
                        for (String fileName : fileNameList02) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Image.getCodeStr().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                            }
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Audio.getCodeStr().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                            }
                            stringBuilder.append(fileName);
                            fileNameList03.add(stringBuilder.toString());
                        }
                        likesInfoVO.setAttacheFilenameList(fileNameList03);
                    }
                }
                if (!Objects.isNull(likeRecord) && Objects.equals(LikeRecordTypeEnum.Comment.getType(), likeRecord.getType())) {
                    Long commentInfoId = likeRecord.getDynamicInfoId();
                    Comment comment = this.commentMapperReader.selectByPrimaryKey(commentInfoId);
                    if (!Objects.isNull(comment)) {
                        likesInfoVO.setDynamicInfoId(comment.getDynamicInfoId());
                    }
                    likesInfoVO.setType(LikeRecordTypeEnum.Comment.getType());
                }
            }
            //??????
            if (Objects.equals(MessageTypeEnum.Comments.getMessage(), type)) {
                likesInfoVO.setType(MessageTypeEnum.Comments.getCodeStr());
                Long commentRecordId = Long.valueOf(message.getReserveColumn04());
                CommentRecord commentRecord = this.commentRecordMapperReader.selectByPrimaryKey(commentRecordId);
                if (!Objects.isNull(commentRecord)) {
                    Long dynamicInfoId = commentRecord.getDynamicInfoId();
                    likesInfoVO.setDynamicInfoId(dynamicInfoId);
                    DynamicInfo dynamicInfo = this.dynamicInfoMapperReader.selectByPrimaryKey(dynamicInfoId);
                    if (!Objects.isNull(dynamicInfo)) {
                        likesInfoVO.setAttacheType(dynamicInfo.getAttacheType());
                        AttacheInfo attacheInfo = new AttacheInfo();
                        AttacheInfoExample attacheInfoExample = new AttacheInfoExample();
                        attacheInfoExample.createCriteria().andDynamicInfoByEqualTo(dynamicInfoId);
                        List<AttacheInfo> attacheInfoList = this.attacheInfoMapperReader.selectByExample(attacheInfoExample);
                        if (!Objects.isNull(attacheInfoList) && !attacheInfoList.isEmpty()) {
                            attacheInfo = attacheInfoList.get(0);
                        }
                        String[] fileNameList01 = StrUtil.split(attacheInfo.getFileName(), "||");
                        List<String> fileNameList02 = new ArrayList<>(Arrays.asList(fileNameList01));
                        List<String> fileNameList03 = new ArrayList<>();
                        for (String fileName : fileNameList02) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDomain())).append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()));
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Image.getCodeStr().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicImageFile()));
                            }
                            if (StrUtil.isNotEmpty(attacheInfo.getDataType()) && AttacheInfoDataTypeEnum.Audio.getCodeStr().equals(attacheInfo.getDataType())) {
                                stringBuilder.append(StrUtil.trimToNull(this.projectUrlProps.getResDynamicVoiceFile()));
                            }
                            stringBuilder.append(fileName);
                            fileNameList03.add(stringBuilder.toString());
                        }
                        likesInfoVO.setAttacheFilenameList(fileNameList03);
                    }
                }
            }
            likesInfoVOs.add(likesInfoVO);
            Long sendUserId = message.getSendUserId();
            likesInfoVO.setUserId(sendUserId);
            User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
            if (!Objects.isNull(sendUser) && !Objects.isNull(sendUser.getHeadIcon())) {
                likesInfoVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                        + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                        + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                        + sendUserId + "/"
                        + sendUser.getHeadIcon());
            }
            likesInfoVO.setContent(message.getContent());
            this.messageMapperWriter.updateLikesMessage(likesInfoVO.getUserId(), userId);
        }
        messageVO2.setLikesInfoVOs(likesInfoVOs);
        return messageVO2;
    }

    @Override
    public CommentsVO2 selectCommentsMessage(Long userId, int pageNum, int pageSize) {
        MessageExample messageExample = new MessageExample();
        messageExample.setOrderByClause("create_time DESC");
        messageExample.createCriteria().andRecipientUserIdEqualTo(userId).andReserveColumn01EqualTo(MessageTypeEnum.Comments.getMessage());
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectByExampleWithBLOBs(messageExample));
        CommentsVO2 commentsVO2 = CommentsVO2.builder().build();
        List<Message> messageList = new ArrayList<>();
        if (!Objects.isNull(listPageInfo)) {
            commentsVO2.setTotalCount(listPageInfo.getTotal());
            commentsVO2.setTotalPage(listPageInfo.getPages());
            messageList = listPageInfo.getList();
        }
        List<CommentInfoVO> commentInfoVOList = new ArrayList<>();
        for (Message message : messageList) {
            CommentInfoVO commentInfoVO = CommentInfoVO.builder().build();
            commentInfoVO.setMessageId(message.getId());
            Long sendUserId = message.getSendUserId();
            User user = this.userMapperReader.selectByPrimaryKey(sendUserId);
            if (!Objects.isNull(user)) {
                commentInfoVO.setUserId(sendUserId);
                commentInfoVO.setHead(StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                        + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                        + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                        + sendUserId + "/"
                        + user.getHeadIcon());
            }
            commentInfoVO.setCommentId(Long.valueOf(message.getReserveColumn04()));
            commentInfoVO.setContent(message.getContent());
            commentInfoVOList.add(commentInfoVO);
            this.messageMapperWriter.updateCommentsMessage(message.getSendUserId(), userId);
        }
        commentsVO2.setCommentInfoVOList(commentInfoVOList);
        return commentsVO2;
    }

    @Override
    public MessageVO selectNewestMessages(Long userId, int pageNum, int pageSize) {
        MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectInteractionMessage(userId));
        final PageInfo<ApplicationRecord> applicationRecordPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.applicationRecordMapperReader.selectByUserId(userId));
        if (!Objects.isNull(applicationRecordPageInfo) && applicationRecordPageInfo.getTotal() > 0) {
            List<ApplicationRecord> applicationRecordList = applicationRecordPageInfo.getList();
            List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
            for (ApplicationRecord applicationRecord : applicationRecordList) {
                long sendUserId = applicationRecord.getUserId();
                long recipientUserId = Long.parseLong(applicationRecord.getReserveColumn01());
                List<Message> messageList = this.messageMapperReader.selectApplicationMessageRecordByUserId2(sendUserId, recipientUserId);
                Message message = null;
                if (!Objects.isNull(messageList) && messageList.size() > 0) {
                    message = messageList.get(0);
                }
                if (!Objects.isNull(message)) {
                    MessageInfoVO messageInfoVO = MessageInfoVO.builder().build();
                    // ???????????????????????????????????????
                    User user1 = this.userMapperReader.selectByPrimaryKey(sendUserId);
                    // ??????????????????????????????????????????
                    User user2 = this.userMapperReader.selectByPrimaryKey(recipientUserId);
                    if (Objects.equals(sendUserId, userId)) {
                        getUserInfo(user2, messageInfoVO);
                    } else {
                        getUserInfo(user1, messageInfoVO);
                    }
                    messageInfoVO.setIsOrNotApplication(Objects.equals(sendUserId, userId));
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
                    if (MessageTypeEnum.Applications.getMessage().equals(message.getReserveColumn01()) && Objects.equals(MessageType2Enum.SEND.getCodeStr(), message.getReserveColumn02())) {
                        messageInfoVO.setType("1");
                    } else {
                        messageInfoVO.setType("0");
                    }
                    if (MessageTypeEnum.Commons.getMessage().equals(message.getReserveColumn01())) {
                        messageInfoVO.setType("0");
                    }
                    if (Objects.equals(false, Objects.equals(sendUserId, userId)) && Objects.equals(0, messageInfoVO.getFlag())) {
                        messageInfoVO.setFlag2(1);
                    } else {
                        messageInfoVO.setFlag2(0);
                    }
                    messageInfoVO.setCreateTime(DateUtil.fomateDate(message.getCreateTime(), DateUtil.sdfTimeCNFmt));
                    long count = this.messageMapperReader.countByUserId2(userId, message.getSendUserId());
                    messageInfoVO.setCount(count);
                    messageInfoVOs.add(messageInfoVO);
                }
            }
            messageInfoVOs = messageInfoVOs.stream().sorted(Comparator.comparing(MessageInfoVO::getCreateTime).reversed()).collect(Collectors.toList());
            messageVO.setMessageInfoVOs(messageInfoVOs);
            messageVO.setTotalCount(applicationRecordPageInfo.getTotal());
            messageVO.setTotalPage(applicationRecordPageInfo.getPages());
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
    public MessageVO3 selectMessageRecord(Long sendUserId, Long recipientUserId, int pageNum, int pageSize) {
        MessageVO3 messageVO3 = MessageVO3.builder().build();
        final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> this.messageMapperReader.selectMessageRecord(sendUserId, recipientUserId));
        if (!Objects.isNull(listPageInfo) && listPageInfo.getTotal() > 0) {
            List<Message> messages = listPageInfo.getList();
            List<MessageInfoVO2> messageInfoVO2s = new ArrayList<>();
            if (!Objects.isNull(messages) && !messages.isEmpty()) {
                for (Message message : messages) {
                    long sendUserIdTmp = message.getSendUserId();
                    User sendUser = this.userMapperReader.selectByPrimaryKey(sendUserIdTmp);
                    if (!Objects.isNull(sendUser)) {
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
                            content = StrUtil.removeAll(content, "|" + sendUser.getWeixinId()) + "????????????????????????" + sendUser.getWeixinId();
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
            messageVO3.setTotalCount(listPageInfo.getTotal());
            messageVO3.setTotalPage(listPageInfo.getPages());
        }
        return messageVO3;
    }

    @Override
    public CommonResult<Map<String, Object>> sendMessageAndPush(Long sendUserId, Long messageId, String content) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("SEND", "ERROR");
        String msg;
        // ???????????????????????????????????????
        Message messageRecord2 = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (Objects.isNull(messageRecord2)) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        // ????????????????????????????????????????????????
        if (MessageStatus2Enum.YES.getStatus().equals(messageRecord2.getReserveColumn03())) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_STATUS2_IS_VALID);
        }
        // ????????????????????????????????????????????????
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
        long recipientUserId;
        if (recipientUser.getId().equals(sendUser.getId())) {
            recipientUserId = messageRecord2.getSendUserId();
        } else {
            recipientUserId = messageRecord2.getRecipientUserId();
        }
        User recipientUser1 = this.userMapperReader.selectByPrimaryKey(recipientUserId);
        if (recipientUser1 == null) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_USER_IS_NOT_EXIST);
        }
        //???????????????????????????????????????????????????
        SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(content);
        if (!Objects.isNull(sensitiveWords)) {
            return CommonResult.validateFailed("??????????????????????????????" + sensitiveWords.getTypeName() + "???????????????????????????????????????");
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
            msg = "?????????????????????";
            String title = sendUser.getNickName();// ????????????
            Map<String, String> extras = new HashMap<>();
            PushBean pushBean = new PushBean();
            pushBean.setAlert(content);
            pushBean.setTitle(title);
            pushBean.setExtras(extras);
            this.jiGuangPushService.pushAndroid(pushBean, recipientUser1.getReserveColumn03());
        } else {
            msg = "?????????????????????";
        }
        return CommonResult.success(data, msg);
    }

    @Override
    public CommonResult<Map<String, Object>> allRead(Long recipientUserId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "UPDATE";
        String value = "ERROR";
        String msg = "?????????????????????????????????";
        int result = this.messageMapperWriter.updateAllByUserId(recipientUserId);
        if (result > 0) {
            value = "OK";
            msg = "?????????????????????????????????";
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }

    /**
     * <pre>
     * ??????????????????????????????????????????????????????????????????
     * </pre>
     */

    @Override
    public CommonResult<Map<String, Object>> delete(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "???????????????????????????";
        Message message = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (!Objects.isNull(message)) {
            message.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            message.setUpdateTime(new Date());
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(message);
            if (count > 0) {
                value = "OK";
                msg = "???????????????????????????";
            }
            data.put(key, value);
        }
        return CommonResult.success(data, msg);
    }

    /**
     * <pre>
     * ?????????????????????????????????
     * </pre>
     */
    @Override
    public CommonResult<Map<String, Object>> deleteLikes(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "???????????????????????????";
        Message messageRecord = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (!Objects.isNull(messageRecord) && Objects.equals(messageRecord.getRecipientUserId(), recipientUserId)) {
            messageRecord.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            messageRecord.setUpdateTime(new Date());
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(messageRecord);
            if (count > 0) {
                value = "OK";
                msg = "???????????????????????????";
            }
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }

    /**
     * <pre>
     * ?????????????????????????????????
     * </pre>
     */
    @Override
    public CommonResult<Map<String, Object>> deleteComments(Long recipientUserId, Long messageId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        String key = "DELETE";
        String value = "ERROR";
        String msg = "???????????????????????????";
        Message messageRecord = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (!Objects.isNull(messageRecord) && Objects.equals(messageRecord.getRecipientUserId(), recipientUserId)) {
            messageRecord.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            messageRecord.setUpdateTime(new Date());
            int count = this.messageMapperWriter.updateByPrimaryKeySelective(messageRecord);
            if (count > 0) {
                value = "OK";
                msg = "???????????????????????????";
            }
        }
        data.put(key, value);
        return CommonResult.success(data, msg);
    }

    @Override
    public CommonResult<Map<String, Object>> replyApplications(Long applicantsUserId, Long messageId, String type, String content, String weChatId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        data.put("REPLY", "ERROR");
        // ???????????????????????????
        Message messageOld = this.messageMapperReader.selectByPrimaryKey(messageId);
        if (Objects.isNull(messageOld)) {
            return CommonResult.failed(data, ResultCode.REPLY_MESSAGE_IS_NOT_EXIST);
        }
        // ?????????????????????id,?????????
        long sendUserId = messageOld.getSendUserId();
        // ?????????????????????????????????????????????????????????????????????????????????????????????????????????
        User applicantsUser = this.userMapperReader.selectByPrimaryKey(applicantsUserId);
        if (Objects.isNull(applicantsUser)) {
            return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
        }
        // ??????????????????????????????????????????????????????????????????????????????????????????????????????
        User applicantUser = this.userMapperReader.selectByPrimaryKey(sendUserId);
        if (Objects.isNull(applicantUser)) {
            return CommonResult.failed(data, ResultCode.APPLICANTS_USER_IS_NOT_EXIST);
        }
        ApplicationRecordExample applicationRecordExample = new ApplicationRecordExample();
        applicationRecordExample.createCriteria()
                .andUserIdEqualTo(sendUserId)
                .andReserveColumn01EqualTo(String.valueOf(applicantsUserId));
        log.info("sendUserId={}, applicantsUserId={}", sendUserId, applicantsUserId);
        List<ApplicationRecord> applicationRecordList = this.applicationRecordMapperReader.selectByExample(applicationRecordExample);
        if (Objects.isNull(applicationRecordList) || applicationRecordList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID1);
        }
        MessageExample messageExample = new MessageExample();
        messageExample.createCriteria().andRecipientUserIdEqualTo(applicantsUserId)
                .andSendUserIdEqualTo(sendUserId)
                .andReserveColumn01EqualTo(MessageTypeEnum.Applications.getMessage());
        List<Message> messageList = this.messageMapperReader.selectByExample(messageExample);
        if (Objects.isNull(messageList) || messageList.isEmpty()) {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID2);
        }
        ApplicationRecord applicationRecord = applicationRecordList.get(0);
        if (!Objects.equals(Long.valueOf(applicationRecord.getReserveColumn01()), applicantsUserId)) {
            return CommonResult.failed(data, ResultCode.REPLY_APPLICATIONS_MESSAGE_IS_VALID3);
        }
        if (StrUtil.isNotEmpty(content)) {
            //?????????????????????????????????????????????
            SensitiveWords sensitiveWords = this.sensitiveWordsService.checkHasSensitiveWords(content);
            if (!Objects.isNull(sensitiveWords)) {
                return CommonResult.validateFailed("??????????????????????????????" + sensitiveWords.getTypeName() + "????????????????????????????????????");
            }
        }
        Message message = new Message();
        // ??????????????????????????????
        String welkinId = applicantsUser.getWeixinId();// ?????????????????????????????????????????????
        if (MessageType3Enum.AGREE.getCodeStr().equals(type)) {
            if (StrUtil.isNotEmpty(weChatId)) {
                if (StrUtil.isEmpty(content)) {
                    content = "?????????????????????????????????????????????|" + weChatId;
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
                        content = "?????????????????????????????????????????????|" + welkinId;
                    } else {
                        if (content.contains(welkinId)) {
                            content = content + "|";
                        } else {
                            content = content + "|" + welkinId;
                        }
                    }
                }
            }
            if (!Objects.equals(weChatId, welkinId)) {
                applicantsUser.setWeixinId(weChatId);
                applicantsUser.setUpdateTime(new Date());
                this.userMapperWriter.updateByPrimaryKeySelective(applicantsUser);
            }
            message.setReserveColumn03(MessageStatus2Enum.NO.getStatus());
        }
        // ??????????????????????????????
        if (MessageType3Enum.REFUSE.getCodeStr().equals(type)) {
            if (StrUtil.isEmpty(content)) {
                content = "?????????????????????????????????";
            }
            message.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            messageOld.setReserveColumn03(MessageStatus2Enum.YES.getStatus());
            messageOld.setUpdateTime(new Date());
            this.messageMapperWriter.updateByPrimaryKey(messageOld);
        }
        message.setSendUserId(applicantsUserId);
        message.setRecipientUserId(sendUserId);
        message.setContent(content);
        message.setSendMode(MessageSendModeEnum.ACTIVE.getStatus());
        message.setStatus(MessageStatusEnum.UNREAD.getStatus());
        message.setReserveColumn01(MessageTypeEnum.Applications.getMessage());
        message.setReserveColumn02(MessageType2Enum.REPLY.getCodeStr());
        message.setReserveColumn04(String.valueOf(messageOld.getId()));
        this.messageMapperWriter.insertSelective(message);
        String title = applicantsUser.getNickName();// ????????????
        PushBean pushBean = new PushBean();
        pushBean.setAlert(content);
        pushBean.setTitle(title);
        if (!Objects.isNull(applicantUser.getReserveColumn03())) {
            this.jiGuangPushService.pushAndroid(pushBean, applicantUser.getReserveColumn03());
        }
        data.put("REPLY", "OK");
        return CommonResult.success(data, ResultCode.SUCCESS.getMessage());
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     *
     * @param recipientUserId ?????????????????????id
     */
    @Override
    public CommonResult<Map<String, Object>> count(Long recipientUserId) {
        Map<String, Object> data = new ConcurrentHashMap<>();
        long count = messageMapperReader.selectUnReadMessageCount(recipientUserId);
        data.put("unReadCount", count);
        return CommonResult.success(data);
    }
}
