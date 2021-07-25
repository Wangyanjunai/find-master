package com.potato369.find.log.service;

import com.potato369.find.mbg.model.OperateRecord;

public interface UserLogService {

    /**
     * 记录操作日志
     *
     * @param operateRecord
     */
    int record(OperateRecord operateRecord);
}
