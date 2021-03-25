package com.potato369.find.common.vo;

import java.util.List;

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
public class MessageVO {

    /**
     * likes:未读点赞消息数据
     */
    @JSONField(name = "likes")
    @JsonProperty(value = "likes")
    private LikesMessageVO likesMessageVO;

    /**
     * totalCount:未读申请加微信消息总条数
     */
    @JSONField(name = "totalCount")
    @JsonProperty(value = "totalCount")
    private Long totalCount;

    /**
     * totalPage:未读申请加微信消息总页数
     */
    @JSONField(name = "totalPage")
    @JsonProperty(value = "totalPage")
    private Integer totalPage;

    /**
     * 未读申请加微信消息数据
     */
    @JSONField(name = "list")
    @JsonProperty(value = "list")
    private List<MessageInfoVO> messageInfoVOs;
}
