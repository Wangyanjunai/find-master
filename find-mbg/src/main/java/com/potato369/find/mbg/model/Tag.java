package com.potato369.find.mbg.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Tag implements Serializable {
    @ApiModelProperty(value = "编号，主键")
    private Long id;

    @ApiModelProperty(value = "标签名称")
    private String name;

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

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", deletedTime=").append(deletedTime);
        sb.append(", reserveColumn01=").append(reserveColumn01);
        sb.append(", reserveColumn02=").append(reserveColumn02);
        sb.append(", reserveColumn03=").append(reserveColumn03);
        sb.append(", reserveColumn04=").append(reserveColumn04);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}