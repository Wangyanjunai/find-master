package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.DynamicExample;
import java.util.List;

import com.potato369.find.mbg.model.DynamicLocation;
import org.apache.ibatis.annotations.Param;

public interface DynamicMapper {
    long countByExample(DynamicExample example);

    int deleteByExample(DynamicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Dynamic record);

    int insertSelective(Dynamic record);
    
    int insertImport(Dynamic record);

    List<Dynamic> selectByExample(DynamicExample example);

    Dynamic selectByPrimaryKey(Long id);

    DynamicLocation selectByUserId(Long userId);

    int updateByExampleSelective(@Param("record") Dynamic record, @Param("example") DynamicExample example);

    int updateByExample(@Param("record") Dynamic record, @Param("example") DynamicExample example);

    int updateByPrimaryKeySelective(Dynamic record);

    int updateByPrimaryKey(Dynamic record);
}