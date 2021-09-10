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
public class User implements Serializable {

    @ApiModelProperty(value = "用户id，主键")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "账号状态，0->正常；1->异常，默认：0->正常")
    private String status;

    @ApiModelProperty(value = "是否测试用户，0->否；1->是，默认：0->否")
    private String isTest;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "微信号码")
    private String weixinId;

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

    @ApiModelProperty(value = "性别，0->女生；1->男生，默认：0->女生")
    private String gender;

    @ApiModelProperty(value = "出生年代")
    private String year;

    @ApiModelProperty(value = "出生月份")
    private String month;

    @ApiModelProperty(value = "出生日期")
    private String date;

    @ApiModelProperty(value = "星座")
    private String constellation;

    @ApiModelProperty(value = "头像小图")
    private String headIcon;

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

    @ApiModelProperty(value = "平台")
    private String platform;

    @ApiModelProperty(value = "IP")
    private String ip;

    @ApiModelProperty(value = "VIP等级，0->VIP0，普通用户；1->VIP1，VIP用户；2->SVIP，超级VIP；默认：0->VIP0，普通用户")
    private String grade;

    @ApiModelProperty(value = "签名，默认：这位靓妹/靓仔还未填写签名。")
    private String autograph;

    @ApiModelProperty(value = "VIP开始时间")
    private Date vipStartTime;

    @ApiModelProperty(value = "VIP结束时间")
    private Date vipEndTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "背景图片")
    private String backgroundIcon;

    @ApiModelProperty(value = "极光推送唯一设备的标识")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    @ApiModelProperty(value = "职业编号")
    private Long professionId;

    @ApiModelProperty(value = "标签1编号，对应标签表id")
    private Long tag1;

    @ApiModelProperty(value = "标签2编号，对应标签表id")
    private Long tag2;

    @ApiModelProperty(value = "标签3编号，对应标签表id")
    private Long tag3;

    @ApiModelProperty(value = "标签4编号，对应标签表id")
    private Long tag4;

    @ApiModelProperty(value = "标签5编号，对应标签表id")
    private Long tag5;

    @ApiModelProperty(value = "预留字段05")
    private String reserveColumn05;

    @ApiModelProperty(value = "预留字段06")
    private String reserveColumn06;

    @ApiModelProperty(value = "预留字段07")
    private String reserveColumn07;

    @ApiModelProperty(value = "预留字段08")
    private String reserveColumn08;

    private static final long serialVersionUID = 1L;
}