package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDetail implements Serializable {
	
    @ApiModelProperty(value = "id，主键")
    private Long id;
    
    @ApiModelProperty(value = "详情id")
    private String detailId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "用户id")
    private Long buyerUserId;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "购买数量")
    private Integer productQuantity;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "预留字段02")
    private String reserveColumn02;

    @ApiModelProperty(value = "预留字段03")
    private String reserveColumn03;

    @ApiModelProperty(value = "预留字段04")
    private String reserveColumn04;

    private static final long serialVersionUID = 1L;
}