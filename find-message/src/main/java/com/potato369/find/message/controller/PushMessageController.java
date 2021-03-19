package com.potato369.find.message.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.message.config.bean.PushBean;
import com.potato369.find.message.service.JiGuangPushService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "消息模块消息推送控制器类", consumes = "消息模块消息推送控制器类")
@RestController
@RequestMapping("/v1/message")
public class PushMessageController {

	private JiGuangPushService jiGuangPushService;

	@Autowired
	public void setJiGuangPushService(JiGuangPushService jiGuangPushService) {
		this.jiGuangPushService = jiGuangPushService;
	}

	/**
	 * 群推，广播：广播推送也就是只要是拥有该appKey下载的安装程序的用户（即所有regId），都会受到信息
	 * 
	 * @param alert 推送内容
	 * @param title 推送标题
	 * @return
	 */
	@PutMapping(value = "/push/all.do")
	public CommonResult<Map<String, Object>> pushAll(
			@RequestParam(name = "alert") @ApiParam(name = "alert", value = "推送内容", required = true, example = "觅鹿通知内容") String alert,
			@RequestParam(name = "title", required = false) @ApiParam(name = "title", value = "推送标题", example = "觅鹿广播通知") String title) {
		PushBean pushBean = new PushBean();
		pushBean.setAlert(alert);
		pushBean.setTitle(title);
		boolean flag = this.jiGuangPushService.pushAndroid(pushBean);
		Map<String, Object> result = new HashMap<>();
		String message = "";
		log.info("flag={}", flag);
		if (flag) {
			message = "OK";
		} else {
			message = "ERROR";
		}
		result.put("PUSH", message);
		return CommonResult.success(result);
	}

	/**
	 * 单独对regId进行推送
	 * 
	 * @param title   推送标题
	 * @param regId   设备对应的唯一极光ID
	 * @param content 推送内容
	 * @return
	 */
	@PutMapping(value = "/push/one.do")
	public CommonResult<Map<String, Object>> pushOne(
			@RequestParam(name = "regId") @ApiParam(name = "regId", value = "推送唯一标识", required = true, example = "1234567890") String regId,
			@RequestParam(name = "alert") @ApiParam(name = "alert", value = "推送内容", required = true, example = "觅鹿通知内容") String alert,
			@RequestParam(name = "title", required = false) @ApiParam(name = "title", value = "通知标题", example = "觅鹿标题") String title,
			@RequestParam(name = "extras", required = false) @ApiParam(name = "extras", value = "推送其它参数") Map<String, String> extras) {
		PushBean pushBean = new PushBean();
		pushBean.setAlert(alert);
		pushBean.setTitle(title);
		pushBean.setExtras(extras);
		boolean flag = this.jiGuangPushService.pushAndroid(pushBean, regId);
		Map<String, Object> result = new HashMap<>();
		String message = "";
		log.info("flag={}", flag);
		if (flag) {
			message = "OK";
		} else {
			message = "ERROR";
		}
		result.put("PUSH", message);
		return CommonResult.success(result);
	}
}
