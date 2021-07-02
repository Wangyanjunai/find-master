package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class DynamicDTO extends AbstractDTO {

    @ApiModelProperty(value = "动态内容id")
    @JSONField(name = "dynamicInfoId")
    @JsonProperty(value = "dynamicInfoId")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "发布者id")
    @JsonProperty(value = "userId")
    @JSONField(name = "userId")
    private Long userId;

    @ApiModelProperty(value = "设备串码")
    @JSONField(name = "imei")
    @JsonProperty(value = "imei")
    private String imei;

    @ApiModelProperty(value = "设备型号")
    @JsonProperty(value = "model")
    @JSONField(name = "model")
    private String model;

    @ApiModelProperty(value = "系统名称")
    @JSONField(name = "sysName")
    @JsonProperty(value = "sysName")
    private String sysName;

    @ApiModelProperty(value = "系统版本")
    @JsonProperty(value = "sysCode")
    @JSONField(name = "sysCode")
    private String sysCode;

    @ApiModelProperty(value = "网络方式")
    @JSONField(name = "networkMode")
    @JsonProperty(value = "networkMode")
    private String networkMode;

    @ApiModelProperty(value = "客户端IP")
    @JsonProperty(value = "ip")
    @JSONField(name = "ip")
    private String ip;

    @ApiModelProperty(value = "经度")
    @JsonProperty(value = "longitude")
    @JSONField(name = "longitude")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    @JsonProperty(value = "latitude")
    @JSONField(name = "latitude")
    private Double latitude;

    @ApiModelProperty(value = "国")
    @JsonProperty(value = "country")
    @JSONField(name = "country")
    @Builder.Default
    private String country = "中国";

    @ApiModelProperty(value = "省")
    @JsonProperty(value = "province")
    @JSONField(name = "province")
    private String province;

    @ApiModelProperty(value = "市")
    @JsonProperty(value = "city")
    @JSONField(name = "city")
    private String city;

    @ApiModelProperty(value = "区/县")
    @JsonProperty(value = "district")
    @JSONField(name = "district")
    private String district;

    @ApiModelProperty(value = "其它")
    @JsonProperty(value = "other")
    @JSONField(name = "other")
    private String other;

    @ApiModelProperty(value = "是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    @JSONField(name = "publicStatus")
    @JsonProperty(value = "publicStatus")
    private String publicStatus;

    @ApiModelProperty(value = "内容")
    @JsonProperty(value = "content")
    @JSONField(name = "content")
    private String content;

    @ApiModelProperty(value = "附件类型，0->无附件，纯文本，1->图片，2->语音")
    @JsonProperty(value = "attacheInfoDataType")
    @JSONField(name = "attacheInfoDataType")
    private String attacheInfoDataType;

    @ApiModelProperty(value = "是否话题，0->否，1->是；默认：0->否")
    @JsonProperty(value = "isTopic")
    @JSONField(name = "isTopic")
    private String isTopic;

    @ApiModelProperty(value = "是否匿名，0->否，1->是；默认：0->否")
    @JsonProperty(value = "isAnonymous")
    @JSONField(name = "isAnonymous")
    private String isAnonymous;

    @ApiModelProperty(value = "话题标题")
    @JsonProperty(value = "topicTitle")
    @JSONField(name = "topicTitle")
    private String topicTitle;
}
