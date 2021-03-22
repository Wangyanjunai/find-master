package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

//用户点赞记录
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesMessageRecord {
	//点赞用户id
	@ApiModelProperty(value = "点赞用户id")
	private Long userId;
	
	//点赞用户头像图片
	@ApiModelProperty(value = "点赞记录用户头像图片")
	private String headIcon;
	
	//点赞发送的消息内容
	@ApiModelProperty(value = "点赞发送的消息内容")
	private String likesContent;
	
	//点赞的动态附件类型
	@ApiModelProperty(value = "点赞的动态附件类型")
	private String attacheType;
	
	//点赞的动态内容附件文件名称
	@ApiModelProperty(value = "点赞的动态内容附件文件名称")
	private String attacheFilename;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(String headIcon) {
		this.headIcon = headIcon;
	}

	public String getLikesContent() {
		return likesContent;
	}

	public void setLikesContent(String likesContent) {
		this.likesContent = likesContent;
	}

	public String getAttacheType() {
		return attacheType;
	}

	public void setAttacheType(String attacheType) {
		this.attacheType = attacheType;
	}

	public String getAttacheFilename() {
		return attacheFilename;
	}

	public void setAttacheFilename(String attacheFilename) {
		this.attacheFilename = attacheFilename;
	}

	@Override
	public String toString() {
		return "LikesMessageRecord [userId=" + userId + ", headIcon=" + headIcon + ", likesContent=" + likesContent
				+ ", attacheType=" + attacheType + ", attacheFilename=" + attacheFilename + "]";
	}
}
