package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttacheInfo implements Serializable {

    @ApiModelProperty(value = "附件id，主键")
    private Long id;

    @ApiModelProperty(value = "状态，0->隐藏，1->显示，默认：1->显示")
    private String status;

    @ApiModelProperty(value = "动态内容id，对应动态信息表主键id")
    private Long dynamicInfoBy;

    @ApiModelProperty(value = "附件名称列表，以“||”分割")
    private String fileName;

    @ApiModelProperty(value = "附件类型，1->图片；2->音频，默认：1->图片")
    private String dataType;

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

    private static final long serialVersionUID = 1L;
}