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
}
