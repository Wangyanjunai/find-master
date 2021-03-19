package com.potato369.find.dynamic.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.dynamic.feign.fallback.MessageServiceFeignFallback;

@FeignClient(name = "message-service", fallback = MessageServiceFeignFallback.class)
public interface MessageService {

	@PutMapping("/find/v1/message/push/all.do")
	CommonResult<Map<String, Object>> pushAll(
			@RequestParam(name = "alert", required = true) String alert,
			@RequestParam(name = "title", required = false) String title);
	
	@PutMapping("/find/v1/message/push/one.do")
	public CommonResult<Map<String, Object>> pushOne(
			@RequestParam(name = "regId", required = true) String regId,
			@RequestParam(name = "alert", required = true) String alert,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "extras", required = false) Map<String, String> extras);
}
