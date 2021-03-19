package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @ApiModelProperty(value = "是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    @JSONField(name = "publicStatus")
    @JsonProperty(value = "publicStatus")
    @Builder.Default
    private String publicStatus = "0";

    @ApiModelProperty(value = "内容")
    @JsonProperty(value = "content")
    @JSONField(name = "content")
    private String content;

    @ApiModelProperty(value = "附件类型，0->无附件，纯文本，1->图片，2->语音")
    @JsonProperty(value = "attacheInfoDataType")
    @JSONField(name = "attacheInfoDataType")
    private String attacheInfoDataType;
}
