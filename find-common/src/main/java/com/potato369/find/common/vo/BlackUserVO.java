package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
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
public class BlackUserVO {
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
     * @JSONField time：添加时间
     * </pre>
     */
    @JSONField(name = "time")
    @JsonProperty(value = "time")
    private String createTime;
}
