package com.potato369.find.common.vo;
//用户评论数据

import java.util.Date;

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
public class CommentVO {
	
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
     * @JSONField nickname：用户昵称
     * </pre>
     */
	@JsonProperty(value = "id")
	@JSONField(name = "id")
	private String nickname;
	
	/**
	 * <pre>
     * @JSONField head：用户头像
     * </pre>
     */
	@JsonProperty(value = "head")
	@JSONField(name = "head")
	private String head;
	
	/**
	 * <pre>
     * @JSONField comment：评论内容
     * </pre>
     */
	@JsonProperty(value = "comment")
	@JSONField(name = "comment")
	private String comment;
	
	/**
	 * <pre>
     * @JSONField commentDateTime：评论时间
     * </pre>
     */
	@JsonProperty(value = "commentDateTime")
	@JSONField(name = "commentDateTime")
	private Date commentDateTime;
	
	/**
	 * <pre>
     * @JSONField isOrNotLikes：当前用户是否点赞这条评论
     * </pre>
     */
	@JsonProperty(value = "isOrNotLikes")
	@JSONField(name = "isOrNotLikes")
	private String isOrNotLikes;
	
	/**
	 * <pre>
     * @JSONField likes：这条评论点赞数
     * </pre>
     */
	@JsonProperty(value = "likes")
	@JSONField(name = "likes")
	private Integer likes;
}
