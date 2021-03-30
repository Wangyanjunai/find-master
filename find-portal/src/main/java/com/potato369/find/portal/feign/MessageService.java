package com.potato369.find.portal.feign;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.vo.MessageVO;
import com.potato369.find.common.vo.MessageVO2;
import com.potato369.find.common.vo.MessageVO3;
import com.potato369.find.portal.feign.fallback.MessageServiceFeignFallback;

//消息中心微服务调用feignClient
@FeignClient(name = "message-service", fallback = MessageServiceFeignFallback.class)
public interface MessageService {

    @GetMapping(value = "/find/v1/message/{id}/all.do")
    CommonResult<MessageVO> all(@PathVariable(name = "id") Long userId,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize);

    @GetMapping(value = "/find/v1/message/{id}/likes.do")
    CommonResult<MessageVO2> likes(@PathVariable(name = "id") Long userId,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize);

    @GetMapping(value = "/find/v1/message/{id1}/{id2}/messages.do")
    CommonResult<MessageVO3> messages(@PathVariable(name = "id1") Long sendUserId,
                                      @PathVariable(name = "id2") Long recipientUserId,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize);

    @PostMapping(value = "/find/v1/message/{id1}/{id2}/send.do")
    CommonResult<Map<String, Object>> send(@PathVariable(name = "id1") Long sendUserId,
                                           @PathVariable(name = "id2") Long recipientUserId,
                                           @RequestParam(name = "messageId", required = false) Long messageId,
                                           @RequestParam(name = "content", required = false) String content);

    @PutMapping(value = "/find/v1/message/{id}/updateAll.do")
    CommonResult<Map<String, Object>> allRead(@PathVariable(name = "id") Long recipientUserId);

    @DeleteMapping(value = "/find/v1/message/{id1}/delete.do")
    CommonResult<Map<String, Object>> delete(@PathVariable(name = "id1") Long recipientUserId,
                                             @RequestParam(name = "id2") Long sendUserId);

    @DeleteMapping(value = "/find/v1/message/{id}/deleteLikes.do")
    CommonResult<Map<String, Object>> deleteLikes(@PathVariable(name = "id") Long recipientUserId,
                                                  @RequestParam(name = "messageId") Long messageId);
}
