package com.potato369.find.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <pre>
 * @PackageName com.potato369.find.mbg.model
 * @ClassName DynamicInfoParam
 * @Desc 动态内容筛选参数
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/31 14:26
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "年龄范围最小值")
    @JsonProperty(value = "minAge")
    @JSONField(name = "minAge")
    private Date minAge;
    
    @ApiModelProperty(value = "年龄范围最大值")
    @JsonProperty(value = "maxAge")
    @JSONField(name = "maxAge")
    private Date maxAge;

    @ApiModelProperty(value = "附件类型")
    private String dataType;

    @ApiModelProperty(value = "星座列表")
    private List<String> constellations;

    @ApiModelProperty(value = "定位地址（国家）列表")
    private List<String> countryLocations;

    @ApiModelProperty(value = "定位地址（省份）列表")
    private List<String> provinceLocations;

    @ApiModelProperty(value = "定位地址（城市）列表")
    private List<String> cityLocations;

    @ApiModelProperty(value = "黑名单用户id列表")
    private List<Long> blackRecordUserIdLongList;
}
