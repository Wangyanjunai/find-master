package com.potato369.find.common.vo;

import java.util.Date;

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
public class MessageInfoVO {

    //申请加微信消息记录id
    @JSONField(name = "messageId")
    @JsonProperty(value = "messageId")
    private Long messageId;
  
    //申请加微信消息发送者用户id
    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId;

    //申请加微信消息发送者用户头像
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;

    //申请加微信消息发送者用户昵称
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickname;

    //申请加微信消息发送者发送的消息内容
    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content;
    
    //申请加微信消息发送者发送的消息时间
    @JSONField(name = "createTime")
    @JsonProperty(value = "createTime")
    private Date createTime;
    
    //申请加微信消息发送者发送消息未读条数
    @JSONField(name = "count")
    @JsonProperty(value = "count")
    private Long count;
}
