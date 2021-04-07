package com.potato369.find.dynamic.feign.fallback;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.dynamic.feign.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Slf4j
@Component
public class MessageServiceFeignFallback implements MessageService {

	@Override
	public CommonResult<Map<String, Object>> pushAll(
			@RequestParam(name = "alert", required = true) String alert, 
			@RequestParam(name = "title", required = false) String title) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> pushOne(
			@RequestParam(name = "regId", required = true) String regId,
			@RequestParam(name = "alert", required = true) String alert,
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "extras", required = false) Map<String, String> extras) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

}
