package com.potato369.find.common.vo;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * <pre>
 * @PackageName com.potato369.common.vo
 * @ClassName DynamicVO
 * @Desc 动态内容前端数据载体
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 11:44
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
import lombok.ToString;

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
