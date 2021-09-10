package com.potato369.find.admin.controller;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//导入后台地址：http://127.0.0.1:8085/find/index/home

@Controller
@Api(value = "主页模块用户管理控制器类")
@RequestMapping("/index")
@Scope("request")
public class IndexController {

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("index/index");
    }

    @GetMapping("/dynamic")
    public ModelAndView dynamic() {
        return new ModelAndView("dynamic/index");
    }

    @GetMapping("/words")
    public ModelAndView words() {
        return new ModelAndView("words/index");
    }
}
