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
    private Long dynamicId;

    @ApiModelProperty(value = "动态内容状态，0->显示；1->隐藏；2->小推；3->大推，默认：0->显示")
    private String dynamicStatus;

    @ApiModelProperty(value = "是否公开定位状态，0->未公开；1->公开，默认：0->未公开")
    private String publicStatus;

    @ApiModelProperty(value = "是否置顶，0->否；1->是，默认：0->否")
    private String isTop;

    @ApiModelProperty(value = "是否话题，0->否；1->是，默认：0->否")
    private String isTopic;

    @ApiModelProperty(value = "是否匿名，0->否；1->是，默认：0->否")
    private String isAnonymous;

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

    @ApiModelProperty(value = "动态附件类型：1->图片，2->语音，0->文字（不包含附件的纯文字），必需传参数")
    private String attacheType;

    @ApiModelProperty(value = "附件数量")
    private Integer attacheNumber;

    @ApiModelProperty(value = "定位（经度）")
    private Double longitude;

    @ApiModelProperty(value = "定位（纬度）")
    private Double latitude;

    @ApiModelProperty(value = "定位（国家）")
    private String country;

    @ApiModelProperty(value = "定位（省份）")
    private String province;

    @ApiModelProperty(value = "定位（城市）")
    private String city;

    @ApiModelProperty(value = "定位（区/县）")
    private String district;

    @ApiModelProperty(value = "定位（其它地址）")
    private String other;

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

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
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

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsTopic() {
        return isTopic;
    }

    public void setIsTopic(String isTopic) {
        this.isTopic = isTopic;
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", userId=" + userId +
                ", dynamicId=" + dynamicId +
                ", dynamicStatus=" + dynamicStatus +
                ", publicStatus=" + publicStatus +
                ", isTop=" + isTop +
                ", isTopic=" + isTopic +
                ", isAnonymous=" + isAnonymous +
                ", topicTitle=" + topicTitle +
                ", likes=" + likes +
                ", comments=" + comments +
                ", applications=" + applications +
                ", shares=" + shares +
                ", attacheType=" + attacheType +
                ", attacheNumber=" + attacheNumber +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", reserveColumn02=" + reserveColumn02 +
                ", reserveColumn03=" + reserveColumn03 +
                ", reserveColumn04=" + reserveColumn04 +
                ", content=" + content +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}