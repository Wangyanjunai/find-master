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
public class LikesMessageVO {

    //最新一条未读点赞消息内容
    @JSONField(name = "content1")
    @JsonProperty(value = "content1")
    private String content;

    //未读点赞消息总条数
    @JSONField(name = "count1")
    @JsonProperty(value = "count1")
    private Long count;
}
