package com.potato369.find.common.vo;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.potato369.find.common.utils.DataSerializerUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductInfoVO {

    /**
     * @JSONField productId：商品id
     */
    @JSONField(name = "pid")
    @JsonProperty(value = "pid")
    private Long id;
    
    /**
     * @JSONField productIcon：商品图片
     */
    @JSONField(name = "icon")
    @JsonProperty(value = "icon")
    private String productIcon;

    /**
     * @JSONField productName：商品名称
     */
    @JSONField(name = "name")
    @JsonProperty(value = "name")
    private String productName;
    
    /**
     * @JSONField productUnitPrice：商品单价
     */
    @JSONField(name = "price")
    @JsonProperty(value = "price")
    @JsonSerialize(using = DataSerializerUtil.class)
    private BigDecimal productUnitPrice;

    /**
     * @JSONField productDescription：商品描述
     */
    @JSONField(name = "desc")
    @JsonProperty(value = "desc")
    private String productDescription;
}
