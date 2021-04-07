package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

//用户点赞消息记录
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesRecord {
	//点赞记录用户id
	@ApiModelProperty(value = "点赞记录用户id")
	private Long likesUserId;
	
	//点赞记录创建时间
	@ApiModelProperty(value = "点赞记录创建时间")
	private Date likesCreateTime;
	
	//点赞记录动态信息id
	@ApiModelProperty(value = "点赞记录动态信息id")
	private Long dynamicInfoId;
}
