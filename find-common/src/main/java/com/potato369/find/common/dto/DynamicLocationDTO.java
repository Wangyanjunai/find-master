package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class DynamicLocationDTO extends AbstractDTO {
	
    @ApiModelProperty(value = "性别")
    @JsonProperty(value = "gender")
    @JSONField(name = "gender")
    private String gender;
    
    @ApiModelProperty(value = "年龄范围最小值")
    @JsonProperty(value = "minAge")
    @JSONField(name = "minAge")
    private Date minAge;
    
    @ApiModelProperty(value = "年龄范围最大值")
    @JsonProperty(value = "maxAge")
    @JSONField(name = "maxAge")
    private Date maxAge;
    
    @ApiModelProperty(value = "动态内容类型")
    @JsonProperty(value = "dataType")
    @JSONField(name = "dataType")
    private String dataType;
    
    @ApiModelProperty(value = "星座")
    @JsonProperty(value = "constellation")
    @JSONField(name = "constellation")
    private String constellation;
    
    @ApiModelProperty(value = "黑名单用户id列表")
    @JsonProperty(value = "blackList")
    @JSONField(name = "blackList")
    private List<Long> list;
    
    @ApiModelProperty(value = "白单用户id列表")
    @JsonProperty(value = "whiteList")
    @JSONField(name = "whiteList")
    private List<Long> whiteList;

    @ApiModelProperty(value = "定位地址列表")
    @JSONField(name = "locations")
    @JsonProperty(value = "locations")
    List<LocationInfoDTO> locationInfoDTOList;
}
