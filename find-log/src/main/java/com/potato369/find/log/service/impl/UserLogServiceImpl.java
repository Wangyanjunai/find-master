package com.potato369.find.log.service.impl;


import com.potato369.find.log.service.UserLogService;
import com.potato369.find.mbg.mapper.OperateRecordMapper;
import com.potato369.find.mbg.model.OperateRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLogServiceImpl implements UserLogService{
    
    private OperateRecordMapper operateRecordMapperWriter;

    @Autowired
	public void setOperateRecordMapperWriter(OperateRecordMapper operateRecordMapperWriter) {
		this.operateRecordMapperWriter = operateRecordMapperWriter;
	}

	@Override
	@Transactional(readOnly = false)
	public int record(OperateRecord operateRecord) {
		return this.operateRecordMapperWriter.insertSelective(operateRecord);
	}
}
