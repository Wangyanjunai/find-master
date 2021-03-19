package com.potato369.find.common.enums;

//充值会员时长套餐产品状态枚举

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ProductInfoStatusEnum implements CodeEnum<Integer> {

	OFF(0, "下架"),

	ON(1, "上架"),;

	private Integer code;

	private String msg;
}
