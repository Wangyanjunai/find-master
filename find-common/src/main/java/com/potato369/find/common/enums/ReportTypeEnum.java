package com.potato369.find.common.enums;

//举报类型枚举
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ReportTypeEnum implements CodeEnum<Integer> {
	
	Dynamic(1, "动态"),
	
	User(2, "用户"),
	;
	private Integer code;
	
	private String name;
}
