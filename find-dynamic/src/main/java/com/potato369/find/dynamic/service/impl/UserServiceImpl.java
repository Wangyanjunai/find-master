package com.potato369.find.dynamic.service.impl;

import com.potato369.find.common.enums.UserStatusEnum;
import com.potato369.find.dynamic.service.UserService;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.User;
import com.potato369.find.mbg.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class UserServiceImpl implements UserService {

    private UserMapper userMapperReader;

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long userId) {
        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(userId).andStatusEqualTo(UserStatusEnum.Normal.getStatus());
        List<User> users = this.userMapperReader.selectByExample(example);
        if (!Objects.isNull(users) && !users.isEmpty()) {
            return users.get(0);
        }
        return null;
    }

}
