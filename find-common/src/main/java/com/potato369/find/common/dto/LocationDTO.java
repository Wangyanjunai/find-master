package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.dto
 * @ClassName LocationDTO
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/15 11:35
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
public class LocationDTO extends AbstractDTO {

    @ApiModelProperty(value = "用户id")
    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId;

    @ApiModelProperty(value = "外网IP")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "定位（国家）")
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    @ApiModelProperty(value = "定位（省份）")
    @JsonProperty(value = "province")
    @JSONField(name = "province")
    private String province;

    @ApiModelProperty(value = "定位（城市）")
    @JsonProperty(value = "city")
    @JSONField(name = "city")
    private String city;

    @ApiModelProperty(value = "定位（区/县）")
    @JsonProperty(value = "district")
    @JSONField(name = "district")
    private String district;

    @ApiModelProperty(value = "定位（其他）")
    @JsonProperty(value = "other")
    @JSONField(name = "other")
    private String other;

    @ApiModelProperty(value = "经度")
    @JSONField(name = "longitude")
    @JsonProperty(value = "longitude")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    @JSONField(name = "latitude")
    @JsonProperty(value = "latitude")
    private Double latitude;
}
