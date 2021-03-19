package com.potato369.find.common.vo;

import java.util.List;

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
     * 点赞数据列表
     */
    @JSONField(name = "likes")
    @JsonProperty(value = "likes")
    private List<LikesInfoVO> likesInfoVOs;
}
