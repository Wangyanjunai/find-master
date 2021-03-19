package com.potato369.find.portal.feign.fallback;

import org.springframework.stereotype.Component;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.portal.feign.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageServiceFeignFallback implements MessageService {

    @Override
    public CommonResult<MessageVO> findAll(Long userId, Integer pageNum, Integer pageSize) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<MessageVO2> findLikes(Long userId, Integer pageNum, Integer pageSize) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

}
