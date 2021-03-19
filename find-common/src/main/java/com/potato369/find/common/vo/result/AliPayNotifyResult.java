package com.potato369.find.common.vo.result;


import com.alibaba.fastjson.annotation.JSONField;
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
public class AliPayNotifyResult extends Result {
	/**
     * <pre>
     * @JSONField appId：支付宝分配给开发者的应用Id。
     * </pre>
     */
    @JSONField(name = "app_id")
    @JsonProperty(value = "app_id")
    private String appId;

    /**
     * <pre>
     * @JSONField notifyTime：通知时间:yyyy-MM-dd HH:mm:ss。
     * </pre>
     */
    @JSONField(name = "notify_time")
    @JsonProperty(value = "notify_time")
    private String notifyTime;

    /**
     * <pre>
     * @JSONField gmtCreate：交易创建时间:yyyy-MM-dd HH:mm:ss。
     * </pre>
     */
    @JSONField(name = "gmt_create")
    @JsonProperty(value = "gmt_create")
    private String gmtCreate;

    /**
     * <pre>
     * @JSONField gmtPayment：交易付款时间。
     * </pre>
     */
    @JSONField(name = "gmt_payment")
    @JsonProperty(value = "gmt_payment")
    private String gmtPayment;

    /**
     * <pre>
     * @JSONField gmtRefund：交易退款时间。
     * </pre>
     */
    @JSONField(name = "gmt_refund")
    @JsonProperty(value = "gmt_refund")
    private String gmtRefund;

    /**
     * <pre>
     * @JSONField gmtClose：交易结束时间。
     * </pre>
     */
    @JSONField(name = "gmt_close")
    @JsonProperty(value = "gmt_close")
    private String gmtClose;

    /**
     * <pre>
     * @JSONField tradeNo：支付宝的交易号。
     * </pre>
     */
    @JSONField(name = "trade_no")
    @JsonProperty(value = "trade_no")
    private String tradeNo;

    /**
     * <pre>
     * @JSONField
     * </pre>
     */
    @JSONField(name = "out_trade_no")
    @JsonProperty(value = "out_trade_no")
    private String outTradeNo;//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）

    /**
     * <pre>
     * @JSONField outBizNo：商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
     * </pre>
     */
    @JSONField(name = "out_biz_no")
    @JsonProperty(value = "out_biz_no")
    private String outBizNo;
    
    /**
     * <pre>
     * @JSONField buyerId：买家支付宝用户号。
     * </pre>
     */
    @JSONField(name = "buyer_id")
    @JsonProperty(value = "buyer_id")
    private String buyerId;

    /**
     * <pre>
     * @JSONField buyerLogonId：买家支付宝账号。
     * </pre>
     */
    @JSONField(name = "buyer_logon_id")
    @JsonProperty(value = "buyer_logon_id")
    private String buyerLogonId;

    /**
     * <pre>
     * @JSONField sellerId：卖家支付宝用户号。
     * </pre>
     */
    @JSONField(name = "seller_id")
    @JsonProperty(value = "seller_id")
    private String sellerId;

    /**
     * <pre>
     * @JSONField sellerEmail：卖家支付宝账号。
     * </pre>
     */
    @JSONField(name = "seller_email")
    @JsonProperty(value = "seller_email")
    private String sellerEmail;

    /**
     * <pre>
     * @JSONField totalAmount：订单金额:本次交易支付的订单金额，单位为人民币（元）。
     * </pre>
     */
    @JSONField(name = "total_amount")
    @JsonProperty(value = "total_amount")
    private String totalAmount;

    /**
     * <pre>
     * @JSONField receiptAmount：实收金额:商家在交易中实际收到的款项，单位为人民币（元）。
     * </pre>
     */
    @JSONField(name = "receipt_amount")
    @JsonProperty(value = "receipt_amount")
    private String receiptAmount;

    /**
     * <pre>
     * @JSONField invoiceAmount：开票金额:用户在交易中支付的可开发票的金额。
     * </pre>
     */
    @JSONField(name = "invoice_amount")
    @JsonProperty(value = "invoice_amount")
    private String invoiceAmount;

    /**
     * <pre>
     * @JSONField buyerPayAmount：付款金额:用户在交易中支付的金额。
     * </pre>
     */
    @JSONField(name = "buyer_pay_amount")
    @JsonProperty(value = "buyer_pay_amount")
    private String buyerPayAmount;

    /**
     * <pre>
     * @JSONField tradeStatus：获取交易状态。
     * </pre>
     */
    @JSONField(name = "trade_status")
    @JsonProperty(value = "trade_status")
    private String tradeStatus;
}
