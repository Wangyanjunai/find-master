package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DynamicInfoStatusEnum implements CodeEnum<Integer> {

    SHOW(0, "0", "显示"),

    HIDE(1, "1", "隐藏"),

    S_RECOMMEND(2, "2", "小推"),

    B_RECOMMEND(3, "3", "大推"),
    ;
    private Integer code;

    private String status;

    private String name;
}
