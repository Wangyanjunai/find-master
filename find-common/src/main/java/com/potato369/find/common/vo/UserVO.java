package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
    /**
     * <pre>
     * @JSONField id：用户id
     * </pre>
     */
    @JsonProperty(value = "id")
    @JSONField(name = "id")
    private Long id;

    /**
     * <pre>
     * @JSONField nickname：昵称
     * </pre>
     */
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickName;

    /**
     * <pre>
     * @JSONField headIcon：头像
     * </pre>
     */
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String headIcon;

    /**
     * <pre>
     * @JSONField bgIcon：背景图片地址
     * </pre>
     */
    @JSONField(name = "bg")
    @JsonProperty(value = "bg")
    private String bgIcon;

    /**
     * <pre>
     * @JSONField grade：VIP等级，VIP0->普通用户，VIP1->VIP 1级用户，VIP2->VIP 2级用户
     * </pre>
     */
    @JSONField(name = "grade")
    @JsonProperty(value = "grade")
    private String grade;

    /**
     * <pre>
     * @JSONField age：年龄
     * </pre>
     */
    @JSONField(name = "age")
    @JsonProperty(value = "age")
    private Integer age;

    /**
     * <pre>
     * @JSONField year：出生年份
     * </pre>
     */
    @JSONField(name = "year")
    @JsonProperty(value = "year")
    private String year;

    /**
     * <pre>
     * @JSONField month：出生月份
     * </pre>
     */
    @JSONField(name = "month")
    @JsonProperty(value = "month")
    private String month;

    /**
     * <pre>
     * @JSONField date：出生日期
     * </pre>
     */
    @JSONField(name = "date")
    @JsonProperty(value = "date")
    private String date;

    /**
     * <pre>
     * @JSONField gender：性别
     * </pre>
     */
    @JSONField(name = "gender")
    @JsonProperty(value = "gender")
    private String gender;

    /**
     * <pre>
     * @JSONField autograph：签名
     * </pre>
     */
    @JSONField(name = "autograph")
    @JsonProperty(value = "autograph")
    private String autograph;

    /**
     * <pre>
     * @JSONField industry：行业
     * </pre>
     */
    @JSONField(name = "industry")
    @JsonProperty(value = "industry")
    private String industry;

    /**
     * <pre>
     * @JSONField profession：职业
     * </pre>
     */
    @JSONField(name = "profession")
    @JsonProperty(value = "profession")
    private String profession;

    /**
     * <pre>
     * @JSONField country：国家
     * </pre>
     */
    @JSONField(name = "country")
    @JsonProperty(value = "country")
    private String country;

    /**
     * <pre>
     * @JSONField province：省份
     * </pre>
     */
    @JSONField(name = "province")
    @JsonProperty(value = "province")
    private String province;

    /**
     * <pre>
     * @JSONField city：城市
     * </pre>
     */
    @JSONField(name = "city")
    @JsonProperty(value = "city")
    private String city;

    /**
     * <pre>
     * @JSONField city：城市
     * </pre>
     */
    @JSONField(name = "district")
    @JsonProperty(value = "district")
    private String district;

    /**
     * <pre>
     * @JSONField other：其它
     * </pre>
     */
    @JSONField(name = "other")
    @JsonProperty(value = "other")
    private String other;

    /**
     * <pre>
     * @JSONField tag1：标签1
     * </pre>
     */
    @JSONField(name = "tag1")
    @JsonProperty(value = "tag1")
    private String tag1;

    /**
     * <pre>
     * @JSONField tag2：标签2
     * </pre>
     */
    @JSONField(name = "tag2")
    @JsonProperty(value = "tag2")
    private String tag2;

    /**
     * <pre>
     * @JSONField tag3：标签3
     * </pre>
     */
    @JSONField(name = "tag3")
    @JsonProperty(value = "tag3")
    private String tag3;

    /**
     * <pre>
     * @JSONField tag4：标签4
     * </pre>
     */
    @JSONField(name = "tag4")
    @JsonProperty(value = "tag4")
    private String tag4;

    /**
     * <pre>
     * @JSONField tag5：标签5
     * </pre>
     */
    @JSONField(name = "tag5")
    @JsonProperty(value = "tag5")
    private String tag5;
}
