package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName IsOrNotUpdateLocationEnum
 * @Desc 是否更新动态定位地址
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/15 14:22
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum IsOrNotUpdateLocationEnum implements CodeEnum<Integer> {
    NO(0, "否"),

    YES(1, "是"),
    ;
    private Integer code;

    private String message;
}