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
public class DynamicInfo implements Serializable {

    @ApiModelProperty(value = "动态内容id，主键")
    private Long id;

    @ApiModelProperty(value = "发布者id")
    private Long userId;

    @ApiModelProperty(value = "动态信息id")
    private Long dynamicId;

    @ApiModelProperty(value = "动态内容状态，0->显示；1->隐藏；2->小推；3->大推，默认：0->显示")
    private String dynamicStatus;

    @ApiModelProperty(value = "是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    private String publicStatus;

    @ApiModelProperty(value = "是否置顶，0->否；1->是，默认：0->否")
    private String isTop;

    @ApiModelProperty(value = "是否话题，0->否；1->是，默认：0->否")
    private String isTopic;

    @ApiModelProperty(value = "是否匿名，0->否；1->是，默认：0->否")
    private String isAnonymous;

    @ApiModelProperty(value = "话题标题")
    private String topicTitle;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "申请加微信数")
    private Integer applications;

    @ApiModelProperty(value = "分享数")
    private Integer shares;

    @ApiModelProperty(value = "动态附件类型：1->图片，2->语音，0->文字（不包含附件的纯文字），必需传参数")
    private String attacheType;

    @ApiModelProperty(value = "附件数量")
    private Integer attacheNumber;

    @ApiModelProperty(value = "定位（经度）")
    private Double longitude;

    @ApiModelProperty(value = "定位（纬度）")
    private Double latitude;

    @ApiModelProperty(value = "定位（国家）")
    private String country;

    @ApiModelProperty(value = "定位（省份）")
    private String province;

    @ApiModelProperty(value = "定位（城市）")
    private String city;

    @ApiModelProperty(value = "定位（区/县）")
    private String district;

    @ApiModelProperty(value = "定位（其它地址）")
    private String other;

    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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