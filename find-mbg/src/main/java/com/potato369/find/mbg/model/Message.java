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
public class Message implements Serializable {

    @ApiModelProperty(value = "消息id，主键")
    private Long id;

    @ApiModelProperty(value = "发送者用户id")
    private Long sendUserId;

    @ApiModelProperty(value = "接收者用户id")
    private Long recipientUserId;

    @ApiModelProperty(value = "已读者用户id")
    private Long readUserId;

    @ApiModelProperty(value = "删除者用户id")
    private Long deleteUserId;

    @ApiModelProperty(value = "发送方式：0->系统自动；1->用户主动，默认：0->系统自动")
    private String sendMode;

    @ApiModelProperty(value = "状态，0->未读；1->已读，默认：0->未读")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "消息类型，“likes”->点赞，“applications”->申请加微信，“commons”->普通消息")
    private String reserveColumn01;

    @ApiModelProperty(value = "消息发送或回复，0->发送，1->回复")
    private String reserveColumn02;

    @ApiModelProperty(value = "是否删除，0->否,1-删除，默认：0")
    private String reserveColumn03;

    @ApiModelProperty(value = "回复的消息id或者点赞记录id")
    private String reserveColumn04;

    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;
}