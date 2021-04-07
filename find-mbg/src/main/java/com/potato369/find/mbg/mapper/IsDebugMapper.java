package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.IsDebug;
import com.potato369.find.mbg.model.IsDebugExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IsDebugMapper {
    long countByExample(IsDebugExample example);

    int deleteByExample(IsDebugExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IsDebug record);

    int insertSelective(IsDebug record);

    List<IsDebug> selectByExample(IsDebugExample example);

    IsDebug selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IsDebug record, @Param("example") IsDebugExample example);

    int updateByExample(@Param("record") IsDebug record, @Param("example") IsDebugExample example);

    int updateByPrimaryKeySelective(IsDebug record);

    int updateByPrimaryKey(IsDebug record);
}