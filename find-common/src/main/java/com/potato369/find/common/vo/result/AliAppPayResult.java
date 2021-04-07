package com.potato369.find.common.vo.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AliAppPayResult extends Result{

    /**
     * <pre>
     * @JSONField appId：应用APPID，支付宝开放平台审核通过的应用APPID。
     * </pre>
     */
    @JSONField(name = "appid")
    @JsonProperty(value = "appid")
    private String appId;

    /**
     * <pre>
     * @JSONField body：应用APPID，微信开放平台审核通过的应用APPID。
     * </pre>
     */
    @JSONField(name = "orderString")
    @JsonProperty(value = "orderString")
    private String body;

    /**
     * <pre>
     * @JSONField orderId：商户订单id。
     * </pre>
     */
    @JSONField(name = "out_trade_no")
    @JsonProperty(value = "out_trade_no")
    private String orderId;
}
