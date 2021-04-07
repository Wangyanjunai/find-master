package com.potato369.find.order.service;

import com.potato369.find.common.vo.ProductInfoVO;
import com.potato369.find.mbg.model.ProductInfo;

import java.util.List;

public interface ProductInfoService {
	
	List<ProductInfoVO> findAll();
	
	ProductInfo findOne(Long id);
}
