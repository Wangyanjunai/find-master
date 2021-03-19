package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName MessageStatusEnum
 * @Desc 消息状态枚举
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/3/15 11:16
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MessageStatusEnum implements CodeEnum<Integer> {

    UNREAD(0, "0", "未读"),

    READ(1, "1", "已读"),
    ;

    private Integer code;

    private String status;

    private String messag;
}
