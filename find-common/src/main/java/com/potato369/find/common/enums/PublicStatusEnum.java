package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PublicStatusEnum implements CodeEnum<Integer> {

    NOPublic(0, "0", "否"),

    Public(1, "1", "是"),
    ;

    private Integer code;

    private String type;

    private String msg;
}
