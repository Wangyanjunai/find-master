package com.potato369.find.dynamic.feign;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.dynamic.feign.fallback.UserLogServiceFeignFallback;
import com.potato369.find.mbg.model.OperateRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

//调用记录用户操作日志openFeignClient
@FeignClient(name = "log-service", fallback = UserLogServiceFeignFallback.class)
public interface UserLogService {

    // 调用记录用户操作日志接口
    @PostMapping("/find/v1/log/{id}/record.do")
    CommonResult<Map<String, Object>> record(@PathVariable(name = "id") Long userIdLong, @RequestBody OperateRecord operateRecord);
}
