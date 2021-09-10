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
public class FeedbackRecord implements Serializable {

    @ApiModelProperty(value = "记录id，主键")
    private Long id;

    @ApiModelProperty(value = "反馈者id")
    private Long userId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "附件路径列表")
    private String filePathList;

    @ApiModelProperty(value = "附件类型，0->图片；1->音频，默认：0->图片")
    private String dataType;

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