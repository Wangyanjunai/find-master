package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ApplicationSetting;
import com.potato369.find.mbg.model.ApplicationSettingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplicationSettingMapper {
    long countByExample(ApplicationSettingExample example);

    int deleteByExample(ApplicationSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ApplicationSetting record);

    int insertSelective(ApplicationSetting record);

    List<ApplicationSetting> selectByExample(ApplicationSettingExample example);

    ApplicationSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ApplicationSetting record, @Param("example") ApplicationSettingExample example);

    int updateByExample(@Param("record") ApplicationSetting record, @Param("example") ApplicationSettingExample example);

    int updateByPrimaryKeySelective(ApplicationSetting record);

    int updateByPrimaryKey(ApplicationSetting record);
}