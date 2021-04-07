package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ReportInfoDTO extends AbstractDTO {

	/**
	 * <pre>
	 * categoryId：举报的类目类型id
	 * </pre>
	 */
	@ApiModelProperty(value = "举报的类目类型id")
	@JSONField(name = "categoryId")
	@JsonProperty(value = "categoryId")
	@NotNull(message = "举报的类目类型id不能为空")
	private Long categoryId;

	/**
	 * <pre>
	 * reportType：举报类目类型
	 * </pre>
	 */
	@ApiModelProperty(value = "举报类目类型")
	@JSONField(name = "reportType")
	@JsonProperty(value = "reportType")
	@NotEmpty(message = "举报类目类型不能为空")
	private String reportType;

	/**
	 * <pre>
	 * beingReportUserId：被举报的用户id或者动态id不能为空
	 * </pre>
	 */
	@ApiModelProperty(value = "被举报的用户id或者动态id")
	@JSONField(name = "beingReportId")
	@JsonProperty(value = "beingReportId")
	@NotNull(message = "被举报的用户id或者动态id不能为空")
	private Long beingReportId;

	/**
	 * <pre>
	 * reportContent：举报填写的内容
	 * </pre>
	 */
	@ApiModelProperty(value = "举报填写的内容")
	@JSONField(name = "reportContent")
	@JsonProperty(value = "reportContent")
	private String reportContent;
}
