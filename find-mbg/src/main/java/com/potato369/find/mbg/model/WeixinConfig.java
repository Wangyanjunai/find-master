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
public class WeixinConfig implements Serializable {

    @ApiModelProperty(value = "配置id，主键")
    private Long id;

    @ApiModelProperty(value = "应用ID")
    private String appId;

    @ApiModelProperty(value = "状态，1->禁用，0->启用，默认：0->启用")
    private String status;

    @ApiModelProperty(value = "应用secret")
    private String appSecret;

    @ApiModelProperty(value = "商户号")
    private String sysServiceProviderId;

    @ApiModelProperty(value = "商户Key")
    private String sysServiceProviderKey;

    @ApiModelProperty(value = "APIv3密钥")
    private String apiV3Key;

    @ApiModelProperty(value = "证书路径")
    private String keyPath;

    @ApiModelProperty(value = "异步回调")
    private String notifyUrl;

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