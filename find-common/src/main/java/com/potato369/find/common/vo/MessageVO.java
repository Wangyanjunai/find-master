package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

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
     * 最新未读消息数据
     */
    @JSONField(name = "list")
    @JsonProperty(value = "list")
    private List<MessageInfoVO> messageInfoVOs;
}
