package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ApplicationRecord;
import com.potato369.find.mbg.model.ApplicationRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicationRecordMapper {
    long countByExample(ApplicationRecordExample example);

    int deleteByExample(ApplicationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApplicationRecord record);

    int insertSelective(ApplicationRecord record);

    List<ApplicationRecord> selectByExample(ApplicationRecordExample example);

    ApplicationRecord selectByPrimaryKey(Long id);

    int selectCountDateByUserId(Long userId);

    int updateByExampleSelective(@Param("record") ApplicationRecord record, @Param("example") ApplicationRecordExample example);

    int updateByExample(@Param("record") ApplicationRecord record, @Param("example") ApplicationRecordExample example);

    int updateByPrimaryKeySelective(ApplicationRecord record);

    int updateByPrimaryKey(ApplicationRecord record);
}