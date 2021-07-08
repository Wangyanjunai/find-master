package com.potato369.find.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class DynamicDTO extends AbstractDTO {

    @ApiModelProperty("动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty("发布者id")
    private Long userId;

    @ApiModelProperty("设备串码")
    private String imei;

    @ApiModelProperty("设备型号")
    private String model;

    @ApiModelProperty("系统名称")
    private String sysName;

    @ApiModelProperty("系统版本")
    private String sysCode;

    @ApiModelProperty("网络方式")
    @Builder.Default
    private String networkMode = "WIFI";

    @ApiModelProperty("客户端IP")
    private String ip;

    @ApiModelProperty("定位地址（经度）")
    private Double longitude;

    @ApiModelProperty("定位地址（纬度）")
    private Double latitude;

    @ApiModelProperty("定位地址（国家）")
    @Builder.Default
    private String country = "中国";

    @ApiModelProperty("定位地址（省份）")
    private String province;

    @ApiModelProperty("定位地址（城市）")
    private String city;

    @ApiModelProperty("定位地址（区/县）")
    private String district;

    @ApiModelProperty("定位地址（其它）")
    private String other;

    @ApiModelProperty("是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    @Builder.Default
    private String publicStatus = "0";

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("动态内容类型，0->图片，1->语音，2->文字（不包含图片，语音资源）")
    private String attacheInfoDataType;

    @ApiModelProperty("是否话题，0->否，1->是；默认：0->否")
    @Builder.Default
    private String isTopic = "0";

    @ApiModelProperty("是否匿名，0->否，1->是；默认：0->否")
    @Builder.Default
    private String isAnonymous = "0";

    @ApiModelProperty("话题标题")
    private String topicTitle;
}
