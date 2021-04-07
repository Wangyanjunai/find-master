package com.potato369.find.mbg.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;

//用户点赞记录
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesMessageRecord {
	
	//点赞记录id
	@ApiModelProperty(value = "点赞记录id")
	private Long messageId;
		
    //点赞消息记录状态
	@ApiModelProperty(value = "点赞消息记录状态，0->取消，1->点赞")
	private String status;
	
	//点赞记录创建时间
	@ApiModelProperty(value = "点赞消息记录创建时间")
	private Date createTime;
	
	//点赞记录更新时间
	@ApiModelProperty(value = "点赞消息记录更新时间")
	private Date updateTime;
	
	//点赞用户id
	@ApiModelProperty(value = "点赞用户id")
	private Long userId;
	
	//点赞用户昵称
	@ApiModelProperty(value = "点赞记录用户昵称")
	private String nickname;
	
	//点赞用户头像图片
	@ApiModelProperty(value = "点赞记录用户头像图片")
	private String headIcon;
	
	//点赞的动态信息内容
	@ApiModelProperty(value = "点赞发送的消息内容")
	private String likesContent;
	
	//点赞的动态附件类型
	@ApiModelProperty(value = "点赞的动态附件类型")
	private String attacheType;
	
	//点赞的动态内容附件文件名称
	@ApiModelProperty(value = "点赞的动态内容附件文件名称")
	private String attacheFilename;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
		StringBuilder builder = new StringBuilder();
		builder.append("LikesMessageRecord [messageId=");
		builder.append(messageId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", updateTime=");
		builder.append(updateTime);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", headIcon=");
		builder.append(headIcon);
		builder.append(", likesContent=");
		builder.append(likesContent);
		builder.append(", attacheType=");
		builder.append(attacheType);
		builder.append(", attacheFilename=");
		builder.append(attacheFilename);
		builder.append("]");
		return builder.toString();
	}
	
}
