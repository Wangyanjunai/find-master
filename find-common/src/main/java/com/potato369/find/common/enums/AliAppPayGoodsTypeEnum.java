package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum AliAppPayGoodsTypeEnum implements CodeEnum<Object> {

    /**
     * 虚拟类商品
     */
    Virtual(0, "0", "虚拟类商品"),

    /**
     * 实物类商品
     */
    Physical(1, "1", "实物类商品"),
    ;

    private Integer code;

    private String codeStr;

    private String message;
}
