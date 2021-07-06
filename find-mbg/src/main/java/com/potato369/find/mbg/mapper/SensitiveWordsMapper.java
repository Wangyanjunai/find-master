package com.potato369.find.mbg.mapper;

import com.potato369.find.mbg.model.SensitiveWords;
import com.potato369.find.mbg.model.SensitiveWordsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensitiveWordsMapper {
    long countByExample(SensitiveWordsExample example);

    int deleteByExample(SensitiveWordsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SensitiveWords record);

    int insertSelective(SensitiveWords record);

    List<SensitiveWords> selectByExampleWithBLOBs(SensitiveWordsExample example);

    List<SensitiveWords> selectByExample(SensitiveWordsExample example);

    SensitiveWords selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SensitiveWords record, @Param("example") SensitiveWordsExample example);

    int updateByExampleWithBLOBs(@Param("record") SensitiveWords record, @Param("example") SensitiveWordsExample example);

    int updateByExample(@Param("record") SensitiveWords record, @Param("example") SensitiveWordsExample example);

    int updateByPrimaryKeySelective(SensitiveWords record);

    int updateByPrimaryKeyWithBLOBs(SensitiveWords record);

    int updateByPrimaryKey(SensitiveWords record);
}