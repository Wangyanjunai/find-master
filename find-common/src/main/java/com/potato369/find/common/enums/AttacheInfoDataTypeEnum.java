package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * @PackageName com.potato369.find.common.enums
 * @EnumName AttacheInfoTypeEnum
 * @Desc 枚举类实现的功能点描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/26 10:46
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AttacheInfoDataTypeEnum implements CodeEnum<Integer> {

    Image(0, "0", "图片"),

    Audio(1, "1", "语音"),
    
    Text(2, "2", "文字"),
    ;
    private Integer code;

    private String codeStr;

    private String name;
}