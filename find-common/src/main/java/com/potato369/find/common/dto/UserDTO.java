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
public class UserDTO extends AbstractDTO {

    /**
     * <pre>
     * phone：手机号码
     * </pre>
     */
    @ApiModelProperty(value = "手机号码")
    @JSONField(name = "phone")
    @JsonProperty(value = "phone")
    private String phone;

    /**
     * <pre>
     * ip：客户端IP
     * </pre>
     */
    @ApiModelProperty(value = "客户端IP")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    private String ip;

    /**
     * <pre>
     * gender：性别
     * </pre>
     */
    @ApiModelProperty(value = "性别")
    @JSONField(name = "gender")
    @JsonProperty(value = "gender")
    private String gender;

    /**
     * <pre>
     * platform：平台
     * </pre>
     */
    @ApiModelProperty(value = "平台")
    @JSONField(name = "platform")
    @JsonProperty(value = "platform")
    private String platform;

    /**
     * <pre>
     * nickname：昵称
     * </pre>
     */
    @ApiModelProperty(value = "昵称")
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickname;

    /**
     * <pre>
     * weixinId：微信号
     * </pre>
     */
    @ApiModelProperty(value = "微信号")
    @JSONField(name = "weixinId")
    @JsonProperty(value = "weixinId")
    private String weixinId;

    /**
     * <pre>
     * imei：设备串码
     * </pre>
     */
    @ApiModelProperty(value = "设备串码")
    @JSONField(name = "imei")
    @JsonProperty(value = "imei")
    private String imei;

    /**
     * <pre>
     * model：设备型号
     * </pre>
     */
    @ApiModelProperty(value = "设备型号")
    @JSONField(name = "model")
    @JsonProperty(value = "model")
    private String model;

    /**
     * <pre>
     * sysName：系统名称
     * </pre>
     */
    @ApiModelProperty(value = "系统名称")
    @JSONField(name = "sysName")
    @JsonProperty(value = "sysName")
    private String sysName;

    /**
     * <pre>
     * sysCode：系统版本
     * </pre>
     */
    @ApiModelProperty(value = "系统版本")
    @JSONField(name = "sysCode")
    @JsonProperty(value = "sysCode")
    private String sysCode;

    /**
     * <pre>
     * networkMode：网络方式
     * </pre>
     */
    @ApiModelProperty(value = "网络方式")
    @JSONField(name = "networkMode")
    @JsonProperty(value = "networkMode")
    private String networkMode;

    /**
     * <pre>
     * year：出生年代
     * </pre>
     */
    @ApiModelProperty(value = "出生年代")
    @JSONField(name = "year")
    @JsonProperty(value = "year")
    private String year;

    /**
     * <pre>
     * month：出生月份
     * </pre>
     */
    @ApiModelProperty(value = "出生月份")
    @JSONField(name = "month")
    @JsonProperty(value = "month")
    private String month;

    /**
     * <pre>
     * date：出生日期
     * </pre>
     */
    @ApiModelProperty(value = "出生日期")
    @JSONField(name = "date")
    @JsonProperty(value = "date")
    private String date;

    /**
     * <pre>
     * constellation：星座
     * </pre>
     */
    @ApiModelProperty(value = "星座")
    @JSONField(name = "constellation")
    @JsonProperty(value = "constellation")
    private String constellation;

    /**
     * <pre>
     * country：国家
     * </pre>
     */
    @ApiModelProperty(value = "国家")
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    /**
     * <pre>
     * province：省份
     * </pre>
     */
    @ApiModelProperty(value = "省份")
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    /**
     * <pre>
     * city：城市
     * </pre>
     */
    @ApiModelProperty(value = "城市")
    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;
    
    /**
     * <pre>
     * district：区/县
     * </pre>
     */
    @ApiModelProperty(value = "区/县")
    @JSONField(name = "district")
    @JsonProperty(value = "district")
    private String district;
    
    /**
     * <pre>
     * other：其它
     * </pre>
     */
    @ApiModelProperty(value = "其它")
    @JSONField(name = "other")
    @JsonProperty(value = "other")
    private String other;

    /**
     * <pre>
     * longitude：经度
     * </pre>
     */
    @ApiModelProperty(value = "经度")
    @JSONField(name = "longitude")
    @JsonProperty(value = "longitude")
    private Double longitude;

    /**
     * <pre>
     * latitude：纬度
     * </pre>
     */
    @ApiModelProperty(value = "纬度")
    @JSONField(name = "latitude")
    @JsonProperty(value = "latitude")
    private Double latitude;

    /**
     * <pre>
     * professionId：职业编号
     * </pre>
     */
    @ApiModelProperty(value = "职业编号")
    @JSONField(name = "professionId")
    @JsonProperty(value = "professionId")
    private Long professionId;

    /**
     * <pre>
     * tag1：标签1
     * </pre>
     */
    @ApiModelProperty(value = "标签1")
    @JSONField(name = "tag1")
    @JsonProperty(value = "tag1")
    private String tag1;

    /**
     * <pre>
     * tag2：标签2
     * </pre>
     */
    @ApiModelProperty(value = "标签2")
    @JSONField(name = "tag2")
    @JsonProperty(value = "tag2")
    private String tag2;

    /**
     * <pre>
     * tag3：标签3编号
     * </pre>
     */
    @ApiModelProperty(value = "标签3")
    @JSONField(name = "tag3")
    @JsonProperty(value = "tag3")
    private String tag3;

    /**
     * <pre>
     * tag4：标签4
     * </pre>
     */
    @ApiModelProperty(value = "标签4")
    @JSONField(name = "tag4")
    @JsonProperty(value = "tag4")
    private String tag4;

    /**
     * <pre>
     * tag5：标签5
     * </pre>
     */
    @ApiModelProperty(value = "标签5")
    @JSONField(name = "tag5")
    @JsonProperty(value = "tag5")
    private String tag5;

    /**
     * <pre>
     * autograph：签名
     * </pre>
     */
    @ApiModelProperty(value = "签名")
    @JSONField(name = "autograph")
    @JsonProperty(value = "autograph")
    private String autograph;
}
