package com.potato369.find.user.service;

import com.potato369.find.mbg.model.Tag;

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
}
