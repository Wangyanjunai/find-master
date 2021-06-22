package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.AttacheInfo;
import com.potato369.find.mbg.model.AttacheInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AttacheInfoMapper {
    long countByExample(AttacheInfoExample example);

    int deleteByExample(AttacheInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AttacheInfo record);

    int insertSelective(AttacheInfo record);

    List<AttacheInfo> selectByExample(AttacheInfoExample example);

    AttacheInfo selectByPrimaryKey(Long id);

    List<AttacheInfo> getAttacheInfoByUserId(@Param("userId") Long userId);

    int updateByExampleSelective(@Param("record") AttacheInfo record, @Param("example") AttacheInfoExample example);

    int updateByExample(@Param("record") AttacheInfo record, @Param("example") AttacheInfoExample example);

    int updateByPrimaryKeySelective(AttacheInfo record);

    int updateByPrimaryKey(AttacheInfo record);
}