package com.potato369.find.user.service.impl;

import java.util.List;

import com.potato369.find.common.enums.DeleteStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.potato369.find.mbg.mapper.TagMapper;
import com.potato369.find.mbg.model.Tag;
import com.potato369.find.mbg.model.TagExample;
import com.potato369.find.user.service.TagService;

@Service
public class TagServiceImpl implements TagService {

    private TagMapper tagMapperReader;

    @Autowired
    public void setTagMapperReader(TagMapper tagMapperReader) {
        this.tagMapperReader = tagMapperReader;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllUndeleteTags() {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.tagMapperReader.selectByExample(tagExample);
    }

}
