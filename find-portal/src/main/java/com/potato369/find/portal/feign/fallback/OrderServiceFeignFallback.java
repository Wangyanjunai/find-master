package com.potato369.find.portal.feign.fallback;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.portal.feign.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.Map;

@Component
@Slf4j
public class OrderServiceFeignFallback implements OrderService {

    @Override
    public CommonResult<Map<String, Object>> list(Long userId) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

	@Override
	public CommonResult<Map<String, Object>> createOrder(Long userId, @Valid OrderDTO orderDTO) {
		log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}
}
