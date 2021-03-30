package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 消息类型
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum MessageType2Enum implements CodeEnum<Object> {

    /**
     * 发送消息
     */
    SEND(0, "0", "send"),

    /**
     * 回复消息
     */
    REPLY(1, "1", "reply"),
    ;

    private Integer code;

    private String codeStr;

    private String message;
}
