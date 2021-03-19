package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum PayStatusEnum implements CodeEnum<Object> {

    WAITING(0, "等待支付"),

    SUCCESS(1, "支付成功"),

    CLOSE(2, "支付关闭"),

    FAIL(3, "支付失败"),
    
    REFUND(4, "退款成功"),

    ;
    private Integer code;

    private String message;
}
