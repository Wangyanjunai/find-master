package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageInfoVO2 {

	// 消息记录id
	@JSONField(name = "messageId")
	@JsonProperty(value = "messageId")
	private Long messageId;
		
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

	// 消息发送时间
	@JSONField(name = "sendDateTime")
	@JsonProperty(value = "sendDateTime")
	private String sendDateTime;

	// 发送的消息内容
	@JSONField(name = "content")
	@JsonProperty(value = "content")
	private String content;
}
