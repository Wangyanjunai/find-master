package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.potato369.find.common.utils.DataSerializerUtil;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
