package com.potato369.find.user.service.impl;

import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.common.enums.TagTypeEnum;
import com.potato369.find.mbg.mapper.TagMapper;
import com.potato369.find.mbg.model.Tag;
import com.potato369.find.mbg.model.TagExample;
import com.potato369.find.user.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
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
    public List<Tag> getAllHotValueTags() {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.setOrderByClause("hot_value desc");
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.tagMapperReader.selectByExample(tagExample).subList(0, 10);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> likesByTagName(String tagName) {
        return this.tagMapperReader.selectByKeywords(tagName);
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findTagByName(String name) {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andNameEqualTo(name);
        List<Tag> tagList = this.tagMapperReader.selectByExample(tagExample);
        if (tagList != null && !tagList.isEmpty()) {
            return tagList.get(0);
        }
        return null;
    }

    @Override
    public Long saveTag(Tag tag) {
        int row = this.getTagMapperWriter.insertSelective(tag);
        if (row > 0) {
            return tag.getId();
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public String findTagById(Long id) {
        Tag tag = this.tagMapperReader.selectByPrimaryKey(id);
        if (tag != null) {
            return tag.getName();
        }
        return null;
    }
}
