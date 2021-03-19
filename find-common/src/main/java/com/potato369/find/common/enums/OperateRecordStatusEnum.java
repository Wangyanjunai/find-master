package com.potato369.find.common.enums;
//用户操作记录状态枚举

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperateRecordStatusEnum implements CodeEnum<Integer>{
	
	Fail(0, "失败"),
	Success(1, "成功"),
	;
	private Integer code;

    private String status;
}
