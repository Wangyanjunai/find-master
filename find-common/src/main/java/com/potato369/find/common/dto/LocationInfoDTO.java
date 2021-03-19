package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.dto
 * @ClassName LocationInfoDTO
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/25 11:00
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class LocationInfoDTO extends AbstractDTO {
	
    @ApiModelProperty(value = "客户端ip")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "国")
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    @ApiModelProperty(value = "省")
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    @ApiModelProperty(value = "市")
    @JsonProperty(value = "city")
    @JSONField(name = "city")
    private String city;

    @ApiModelProperty(value = "区/县")
    @JsonProperty(value = "district")
    @JSONField(name = "district")
    private String district;

    @ApiModelProperty(value = "其他")
    @JsonProperty(value = "other")
    @JSONField(name = "other")
    private String other;
}
