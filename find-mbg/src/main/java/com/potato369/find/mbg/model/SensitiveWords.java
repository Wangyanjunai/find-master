package com.potato369.find.mbg.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SensitiveWords implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "编号，主键")
    private Long id;
    @ApiModelProperty(value = "类型名称")
    private String typeName;
    @ApiModelProperty(value = "是否删除状态，0->否，1->是，默认：0->否")
    private String deleteStatus;
    @ApiModelProperty(value = "创建时间")
    private Date createdTime;
    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;
    @ApiModelProperty(value = "删除时间")
    private Date deletedTime;
    @ApiModelProperty(value = "预留字段01")
    private String reserveColumn01;
    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;
    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;
    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;
    @ApiModelProperty(value = "内容")
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(String deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Date getDeletedTime() {
        return deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

    public String getReserveColumn01() {
        return reserveColumn01;
    }

    public void setReserveColumn01(String reserveColumn01) {
        this.reserveColumn01 = reserveColumn01;
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
                ", typeName=" + typeName +
                ", deleteStatus=" + deleteStatus +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", deletedTime=" + deletedTime +
                ", reserveColumn01=" + reserveColumn01 +
                ", reserveColumn02=" + reserveColumn02 +
                ", reserveColumn03=" + reserveColumn03 +
                ", reserveColumn04=" + reserveColumn04 +
                ", content=" + content +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}