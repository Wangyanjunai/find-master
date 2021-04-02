package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 回复申请加微信消息类型枚举
@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum MessageType3Enum implements CodeEnum<Object> {

    /**
     * 拒绝
     */
	REFUSE(0, "0", "refuse"),

    /**
     * 同意
     */
	AGREE(1, "1", "agree"),
    ;

    private Integer code;

    private String codeStr;

    private String message;
}
