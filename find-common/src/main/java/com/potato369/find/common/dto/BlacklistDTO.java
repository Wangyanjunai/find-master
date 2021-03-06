package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlacklistDTO extends AbstractDTO {

    /**
     * <pre>
     * blackUserId：黑名单用户id
     * </pre>
     */
    @ApiModelProperty(value = "黑名单用户id")
    @JSONField(name = "blackUserId")
    @JsonProperty(value = "blackUserId")
    @NotNull(message = "黑名单用户id不能为空")
    private Long blackUserId;

    /**
     * <pre>
     * type：类型，奇数->拉入，偶数->推出
     * </pre>
     */
    @ApiModelProperty(value = "类型")
    @JSONField(name = "type")
    @JsonProperty(value = "type")
    @NotNull(message = "类型不能为空")
    private Integer type;
}
