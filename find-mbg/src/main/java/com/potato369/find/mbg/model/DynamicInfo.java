package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicInfo implements Serializable {
    @ApiModelProperty(value = "动态内容id，主键")
    private Long id;

    @ApiModelProperty(value = "发布者id")
    private Long userId;

    @ApiModelProperty(value = "动态信息id")
    private Long dynamic_id;

    @ApiModelProperty(value = "动态内容状态，0->显示；1->隐藏；2->小推；3->大推，默认：0->显示")
    private String dynamicStatus;

    @ApiModelProperty(value = "是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    private String publicStatus;

    @ApiModelProperty(value = "是否是话题，0->否；1->是，默认：0->否")
    private String isTopic;

    @ApiModelProperty(value = "话题标题")
    private String topicTitle;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "申请加微信数")
    private Integer applications;

    @ApiModelProperty(value = "分享数")
    private Integer shares;

    @ApiModelProperty(value = "附件类型，0->文本；1->图片；2->音频，默认：0->文本")
    private String attacheType;

    @ApiModelProperty(value = "附件数量")
    private Integer attacheNumber;

    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;

    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    @ApiModelProperty(value = "内容")
    private String content;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDynamic_id() {
        return dynamic_id;
    }

    public void setDynamic_id(Long dynamic_id) {
        this.dynamic_id = dynamic_id;
    }

    public String getDynamicStatus() {
        return dynamicStatus;
    }

    public void setDynamicStatus(String dynamicStatus) {
        this.dynamicStatus = dynamicStatus;
    }

    public String getPublicStatus() {
        return publicStatus;
    }

    public void setPublicStatus(String publicStatus) {
        this.publicStatus = publicStatus;
    }

    public String getIsTopic() {
        return isTopic;
    }

    public void setIsTopic(String isTopic) {
        this.isTopic = isTopic;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Integer getApplications() {
        return applications;
    }

    public void setApplications(Integer applications) {
        this.applications = applications;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getAttacheType() {
        return attacheType;
    }

    public void setAttacheType(String attacheType) {
        this.attacheType = attacheType;
    }

    public Integer getAttacheNumber() {
        return attacheNumber;
    }

    public void setAttacheNumber(Integer attacheNumber) {
        this.attacheNumber = attacheNumber;
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

    public String getReserveColumn02() {
        return reserveColumn02;
    }

    public void setReserveColumn02(String reserveColumn02) {
        this.reserveColumn02 = reserveColumn02;
    }

    public String getReserveColumn03() {
        return reserveColumn03;
    }

    public void setReserveColumn03(String reserveColumn03) {
        this.reserveColumn03 = reserveColumn03;
    }

    public String getReserveColumn04() {
        return reserveColumn04;
    }

    public void setReserveColumn04(String reserveColumn04) {
        this.reserveColumn04 = reserveColumn04;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", dynamic_id=").append(dynamic_id);
        sb.append(", dynamicStatus=").append(dynamicStatus);
        sb.append(", publicStatus=").append(publicStatus);
        sb.append(", isTopic=").append(isTopic);
        sb.append(", topicTitle=").append(topicTitle);
        sb.append(", likes=").append(likes);
        sb.append(", comments=").append(comments);
        sb.append(", applications=").append(applications);
        sb.append(", shares=").append(shares);
        sb.append(", attacheType=").append(attacheType);
        sb.append(", attacheNumber=").append(attacheNumber);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reserveColumn02=").append(reserveColumn02);
        sb.append(", reserveColumn03=").append(reserveColumn03);
        sb.append(", reserveColumn04=").append(reserveColumn04);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}