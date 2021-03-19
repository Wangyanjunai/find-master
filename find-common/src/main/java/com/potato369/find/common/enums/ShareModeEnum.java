package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ShareModeEnum implements CodeEnum<Integer> {
	
	Wechat_Friend(0, "0", "微信好友"),
	
	Qq_Friend(1, "1", "QQ好友"),
	
	Wechat_Moments(2, "2", "微信朋友圈"),
	
	Qq_Moments(3, "3", "QQ空间"),
	
	Wechat_Collection(4, "4", "微信收藏"),
	;
	private Integer code;
	
	private String mode;
	
	private String name;
}
