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
public class AlipayConfig implements Serializable {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "应用ID")
    private String appId;
    
    @ApiModelProperty(value = "状态，1->禁用，0->启用，默认：0->启用")
    private String status;

    @ApiModelProperty(value = "编码")
    private String charset;

    @ApiModelProperty(value = "类型 固定格式json")
    private String format;

    @ApiModelProperty(value = "网关地址")
    private String gatewayUrl;

    @ApiModelProperty(value = "异步回调")
    private String notifyUrl;

    @ApiModelProperty(value = "回调地址")
    private String returnUrl;

    @ApiModelProperty(value = "签名方式")
    private String signType;

    @ApiModelProperty(value = "商户号")
    private String sysServiceProviderId;

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

    @ApiModelProperty(value = "私钥")
    private String privateKey;

    @ApiModelProperty(value = "公钥")
    private String publicKey;

    private static final long serialVersionUID = 1L;
}