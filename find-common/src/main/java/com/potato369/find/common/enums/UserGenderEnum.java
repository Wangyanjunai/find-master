package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserGenderEnum implements CodeEnum<Integer>{

	Female(0, "0", "女生"),
	
	Male(1, "1", "男生"),
	
	ALL(2, "2", "男生+女生"),
	;
	private Integer code;
	
	private String gender;
	
	private String name;
}
