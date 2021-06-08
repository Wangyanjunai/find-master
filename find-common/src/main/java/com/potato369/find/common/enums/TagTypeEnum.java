package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 标签类型枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TagTypeEnum implements CodeEnum<Integer> {

    SYS(0, "0", "系统定义"),

    USER(1, "1", "用户定义"),
    ;
    private Integer code;

    private String type;

    private String name;
}
