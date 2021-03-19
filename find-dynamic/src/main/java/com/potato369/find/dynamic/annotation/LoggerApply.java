package com.potato369.find.dynamic.annotation;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: Lingye
 * @Date: 2018/11/11
 * @Describe:
 * @Modified By:
 */
@Slf4j
@Component
public class LoggerApply {
	
    @Lingyejun(module = "http://www.cnblogs.com/lingyejun/")
    public void lingLogger(String event) throws Exception {
        log.info("lingLogger(String event) : lingyejun will auth by blog address");
        throw new Exception();
    }
}
