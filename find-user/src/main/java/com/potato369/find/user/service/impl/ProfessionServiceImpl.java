package com.potato369.find.user.service.impl;

import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.mbg.mapper.IndustrysMapper;
import com.potato369.find.mbg.mapper.ProfessionsMapper;
import com.potato369.find.mbg.model.Industrys;
import com.potato369.find.mbg.model.IndustrysExample;
import com.potato369.find.mbg.model.Professions;
import com.potato369.find.mbg.model.ProfessionsExample;
import com.potato369.find.user.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <pre>
 * @PackageName com.potato369.find.user.service.impl
 * @ClassName ProfessionServiceImpl
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/02 14:30
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Service
public class ProfessionServiceImpl implements ProfessionService {

    private IndustrysMapper industrysMapperReader;

    private ProfessionsMapper professionsMapperReader;

    @Autowired
    public void setIndustrysMapperReader(IndustrysMapper industrysMapperReader) {
        this.industrysMapperReader = industrysMapperReader;
    }

    @Autowired
    public void setProfessionsMapperReader(ProfessionsMapper professionsMapperReader) {
        this.professionsMapperReader = professionsMapperReader;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Industrys> getAllUnDeleteIndustrys() {
        IndustrysExample industrysExample = new IndustrysExample();
        industrysExample.setDistinct(true);
        industrysExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.industrysMapperReader.selectByExample(industrysExample);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professions> getAllUndeleteProfessions() {
        ProfessionsExample professionsExample = new ProfessionsExample();
        professionsExample.setDistinct(true);
        professionsExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        return this.professionsMapperReader.selectByExample(professionsExample);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Professions> getProfessionsByIndustrysId(Long id) {
        ProfessionsExample professionsExample = new ProfessionsExample();
        professionsExample.setDistinct(true);
        professionsExample.createCriteria()
                .andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus())
                .andIndustryIdEqualTo(id);
        return this.professionsMapperReader.selectByExample(professionsExample);
    }
}
