package com.potato369.find.order.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAuthorizeException extends RuntimeException {
	
	private static final long serialVersionUID = -3184717027909669490L;
	
	private String message;
}
