package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//订单状态枚举
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum OrderStatusEnum implements CodeEnum<Object>{
    
    NEW(0, "新订单"),

    SUCCESS(1, "已完结"),

    CANCEL(2, "已取消"),

    CLOSE(3, "已关闭"),
    
    REFUND(4, "已退款"),

    ;
    private Integer code;

    private String message;
}
