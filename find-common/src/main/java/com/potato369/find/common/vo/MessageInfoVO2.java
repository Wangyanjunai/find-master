package com.potato369.find.common.vo;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class MessageInfoVO2 {

	// 消息发送者用户id
	@JSONField(name = "sendUserId")
	@JsonProperty(value = "sendUserId")
	private Long sendUserId;

	// 消息发送者用户头像
	@JSONField(name = "sendUserHead")
	@JsonProperty(value = "sendUserHead")
	private String sendUserHeadIcon;

	// 消息发送者用户昵称
	@JSONField(name = "sendUserNickname")
	@JsonProperty(value = "sendUserNickname")
	private String sendUserNickname;

	// 消息发送者用户id
	@JSONField(name = "recipientUserId")
	@JsonProperty(value = "recipientUserId")
	private Long recipientUserId;

	// 消息发送者用户头像
	@JSONField(name = "recipientUserHeadIcon")
	@JsonProperty(value = "recipientUserHeadIcon")
	private String recipientUserHeadIcon;

	// 消息发送者用户昵称
	@JSONField(name = "recipientUserNickname")
	@JsonProperty(value = "recipientUserNickname")
	private String recipientUserNickname;

	// 消息发送时间
	@JSONField(name = "sendDateTime")
	@JsonProperty(value = "sendDateTime")
	private Date sendDateTime;

	// 发送的消息内容
	@JSONField(name = "content")
	@JsonProperty(value = "content")
	private String content;
}
