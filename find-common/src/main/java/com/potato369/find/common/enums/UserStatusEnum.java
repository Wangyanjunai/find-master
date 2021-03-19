package com.potato369.find.common.enums;

//用户账号状态枚举
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserStatusEnum implements CodeEnum<Integer>{

	Normal(0, "正常"),
	
	Abnormal(1, "异常"),
	;
	private Integer code;
	
	private String name;
}
