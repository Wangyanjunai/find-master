package com.potato369.find.order.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.json.JSONUtil;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderInfoDTO;
import com.potato369.find.common.enums.AliAppPayGoodsTypeEnum;
import com.potato369.find.common.enums.PayStatusEnum;
import com.potato369.find.common.enums.StatusEnum;
import com.potato369.find.common.utils.MathUtil;
import com.potato369.find.common.vo.PayMessageVO;
import com.potato369.find.common.vo.result.AliAppPayResult;
import com.potato369.find.common.vo.result.AliPayNotifyResult;
import com.potato369.find.common.vo.result.WeChatAppPayResult;
import com.potato369.find.mbg.mapper.AlipayConfigMapper;
import com.potato369.find.mbg.mapper.OrderSettingMapper;
import com.potato369.find.mbg.model.*;
import com.potato369.find.order.service.OrderService;
import com.potato369.find.order.service.PayService;
import com.potato369.find.order.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(isolation = Isolation.SERIALIZABLE, rollbackFor = Exception.class, timeout = 30)
public class PayServiceImpl implements PayService {

    private WxPayService mobileWxPayService;

    private OrderService orderService;

    private ProductInfoService productInfoService;

    private WebSocketService websocket;

    private OrderSettingMapper orderSettingMapperReader;

    private AlipayConfigMapper alipayConfigMapperReader;

