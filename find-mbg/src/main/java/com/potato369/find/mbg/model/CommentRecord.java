package com.potato369.find.mbg.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class CommentRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "记录id，主键")
    private Long id;
    @ApiModelProperty(value = "评论者用户id")
    private Long userId;
    @ApiModelProperty(value = "被评论者用户id")
    private Long beUserId;
    @ApiModelProperty(value = "被评论者动态内容id")
    private Long dynamicInfoId;
    @ApiModelProperty(value = "评论时间")
    private Date createTime;
    @ApiModelProperty(value = "预留字段01")
    private String reserveColumn01;
    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;
    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;
    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

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

    public Long getBeUserId() {
        return beUserId;
    }

    public void setBeUserId(Long beUserId) {
        this.beUserId = beUserId;
    }

    public Long getDynamicInfoId() {
        return dynamicInfoId;
    }

    public void setDynamicInfoId(Long dynamicInfoId) {
        this.dynamicInfoId = dynamicInfoId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        sb.append(", userId=").append(userId);
        sb.append(", beUserId=").append(beUserId);
        sb.append(", dynamicInfoId=").append(dynamicInfoId);
        sb.append(", createTime=").append(createTime);
        sb.append(", reserveColumn01=").append(reserveColumn01);
        sb.append(", reserveColumn02=").append(reserveColumn02);
        sb.append(", reserveColumn03=").append(reserveColumn03);
        sb.append(", reserveColumn04=").append(reserveColumn04);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}