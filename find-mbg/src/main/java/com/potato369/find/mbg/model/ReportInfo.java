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
public class ReportInfo implements Serializable {

    @ApiModelProperty(value = "举报信息id，主键")
    private Long id;

    @ApiModelProperty(value = "类目id，对应举报类目表主键id")
    private Long categoryType;

    @ApiModelProperty(value = "举报类型，1->动态，2->用户，默认：1-动态")
    private String reportType;

    @ApiModelProperty(value = "处理建议类型，0->默认，1->已忽略，2->已隐藏（针对动态），3—>已禁用（针对用户。亦可做其他方式处理）），默认：0->默认")
    private String suggestType;

    @ApiModelProperty(value = "举报用户id")
    private Long reportUserId;

    @ApiModelProperty(value = "被举报动态id")
    private Long beingReportUserId;

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

    @ApiModelProperty(value = "举报内容")
    private String reportContent;

    private static final long serialVersionUID = 1L;
}