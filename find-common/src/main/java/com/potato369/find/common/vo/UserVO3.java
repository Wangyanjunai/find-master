package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 鹿可模块返回用户资料模型数据
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO3 {

    /**
     * <pre>
     * id：用户id
     * </pre>
     */
    @JsonProperty(value = "id")
    @JSONField(name = "id")
    private Long id;

    /**
     * <pre>
     * nickname：昵称
     * </pre>
     */
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickName;

    /**
     * <pre>
     * age：年龄
     * </pre>
     */
    @JSONField(name = "age")
    @JsonProperty(value = "age")
    private Integer age;

    /**
     * <pre>
     * country：国家
     * </pre>
     */
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    /**
     * <pre>
     * province：省份
     * </pre>
     */
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    /**
     * <pre>
     * city：城市
     * </pre>
     */
    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;

    /**
     * <pre>
     * district：区/县
     * </pre>
     */
    @JSONField(name = "district")
    @JsonProperty(value = "district")
    private String district;

    /**
     * <pre>
     * other：其它
     * </pre>
     */
    @JSONField(name = "other")
    @JsonProperty(value = "other")
    private String other;

    /**
     * <pre>
     * distance：距离（单位米）
     * </pre>
     */
    @JSONField(name = "distance")
    @JsonProperty(value = "distance")
    private Double distance;

    /**
     * <pre>
     * img：第一张动态图片地址
     * </pre>
     */
    @JSONField(name = "img")
    @JsonProperty(value = "img")
    private String img;

    /**
     * <pre>
     * dynamicInfoId：动态内容id
     * </pre>
     */
    @JSONField(name = "dynamicInfoId")
    @JsonProperty(value = "dynamicInfoId")
    private Long dynamicInfoId;
}
