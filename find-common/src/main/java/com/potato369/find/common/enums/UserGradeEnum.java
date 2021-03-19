package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserGradeEnum implements CodeEnum<Integer>{

	VIP0(0, "0", "普通用户"),
	
	VIP1(1, "1", "VIP用户"),
	
	VIP2(2, "2", "超级VIP"),
	
	;
	private Integer code;
	
	private String grade;
	
	private String name;
}
