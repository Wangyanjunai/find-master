package com.potato369.find.user.service.impl;

import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.common.enums.TagTypeEnum;
import com.potato369.find.mbg.mapper.TagMapper;
import com.potato369.find.mbg.model.Tag;
import com.potato369.find.mbg.model.TagExample;
import com.potato369.find.user.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    private TagMapper tagMapperReader;

    private TagMapper getTagMapperWriter;

    @Autowired
    public void setTagMapperReader(TagMapper tagMapperReader) {
        this.tagMapperReader = tagMapperReader;
    }

    @Autowired
    public void setGetTagMapperWriter(TagMapper getTagMapperWriter) {
        this.getTagMapperWriter = getTagMapperWriter;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> getAllUndeleteTags() {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andReserveColumn01EqualTo(TagTypeEnum.SYS.getType());
        return this.tagMapperReader.selectByExample(tagExample);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findTagByName(String name) {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andNameEqualTo(name);
        return this.tagMapperReader.selectByExample(tagExample).get(0);
    }

    @Override
    @Transactional(readOnly = false)
    public Long saveTag(Tag tag) {
        int row = this.getTagMapperWriter.insertSelective(tag);
        if (row > 0) {
            return tag.getId();
        } else {
            return null;
        }
    }
}
