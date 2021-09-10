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
public class Dynamic implements Serializable {

    @ApiModelProperty(value = "动态id，主键")
    private Long id;

    @ApiModelProperty(value = "用户id，对应用户信息表主键id")
    private Long userId;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "设备串码")
    private String imei;

    @ApiModelProperty(value = "设备型号")
    private String model;

    @ApiModelProperty(value = "系统名称")
    private String sysName;

    @ApiModelProperty(value = "系统版本")
    private String sysCode;

    @ApiModelProperty(value = "网络方式")
    private String networkMode;

    @ApiModelProperty(value = "国")
    private String country;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区/县")
    private String district;

    @ApiModelProperty(value = "其它地址")
    private String other;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

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