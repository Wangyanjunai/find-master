package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 鹿可模块传递参数实体
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserDTO3 extends AbstractDTO {

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
     * longitude：经度
     * </pre>
     */
    @ApiModelProperty(value = "经度")
    @JSONField(name = "longitude")
    @JsonProperty(value = "longitude")
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /**
     * <pre>
     * latitude：纬度
     * </pre>
     */
    @ApiModelProperty(value = "纬度")
    @JSONField(name = "latitude")
    @JsonProperty(value = "latitude")
    @NotNull(message = "纬度不能为空")
    private Double latitude;
}
