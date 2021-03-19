package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName LikesStatusEnum
 * @Desc 枚举类实现的功能点描述
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/2/1 00:16
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MessageSendModeEnum implements CodeEnum<Integer> {

    PASSIVE(0, "0", "系统自动"),

    ACTIVE(1, "1", "用户主动"),
    ;

    private Integer code;

    private String status;

    private String msg;
}
