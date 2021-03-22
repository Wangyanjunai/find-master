package com.potato369.find.message.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.potato369.find.common.enums.AttacheInfoDataTypeEnum;
import com.potato369.find.common.enums.MessageStatusEnum;
import com.potato369.find.common.enums.MessageTypeEnum;
import com.potato369.find.common.vo.LikesInfoVO;
import com.potato369.find.common.vo.LikesMessageVO;
import com.potato369.find.common.vo.MessageInfoVO;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.mbg.mapper.MessageMapper;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.LikesMessageRecord;
import com.potato369.find.mbg.model.Message;
import com.potato369.find.mbg.model.MessageExample;
import com.potato369.find.mbg.model.NotLikesMessageRecord;
import com.potato369.find.mbg.model.User;
import com.potato369.find.message.config.props.ProjectUrlProps;
import com.potato369.find.message.service.MessageService;

import cn.hutool.core.util.StrUtil;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageMapper messageMapperReader;
    
    private UserMapper userMapperReader;
    
    private ProjectUrlProps projectUrlProps;

    @Autowired
    public void setMessageMapperReader(MessageMapper messageMapperReader) {
        this.messageMapperReader = messageMapperReader;
    }

    @Autowired
	public void setUserMapperReader(UserMapper userMapperReader) {
		this.userMapperReader = userMapperReader;
	}

	@Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
		this.projectUrlProps = projectUrlProps;
	}

    @Override
    @Transactional(readOnly = true)
    public LikesMessageVO selectLikesMessage(Long userId) {
    	//查询未读点赞消息总条数
        MessageExample messageExample = new MessageExample();
        messageExample.setDistinct(true);
        messageExample.setOrderByClause("create_time DESC, update_time DESC");
        messageExample.createCriteria()
                .andReserveColumn01EqualTo(MessageTypeEnum.Likes.getMessage())
                .andStatusEqualTo(MessageStatusEnum.UNREAD.getStatus())
                .andRecipientUserIdEqualTo(userId);
        LikesMessageVO likesMessageVO = new LikesMessageVO();
        List<Message> messages = this.messageMapperReader.selectByExample(messageExample);
        if (messages != null && !messages.isEmpty()) {
        	likesMessageVO.setCount(messages.size());
		} else {
			likesMessageVO.setCount(0);
		}
        //查询最后一条点赞消息记录
        MessageExample messageExample2 = new MessageExample();
        messageExample2.setDistinct(true);
        messageExample2.setOrderByClause("create_time DESC, update_time DESC");
        messageExample2.createCriteria()
                .andReserveColumn01EqualTo(MessageTypeEnum.Likes.getMessage())
                .andRecipientUserIdEqualTo(userId);
        List<Message> messages2 = this.messageMapperReader.selectByExampleWithBLOBs(messageExample2);
        if (messages2 != null && !messages2.isEmpty()) {
            Message message = messages2.get(0);
            likesMessageVO.setContent(message.getContent());
        }
        return likesMessageVO;
    }

    @Override
    @Transactional(readOnly = true)
    public MessageVO selectNotLikesMessage(Long userId, Integer pageNum, Integer pageSize) {
        MessageVO messageVO = new MessageVO();
        messageVO.setLikesMessageVO(this.selectLikesMessage(userId));
        final PageInfo<NotLikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> this.messageMapperReader.selectUnLikesRecordByUserId(userId));
        messageVO.setTotalCount(listPageInfo.getTotal());
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
	@Transactional(readOnly = true)
	public MessageVO2 selectLikesMessage(Long userId, Integer pageNum, Integer pageSize) {
		MessageVO2 messageVO2 = MessageVO2.builder().build();
		final PageInfo<LikesMessageRecord> listPageInfo = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(() -> this.messageMapperReader.selectLikesMessageRecordByUserId(userId));
		messageVO2.setTotalCount(listPageInfo.getTotal());
		messageVO2.setTotalPage(listPageInfo.getPages());
		List<LikesInfoVO> likesInfoVOs = new ArrayList<>();
		List<LikesMessageRecord> likesMessageRecordList = listPageInfo.getList();
		for (LikesMessageRecord likesMessageRecord : likesMessageRecordList) {
			LikesInfoVO likesInfoVO = LikesInfoVO.builder().build();
			likesInfoVO.setUserId(likesMessageRecord.getUserId());
			likesInfoVO.setHead(
					  StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + likesMessageRecord.getUserId()
                    + "/"
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
		}
		messageVO2.setLikesInfoVOs(likesInfoVOs);
		return messageVO2;
	}

	@Override
	public MessageVO selectApplicationsMessage(Long userId, Integer pageNum, Integer pageSize) {
		MessageVO messageVO = MessageVO.builder().build();
        messageVO.setLikesMessageVO(this.selectLikesMessage(userId));
		final PageInfo<Message> listPageInfo = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(() -> this.messageMapperReader.selectApplicationMessageRecordByUserId(userId));
		List<Message> messages = listPageInfo.getList();
		List<MessageInfoVO> messageInfoVOs = new ArrayList<>();
		if (messages != null && !messages.isEmpty()) {
			for (Message message : messages) {
				MessageInfoVO messageInfoVO = MessageInfoVO.builder().build();
				User user = this.userMapperReader.selectByPrimaryKey(message.getSendUserId());
				messageInfoVO.setUserId(user.getId());
				messageInfoVO.setHead(
					  StrUtil.trimToNull(this.projectUrlProps.getResDomain())
                    + StrUtil.trimToNull(this.projectUrlProps.getProjectName())
                    + StrUtil.trimToNull(this.projectUrlProps.getResHeadIcon())
                    + user.getId()
                    + "/"
                    + user.getHeadIcon());
				messageInfoVO.setNickname(user.getNickName());
				messageInfoVO.setContent(message.getContent());
				Long count = this.messageMapperReader.
						selectApplicationMessageRecordBySendUserIdAndRecipientUserIdCount(message.getSendUserId(), message.getRecipientUserId());
				messageInfoVO.setCount(count);
				messageInfoVOs.add(messageInfoVO);
			}
		}
		messageVO.setMessageInfoVOs(messageInfoVOs);
		messageVO.setTotalCount(listPageInfo.getTotal());
		messageVO.setTotalPage(listPageInfo.getPages());
		return messageVO;
	}
}
