package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum DynamicInfoToppingEnum implements CodeEnum<Integer> {
    NO(0, "0", "否"),
    YES(1, "1", "是"),
    ;

    private Integer code;

    private String status;

    private String name;
}
