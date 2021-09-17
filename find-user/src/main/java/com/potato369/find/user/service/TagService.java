package com.potato369.find.user.service;

import com.potato369.find.common.dto.UpdateUserDTO;
import com.potato369.find.common.vo.UserVO;
import com.potato369.find.common.vo.UserVO4;
import com.potato369.find.mbg.model.Tag;
import com.potato369.find.mbg.model.User;

import java.util.List;

public interface TagService {

    List<Tag> getAllUndeleteTags();

    //获取所有的热门标签列表
    List<Tag> getAllHotValueTags();

    //模糊搜索标签列表
    List<Tag> likesByTagName(String tagName);

    Tag findTagByName(String name);

    Long saveTag(Tag tag);

    String findTagById(Long id);
    
    Long getTagIdByName(String name);
    
    String getTagNameById(Long id);
    
    void setTags(User user, UpdateUserDTO userDTO);
    
    void setUserVO(UserVO userVO3, User user);
    
    void setUserVO4(UserVO4 userVO4, User user);
    
    void updateByTagId(Long tagId);
    
}
