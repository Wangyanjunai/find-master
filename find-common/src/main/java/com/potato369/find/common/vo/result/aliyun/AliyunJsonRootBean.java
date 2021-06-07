package com.potato369.find.common.vo.result.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.common.vo.result.aliyun
 * @ClassName JsonRootBean
 * @Desc 阿里云普通IP定位返回数据
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/07 09:51
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
public class AliyunJsonRootBean {
    @JSONField(name = "status")
    @JsonProperty(value = "status")
    private String status;

    @JSONField(name = "info")
    @JsonProperty(value = "info")
    private String info;

    @JSONField(name = "infocode")
    @JsonProperty(value = "infocode")
    private String infoCode;

    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;

    @JSONField(name = "adcode")
    @JsonProperty(value = "adcode")
    private String adCode;

    @JSONField(name = "rectangle")
    @JsonProperty(value = "rectangle")
    private String rectangle;
}
