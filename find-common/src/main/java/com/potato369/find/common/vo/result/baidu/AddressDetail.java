package com.potato369.find.common.vo.result.baidu;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.vo.result.baidu
 * @ClassName AddressDetail
 * @Desc 百度普通IP定位
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/03 17:08
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
public class AddressDetail {
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;

    @JSONField(name = "street")
    @JsonProperty(value = "street")
    private String street;

    @JSONField(name = "district")
    @JsonProperty(value = "district")
    private String district;

    @JSONField(name = "street_number")
    @JsonProperty(value = "street_number")
    private String streetNumber;

    @JSONField(name = "city_code")
    @JsonProperty(value = "city_code")
    private int cityCode;
}
