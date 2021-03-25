package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO2 {
	
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
     * @JSONField gender：性别
     * </pre>
     */
	@JsonProperty(value = "gender")
    @JSONField(name = "gender")
    private String gender;

    /**
     * <pre>
     * nickname：昵称
     * </pre>
     */
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickname;
    
    /**
     * <pre>
     * head：头像图片url
     * </pre>
     */
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;
    
    /**
     * <pre>
     * head：背景图片url
     * </pre>
     */
    @JSONField(name = "bg")
    @JsonProperty(value = "bg")
    private String background;
    
    /**
     * <pre>
     * autograph：签名
     * </pre>
     */
    @JSONField(name = "autograph")
    @JsonProperty(value = "autograph")
    private String autograph;
}
