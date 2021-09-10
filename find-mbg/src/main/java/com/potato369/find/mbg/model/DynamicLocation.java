package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicLocation implements Serializable {

    @ApiModelProperty(value = "定位（国家）")
    private String country;

    @ApiModelProperty(value = "定位（省份）")
    private String province;

    @ApiModelProperty(value = "定位（城市）")
    private String city;

    @ApiModelProperty(value = "定位（区/县）")
    private String district;

    @ApiModelProperty(value = "定位（其它）")
    private String other;

    @ApiModelProperty(value = "定位（经度）")
    private Double longitude;

    @ApiModelProperty(value = "定位（纬度）")
    private Double latitude;

    private static final long serialVersionUID = 1L;
}