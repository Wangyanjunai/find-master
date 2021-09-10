package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.potato369.find.common.enums.OrderStatusEnum;
import com.potato369.find.common.enums.PayModeEnum;
import com.potato369.find.common.enums.PayStatusEnum;
import com.potato369.find.common.utils.EnumUtil;
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
public class OrderMaster implements Serializable {

    @ApiModelProperty(value = "id，主键")
    private Long id;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "订单支付交易流水号")
    private String transactionId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "买家昵称")
    private String buyerName;

    @ApiModelProperty(value = "买家手机号码")
    private String buyerPhone;

    @ApiModelProperty(value = "IP")
    private String buyerIp;

    @ApiModelProperty(value = "订单名称")
    private String orderName;

    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderAmount;

    @ApiModelProperty(value = "订单状态：0->新订单；1->已完结；2->已取消，默认：0->新订单")
    private String orderStatus;

    @ApiModelProperty(value = "支付状态：0->等待支付；1->支付成功；2->关闭支付，默认：0->等待支付")
    private String payStatus;

    @ApiModelProperty(value = "支付方式：0->微信支付；1->支付宝支付，默认：0->微信支付")
    private String payMode;

    @ApiModelProperty(value = "支付时间")
    private Date payTime;

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

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(Integer.parseInt(orderStatus), OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(Integer.parseInt(payStatus), PayStatusEnum.class);
    }

    @JsonIgnore
    public PayModeEnum getPayModeEnum() {
        return EnumUtil.getByCode(Integer.parseInt(payMode), PayModeEnum.class);
    }

    private static final long serialVersionUID = 1L;
}