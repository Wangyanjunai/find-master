package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.ScreenSetting;
import com.potato369.find.mbg.model.ScreenSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScreenSettingMapper {
    long countByExample(ScreenSettingExample example);

    int deleteByExample(ScreenSettingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ScreenSetting record);

    int insertSelective(ScreenSetting record);

    List<ScreenSetting> selectByExample(ScreenSettingExample example);

    ScreenSetting selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ScreenSetting record, @Param("example") ScreenSettingExample example);

    int updateByExample(@Param("record") ScreenSetting record, @Param("example") ScreenSettingExample example);

    int updateByPrimaryKeySelective(ScreenSetting record);

    int updateByPrimaryKey(ScreenSetting record);
}