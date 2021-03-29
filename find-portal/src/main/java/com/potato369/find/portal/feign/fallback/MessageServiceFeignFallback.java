package com.potato369.find.portal.feign.fallback;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.portal.feign.MessageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageServiceFeignFallback implements MessageService {

    @Override
    public CommonResult<MessageVO> all(Long userId, Integer pageNum, Integer pageSize) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

    @Override
    public CommonResult<MessageVO2> likes(Long userId, Integer pageNum, Integer pageSize) {
        log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
    }

	@Override
	public CommonResult<MessageVO3> messages(Long sendUserId, Long recipientUserId, Integer pageNum, Integer pageSize) {
		log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> send(Long sendUserId, Long recipientUserId, String content) {
		log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

	@Override
	public CommonResult<Map<String, Object>> allRead(Long recipientUserId) {
		log.error("进入了熔断器方法！！！");
        return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
	}

  
  /**
   * <pre>
   * 描述该方法的实现功能：
   * @see com.potato369.find.portal.feign.MessageService#delete(java.lang.Long, java.lang.Long)
   * </pre>
   */
  	
  @Override
  public CommonResult<Map<String, Object>> delete(Long recipientUserId, Long messageId) {
    log.error("进入了熔断器方法！！！");
    return CommonResult.failed("fallback; reason was: 服务忙，稍后重试！");
  }
}
