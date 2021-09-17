package com.potato369.find.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.enums.ProductInfoStatusEnum;
import com.potato369.find.common.vo.ProductInfoVO;
import com.potato369.find.mbg.mapper.ProductInfoMapper;
import com.potato369.find.mbg.model.ProductInfo;
import com.potato369.find.mbg.model.ProductInfoExample;
import com.potato369.find.order.config.properties.ProjectUrlProps;
import com.potato369.find.order.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class ProductInfoServiceImpl implements ProductInfoService {

    private ProductInfoMapper productInfoMapperReader;

    private ProjectUrlProps projectUrlProps;

    @Autowired
    public void setProductInfoMapperReader(ProductInfoMapper productInfoMapperReader) {
        this.productInfoMapperReader = productInfoMapperReader;
    }

    @Autowired
    public void setProjectUrlProps(ProjectUrlProps projectUrlProps) {
        this.projectUrlProps = projectUrlProps;
    }

    @Override
    public List<ProductInfoVO> findAll() {
        ProductInfoExample example = new ProductInfoExample();
        example.setDistinct(true);
        example.setOrderByClause("id ASC, create_time DESC, update_time DESC");
        example.createCriteria().andProductStatusEqualTo(ProductInfoStatusEnum.ON.getCode().toString());
        List<ProductInfo> productInfoList = this.productInfoMapperReader.selectByExample(example);
        List<ProductInfoVO> productInfoVOList = new ArrayList<>();
        productInfoList.forEach(source -> {
            ProductInfoVO target = ProductInfoVO.builder().build();
            BeanUtils.copyProperties(source, target);
            target.setProductIcon(new StringBuffer()
                    .append(StrUtil.trimToNull(this.projectUrlProps.getDomain()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getProjectName()))
                    .append(StrUtil.trimToNull(this.projectUrlProps.getResAppProductImg()))
                    .append(target.getProductIcon())
                    .toString());
            productInfoVOList.add(target);
        });
        return productInfoVOList;
    }

    @Override
    public ProductInfo findOne(Long id) {
        return this.productInfoMapperReader.selectByPrimaryKey(id);
    }
}
