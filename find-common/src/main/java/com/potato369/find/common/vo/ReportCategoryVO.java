package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportCategoryVO {

	@JSONField(name = "id")
	@JsonProperty(value = "id")
	private Long id;
	
	@JSONField(name = "name")
	@JsonProperty(value = "name")
	private String name;
}
