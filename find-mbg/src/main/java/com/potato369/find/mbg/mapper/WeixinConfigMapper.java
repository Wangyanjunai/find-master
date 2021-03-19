package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.WeixinConfig;
import com.potato369.find.mbg.model.WeixinConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeixinConfigMapper {
    long countByExample(WeixinConfigExample example);

    int deleteByExample(WeixinConfigExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WeixinConfig record);

    int insertSelective(WeixinConfig record);

    List<WeixinConfig> selectByExample(WeixinConfigExample example);

    WeixinConfig selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WeixinConfig record, @Param("example") WeixinConfigExample example);

    int updateByExample(@Param("record") WeixinConfig record, @Param("example") WeixinConfigExample example);

    int updateByPrimaryKeySelective(WeixinConfig record);

    int updateByPrimaryKey(WeixinConfig record);
}