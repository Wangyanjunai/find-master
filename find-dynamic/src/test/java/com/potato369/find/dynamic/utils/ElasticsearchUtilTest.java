package com.potato369.find.dynamic.utils;

//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.potato369.find.dynamic.DynamicServiceApplication;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DynamicServiceApplication.class)
//public class ElasticsearchUtilTest {
//
//    @Test
//    public void isIndexExistTest() {
//        boolean d = ElasticsearchUtil.isIndexExist("user_index");
//        assertTrue(d);
//    }
//
//    @Test
//    public void createIndexTest() {
//        boolean d = ElasticsearchUtil.createIndex("user_index2233");
//        assertTrue(d);
//    }
//
//    @Test
//    public void deleteIndexTest() {
//        boolean d = ElasticsearchUtil.deleteIndex("user_index2233");
//        assertTrue(d);
//    }
//
//    @Test
//    public void searchDataByIdTest() {
//        Map<String, Object> resultMap = ElasticsearchUtil.searchDataById("dynamic_index", "doc", "1", "");
//        log.info("resultMap={}", resultMap);
//    }
//}
