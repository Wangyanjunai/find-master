package com.potato369.find.order.service.impl;

import com.potato369.find.common.vo.ProductInfoVO;
import com.potato369.find.order.OrderServiceApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderServiceApplication.class)
public class ProductInfoServiceImplTest {

    public ProductInfoServiceImpl productInfoService;

    @Autowired
    public void setProductInfoService(ProductInfoServiceImpl productInfoService) {
        this.productInfoService = productInfoService;
    }

    @Test
    public void findAll() {
        List<ProductInfoVO> productInfoList = this.productInfoService.findAll();
        Assert.assertEquals(4, productInfoList.size());
    }
}