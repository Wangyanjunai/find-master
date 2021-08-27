package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentInfoVO {

    //评论消息记录id
    @JSONField(name = "messageId")
    @JsonProperty(value = "messageId")
    private Long messageId;

    //评论id
    @JSONField(name = "commentId")
    @JsonProperty(value = "commentId")
    private Long commentId;

    //评论的用户id
    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId;

    //评论的用户头像
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;

    //评论发送的消息内容
    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content;
}

