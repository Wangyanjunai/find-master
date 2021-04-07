package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.common.vo
 * @ClassName BaiduVO
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 11:44
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
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
public class BaiduVO {

    @JSONField(name = "address")
    @JsonProperty(value = "address")
    private String address;

    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private BaiduVO1 baiduVO1;

    @JSONField(name = "status")
    @JsonProperty(value = "status")
    private String status;

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    class BaiduVO1 {
    	
        @JSONField(name = "address")
        @JsonProperty(value = "address")
        private String address;

        @JSONField(name = "address_detail")
        @JsonProperty(value = "address_detail")
        private BaiduVO2 baiduVO2;

        @JSONField(name = "point")
        @JsonProperty(value = "point")
        private BaiduVO3 baiduVO3;
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    class BaiduVO2 {

        @JSONField(name = "city")
        @JsonProperty(value = "city")
        private String city;

        @JSONField(name = "city_code")
        @JsonProperty(value = "city_code")
        private String cityCode;

        @JSONField(name = "province")
        @JsonProperty(value = "province")
        private String province;
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    class BaiduVO3 {

        @JSONField(name = "x")
        @JsonProperty(value = "x")
        private String x;

        @JSONField(name = "y")
        @JsonProperty(value = "y")
        private String y;
    }
}
