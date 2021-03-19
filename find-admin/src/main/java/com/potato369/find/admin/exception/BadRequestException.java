package com.potato369.find.admin.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -1073205900953088564L;
	
    private Integer status = BAD_REQUEST.value();

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }

}
