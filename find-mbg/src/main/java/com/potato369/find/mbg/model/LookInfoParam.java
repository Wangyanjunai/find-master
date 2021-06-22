package com.potato369.find.mbg.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * @PackageName com.potato369.find.mbg.model
 * @ClassName LookInfoParam
 * @Desc 鹿可模块筛选条件参数
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/17 15:09
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LookInfoParam implements Serializable {

    private static final long serialVersionUID = 6080249421284905175L;

    @ApiModelProperty(value = "鹿可性别筛选条件")
    private String gender;

    @ApiModelProperty(value = "鹿可年龄最小值筛选条件")
    @JsonProperty(value = "minAge")
    @JSONField(name = "minAge")
    private Date minAge;

    @ApiModelProperty(value = "鹿可年龄最大值筛选条件")
    @JsonProperty(value = "maxAge")
    @JSONField(name = "maxAge")
    private Date maxAge;

    @ApiModelProperty(value = "排除自己的用户id")
    @JsonProperty(value = "userId")
    @JSONField(name = "userId")
    private Long userId;
}
