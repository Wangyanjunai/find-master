package com.potato369.find.common.enums;

//状态枚举
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusEnum implements CodeEnum<Integer> {

	Enabled(0, "启用"),
	
    Disabled(1, "禁用"),
    ;
    private Integer code;

    private String name;
}
