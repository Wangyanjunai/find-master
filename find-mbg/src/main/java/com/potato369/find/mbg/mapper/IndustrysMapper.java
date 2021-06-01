package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.Industrys;
import com.potato369.find.mbg.model.IndustrysExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IndustrysMapper {
    long countByExample(IndustrysExample example);

    int deleteByExample(IndustrysExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Industrys record);

    int insertSelective(Industrys record);

    List<Industrys> selectByExample(IndustrysExample example);

    Industrys selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Industrys record, @Param("example") IndustrysExample example);

    int updateByExample(@Param("record") Industrys record, @Param("example") IndustrysExample example);

    int updateByPrimaryKeySelective(Industrys record);

    int updateByPrimaryKey(Industrys record);
}