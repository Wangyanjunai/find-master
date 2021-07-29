package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BlacklistRecordStatusEnum implements CodeEnum<Integer>{
	
	PUSH(5, "5", "拉入"),
	
	PULL(6, "6", "推出"),
	;
	
	private Integer code;
    
    private String status;

    private String name;
}
