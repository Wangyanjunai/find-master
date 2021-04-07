package com.potato369.find.dynamic.handler;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;

/**
 * 设置一个@ControllerAdvice用来监控Multipart上传的文件大小是否受限，当出现此异常时在前端页面给出提示。
 * 利用@ControllerAdvice可以做很多东西，比如全局的统一异常处理等，感兴趣的同学可以下来了解。
 * @author admin
 *
 */
@Slf4j
@RestControllerAdvice
public class MultipartExceptionHandler {
	
	@ExceptionHandler(MultipartException.class)
    public CommonResult<String> handleError1(MultipartException e) {
        log.error(ResultCode.FILE_EXCEEDS_LIMIT.getMessage(),  e);
        return CommonResult.failed(ResultCode.FILE_EXCEEDS_LIMIT.getCode(), ResultCode.FILE_EXCEEDS_LIMIT.getMessage());
    }
}
