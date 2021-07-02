package com.potato369.find.dynamic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 * @PackageName com.potato369.find.dynamic.controller
 * @ClassName HelloController
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/02 18:58
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@RestController
@RequestMapping("/v1/hello")
public class HelloController {

    @GetMapping("/say.do")
    public String say() {
        return "Hello World !";
    }
}
