package com.potato369.find.order.task;

import com.potato369.find.common.dto.OrderInfoDTO;
import com.potato369.find.mbg.model.OrderMaster;
import com.potato369.find.mbg.model.OrderSetting;
import com.potato369.find.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <pre>
 * @PackageName com.potato369.find.order.task
 * @ClassName CancelOrderTimeOutTask
 * @Desc 取消超时支付订单，并关闭订单
 * @WebSite https://www.potato369.com
 * @Author Jack
 * @Date 2021/2/13 20:49
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Component
public class CloseTimeOutOrderTasker {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public void closeOrder() throws Exception {
        OrderSetting orderSetting = this.orderService.orderSetting();
        List<OrderMaster> orderMasterList = this.orderService.getTimeOutOrderMaster(orderSetting.getNormalOrderOvertime());
        for (OrderMaster orderMaster : orderMasterList) {
            OrderInfoDTO orderDTO = new OrderInfoDTO();
            orderDTO.setOrderId(orderMaster.getOrderId());
            orderDTO.setOrderAmount(orderMaster.getOrderAmount().intValue());
            orderDTO.setOrderName(orderMaster.getOrderName());
            orderDTO.setProductId(orderMaster.getProductId());
            this.orderService.close(orderDTO);
        }
    }
}
