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
public class ShareRecord implements Serializable {

    @ApiModelProperty(value = "记录id，主键")
    private Long id;

    @ApiModelProperty(value = "分享者id")
    private Long userId;

    @ApiModelProperty(value = "动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "方式，0->微信好友；1->QQ好友；2->微信朋友圈；3->QQ空间；")
    private String mode;

    @ApiModelProperty(value = "创建时间")
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