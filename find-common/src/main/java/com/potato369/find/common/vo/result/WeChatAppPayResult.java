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
public class WeChatAppPayResult extends Result{
	/**
     * <pre>
     * @JSONField appId：应用APPID，微信开放平台审核通过的应用APPID。
     * </pre>
     */
    @JSONField(name = "appid")
    @JsonProperty(value = "appid")
    private String appId;

    /**
     * <pre>
     * @JSONField partnerId：商户号，微信支付分配的商户号。
     * </pre>
     */
    @JSONField(name = "partnerid")
    @JsonProperty(value = "partnerid")
    private String partnerId;

    /**
     * <pre>
     * @JSONField prepayId：预支付交易会话ID，微信返回的支付交易会话ID。
     * </pre>
     */
    @JSONField(name = "prepayid")
    @JsonProperty(value = "prepayid")
    private String prepayId;

    /**
     * <pre>
     * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
     * @JSONField packageValue：扩展字段，暂填写固定值Sign=WXPay
     * </pre>
     */
    @JSONField(name = "package")
    @JsonProperty(value = "package")
    private String packageValue;

    /**
     * <pre>
     * @JSONField nonceStr：随机字符串，微信返回的随机字符串。
     * </pre>
     */
    @JSONField(name = "noncestr")
    @JsonProperty(value = "nonceStr")
    private String nonceStr;

    /**
     * <pre>
     * @JSONField timeStamp：时间戳，时间戳，请见接口规则-参数规定。
     * </pre>
     */
    @JSONField(name = "timestamp")
    @JsonProperty(value = "timeStamp")
    private String timeStamp;

    /**
     * <pre>
     * @JSONField sign：签名，微信返回的签名，详见签名算法。
     * </pre>
     */
    @JSONField(name = "sign")
    @JsonProperty(value = "sign")
    private String sign;

    /**
     * <pre>
     * @JSONField orderId：商户订单id。
     * </pre>
     */
    @JSONField(name = "out_trade_no")
    @JsonProperty(value = "out_trade_no")
    private String orderId;
}
