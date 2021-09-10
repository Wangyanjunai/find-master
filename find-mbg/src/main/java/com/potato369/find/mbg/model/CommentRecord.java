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
public class CommentRecord implements Serializable {

    @ApiModelProperty(value = "记录id，主键")
    private Long id;

    @ApiModelProperty(value = "评论者用户id")
    private Long userId;

    @ApiModelProperty(value = "被评论者用户id")
    private Long beUserId;

    @ApiModelProperty(value = "被评论者动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "评论时间")
    private Date createTime;

    @ApiModelProperty(value = "预留字段01")
    private String reserveColumn01;

    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;

    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    private static final long serialVersionUID = 1L;
}