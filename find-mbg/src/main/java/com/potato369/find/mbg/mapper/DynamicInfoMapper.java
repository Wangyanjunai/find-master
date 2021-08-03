package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicInfoMapper {

    long countByExample(DynamicInfoExample example);

    int deleteByExample(DynamicInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DynamicInfo record);

    int insertSelective(DynamicInfo record);

    int insertImport(DynamicInfo record);

    List<DynamicInfo> selectByExampleWithBLOBs(DynamicInfoExample example);

    List<DynamicInfo> selectByExample(DynamicInfoExample example);

    DynamicInfo selectByPrimaryKey(Long id);

    List<DynamicInfoData> selectDynamicInfoData(@Param("param") DynamicInfoParam param);

    List<DynamicInfoData> selectMyDynamicInfoData(Long userId);

    List<HotTopic> selectHotTopic();

    List<HotTopic> selectHotTopicTitle();

    String getFileNameByUserId(@Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") DynamicInfo record, @Param("example") DynamicInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") DynamicInfo record, @Param("example") DynamicInfoExample example);

    int updateByExample(@Param("record") DynamicInfo record, @Param("example") DynamicInfoExample example);

    int updateByPrimaryKeySelective(DynamicInfo record);

    int updateByPrimaryKeyWithBLOBs(DynamicInfo record);

    int updateByPrimaryKey(DynamicInfo record);
}