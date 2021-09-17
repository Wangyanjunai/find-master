package com.potato369.find.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.dto.UpdateUserDTO;
import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.common.enums.TagTypeEnum;
import com.potato369.find.common.vo.UserVO;
import com.potato369.find.common.vo.UserVO4;
import com.potato369.find.mbg.mapper.IndustrysMapper;
import com.potato369.find.mbg.mapper.ProfessionsMapper;
import com.potato369.find.mbg.mapper.TagMapper;
import com.potato369.find.mbg.model.*;
import com.potato369.find.user.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class TagServiceImpl implements TagService {

    private TagMapper tagMapperReader;

    private TagMapper tagMapperWriter;

    private ProfessionsMapper professionsMapperReader;

    private IndustrysMapper industrysMapperReader;

    @Autowired
    public void setTagMapperReader(TagMapper tagMapperReader) {
        this.tagMapperReader = tagMapperReader;
    }

    @Autowired
    public void setTagMapperWriter(TagMapper tagMapperWriter) {
        this.tagMapperWriter = tagMapperWriter;
    }

    @Autowired
    public void setProfessionsMapperReader(ProfessionsMapper professionsMapperReader) {
        this.professionsMapperReader = professionsMapperReader;
    }

    @Autowired
    public void setIndustrysMapperReader(IndustrysMapper industrysMapperReader) {
        this.industrysMapperReader = industrysMapperReader;
    }

    @Override
    public List<Tag> getAllUndeleteTags() {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andReserveColumn01EqualTo(TagTypeEnum.SYS.getType());
        return this.tagMapperReader.selectByExample(tagExample);
    }

    @Override
    public List<Tag> getAllHotValueTags() {
        TagExample tagExample = new TagExample();
        tagExample.setDistinct(true);
        tagExample.setOrderByClause("hot_value desc");
        tagExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.tagMapperReader.selectByExample(tagExample).subList(0, 10);
    }

    @Override
    public List<Tag> likesByTagName(String tagName) {
        return this.tagMapperReader.selectByKeywords(tagName);
    }

    @Override
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
        int row = this.tagMapperWriter.insertSelective(tag);
        if (row > 0) {
            return tag.getId();
        } else {
            return null;
        }
    }

    @Override
    public String findTagById(Long id) {
        Tag tag = this.tagMapperReader.selectByPrimaryKey(id);
        if (tag != null) {
            return tag.getName();
        }
        return null;
    }

    /**
     * <pre>
     * 根据标签的名称获取标签Id
     * @param name 标签名称
     * @return 标签信息Id
     * </pre>
     */
    @Override
    public Long getTagIdByName(String name) {
        Long tagIdLong = null;
        if (!Objects.isNull(name)) {
            Tag tag = this.findTagByName(name);
            if (!Objects.isNull(tag)) {
                tagIdLong = tag.getId();
            } else {
                tag = new Tag();
                tag.setName(name);
                tag.setReserveColumn01(TagTypeEnum.USER.getType());
                tagIdLong = this.saveTag(tag);
            }
        }
        return tagIdLong;
    }

    /**
     * <pre>
     * 根据标签Id获取标签名称
     * @param id 标签id
     * @return 标签名称
     * </pre>
     */
    @Override
    public String getTagNameById(Long id) {
        return this.findTagById(id);
    }

    @Override
    public void setTags(User user, UpdateUserDTO userDTO) {
        if (StrUtil.isNotBlank(userDTO.getTag1())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag1());
            if (!Objects.equals(tagIdLong, user.getTag1())) {
                user.setTag1(tagIdLong);
            }
        } else {
            if ("".equals(userDTO.getTag1())) {
                user.setTag1(null);
            }
        }
        if (StrUtil.isNotBlank(userDTO.getTag2())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag2());
            if (!Objects.equals(tagIdLong, user.getTag2())) {
                user.setTag2(tagIdLong);
            }
        } else {
            if ("".equals(userDTO.getTag2())) {
                user.setTag2(null);
            }
        }
        if (StrUtil.isNotBlank(userDTO.getTag3())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag3());
            if (!Objects.equals(tagIdLong, user.getTag3())) {
                user.setTag3(tagIdLong);
            }
        } else {
            if ("".equals(userDTO.getTag3())) {
                user.setTag3(null);
            }
        }
        if (StrUtil.isNotBlank(userDTO.getTag4())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag4());
            if (!Objects.equals(tagIdLong, user.getTag4())) {
                user.setTag4(tagIdLong);
            }
        } else {
            if ("".equals(userDTO.getTag4())) {
                user.setTag4(null);
            }
        }
        if (StrUtil.isNotBlank(userDTO.getTag5())) {
            Long tagIdLong = this.getTagIdByName(userDTO.getTag5());
            if (!Objects.equals(tagIdLong, user.getTag5())) {
                user.setTag5(tagIdLong);
            }
        } else {
            if ("".equals(userDTO.getTag5())) {
                user.setTag5(null);
            }
        }
    }

    @Override
    public void setUserVO(UserVO userVO3, User user) {
        if (!Objects.isNull(userVO3) && !Objects.isNull(user)) {
            Professions professions = this.professionsMapperReader.selectByPrimaryKey(user.getProfessionId());
            if (!Objects.isNull(professions)) {
                userVO3.setProfession(professions.getName());
                Industrys industrys = this.industrysMapperReader.selectByPrimaryKey(professions.getIndustryId());
                if (!Objects.isNull(industrys)) {
                    userVO3.setIndustry(industrys.getName());
                }
            }
            userVO3.setTag1(this.getTagNameById(user.getTag1()));
            userVO3.setTag2(this.getTagNameById(user.getTag2()));
            userVO3.setTag3(this.getTagNameById(user.getTag3()));
            userVO3.setTag4(this.getTagNameById(user.getTag4()));
            userVO3.setTag5(this.getTagNameById(user.getTag5()));
        }
    }

    @Override
    public void setUserVO4(UserVO4 userVO4, User user) {
        if (!Objects.isNull(userVO4) && !Objects.isNull(user)) {
            Professions professions = this.professionsMapperReader.selectByPrimaryKey(user.getProfessionId());
            if (!Objects.isNull(professions)) {
                userVO4.setProfession(professions.getName());
                Industrys industrys = this.industrysMapperReader.selectByPrimaryKey(professions.getIndustryId());
                if (!Objects.isNull(industrys)) {
                    userVO4.setIndustry(industrys.getName());
                }
            }
            userVO4.setTag1(this.getTagNameById(user.getTag1()));
            userVO4.setTag2(this.getTagNameById(user.getTag2()));
            userVO4.setTag3(this.getTagNameById(user.getTag3()));
            userVO4.setTag4(this.getTagNameById(user.getTag4()));
            userVO4.setTag5(this.getTagNameById(user.getTag5()));
        }
    }

    @Override
    public void updateByTagId(Long tagId) {
        if (!Objects.isNull(tagId)) {
            Tag tag = this.tagMapperReader.selectByPrimaryKey(tagId);
            if (!Objects.isNull(tag)) {
                tag.setHotValue(tag.getHotValue() + 1);
                tag.setUpdatedTime(new Date());
                this.tagMapperWriter.updateByPrimaryKeySelective(tag);
            }
        }
    }
}
