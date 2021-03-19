package com.potato369.find.dynamic.annotation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.potato369.find.dynamic.DynamicServiceApplication;

//参考：https://www.cnblogs.com/lingyejun/p/9941350.html 
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicServiceApplication.class)
public class AnnotationTest {
	
	@Autowired
    private LoggerApply loggerApply;
 
    @Test
    public void testAnnotationLogger() {
        try {
            loggerApply.lingLogger("Blog Home");
        } catch (Exception e) {
            System.out.println("a exception be there");
        }
    }
}
