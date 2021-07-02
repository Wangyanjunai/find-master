package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName IsTopicEnum
 * @Desc 是否话题枚举
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/02 10:07
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum IsTopicEnum implements CodeEnum<Integer> {

    No(0, "0", "否"),

    Yes(1, "1", "是"),

    ;
    private Integer code;

    private String type;

    private String name;
}