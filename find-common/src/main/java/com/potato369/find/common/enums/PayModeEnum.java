package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
//支付方式枚举
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PayModeEnum implements CodeEnum<Integer> {
	
	weixinpay(0, "微信支付"),
	
	alipay(1, "支付宝支付"),
	;
	private Integer code;

    private String message;
}
