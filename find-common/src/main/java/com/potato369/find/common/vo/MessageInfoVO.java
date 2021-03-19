package com.potato369.find.common.vo;

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
public class MessageInfoVO {

    //点赞的用户id
    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId;

    //点赞的用户头像
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;

    //点赞的用户昵称
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickname;

    //点赞时发送的消息内容
    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content;
}
