package com.potato369.find.common.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
//行业
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IndustriesVO {

	@JSONField(name = "id")
	@JsonProperty(value = "id")
	private Long id;

	@JSONField(name = "name")
	@JsonProperty(value = "name")
	private String name;

	@JSONField(name = "list")
	@JsonProperty(value = "list")
	private List<ProfessionsVO> professions;
}
