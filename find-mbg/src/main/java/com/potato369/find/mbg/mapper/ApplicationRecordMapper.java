package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ApplicationRecord;
import com.potato369.find.mbg.model.ApplicationRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationRecordMapper {
    long countByExample(ApplicationRecordExample example);

    int deleteByExample(ApplicationRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApplicationRecord record);

    int insertSelective(ApplicationRecord record);

    List<ApplicationRecord> selectByExample(ApplicationRecordExample example);

    ApplicationRecord selectByPrimaryKey(Long id);

    List<ApplicationRecord> selectByUserId(@Param("userId") Long userId);

    int selectCountDataByUserId(@Param("userId") Long userId);

    /**
     * 查询申请加微信者当天申请加被申请加微信者次数
     *
     * @param applicantUserId  申请加微信者用户id
     * @param applicantsUserId 被申请加微信者用户id
     * @return
     */
    int countByUserId(@Param("applicantUserId") Long applicantUserId, @Param("applicantsUserId") Long applicantsUserId);

    int updateByExampleSelective(@Param("record") ApplicationRecord record, @Param("example") ApplicationRecordExample example);

    int updateByExample(@Param("record") ApplicationRecord record, @Param("example") ApplicationRecordExample example);

    int updateByPrimaryKeySelective(ApplicationRecord record);

    int updateByPrimaryKey(ApplicationRecord record);
}