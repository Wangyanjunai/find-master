package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName AttacheInfoStatusEnum
 * @Desc 枚举类实现的功能点描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/26 10:30
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AttacheInfoStatusEnum implements CodeEnum<Integer> {

    Hide(0, "隐藏"),

    Show(1, "显示"),
    ;
    private Integer code;

    private String name;
}