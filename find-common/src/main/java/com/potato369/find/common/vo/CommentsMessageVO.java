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
public class CommentsMessageVO {
    //最新一条未读评论消息内容
    @JSONField(name = "content2")
    @JsonProperty(value = "content2")
    private String content;

    //未读评论消息总条数
    @JSONField(name = "count2")
    @JsonProperty(value = "count2")
    private Long count;
}
