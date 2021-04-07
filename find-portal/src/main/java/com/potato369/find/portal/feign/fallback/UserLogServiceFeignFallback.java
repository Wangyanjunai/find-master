package com.potato369.find.portal.feign.fallback;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.mbg.model.OperateRecord;
import com.potato369.find.portal.feign.UserLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class UserLogServiceFeignFallback implements UserLogService {

	@Override
	public CommonResult<Map<String, Object>> record(Long userIdLong, OperateRecord operateRecord) {
		log.error("进入了熔断器方法！！！");
		return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}
}
