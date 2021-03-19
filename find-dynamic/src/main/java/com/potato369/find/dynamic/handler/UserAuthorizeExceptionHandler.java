package com.potato369.find.dynamic.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.exception.UserAuthorizeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class UserAuthorizeExceptionHandler {
	
	@ExceptionHandler(UserAuthorizeException.class)
    public CommonResult<String> handleError(UserAuthorizeException e) {
        log.error(e.getMessage(),  e);
        return CommonResult.failed(e.getMessage());
    }
}
