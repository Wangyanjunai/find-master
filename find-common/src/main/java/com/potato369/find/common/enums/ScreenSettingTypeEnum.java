package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ScreenSettingTypeEnum implements CodeEnum<Object> {
    LOOK_AGE_FEMALE(0, "0", "鹿可女生年龄筛选条件"),
    LOOK_AGE_MALE(1, "1", "鹿可男生年龄筛选条件"),
    DYNAMIC_AGE_MIN(2, "2", "动态年龄范围筛选条件最小值"),
    DYNAMIC_AGE_MAX(3, "3", "动态年龄范围筛选条件最大值"),
    ;
    private Integer code;

    private String type;

    private String name;
}
