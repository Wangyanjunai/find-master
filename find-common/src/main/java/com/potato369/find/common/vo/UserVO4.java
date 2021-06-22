package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * 鹿可模块返回用户资料详情模型数据
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO4 {

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
     * attacheList：用户最新6张动态图片列表
     * </pre>
     */
    @JSONField(name = "attacheList")
    @JsonProperty(value = "attacheList")
    private List<String> attacheList;//动态图片列表

    /**
     * <pre>
     *  tag1：标签1
     * </pre>
     */
    @JSONField(name = "tag1")
    @JsonProperty(value = "tag1")
    private String tag1;

    /**
     * <pre>
     * tag2：标签2
     * </pre>
     */
    @JSONField(name = "tag2")
    @JsonProperty(value = "tag2")
    private String tag2;

    /**
     * <pre>
     *  tag3：标签3
     * </pre>
     */
    @JSONField(name = "tag3")
    @JsonProperty(value = "tag3")
    private String tag3;

    /**
     * <pre>
     *  tag4：标签4
     * </pre>
     */
    @JSONField(name = "tag4")
    @JsonProperty(value = "tag4")
    private String tag4;

    /**
     * <pre>
     *  tag5：标签5
     * </pre>
     */
    @JSONField(name = "tag5")
    @JsonProperty(value = "tag5")
    private String tag5;

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
     * constellation：星座
     * </pre>
     */
    @JSONField(name = "constellation")
    @JsonProperty(value = "constellation")
    private String constellation;

    /**
     * <pre>
     * gender：性别
     * </pre>
     */
    @JSONField(name = "gender")
    @JsonProperty(value = "gender")
    private String gender;

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
     * industry：行业
     * </pre>
     */
    @JSONField(name = "industry")
    @JsonProperty(value = "industry")
    private String industry;

    /**
     * <pre>
     * profession：职业
     * </pre>
     */
    @JSONField(name = "profession")
    @JsonProperty(value = "profession")
    private String profession;

    /**
     * <pre>
     * country：国
     * </pre>
     */
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    /**
     * <pre>
     * province：省
     * </pre>
     */
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    /**
     * <pre>
     * city：市
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
}
