package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;

/**
 * <pre>
 * @PackageName com.potato369.common.dto
 * @ClassName BaiduDTO
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 11:19
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class BaiduDTO extends AbstractDTO {

    /**
     * <pre>
     * ip：用户上网的IP地址，请求中如果不出现或为空，会针对发来请求的IP进行定位。
     * 如您需要通过IPv6来获取位置信息，请提交工单申请。
     * </pre>
     */
	@ApiModelProperty(value = "用户上网的IP地址")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    private String ip;

    /**
     * <pre>
     * ak：开发者密钥，可在API控制台申请获得。
     * </pre>
     */
	@ApiModelProperty(value = "开发者密钥")
    @JSONField(name = "ak")
    @JsonProperty(value = "ak")
    private String ak;

    /**
     * sn：若用户所用AK的校验方式为SN校验时该参数必填（什么是SN校验？）。其他AK校验方式的可不填写
     */
	@ApiModelProperty(value = "SN校验值")
    @JSONField(name = "sn")
    @JsonProperty(value = "sn")
    @NotEmpty(message = "SN校验时该参数不能为空")
    private String sn;

    /**
     * coor：设置返回位置信息中，经纬度的坐标类型，分别如下：
     * coor不出现、或为空：百度墨卡托坐标，即百度米制坐标
     * coor = bd09ll：百度经纬度坐标，在国测局坐标基础之上二次加密而来
     * coor = gcj02：国测局02坐标，在原始GPS坐标基础上，按照国家测绘行业统一要求，加密后的坐标
     * 注意：百度地图的坐标类型为bd09ll，如果结合百度地图使用，请注意坐标选择
     */
	@ApiModelProperty(value = "经纬度的坐标类型")
    @JSONField(name = "coor")
    @JsonProperty(value = "coor")
    private String coor;
}
