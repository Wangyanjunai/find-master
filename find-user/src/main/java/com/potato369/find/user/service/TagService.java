package com.potato369.find.user.service;

import com.potato369.find.mbg.model.Tag;

import java.util.List;

public interface TagService {

    List<Tag> getAllUndeleteTags();

    Tag findTagByName(String name);

    Long saveTag(Tag tag);

    String findTagById(Long id);
}
