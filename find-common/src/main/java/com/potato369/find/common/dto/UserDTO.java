package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    /**
     * <pre>
     * ip：客户端IP
     * </pre>
     */
	@ApiModelProperty(value = "客户端IP")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    @NotEmpty(message = "客户端IP不能为空")
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
     * country：国
     * </pre>
     */
	@ApiModelProperty(value = "国")
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    /**
     * <pre>
     * province：省
     * </pre>
     */
	@ApiModelProperty(value = "省")
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    /**
     * <pre>
     * city：市
     * </pre>
     */
	@ApiModelProperty(value = "市")
    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;

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
