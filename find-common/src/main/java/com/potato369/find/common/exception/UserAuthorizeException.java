package com.potato369.find.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthorizeException extends RuntimeException {
	
	private static final long serialVersionUID = -5844580017837236611L;
	
	private String message;
}
