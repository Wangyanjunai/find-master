package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.AlipayConfig;
import com.potato369.find.mbg.model.AlipayConfigExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlipayConfigMapper {
    long countByExample(AlipayConfigExample example);

    int deleteByExample(AlipayConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AlipayConfig record);

    int insertSelective(AlipayConfig record);

    List<AlipayConfig> selectByExampleWithBLOBs(AlipayConfigExample example);

    List<AlipayConfig> selectByExample(AlipayConfigExample example);

    AlipayConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AlipayConfig record, @Param("example") AlipayConfigExample example);

    int updateByExampleWithBLOBs(@Param("record") AlipayConfig record, @Param("example") AlipayConfigExample example);

    int updateByExample(@Param("record") AlipayConfig record, @Param("example") AlipayConfigExample example);

    int updateByPrimaryKeySelective(AlipayConfig record);

    int updateByPrimaryKeyWithBLOBs(AlipayConfig record);

    int updateByPrimaryKey(AlipayConfig record);
}