package com.potato369.find.log.service.impl;


import com.potato369.find.log.dao.UserDaoUseJdbcTemplate;
import com.potato369.find.log.service.UserLogService;
import com.potato369.find.mbg.mapper.OperateRecordMapper;
import com.potato369.find.mbg.model.OperateRecord;
import com.potato369.find.mbg.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLogServiceImpl implements UserLogService{
	
    private UserDaoUseJdbcTemplate userDaoUseJdbcTemplate;
    
    private OperateRecordMapper operateRecordMapperWrite;

    @Autowired
	public void setUserDaoUseJdbcTemplate(UserDaoUseJdbcTemplate userDaoUseJdbcTemplate) {
		this.userDaoUseJdbcTemplate = userDaoUseJdbcTemplate;
	}

    @Autowired
	public void setOperateRecordMapperWrite(OperateRecordMapper operateRecordMapperWrite) {
		this.operateRecordMapperWrite = operateRecordMapperWrite;
	}

	@Override
	@Transactional(readOnly = false)
	public int record(Long id, OperateRecord operateRecord) {
		User currentInstance = this.userDaoUseJdbcTemplate.getById(id);
		if (currentInstance != null) {
			operateRecord.setUserId(currentInstance.getId());
		} else {
			operateRecord.setUserId(id);
		}
		return this.operateRecordMapperWrite.insert(operateRecord);
	}
}
