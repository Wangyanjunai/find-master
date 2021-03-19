package com.potato369.find.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

//导入后台地址：http://127.0.0.1:8081/find/index/home.do

@Api(value = "主页模块用户管理控制器类")
@Controller
@RequestMapping("/index")
public class IndexController {

	@GetMapping("/home.do")
	public ModelAndView home() {
		return new ModelAndView("index/index");
	}
}
