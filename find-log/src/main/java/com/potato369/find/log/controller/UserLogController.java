package com.potato369.find.log.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.dto.OperateRecordDTO;
import com.potato369.find.log.service.UserLogService;
import com.potato369.find.mbg.model.OperateRecord;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public CommonResult<Map<String, Object>> record(
            @PathVariable(name = "id") Long userIdLong,
            OperateRecordDTO operateRecordDTO) {
        if (log.isDebugEnabled()) {
            log.debug("开始记录用户操作日志");
        }
        Map<String, Object> result = new ConcurrentHashMap<>();
        result.put("LOGGER", "ERROR");
        try {
            OperateRecord operateRecord = new OperateRecord();
            BeanUtils.copyProperties(operateRecordDTO, operateRecord);
            int row = this.userLogService.record(userIdLong, operateRecord);
            if (row > 0) {
                result.put("LOGGER", "OK");
            }
            return CommonResult.success(result, ResultCode.SUCCESS.getMessage());
        } catch (Exception e) {
            log.error("记录用户操作日志出错", e);
            return CommonResult.failed(result, ResultCode.FAILED);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束记录用户操作日志");
            }
        }
    }
}
