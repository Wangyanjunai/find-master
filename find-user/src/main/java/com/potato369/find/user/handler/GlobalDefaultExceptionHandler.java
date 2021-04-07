package com.potato369.find.user.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * <pre>
 * @PackageName com.potato369.find.user.exception
 * @ClassName GlobalDefaultExceptionHandler
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/10/29 17:34
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example
        // at the start of this post.
        // AnnotationUtils is a Spring Framework utility class.
//		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//			throw e;
//		}

        // Otherwise setup and send the user to a default error-view.
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", e);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName(DEFAULT_ERROR_VIEW);
//		return mav;
//        e.printStackTrace();
//        System.out.println("GlobalDefaultExceptionHandler.defaultErrorHandler()");
        log.error("GlobalDefaultExceptionHandler.defaultErrorHandler()", e);
    }
}
