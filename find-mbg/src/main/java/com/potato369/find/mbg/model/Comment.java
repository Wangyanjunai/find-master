package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Comment implements Serializable {

    @ApiModelProperty(value = "编号，主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "评论的点赞数")
    private Integer likes;

    @ApiModelProperty(value = "是否删除状态，0->否，1->是，默认：0->否")
    private String deleteStatus;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "删除时间")
    private Date deletedTime;

    @ApiModelProperty(value = "预留字段01")
    private String reserveColumn01;

    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;

    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;
}