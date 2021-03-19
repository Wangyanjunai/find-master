package com.potato369.find.common.dto;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
//用户黑名单数据传输类
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class BlacklistDTO extends AbstractDTO {
	
    /**
     * <pre>
     * blackUserId：黑名单用户id。
     * </pre>
     */
	@ApiModelProperty(value = "黑名单用户id")
	@JSONField(name = "blackUserId")
	@JsonProperty(value = "blackUserId")
	@NotNull(message = "黑名单用户id不能为空")
	private Long blackUserId;
	
    /**
     * <pre>
     * type：类型，1或者奇数->拉入，0或者偶数->推出。
     * </pre>
     */
	@ApiModelProperty(value = "类型")
	@JSONField(name = "type")
	@JsonProperty(value = "type")
	@NotNull(message = "类型不能为空")
	private Integer type;
}
