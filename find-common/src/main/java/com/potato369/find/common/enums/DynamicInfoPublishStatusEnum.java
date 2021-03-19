package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DynamicInfoPublishStatusEnum implements CodeEnum<Integer> {
	
	No_Publish(0, "未公开"),

    Yes_Publish(1, "已公开"),
    ;
    private Integer code;

    private String name;
}
