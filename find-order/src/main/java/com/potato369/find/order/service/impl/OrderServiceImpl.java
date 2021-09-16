package com.potato369.find.order.service.impl;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.common.dto.OrderInfoDTO;
import com.potato369.find.common.enums.*;
import com.potato369.find.common.utils.DateUtil;
import com.potato369.find.common.utils.UUIDUtil;
import com.potato369.find.mbg.mapper.*;
import com.potato369.find.mbg.model.*;
import com.potato369.find.order.service.OrderService;
import com.potato369.find.order.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class OrderServiceImpl implements OrderService {

    private UserMapper userMapperReader;

    private UserMapper userMapperWriter;

    private ProductInfoMapper productInfoMapperReader;

    private IsDebugMapper isDebugMapperReader;

    private OrderMasterMapper orderMasterMapperWriter;

    private OrderMasterMapper orderMasterMapperReader;

    private OrderDetailMapper orderDetailMapperWriter;

    private OrderSettingMapper orderSettingMapperReader;

    private PayService payService;

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Autowired
    public void setUserMapperWriter(UserMapper userMapperWriter) {
        this.userMapperWriter = userMapperWriter;
    }

    @Autowired
    public void setProductInfoMapperReader(ProductInfoMapper productInfoMapperReader) {
        this.productInfoMapperReader = productInfoMapperReader;
    }

    @Autowired
    public void setIsDebugMapperReader(IsDebugMapper isDebugMapperReader) {
        this.isDebugMapperReader = isDebugMapperReader;
    }

    @Autowired
    public void setOrderMasterMapperWriter(OrderMasterMapper orderMasterMapperWriter) {
        this.orderMasterMapperWriter = orderMasterMapperWriter;
    }

    @Autowired
    public void setOrderDetailMapperWriter(OrderDetailMapper orderDetailMapperWriter) {
        this.orderDetailMapperWriter = orderDetailMapperWriter;
    }

    @Autowired
    public void setOrderMasterMapperReader(OrderMasterMapper orderMasterMapperReader) {
        this.orderMasterMapperReader = orderMasterMapperReader;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    @Autowired
    public void setOrderSettingMapperReader(OrderSettingMapper orderSettingMapperReader) {
        this.orderSettingMapperReader = orderSettingMapperReader;
    }

    //创建预支付订单信息
    @Override
    public CommonResult<Map<String, Object>> create(OrderDTO orderDTO, Long userId) throws Exception {
        if (check(orderDTO, userId)) {
            Long productId = orderDTO.getProductId();   //商品id
            Integer payMode = orderDTO.getPayMode();    //支付方式
            String userIp = orderDTO.getUserIp();       //终端IP
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                return CommonResult.failed(ResultCode.USER_IS_NOT_EXIST);
            }
            ProductInfo productInfo = this.productInfoMapperReader.selectByPrimaryKey(productId);
            if (productInfo == null) {
                return CommonResult.failed(ResultCode.PRODUCT_IS_NOT_EXIST);
            }
            OrderMaster orderMaster = new OrderMaster();
            String orderId = UUIDUtil.genTimstampUUID();
            String orderName = "充值" + productInfo.getProductName() + "VIP";
            orderMaster.setOrderId(orderId);
            if (StrUtil.isEmpty(userIp)) {
                userIp = "127.0.0.1";
            }
            orderMaster.setBuyerIp(userIp);
            orderMaster.setBuyerName(user.getNickName());
            orderMaster.setBuyerPhone(user.getPhone());
            BigDecimal orderAmount = BigDecimal.ZERO;
            if (UserIsTestEnum.Yes.getCode().toString().equals(user.getIsTest())) {
                IsDebugExample example = new IsDebugExample();
                example.setDistinct(true);
                example.createCriteria().andUserIdEqualTo(userId).andIsDebugEqualTo(UserIsTestEnum.Yes.getCode().toString());
                List<IsDebug> isDebugList = this.isDebugMapperReader.selectByExample(example);
                if (!isDebugList.isEmpty()) {
                    IsDebug isDebug = isDebugList.get(0);
                    orderAmount = isDebug.getPrice();
                }
            } else {
                orderAmount = productInfo.getProductUnitPrice();
            }
            orderMaster.setOrderAmount(orderAmount);
            orderMaster.setOrderName(orderName);
            orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode().toString());
            orderMaster.setPayMode(String.valueOf(payMode));
            orderMaster.setPayStatus(PayStatusEnum.WAITING.getCode().toString());
            orderMaster.setProductId(productInfo.getId());
            orderMaster.setUserId(userId);
            this.orderMasterMapperWriter.insertSelective(orderMaster);
            OrderDetail orderDetail = new OrderDetail();
            String detailId = UUIDUtil.gen32UUID();
            orderDetail.setDetailId(detailId);
            orderDetail.setBuyerUserId(userId);
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productId);
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(orderAmount);
            orderDetail.setProductQuantity(1);
            this.orderDetailMapperWriter.insertSelective(orderDetail);
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
            orderInfoDTO.setOrderAmount(orderAmount.multiply(new BigDecimal(100)).intValue());
            orderInfoDTO.setOrderId(orderId);
            orderInfoDTO.setOrderIp(userIp);
            orderInfoDTO.setOrderName(orderName);
            orderInfoDTO.setProductId(productId);
            Object result;
            log.info("orderInfoDTO={}", orderInfoDTO);
            if (PayModeEnum.alipay.getCode().equals(payMode)) {
                result = this.payService.payWithAliAppPay(orderInfoDTO);
            } else {
                result = this.payService.payWithWeixinAppPay(orderInfoDTO);
            }
            Map<String, Object> tradeInfo = new HashMap<>();
            tradeInfo.put("trade_info", result);
            return CommonResult.success(tradeInfo);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderMaster findOne(String orderId) throws Exception {
        OrderMasterExample example = new OrderMasterExample();
        example.setDistinct(true);
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<OrderMaster> orderMasterList = this.orderMasterMapperReader.selectByExample(example);
        if (!orderMasterList.isEmpty()) {
            return orderMasterList.get(0);
        }
        return null;
    }

    @Override
    public OrderMaster cancel(OrderInfoDTO orderInfoDTO) throws Exception {
        OrderMaster orderMaster = this.orderMasterMapperReader.selectByOrderId(orderInfoDTO.getOrderId());
        if (orderMaster != null) {
            orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode().toString());
            orderMaster.setPayStatus(PayStatusEnum.CLOSE.getCode().toString());
            orderMaster.setUpdateTime(new Date());
            this.orderMasterMapperWriter.updateByPrimaryKeySelective(orderMaster);
        }
        return orderMaster;
    }

    @Override
    public OrderMaster close(OrderInfoDTO orderInfoDTO) throws Exception {
        OrderMaster orderMaster = this.orderMasterMapperReader.selectByOrderId(orderInfoDTO.getOrderId());
        if (orderMaster != null) {
            orderMaster.setOrderStatus(OrderStatusEnum.CLOSE.getCode().toString());
            orderMaster.setPayStatus(PayStatusEnum.FAIL.getCode().toString());
            orderMaster.setUpdateTime(new Date());
            this.orderMasterMapperWriter.updateByPrimaryKeySelective(orderMaster);
        }
        return orderMaster;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderSetting orderSetting() {
        OrderSettingExample example = new OrderSettingExample();
        example.setDistinct(true);
        example.setOrderByClause("create_time DESC, update_time DESC");
        example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
        OrderSetting orderSetting = null;
        List<OrderSetting> orderSettingList = this.orderSettingMapperReader.selectByExample(example);
        if (orderSettingList != null && !orderSettingList.isEmpty()) {
            orderSetting = orderSettingList.get(0);
        }
        return orderSetting;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderMaster> getTimeOutOrderMaster(Integer minute) throws Exception {
        return this.orderMasterMapperReader.selectTimeOut(minute);
    }

    @Override
    public OrderMaster finish(OrderInfoDTO orderInfoDTO) throws Exception {
        OrderMaster orderMaster = this.orderMasterMapperReader.selectByOrderId(orderInfoDTO.getOrderId());
        if (orderMaster != null) {
            orderMaster.setOrderStatus(OrderStatusEnum.SUCCESS.getCode().toString());
            orderMaster.setPayStatus(PayStatusEnum.CLOSE.getCode().toString());
            orderMaster.setUpdateTime(new Date());
            this.orderMasterMapperWriter.updateByPrimaryKeySelective(orderMaster);
        }
        return null;
    }

    @Override
    public OrderMaster paid(OrderMaster orderMaster) throws Exception {
        if (checkOrder(orderMaster)) {
            Long productId = orderMaster.getProductId();
            ProductInfo productInfo = this.productInfoMapperReader.selectByPrimaryKey(productId);
            int vip_1_month_numbers = productInfo.getVipChargeTime();
            Long userId = orderMaster.getUserId();
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            user.setGrade(UserGradeEnum.VIP1.getCode().toString());
            Date now = new Date();
            Date vipStartTime = user.getVipStartTime();
            Date vipEndTime = user.getVipEndTime();
            if (vipEndTime != null && vipStartTime != null) {
                vipEndTime = DateUtil.getTimeByMonthAndDate(vip_1_month_numbers, vipEndTime);
            } else {
                if (vipStartTime == null) {
                    user.setVipStartTime(now);
                }
                vipEndTime = DateUtil.getTimeByMonthAndDate(vip_1_month_numbers, now);
            }
            user.setVipStartTime(vipStartTime);
            user.setVipEndTime(vipEndTime);
            this.userMapperWriter.updateByPrimaryKeySelective(user);

            orderMaster.setOrderStatus(OrderStatusEnum.SUCCESS.getCode().toString());
            orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode().toString());
            this.orderMasterMapperWriter.updateByPrimaryKeySelective(orderMaster);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean check(OrderDTO orderDTO, Long userId) throws Exception {
        if (orderDTO != null) {
            Long productId = orderDTO.getProductId();    //商品id
            Integer payMode = orderDTO.getPayMode();    //支付方式
            User user = this.userMapperReader.selectByPrimaryKey(userId);
            if (user == null) {
                log.error("用户信息不存在，无法创建预支付订单");
                return false;
            }
            String status = user.getStatus();
            if (UserStatusEnum.Abnormal.getCode().toString().equals(status)) {
                log.error("用户信息状态异常，无法创建预支付订单");
                return false;
            }
            ProductInfo productInfo = this.productInfoMapperReader.selectByPrimaryKey(productId);
            if (productInfo == null) {
                log.error("商品信息不存在，无法创建预支付订单");
                return false;
            }
            if (!PayModeEnum.weixinpay.getCode().equals(payMode) && !PayModeEnum.alipay.getCode().equals(payMode)) {
                log.error("支付方式不正确，无法创建预支付订单");
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkOrder(OrderMaster orderInfo) {
        if (orderInfo == null) {
            log.error("APP支付回调，订单信息不存在");
            return false;
        }
        if (!OrderStatusEnum.NEW.getCode().toString().equals(orderInfo.getOrderStatus())) {
            log.error("APP支付回调，订单状态不正确，订单状态={}", orderInfo.getOrderStatusEnum().getMessage());
            return false;
        }
        if (!PayStatusEnum.WAITING.getCode().toString().equals(orderInfo.getPayStatus())) {
            log.error("APP支付回调，支付状态不正确，支付状态={}", orderInfo.getPayStatusEnum().getMessage());
            return false;
        }
        if (!PayModeEnum.weixinpay.getCode().toString().equals(orderInfo.getPayMode()) &&
                !PayModeEnum.alipay.getCode().toString().equals(orderInfo.getPayMode())) {
            log.error("APP支付回调，支付方式不正确，支付方式={}", orderInfo.getPayModeEnum().getMessage());
            return false;
        }
        Long userId = orderInfo.getUserId();
        if (userId == null) {
            log.error("APP支付回调，用户id为空");
            return false;
        }
        User userInfo = this.userMapperReader.selectByPrimaryKey(userId);
        if (userInfo == null) {
            log.error("APP支付回调，用户信息不存在");
            return false;
        }
        //充值商品id
        Long rechargeProductId = orderInfo.getProductId();
        ProductInfo rechargeProduct = this.productInfoMapperReader.selectByPrimaryKey(rechargeProductId);
        if (rechargeProduct == null) {
            log.error("APP支付回调，充值商品信息不存在");
            return false;
        }
        return true;
    }
}
