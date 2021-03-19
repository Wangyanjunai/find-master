package com.potato369.find.portal.feign;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.portal.feign.fallback.OrderServiceFeignFallback;

//订单中心微服务调用feignClient
@FeignClient(name = "order-service", fallback = OrderServiceFeignFallback.class)
public interface OrderService {
	
	@GetMapping(value = "/find/v1/order/{id}/product/list.do")
	CommonResult<Map<String, Object>> list(@PathVariable("id") Long userId);
	
	@PostMapping(value = "/find/v1/order/{id}/mobile/create.do", produces = {"application/json;charset=utf-8"})
	CommonResult<Map<String, Object>> createOrder(@PathVariable("id") Long userId, @RequestBody @Valid OrderDTO orderDTO);
}
