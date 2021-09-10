package com.potato369.find.mbg.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotLikesMessageRecord implements Serializable {
	
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

	private static final long serialVersionUID = 1L;
}
