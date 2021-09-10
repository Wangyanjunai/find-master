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
public class OperateRecord implements Serializable {

    @ApiModelProperty(value = "id，主键")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "状态：0->失败，1->成功，默认：1->成功")
    private String status;

    @ApiModelProperty(value = "类型：0->发布动态，1->删除动态，2->点赞动态，3->申请加微信，4->分享动态，5->拉黑用户，6->举报动态，7-创建用户，8->修改资料")
    private Integer type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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