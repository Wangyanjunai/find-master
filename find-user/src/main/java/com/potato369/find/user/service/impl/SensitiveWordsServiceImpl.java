package com.potato369.find.user.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.enums.DeleteStatusEnum;
import com.potato369.find.mbg.mapper.SensitiveWordsMapper;
import com.potato369.find.mbg.model.SensitiveWords;
import com.potato369.find.mbg.model.SensitiveWordsExample;
import com.potato369.find.user.service.SensitiveWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

    private SensitiveWordsMapper sensitiveWordsMapperReader;

    @Autowired
    public void setSensitiveWordsMapperReader(SensitiveWordsMapper sensitiveWordsMapperReader) {
        this.sensitiveWordsMapperReader = sensitiveWordsMapperReader;
    }

    @Override
    @Transactional(readOnly = true)
    public SensitiveWords checkHasSensitiveWords(String content) {
        SensitiveWordsExample sensitiveWordsExample = new SensitiveWordsExample();
        sensitiveWordsExample.createCriteria().andDeleteStatusEqualTo(DeleteStatusEnum.NO.getStatus());
        List<SensitiveWords> sensitiveWordsList = this.sensitiveWordsMapperReader.selectByExampleWithBLOBs(sensitiveWordsExample);
        for (SensitiveWords sensitiveWords : sensitiveWordsList) {
            if (StrUtil.isNotEmpty(content)) {
                if (content.contains(sensitiveWords.getContent())) {
                    return sensitiveWords;
                }
            }
        }
        return null;
    }
}
