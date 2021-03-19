package com.potato369.find.common.dto;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderDTO extends AbstractDTO {

    /**
     * productId:商品id
     */
    @ApiModelProperty(name = "pid", value = "商品id")
    @JSONField(name = "pid")
    @JsonProperty(value = "pid")
    @NotNull(message = "商品id不能为空")
    private long productId;

    /**
     * payMode:支付方式，0->微信支付，1->支付宝支付
     */
    @ApiModelProperty(name = "mode", value = "支付方式，0->微信支付，1->支付宝支付")
    @JSONField(name = "mode")
    @JsonProperty(value = "mode")
    @NotNull(message = "支付方式不能为空")
    private int payMode;

    /**
     * userIp:终端IP
     */
    @ApiModelProperty(name = "ip", value = "终端IP")
    @JSONField(name = "ip")
    @JsonProperty(value = "ip")
    @NotEmpty(message = "终端IP不能为空")
    private String userIp;
}
