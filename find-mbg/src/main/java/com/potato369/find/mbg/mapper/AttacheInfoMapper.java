package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.AttacheInfo;
import com.potato369.find.mbg.model.AttacheInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AttacheInfoMapper {
    long countByExample(AttacheInfoExample example);

    int deleteByExample(AttacheInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AttacheInfo record);

    int insertSelective(AttacheInfo record);

    List<AttacheInfo> selectByExample(AttacheInfoExample example);

    AttacheInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AttacheInfo record, @Param("example") AttacheInfoExample example);

    int updateByExample(@Param("record") AttacheInfo record, @Param("example") AttacheInfoExample example);

    int updateByPrimaryKeySelective(AttacheInfo record);

    int updateByPrimaryKey(AttacheInfo record);
}