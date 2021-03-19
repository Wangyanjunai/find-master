package com.potato369.find.order.service;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.common.dto.OrderInfoDTO;
import com.potato369.find.mbg.model.OrderMaster;
import com.potato369.find.mbg.model.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderService {

    /**
     * 创建待支付订单
     */
    CommonResult<Map<String, Object>> create(OrderDTO orderDTO, Long userId) throws Exception;

    /**
     * 查询单个订单
     */
    OrderMaster findOne(String orderId) throws Exception;

    /**
     * 取消订单
     */
    OrderMaster cancel(OrderInfoDTO orderInfoDTO) throws Exception;

    /**
     * 完结订单
     */
    OrderMaster finish(OrderInfoDTO orderInfoDTO) throws Exception;

    /**
     * 关闭订单
     */
    OrderMaster close(OrderInfoDTO orderInfoDTO) throws Exception;

    /**
     * 获取订单设置
     */
    OrderSetting orderSetting();

    /**
     * 得到所有的超时支付的订单
     */
    List<OrderMaster> getTimeOutOrderMaster(Integer minute) throws Exception;

    /**
     * 支付订单
     */
    OrderMaster paid(OrderMaster orderMaster) throws Exception;

    /**
     * 检查订单
     */
    boolean check(OrderDTO orderDTO, Long userId) throws Exception;

    /**
     * 检查订单
     */
    boolean checkOrder(OrderMaster orderInfo);
}
