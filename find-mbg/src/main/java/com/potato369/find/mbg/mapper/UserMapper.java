package com.potato369.find.mbg.mapper;

import com.potato369.find.common.dto.DynamicLocationDTO;
import com.potato369.find.mbg.model.LookInfoParam;
import com.potato369.find.mbg.model.User;
import com.potato369.find.mbg.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    int insertImport(User record);

    List<User> selectByExample(UserExample example);

    List<User> selectByUserIdList(@Param("param") DynamicLocationDTO userIdList);

    List<User> selectVIPExpireUser();

    User selectByPrimaryKey(Long id);

    List<User> selectLookUserList(@Param("param") LookInfoParam lookInfoParam);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}