package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.vo
 * @ClassName TagVO
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/01 11:51
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TagVO {
    /**
     * <pre>
     * @JSONField id：id
     * </pre>
     */
    @JsonProperty(value = "id")
    @JSONField(name = "id")
    private Long id;

    /**
     * <pre>
     * @JSONField name：标签名称
     * </pre>
     */
    @JsonProperty(value = "name")
    @JSONField(name = "name")
    private String name;
}
