package com.potato369.find.mbg.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <pre>
 * @PackageName com.potato369.find.mbg.model
 * @ClassName DynamicInfoData
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/31 15:05
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicInfoData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "头像图片地址")
    private String headIcon;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "发布时间")
    private String publishTime;

    @ApiModelProperty(value = "动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "是否公开定位")
    private String publishStatus;

    @ApiModelProperty(value = "定位地址（国家）")
    private String country;

    @ApiModelProperty(value = "定位地址（省份）")
    private String province;

    @ApiModelProperty(value = "定位地址（城市）")
    private String city;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "申请加微信数")
    private Integer applications;

    @ApiModelProperty(value = "附件存储文件类型")
    private String attacheFileDataType;

    @ApiModelProperty(value = "附件存储文件名称列表")
    private String fileName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getDynamicInfoId() {
        return dynamicInfoId;
    }

    public void setDynamicInfoId(Long dynamicInfoId) {
        this.dynamicInfoId = dynamicInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCountry() {
        return country;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
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

    public String getAttacheFileDataType() {
        return attacheFileDataType;
    }

    public void setAttacheFileDataType(String attacheFileDataType) {
        this.attacheFileDataType = attacheFileDataType;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getApplications() {
        return applications;
    }

    public void setApplications(Integer applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "DynamicInfoData{" +
                "userId=" + userId +
                ", headIcon='" + headIcon + '\'' +
                ", nickName='" + nickName + '\'' +
                ", createTime=" + createTime +
                ", createTime=" + publishTime +
                ", dynamicInfoId=" + dynamicInfoId +
                ", content='" + content + '\'' +
                ", publishStatus='" + publishStatus + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", likes=" + likes +
                ", applications=" + applications +
                ", attacheFileDataType='" + attacheFileDataType + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
