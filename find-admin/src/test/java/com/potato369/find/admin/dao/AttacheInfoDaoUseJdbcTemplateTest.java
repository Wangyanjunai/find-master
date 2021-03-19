package com.potato369.find.admin.dao;

import com.potato369.find.admin.AdminServiceApplication;
import com.potato369.find.mbg.model.AttacheInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <pre>
 * @PackageName com.potato369.find.admin.dao
 * @ClassName AttacheInfoDaoUseJdbcTemplateTest
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/26 10:03
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminServiceApplication.class)
public class AttacheInfoDaoUseJdbcTemplateTest {

    private AttacheInfoDaoUseJdbcTemplate attacheInfoDaoUseJdbcTemplate;

    @Autowired
    public void setAttacheInfoDaoUseJdbcTemplate(AttacheInfoDaoUseJdbcTemplate attacheInfoDaoUseJdbcTemplate) {
        this.attacheInfoDaoUseJdbcTemplate = attacheInfoDaoUseJdbcTemplate;
    }

    @Test
    public void getByIdTest() {
        Long id = 1L;
        AttacheInfo attacheInfo = this.attacheInfoDaoUseJdbcTemplate.getById(id);
        Assert.assertNull(attacheInfo);
    }


}
