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
public class ScreenSetting implements Serializable {
    @ApiModelProperty(value = "编号，主键")
    private Long id;

    @ApiModelProperty(value = "类型，0->鹿可女生年龄筛选条件；1->鹿可男生年龄筛选条件；2->动态年龄范围筛选条件最小值；3->动态年龄范围筛选条件最大值，默认：0->鹿可女生年龄筛选条件")
    private String type;

    @ApiModelProperty(value = "值")
    private Integer value;

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
}