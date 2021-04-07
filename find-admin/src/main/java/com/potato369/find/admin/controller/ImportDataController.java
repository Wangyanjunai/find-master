package com.potato369.find.admin.controller;

import com.potato369.find.admin.service.DynamicService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Api(value = "管理模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/dynamic")
public class ImportDataController {

	private DynamicService dynamicService;

	@Autowired
	public void setDynamicService(DynamicService dynamicService) {
		this.dynamicService = dynamicService;
	}

	@PostMapping(value = "/fileUpload.do", produces = "application/text; charset=utf-8")
	public ModelAndView UploadExcel(HttpServletRequest request, HttpServletResponse response) {
		String messageString;
		Map<String, Object> data = new HashedMap<>();
		try {
			messageString = this.dynamicService.ajaxUploadExcel(request, response);
			data.put("msg", messageString);
			data.put("url", "/find/index/home.do");
			return new ModelAndView("common/success", data);
		} catch (Exception e) {
			log.error("解析表格文件导入到数据库出错", e);
			messageString = "解析表格文件导入到数据库出错";
			data.put("msg", messageString);
			data.put("url", "/find/index/home.do");
			return new ModelAndView("common/error", data);
		}
	}

	@PostMapping(value = "/ajaxUpload.do", produces = "application/text; charset=utf-8")
	public ModelAndView ajaxUploadExcel(@RequestParam("upfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		String messageString;
		Map<String, Object> data = new HashedMap<>();
		try {
			messageString = this.dynamicService.ajaxUploadExcel(request, response);
			data.put("msg", messageString);
			data.put("url", "/find/index/home.do");
			return new ModelAndView("common/success", data);
		} catch (Exception e) {
			log.error("解析表格文件导入到数据库出错", e);
			messageString = "解析表格文件导入到数据库出错";
			data.put("msg", messageString);
			data.put("url", "/find/index/home.do");
			return new ModelAndView("common/error", data);
		}
	}
}
