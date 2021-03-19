package com.potato369.find.order.service;

import java.util.List;

import com.potato369.find.common.vo.ProductInfoVO;
import com.potato369.find.mbg.model.ProductInfo;

public interface ProductInfoService {
	
	List<ProductInfoVO> findAll();
	
	ProductInfo findOne(Long id);
}
