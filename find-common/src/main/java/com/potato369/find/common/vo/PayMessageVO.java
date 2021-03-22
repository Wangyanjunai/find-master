package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PayMessageVO {

    /**
     * <pre>
     * @JsonFeid payState：支付状态
     * </pre>
     */
    @JSONField(name = "pay_state")
    @JsonProperty(value = "payState")
    private Integer payState;

    /**
     * <pre>
     * @JsonFeid tradeState：交易状态
     * </pre>
     */
    @JSONField(name = "trade_state")
    @JsonProperty(value = "trade_state")
    private String tradeState;

    /**
     * <pre>
     * @JsonFeid tradeStateDesc：交易状态描述
     * </pre>
     */
    @JSONField(name = "trade_state_desc")
    @JsonProperty(value = "trade_state_desc")
    private String tradeStateDesc;

    /**
     * <pre>
     * @JsonFeid payMessage：支付消息
     * </pre>
     */
    @JSONField(name = "pay_msg")
    @JsonProperty(value = "payMessage")
    private String payMessage;

    /**
     * <pre>
     * @JsonFeid productName：支付产品名称
     * </pre>
     */
    @JSONField(name = "product_name")
    @JsonProperty(value = "product_name")
    private String productName;

    /**
     * <pre>
     * @JsonFeid isOrNotVIP：支付产品是否是vip时长类型
     * </pre>
     */
    @JSONField(name = "is_vip")
    @JsonProperty(value = "isOrNotVIP")
    private Boolean isOrNotVIP;

    /**
     * <pre>
     * @JsonFeid isOrNotFirstGift：支付产品是否是首充礼包类型，0-不是，1-是
     * </pre>
     */
    @JSONField(name = "is_first")
    @JsonProperty(value = "is_first")
    private Integer isOrNotFirstGift;

    /**
     * <pre>
     * @JsonFeid orderId：商户订单编号
     * </pre>
     */
    @JSONField(name = "orderId")
    @JsonProperty(value = "orderId")
    private String orderId;
}