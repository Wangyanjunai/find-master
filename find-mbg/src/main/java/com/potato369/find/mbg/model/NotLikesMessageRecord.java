package com.potato369.find.mbg.model;


import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotLikesMessageRecord {
	
	//发送消息内容
	@ApiModelProperty(value = "消息内容")
	private String messageContent;
	
	//消息发送者用户id
	@ApiModelProperty(value = "消息发送者用户id")
	private Long sendUserId;
	
	//消息发送者用户头像
	@ApiModelProperty(value = "消息发送者用户头像")
	private String sendUserHead;
	
	//消息发送者用户昵称
	@ApiModelProperty(value = "消息发送者用户昵称")
	private String sendUserNickname;

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Long getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(Long sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getSendUserHead() {
		return sendUserHead;
	}

	public void setSendUserHead(String sendUserHead) {
		this.sendUserHead = sendUserHead;
	}

	public String getSendUserNickname() {
		return sendUserNickname;
	}

	public void setSendUserNickname(String sendUserNickname) {
		this.sendUserNickname = sendUserNickname;
	}

	@Override
	public String toString() {
		return "NotLikesMessageRecord [messageContent=" + messageContent + ", sendUserId=" + sendUserId
				+ ", sendUserHead=" + sendUserHead + ", sendUserNickname=" + sendUserNickname + "]";
	}
}
