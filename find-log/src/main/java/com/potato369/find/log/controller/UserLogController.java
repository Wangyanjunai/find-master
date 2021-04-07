package com.potato369.find.log.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.enums.OperateRecordStatusEnum;
import com.potato369.find.log.service.UserLogService;
import com.potato369.find.mbg.model.OperateRecord;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "日志模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/v1/log")
public class UserLogController {
	
	private UserLogService userLogService;

	@Autowired
    public void setUserLogService(UserLogService userLogService) {
		this.userLogService = userLogService;
	}

	//记录用户操作日志接口
    @PostMapping(value = "/{id}/record.do")
    public CommonResult<Map<String, Object>> record(@PathVariable(name = "id", required = true) Long userIdLong, @RequestBody OperateRecord operateRecord) {
        if (log.isDebugEnabled()) {
            log.debug("开始记录用户操作日志");
        }
        Map<String, Object> result = new HashMap<>();
        try {
            operateRecord.setStatus(OperateRecordStatusEnum.Success.getCode().toString());
            result.put("message", "OK");
            return CommonResult.success(result);
        } catch (Exception e) {
        	operateRecord.setStatus(OperateRecordStatusEnum.Fail.getCode().toString());
            log.error("记录用户操作日志出错", e);
            return CommonResult.failed();
        } finally {
        	try {
        		this.userLogService.record(userIdLong, operateRecord);
			} catch (Exception e2) {
				log.error("记录用户操作日志出错", e2);
			}
            if (log.isDebugEnabled()) {
                log.debug("结束记录用户操作日志");
            }
        }
    }
}