    @Autowired
    public void setMobileWxPayService(WxPayService mobileWxPayService) {
        this.mobileWxPayService = mobileWxPayService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setProductInfoService(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @Autowired
    public void setWebsocket(WebSocketService websocket) {
        this.websocket = websocket;
    }

    @Autowired
    public void setOrderSettingMapperReader(OrderSettingMapper orderSettingMapperReader) {
        this.orderSettingMapperReader = orderSettingMapperReader;
    }

    @Autowired
    public void setAlipayConfigMapperReader(AlipayConfigMapper alipayConfigMapperReader) {
        this.alipayConfigMapperReader = alipayConfigMapperReader;
    }

    @Override
    public WeChatAppPayResult payWithWeixinAppPay(OrderInfoDTO orderInfoDTO) {
        WeChatAppPayResult payResult = new WeChatAppPayResult();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始请求生成微信APP支付订单信息");
            }
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setBody(orderInfoDTO.getOrderName());
            orderRequest.setOutTradeNo(String.valueOf(orderInfoDTO.getOrderId()));
            orderRequest.setTotalFee(orderInfoDTO.getOrderAmount());
            orderRequest.setSpbillCreateIp(orderInfoDTO.getOrderIp());
            orderRequest.setProductId(orderInfoDTO.getProductId().toString());
            Date date = new Date();
            String format = "yyyyMMddHHmmss";
            String timeStart = DateUtil.format(date, format);
            OrderSetting orderSetting = null;
            OrderSettingExample example = new OrderSettingExample();
            example.setDistinct(true);
            example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
            List<OrderSetting> orderSettingList = this.orderSettingMapperReader.selectByExample(example);
            Integer timeoutExpress;
            if (!orderSettingList.isEmpty()) {
                orderSetting = orderSettingList.get(0);
            }
            if (orderSetting != null) {
                timeoutExpress = orderSetting.getNormalOrderOvertime();
            } else {
                timeoutExpress = 30;
            }
            String timeExpire = DateUtil.format(DateUtil.offsetMinute(date, timeoutExpress), format);
            orderRequest.setTimeStart(timeStart);
            orderRequest.setTimeExpire(timeExpire);
            if (log.isDebugEnabled()) {
                log.debug("请求生成微信APP支付订单信息，请求参数request={}", JSONUtil.toJsonPrettyStr(orderRequest));
            }
            WxPayAppOrderResult appOrderResult = this.mobileWxPayService.createOrder(orderRequest);
            if (log.isDebugEnabled()) {
                log.debug("请求生成微信APP支付订单信息，响应内容response={}", JSONUtil.toJsonPrettyStr(appOrderResult));
            }
            if (appOrderResult != null) {
                payResult.setReturnCode("Success");
                payResult.setReturnMsg("生成微信APP支付预支付订单信息成功。");
                payResult.setResultCode("Success");
                payResult.setErrCode("Success");
                payResult.setErrCodeDes("请求微信支付统一下单接口生成APP支付预付单信息成功。");
                payResult.setAppId(appOrderResult.getAppId());
                payResult.setPartnerId(appOrderResult.getPartnerId());
                payResult.setPackageValue(appOrderResult.getPackageValue());
                payResult.setPrepayId(appOrderResult.getPrepayId());
                payResult.setNonceStr(appOrderResult.getNonceStr());
                payResult.setSign(appOrderResult.getSign());
                payResult.setTimeStamp(appOrderResult.getTimeStamp());

            } else {
                payResult.setReturnCode("Failed");
                payResult.setReturnMsg("生成微信APP支付预支付订单信息失败。");
                payResult.setResultCode("Failed");
                payResult.setErrCode("Failed");
                payResult.setErrCodeDes("请求微信支付统一下单接口生成APP支付预付单信息为空。");
            }
            if (log.isDebugEnabled()) {
                log.debug("请求生成微信APP支付订单信息返回前端result={}", JSONUtil.toJsonPrettyStr(payResult));
            }
            payResult.setOrderId(orderInfoDTO.getOrderId());
            return payResult;
        } catch (Exception e) {
            log.error("请求生成微信APP支付订单信息出现错误", e);
            payResult.setReturnCode("Failed");
            payResult.setReturnMsg("生成微信APP支付预支付订单信息失败。");
            payResult.setResultCode("Failed");
            payResult.setErrCode("Failed");
            payResult.setErrCodeDes("请求微信支付统一下单接口生成APP支付预付单信息为空。");
            return payResult;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束请求生成微信APP支付订单信息");
            }
        }
    }

    @Override
    public AliAppPayResult payWithAliAppPay(OrderInfoDTO orderInfoDTO) {
        AliAppPayResult aliAppPayResult = new AliAppPayResult();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始请求支付宝服务器生成支付宝APP支付预支付订单信息");
            }
            AlipayConfigExample example = new AlipayConfigExample();
            example.setDistinct(true);
            example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
            AlipayConfig alipayConfig = null;
            List<AlipayConfig> alipayConfigList = this.alipayConfigMapperReader.selectByExampleWithBLOBs(example);
            if (!alipayConfigList.isEmpty()) {
                alipayConfig = alipayConfigList.get(0);
            }
            if (alipayConfig != null) {
                aliAppPayResult.setAppId(StrUtil.trimToNull(alipayConfig.getAppId()));
                aliAppPayResult.setOrderId(String.valueOf(orderInfoDTO.getOrderId()));
                AlipayClient alipayClient = new DefaultAlipayClient(
                        // 请求网关
                        StrUtil.trimToNull(alipayConfig.getGatewayUrl()),
                        // 应用id
                        StrUtil.trimToNull(alipayConfig.getAppId()),
                        // 应用私钥
                        StrUtil.trimToNull(alipayConfig.getPrivateKey()),
                        // 数据传输格式
                        StrUtil.trimToNull(alipayConfig.getFormat()),
                        // 编码格式
                        StrUtil.trimToNull(alipayConfig.getCharset()),
                        // 支付宝公钥
                        StrUtil.trimToNull(alipayConfig.getPublicKey()),
                        // 签名类型
                        StrUtil.trimToNull(alipayConfig.getSignType()));
                AlipayTradeAppPayModel appPayModel = new AlipayTradeAppPayModel();
                // 订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
                appPayModel.setTotalAmount(String.valueOf(orderInfoDTO.getOrderAmount()));
                // 商品的标题/交易标题/订单标题/订单关键字等。
                appPayModel.setSubject(orderInfoDTO.getOrderName());
                // 商户网站唯一订单号
                appPayModel.setOutTradeNo(String.valueOf(orderInfoDTO.getOrderId()));
                // 该笔订单允许的最晚付款时间，逾期将关闭交易。
                OrderSetting orderSetting = null;
                OrderSettingExample example2 = new OrderSettingExample();
                example.setDistinct(true);
                example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
                List<OrderSetting> orderSettingList = this.orderSettingMapperReader.selectByExample(example2);
                Integer timeoutExpress;
                if (!orderSettingList.isEmpty()) {
                    orderSetting = orderSettingList.get(0);
                }
                if (orderSetting != null) {
                    timeoutExpress = orderSetting.getNormalOrderOvertime();
                } else {
                    timeoutExpress = 30;
                }
                appPayModel.setTimeoutExpress(String.valueOf(timeoutExpress));
                //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
                //appPayModel.setBody(order.getOrderName());
                //商品主类型 :0-虚拟类商品,1-实物类商品
                appPayModel.setGoodsType(AliAppPayGoodsTypeEnum.Virtual.getCodeStr());
                //可用渠道，用户只能在指定渠道范围内支付当有多个渠道时用“,”分隔。注：与 disable_pay_channels互斥；
                //appPayModel.setEnablePayChannels(StrUtil.trimToNull(this.mobileAliPayProperties.getEnablePayChannels()));
                //禁用渠道，用户不可用指定渠道支付当有多个渠道时用“,”分隔。注：与 enable_pay_channels互斥；
                //appPayModel.setDisablePayChannels(StrUtil.trimToNull(this.mobileAliPayProperties.getDisablePayChannels()));
                AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
                request.setBizModel(appPayModel);
                request.setNotifyUrl(alipayConfig.getNotifyUrl());
                request.setApiVersion("2.0");
                if (log.isDebugEnabled()) {
                    String requestString = JSONUtil.toJsonPrettyStr(request);
                    log.debug("请求支付宝服务器生成支付宝APP支付订单信息，请求参数request={}", requestString);
                }
                log.info("alipayClient={}", JSONUtil.toJsonPrettyStr(alipayClient));
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
                if (log.isDebugEnabled()) {
                    String responseString = JSONUtil.toJsonPrettyStr(response);
                    responseString = URLUtil.decode(responseString, "UTF-8");
                    log.debug("请求支付宝服务器生成支付宝APP支付订单信息，响应内容response={}", responseString);
                }
                if (response != null && response.isSuccess()) {
                    String body = response.getBody();
                    aliAppPayResult.setBody(body);
                    body = URLUtil.decode(body, "UTF-8");
                    if (log.isDebugEnabled()) {
                        log.debug("请求支付宝服务器生成支付宝APP支付订单信息，响应内容body={}", body);
                    }
                    aliAppPayResult.setReturnCode("Success");
                    aliAppPayResult.setResultCode("Success");
                    aliAppPayResult.setReturnMsg("生成支付宝APP预支付订单信息返回数据成功。");
                    aliAppPayResult.setErrCode("Success");
                    aliAppPayResult.setErrCodeDes("生成支付宝APP预支付订单信息返回数据成功。");
                } else {
                    aliAppPayResult.setReturnCode("Failed");
                    aliAppPayResult.setResultCode("Failed");
                    aliAppPayResult.setReturnMsg("生成支付宝APP预支付订单信息返回数据失败。");
                    aliAppPayResult.setErrCode("Failed");
                    aliAppPayResult.setErrCodeDes("生成支付宝APP预支付订单信息返回数据失败。");
                }
                if (log.isDebugEnabled()) {
                    log.debug("请求支付宝服务器生成支付宝APP支付订单信息，返回前端result={}", JSONUtil.toJsonPrettyStr(aliAppPayResult));
                }
            }
            return aliAppPayResult;
        } catch (Exception e) {
            log.error("请求支付宝服务器生成支付宝APP支付订单信息出现错误", e);
            aliAppPayResult.setReturnCode("Failed");
            aliAppPayResult.setResultCode("Failed");
            aliAppPayResult.setReturnMsg("生成支付宝APP预支付订单信息返回数据失败。");
            aliAppPayResult.setErrCode("Failed");
            aliAppPayResult.setErrCodeDes("生成支付宝APP预支付订单信息返回数据失败。");
            return aliAppPayResult;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束请求支付宝服务器生成支付宝APP支付预支付订单信息");
            }
        }
    }

    @Override
    public WxPayOrderNotifyResult weixinAppPayNotify(String notifyData) {
        String resultCode = "FAIL";
        String outTradeNo = "";
        Long userId = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始【微信APP支付】异步通知结果");
            }
            if (log.isDebugEnabled()) {
                log.debug("【微信APP支付】异步通知结果notifyData={}", notifyData);
            }
            PayMessageVO payMessageVO = PayMessageVO.builder().build();
            WxPayOrderNotifyResult payOrderNotifyResult = this.mobileWxPayService.parseOrderNotifyResult(notifyData);
            if (payOrderNotifyResult != null) {
                if (log.isDebugEnabled()) {
                    log.debug("【微信APP支付】异步通知结果payOrderNotifyResult={}", payOrderNotifyResult);
                }
                outTradeNo = payOrderNotifyResult.getOutTradeNo();
                // 返回状态码
                String returnCode = payOrderNotifyResult.getReturnCode();
                // 返回信息
                String returnMsg = payOrderNotifyResult.getReturnMsg();
                // 业务结果
                resultCode = payOrderNotifyResult.getResultCode();
                if (log.isDebugEnabled()) {
                    log.debug("返回状态码resultCode={}, 返回信息returnMsg={}, 业务结果resultCode={}", resultCode, returnMsg,
                            resultCode);
                }
                if ("SUCCESS".equals(returnCode) && "SUCCESS".equals(resultCode)) {
                    // 商户系统内部的订单号
                    String orderId = payOrderNotifyResult.getOutTradeNo();
                    OrderMaster orderMaster = this.orderService.findOne(orderId);
                    if (orderMaster == null) {
                        log.error("【微信APP支付】异步通知结果，订单信息不存在 orderId={}", orderId);
                        return payOrderNotifyResult;
                    }
                    // 微信支付回调订单总金额
                    Double notifyOrderAmount = payOrderNotifyResult.getTotalFee().doubleValue();
                    // 商户数据库存储订单总金额
                    Double orderAmount = BaseWxPayRequest.yuanToFen(String.valueOf(orderMaster.getOrderAmount()))
                            .doubleValue();
                    if (!MathUtil.equals(notifyOrderAmount, orderAmount)) {
                        log.error("【微信APP支付】异步通知结果，订单金额不一致orderId={}，微信支付回调订单总金额={}，商户数据库存储记录订单总金额={}",
                                notifyOrderAmount, orderAmount);
                        return payOrderNotifyResult;
                    }
                    // 微信支付订单号
                    String ransactionId = payOrderNotifyResult.getTransactionId();
                    orderMaster.setTransactionId(ransactionId);
                    // 支付完成时间
                    String timeEnd = payOrderNotifyResult.getTimeEnd();
                    orderMaster.setPayTime(DateUtil.parse(timeEnd, DatePattern.PURE_DATETIME_FORMAT));
                    // 用户标识
                    //String openid = payOrderNotifyResult.getOpenid();
                    userId = orderMaster.getUserId();
                    payMessageVO.setOrderId(outTradeNo);
                    Long productId = orderMaster.getProductId();
                    ProductInfo rechargeProduct = this.productInfoService.findOne(productId);
                    if (rechargeProduct != null) {
                        payMessageVO.setProductName(rechargeProduct.getProductName());
                    }
                    orderMaster = this.orderService.paid(orderMaster);
                    payMessageVO.setPayMessage("支付成功");
                    payMessageVO.setPayState(PayStatusEnum.SUCCESS.getCode());
                    payMessageVO.setTradeState(resultCode);
                    payMessageVO.setTradeStateDesc("支付成功");
                    String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
                    String message = new StringBuffer().append(messageJson).append("|").append(userId).toString()
                            .trim();
                    this.websocket.sendToUser(message);
                    return payOrderNotifyResult;
                } else {
                    payMessageVO.setOrderId(outTradeNo);
                    payMessageVO.setPayMessage("支付失败");
                    payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
                    payMessageVO.setTradeState(resultCode);
                    payMessageVO.setTradeStateDesc("支付失败");
                    payMessageVO.setIsOrNotVIP(Boolean.FALSE);
                    String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
                    String message = new StringBuffer().append(messageJson).append("|").append(userId).toString()
                            .trim();
                    this.websocket.sendToUser(message);
                    return payOrderNotifyResult;
                }
            } else {
                payMessageVO.setOrderId(outTradeNo);
                payMessageVO.setPayMessage("支付失败");
                payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
                payMessageVO.setTradeState(resultCode);
                payMessageVO.setTradeStateDesc("支付失败");
                payMessageVO.setIsOrNotVIP(Boolean.FALSE);
                String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
                String message = new StringBuffer().append(messageJson).append("|").append(userId).toString().trim();
                this.websocket.sendToUser(message);
                return payOrderNotifyResult;
            }
        } catch (Exception e) {
            log.error("【微信APP支付】异步通知结果出现错误", e);
            PayMessageVO payMessageVO = PayMessageVO.builder().build();
            payMessageVO.setOrderId(outTradeNo);
            payMessageVO.setPayMessage("支付失败");
            payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
            payMessageVO.setTradeState(resultCode);
            payMessageVO.setTradeStateDesc("支付失败");
            payMessageVO.setIsOrNotVIP(Boolean.FALSE);
            String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
            String message = new StringBuffer().append(messageJson).append("|").append(userId).toString().trim();
            try {
                this.websocket.sendToUser(message);
            } catch (Exception e1) {
                log.error("【微信APP支付】异步通知结果出现错误", e1);
            }
            return null;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束【微信APP支付】异步通知结果");
            }
        }
    }

    @Override
    public String aliAppPayNotify(Map<String, String> conversionParams) {
        Boolean signVerified = Boolean.FALSE;
        String outTradeNo = "";
        Long userId = null;
        OrderMaster orderMaster = null;
        AlipayConfig alipayConfig = null;
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始【支付宝APP支付】异步通知数据验签");
                log.debug("支付宝返回的数据conversionParams={}", conversionParams);
            }
            if (!CollectionUtils.isEmpty(conversionParams) && conversionParams.containsKey("out_trade_no")) {
                outTradeNo = conversionParams.get("out_trade_no");
            }
            orderMaster = this.orderService.findOne(outTradeNo);
            if (orderMaster == null) {
                log.error("【支付宝APP支付】异步通知数据，订单不存在 orderId={}", outTradeNo);
            }
            userId = orderMaster.getUserId();
            // 调用SDK验证签名，签名验证（对支付宝返回的数据验证，确定是支付宝返回的）。
            AlipayConfigExample example = new AlipayConfigExample();
            example.setDistinct(true);
            example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());

            List<AlipayConfig> alipayConfigList = this.alipayConfigMapperReader.selectByExampleWithBLOBs(example);
            if (!alipayConfigList.isEmpty()) {
                alipayConfig = alipayConfigList.get(0);
            }
            if (alipayConfig != null) {
                signVerified = AlipaySignature.rsaCheckV1(conversionParams,
                        StringUtils.trimToNull(alipayConfig.getPublicKey()),
                        StringUtils.trimToNull(alipayConfig.getCharset()),
                        StringUtils.trimToNull(alipayConfig.getSignType()));
                if (log.isDebugEnabled()) {
                    log.debug("调用SDK签名验证结果={}", signVerified);
                }
            }

        } catch (Exception e) {
            log.error("【支付宝APP支付】异步通知数据验签出现错误", e);
            // 商户订单号
            PayMessageVO payMessageVO = PayMessageVO.builder().build();
            payMessageVO.setOrderId(outTradeNo);
            payMessageVO.setTradeState("FAIL");
            payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
            payMessageVO.setTradeStateDesc("支付宝APP支付异步通知数据验签出现错误");
            payMessageVO.setIsOrNotVIP(Boolean.FALSE);
            payMessageVO.setPayMessage("支付失败");
            String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
            String message = new StringBuffer().append(messageJson).append("|").append(userId).toString().trim();
            try {
                this.websocket.sendToUser(message);
            } catch (Exception e1) {
                log.error("【支付宝APP支付】异步通知出现错误", e1);
            }
            return "fail";
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束【支付宝APP支付】异步通知数据验签");
            }
        }
        // 对验签进行处理
        PayMessageVO payMessageVO = PayMessageVO.builder().build();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始对支付宝APP支付异步通知数据进行处理");
            }
            if (signVerified) {
                AliPayNotifyResult result = checkNotifyParams(conversionParams);
                if (result != null) {
                    // 支付宝通知总金额
                    Double notifyTotalAmount = Double.parseDouble(result.getTotalAmount());
                    // 商户订单系统记录总金额
                    Double totalAmount = orderMaster.getOrderAmount().doubleValue();
                    if (!MathUtil.equals(notifyTotalAmount, totalAmount)) {
                        log.error("【支付宝APP支付】异步通知，订单金额不一致orderId={}，支付宝通知总金额={}，商户订单系统记录总金额={}", result.getOutTradeNo(),
                                result.getTotalAmount(), orderMaster.getOrderAmount());
                        throw new Exception("【支付宝APP支付】异步通知，订单金额不一致。");
                    }
                    // 系统支付宝分配给开发者的应用Id
                    String appId = StringUtils.trimToNull(alipayConfig.getAppId());
                    if (!appId.equals(result.getAppId())) {
                        log.error("【支付宝APP支付】异步通知，支付宝分配给开发者的应用Id不一致。异步通知支付宝分配给开发者的应用Id={}，系统支付宝分配给开发者的应用Id={}",
                                result.getAppId(), appId);
                        throw new Exception("【支付宝APP支付】异步通知，支付宝分配给开发者的应用Id不一致。");
                    }
                    // 商户订单号
                    payMessageVO.setOrderId(outTradeNo);
                    // 买家支付宝用户号
                    //openid = result.getBuyerId();
                    Long productId = orderMaster.getProductId();
                    ProductInfo rechargeProduct = this.productInfoService.findOne(productId);
                    if (rechargeProduct != null) {
                        payMessageVO.setProductName(rechargeProduct.getProductName());
                    }
                }
                // 支付宝交易号
                String tradeNo = result.getTradeNo();
                orderMaster.setTransactionId(tradeNo);
                // 交易付款时间
                String gmtPayment = result.getGmtPayment();
                orderMaster.setPayTime(DateUtil.parse(gmtPayment, DatePattern.NORM_DATETIME_FORMAT));
                payMessageVO.setTradeState(result.getTradeStatus());
                payMessageVO.setTradeStateDesc(result.getReturnMsg());
                orderMaster = this.orderService.paid(orderMaster);
                if ("TRADE_SUCCESS".equals(result.getTradeStatus()) && orderMaster != null) {
                    payMessageVO.setPayMessage("支付成功");
                    payMessageVO.setPayState(PayStatusEnum.SUCCESS.getCode());
                    String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
                    String message = new StringBuffer().append(messageJson).append("|").append(userId).toString()
                            .trim();
                    this.websocket.sendToUser(message);
                    return "success";
                }
            } else {
                log.error("调用SDK验证签名不通过");
                payMessageVO.setOrderId(outTradeNo);
                payMessageVO.setPayMessage("调用SDK验证签名不通过");
                payMessageVO.setTradeState("FAIL");
                payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
                payMessageVO.setTradeStateDesc("支付宝异步通知数据验签不通过");
                payMessageVO.setIsOrNotVIP(Boolean.FALSE);
                payMessageVO.setPayMessage("支付失败");
                String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
                String message = new StringBuffer().append(messageJson).append("|").append(userId).toString().trim();
                this.websocket.sendToUser(message);
                return "fail";
            }
        } catch (Exception e) {
            log.error("【支付宝APP支付】异步通知出现错误", e);
            payMessageVO.setOrderId(outTradeNo);
            payMessageVO.setPayMessage("支付宝异步通知出现错误");
            payMessageVO.setTradeState("FAIL");
            payMessageVO.setPayState(PayStatusEnum.WAITING.getCode());
            payMessageVO.setTradeStateDesc("支付宝异步通知出现错误");
            payMessageVO.setIsOrNotVIP(Boolean.FALSE);
            payMessageVO.setPayMessage("支付失败");
            String messageJson = JSONUtil.toJsonPrettyStr(CommonResult.success(payMessageVO));
            String message = new StringBuffer().append(messageJson).append("|").append(userId).toString().trim();
            try {
                this.websocket.sendToUser(message);
            } catch (Exception e1) {
                log.error("【支付宝APP支付】异步通知出现错误", e1);
            }
            return "fail";
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束对支付宝APP支付异步通知数据进行处理");
            }
        }
        return "fail";
    }

    private AliPayNotifyResult checkNotifyParams(Map<String, String> conversionParams) {
        AliPayNotifyResult result = new AliPayNotifyResult();
        if (conversionParams != null) {
            /** 支付宝分配给开发者的应用Id */
            String appId_key = "app_id", appId_value;

            /** 通知时间:yyyy-MM-dd HH:mm:ss */
            String notifyTime_key = "notify_time", notifyTime_value;

            /** 交易创建时间:yyyy-MM-dd HH:mm:ss */
            String gmtCreate_key = "gmt_create", gmtCreate_value;

            /** 交易付款时间 */
            String gmtPayment_key = "gmt_payment", gmtPayment_value;

            /** 交易退款时间 */
            String gmtRefund_key = "gmt_refund", gmtRefund_value;

            /** 交易结束时间 */
            String gmtClose_key = "gmt_close", gmtClose_value;

            /** 支付宝的交易号 */
            String tradeNo_key = "trade_no", tradeNo_value;

            /** 获取商户之前传给支付宝的订单号（商户系统的唯一订单号） */
            String outTradeNo_key = "out_trade_no", outTradeNo_value;

            /** 商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号) */
            String outBizNo_key = "out_biz_no", outBizNo_value;

            /** 买家支付宝用户号 */
            String buyerId_key = "buyer_id", buyerId_value;

            /** 买家支付宝账号 */
            String buyerLogonId_key = "buyer_logon_id", buyerLogonId_value;

            /** 卖家支付宝用户号 */
            String sellerId_key = "seller_id", sellerId_value;

            /** 卖家支付宝账号 */
            String sellerEmail_key = "seller_email", sellerEmail_value;

            /** 订单金额:本次交易支付的订单金额，单位为人民币（元） */
            String totalAmount_key = "total_amount", totalAmount_value;

            /** 实收金额:商家在交易中实际收到的款项，单位为人民币（元） */
            String receiptAmount_key = "receipt_amount", receiptAmount_value;

            /** 开票金额:用户在交易中支付的可开发票的金额 */
            String invoiceAmount_key = "invoice_amount", invoiceAmount_value;

            /** 付款金额:用户在交易中支付的金额 */
            String buyerPayAmount_key = "buyer_pay_amount", buyerPayAmount_value;

            /** 交易状态 */
            String tradeStatus_key = "trade_status", tradeStatus_value;
            if (conversionParams.containsKey(appId_key)) {
                appId_value = conversionParams.get(appId_key);
                result.setAppId(appId_value);
            }
            if (conversionParams.containsKey(notifyTime_key)) {
                notifyTime_value = conversionParams.get(notifyTime_key);
                result.setNotifyTime(notifyTime_value);
            }
            if (conversionParams.containsKey(gmtCreate_key)) {
                gmtCreate_value = conversionParams.get(gmtCreate_key);
                result.setGmtCreate(gmtCreate_value);
            }
            if (conversionParams.containsKey(gmtPayment_key)) {
                gmtPayment_value = conversionParams.get(gmtPayment_key);
                result.setGmtPayment(gmtPayment_value);
            }
            if (conversionParams.containsKey(gmtRefund_key)) {
                gmtRefund_value = conversionParams.get(gmtRefund_key);
                result.setGmtRefund(gmtRefund_value);
            }
            if (conversionParams.containsKey(gmtClose_key)) {
                gmtClose_value = conversionParams.get(gmtClose_key);
                result.setGmtClose(gmtClose_value);
            }
            if (conversionParams.containsKey(tradeNo_key)) {
                tradeNo_value = conversionParams.get(tradeNo_key);
                result.setTradeNo(tradeNo_value);
            }
            if (conversionParams.containsKey(outTradeNo_key)) {
                outTradeNo_value = conversionParams.get(outTradeNo_key);
                result.setOutTradeNo(outTradeNo_value);
            }
            if (conversionParams.containsKey(outBizNo_key)) {
                outBizNo_value = conversionParams.get(outBizNo_key);
                result.setOutBizNo(outBizNo_value);
            }
            if (conversionParams.containsKey(buyerId_key)) {
                buyerId_value = conversionParams.get(buyerId_key);
                result.setBuyerId(buyerId_value);
            }
            if (conversionParams.containsKey(buyerLogonId_key)) {
                buyerLogonId_value = conversionParams.get(buyerLogonId_key);
                result.setBuyerLogonId(buyerLogonId_value);
            }
            if (conversionParams.containsKey(sellerId_key)) {
                sellerId_value = conversionParams.get(sellerId_key);
                result.setSellerId(sellerId_value);
            }
            if (conversionParams.containsKey(sellerEmail_key)) {
                sellerEmail_value = conversionParams.get(sellerEmail_key);
                result.setSellerEmail(sellerEmail_value);
            }
            if (conversionParams.containsKey(totalAmount_key)) {
                totalAmount_value = conversionParams.get(totalAmount_key);
                result.setTotalAmount(totalAmount_value);
            }
            if (conversionParams.containsKey(receiptAmount_key)) {
                receiptAmount_value = conversionParams.get(receiptAmount_key);
                result.setReceiptAmount(receiptAmount_value);
            }
            if (conversionParams.containsKey(invoiceAmount_key)) {
                invoiceAmount_value = conversionParams.get(invoiceAmount_key);
                result.setInvoiceAmount(invoiceAmount_value);
            }
            if (conversionParams.containsKey(buyerPayAmount_key)) {
                buyerPayAmount_value = conversionParams.get(buyerPayAmount_key);
                result.setBuyerPayAmount(buyerPayAmount_value);
            }
            if (conversionParams.containsKey(tradeStatus_key)) {
                tradeStatus_value = conversionParams.get(tradeStatus_key);
                result.setTradeStatus(tradeStatus_value);
            }
        }
        return result;
    }
}
