package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicVO {
	
	@JSONField(name = "userId")
	@JsonProperty(value = "userId")
	private long userId;//用户id
	
	@JSONField(name = "dynamicId")
	@JsonProperty(value = "dynamicId")
	private long dynamicId;//动态id
	
	@JSONField(name = "dynamicInfoId")
	@JsonProperty(value = "dynamicInfoId")
	private long dynamicInfoId;//动态内容id
	
	@JSONField(name = "headIcon")
	@JsonProperty(value = "headIcon")
	private String headIcon;//头像
	
	@JSONField(name = "nickname")
	@JsonProperty(value = "nickname")
	private String nickname;//昵称
	
	@JSONField(name = "publishTime")
	@JsonProperty(value = "publishTime")
	private Date publishTime;//发布时间
	
	@JSONField(name = "content")
	@JsonProperty(value = "content")
	private String content;//动态内容
	
	@JSONField(name = "atacheList")
	@JsonProperty(value = "atacheList")
	private List<String> atacheList;//附件内容列表
	
	@JSONField(name = "address")
	@JsonProperty(value = "addrss")
	private String address;//动态发布地址
	
	@JSONField(name = "likes")
	@JsonProperty(value = "likes")
	private String likes;//点赞数
	
	@JSONField(name = "applications")
	@JsonProperty(value = "applications")
	private String applications;//申请加微信数
}
