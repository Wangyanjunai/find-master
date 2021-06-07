package com.potato369.find.common.vo.result.baidu;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.vo.result.baidu
 * @ClassName JsonRootBean
 * @Desc 百度普通IP定位返回数据
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/03 15:07
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BaiduJsonRootBean {
    @JSONField(name = "address")
    @JsonProperty(value = "address")
    private String address;

    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private Content content;

    @JSONField(name = "status")
    @JsonProperty(value = "status")
    private int status;
}

