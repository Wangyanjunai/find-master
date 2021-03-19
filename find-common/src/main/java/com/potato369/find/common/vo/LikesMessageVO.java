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
public class LikesMessageVO {
	
    //最新一条点赞用户的头像
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;

    //最新一条点赞消息内容
    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content;
    
    //未读点赞消息总条数
    @JSONField(name = "count")
    @JsonProperty(value = "count")
    private Integer count;
}
