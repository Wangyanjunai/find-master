package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum MessageTypeEnum implements CodeEnum<Object> {

    /**
     * 点赞消息
     */
    Likes(0, "0", "likes"),

    /**
     * 申请加微信消息
     */
    Applications(1, "1", "applications"),
    
    /**
     * 普通消息
     */
    Commons(1, "1", "commons"),
    ;

    private Integer code;

    private String codeStr;

    private String message;
}
