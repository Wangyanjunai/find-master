package com.potato369.find.dynamic.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.dynamic.feign.MessageService;

@RestController
@RequestMapping("/v1/dynamic")
public class MessageController {
	
	private MessageService messageFeignClient;

	@Autowired
	public void setMessageFeignClient(MessageService messageFeignClient) {
		this.messageFeignClient = messageFeignClient;
	}
	
	/**
	 * 群推，广播：广播推送也就是只要是拥有该appKey下载的安装程序的用户（即所有regId），都会受到信息
	 * 
	 * @param title   推送标题
	 * @param content 推送内容
	 * @return
	 */
	@PutMapping(value = "/push/all")
	public CommonResult<Map<String, Object>> pushAll(
			@RequestParam(name = "alert", required = true) String alert,
			@RequestParam(name = "title", required = false) String title) {
		return this.messageFeignClient.pushAll(alert, title);
	}
	
	/**
	 * 单独对regId进行推送
	 * 
	 * @param title   推送标题
	 * @param regId   设备对应的唯一极光ID
	 * @param content 推送内容
	 * @return
	 */
	@PutMapping(value = "/push/one")
	public CommonResult<Map<String, Object>> pushOne(
			@RequestParam(name = "regId", required = true) String regId,
			@RequestParam(name = "alert", required = true) String alert,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "extras", required = false) Map<String, String> extras) {
		return this.messageFeignClient.pushOne(regId, alert, title, extras);
	}
}
