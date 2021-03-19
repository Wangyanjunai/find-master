package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PublicStatusEnum implements CodeEnum<Integer>{
	
	NOPublic(0, "未公开"),

	Public(1, "公开"),
	;
	
	private Integer code;

	private String msg;
}
