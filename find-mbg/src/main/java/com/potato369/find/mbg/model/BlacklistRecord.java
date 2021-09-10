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
public class BlacklistRecord implements Serializable {

    @ApiModelProperty(value = "记录id，主键")
    private Long id;

    @ApiModelProperty(value = "拥有者id")
    private Long ownerUserId;

    @ApiModelProperty(value = "黑名单用户id")
    private Long blackUserId;

    @ApiModelProperty(value = "黑名单状态，0或者偶数表示移出黑名单，1或者奇数表示拉入黑名单")
    private Integer status;

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