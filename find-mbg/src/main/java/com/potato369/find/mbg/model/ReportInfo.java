package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportInfo implements Serializable {
    @ApiModelProperty(value = "举报信息id，主键")
    private Long id;

    @ApiModelProperty(value = "类目id，对应举报类目表主键id")
    private Long categoryType;

    @ApiModelProperty(value = "举报类型，1->动态，2->用户，默认：1-动态")
    private String reportType;

    @ApiModelProperty(value = "处理建议类型，0->默认，1->已忽略，2->已隐藏（针对动态），3—>已禁用（针对用户。亦可做其他方式处理）），默认：0->默认")
    private String suggestType;

    @ApiModelProperty(value = "举报用户id")
    private Long reportUserId;

    @ApiModelProperty(value = "被举报动态id")
    private Long beingReportUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "预留字段01")
    private String reserveColumn01;

    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;

    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    @ApiModelProperty(value = "举报内容")
    private String reportContent;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Long categoryType) {
        this.categoryType = categoryType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getSuggestType() {
        return suggestType;
    }

    public void setSuggestType(String suggestType) {
        this.suggestType = suggestType;
    }

    public Long getReportUserId() {
        return reportUserId;
    }

    public void setReportUserId(Long reportUserId) {
        this.reportUserId = reportUserId;
    }

    public Long getBeingReportUserId() {
        return beingReportUserId;
    }

    public void setBeingReportUserId(Long beingReportUserId) {
        this.beingReportUserId = beingReportUserId;
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

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryType=").append(categoryType);
        sb.append(", reportType=").append(reportType);
        sb.append(", suggestType=").append(suggestType);
        sb.append(", reportUserId=").append(reportUserId);
        sb.append(", beingReportUserId=").append(beingReportUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", reserveColumn01=").append(reserveColumn01);
        sb.append(", reserveColumn02=").append(reserveColumn02);
        sb.append(", reserveColumn03=").append(reserveColumn03);
        sb.append(", reserveColumn04=").append(reserveColumn04);
        sb.append(", reportContent=").append(reportContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}