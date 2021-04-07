package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class OrderInfoDTO extends AbstractDTO {

	/**
	 * 待支付订单id
	 */
	@ApiModelProperty(value = "待支付订单id")
	@JSONField(name = "oid")
	@JsonProperty(value = "oid")
	private String orderId;
	
	/**
	 * 待支付订单名称
	 */
	@ApiModelProperty(value = "待支付订单名称")
	@JSONField(name = "oname")
	@JsonProperty(value = "oname")
	private String orderName;
	
	/**
	 * 待支付订单总金额
	 */
	@ApiModelProperty(value = "待支付订单总金额")
	@JSONField(name = "oamount")
	@JsonProperty(value = "oamount")
	private Integer orderAmount;
	
	/**
	 * 待支付订单客户端IP
	 */
	@ApiModelProperty(value = "待支付订单客户端IP")
	@JSONField(name = "oip")
	@JsonProperty(value = "oip")
	private String orderIp;

	/**
	 * 待支付订单的商品id
	 */
	@ApiModelProperty(value = "待支付订单的商品id")
	@JSONField(name = "pid")
	@JsonProperty(value = "pid")
	private Long productId;
}
