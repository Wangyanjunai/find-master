package com.potato369.find.common.enums;

//用户是否是假用户枚举
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserIsTestEnum implements CodeEnum<Integer>{

	No(0, "否"),
	
	Yes(1, "是"),
	;
	private Integer code;
	
	private String name;
}
