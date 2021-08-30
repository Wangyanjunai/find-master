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
public class MessageVO2 {

    /**
     * totalCount:点赞总条数
     */
    @JSONField(name = "totalCount")
    @JsonProperty(value = "totalCount")
    private Long totalCount;

    /**
     * totalPage:点赞总页数
     */
    @JSONField(name = "totalPage")
    @JsonProperty(value = "totalPage")
    private Integer totalPage;

    /**
     * 点赞或者评论消息数据列表
     */
    @JSONField(name = "list")
    @JsonProperty(value = "list")
    private List<LikesInfoVO> likesInfoVOs;
}
