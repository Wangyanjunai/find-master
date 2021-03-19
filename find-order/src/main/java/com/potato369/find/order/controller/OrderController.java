package com.potato369.find.order.controller;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.dto.OrderDTO;
import com.potato369.find.common.utils.ErrorMessageUtil;
import com.potato369.find.common.vo.ProductInfoVO;
import com.potato369.find.order.service.OrderService;
import com.potato369.find.order.service.PayService;
import com.potato369.find.order.service.ProductInfoService;
import com.potato369.find.order.utils.MD5SignatureUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * @PackageName com.potato369.find.order.controller
 * @ClassName OrderController
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/14 14:38
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Api(value = "订单模块用户管理控制器类")
@Slf4j
@RestController
@RequestMapping("/v1/order")
public class OrderController {

    private ProductInfoService productInfoService;

    private OrderService orderService;

    private PayService payService;

    @Autowired
    public void setProductInfoService(ProductInfoService productInfoService) {
        this.productInfoService = productInfoService;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setPayService(PayService payService) {
        this.payService = payService;
    }

    //获取充值商品列表接口
    @GetMapping(value = "/{id}/product/list.do")
    public CommonResult<Map<String, Object>> createList(@PathVariable(name = "id") Long userId) {
        Map<String, Object> result = new HashMap<>();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始查询充值会员时长套餐产品列表");
            }
            List<ProductInfoVO> productInfoVOList = this.productInfoService.findAll();
            result.put("list", productInfoVOList);
            return CommonResult.success(result, "查询充值会员时长套餐产品列表成功");
        } catch (Exception e) {
            log.error("查询充值会员时长套餐产品列表出错", e);
            return CommonResult.failed("查询充值会员时长套餐产品列表失败");
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束查询充值会员时长套餐产品列表");
            }
        }
    }

    //创建待支付订单接口
    @PostMapping("/{id}/mobile/create.do")
    public CommonResult<Map<String, Object>> createOrder(@PathVariable(name = "id") Long userId,
                                                         @RequestBody @Valid OrderDTO orderDTO, BindingResult bindingResult) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始生成预支付订单信息");
            }
            if (bindingResult.hasErrors()) {
                String message = ErrorMessageUtil.messageBuild(bindingResult.getAllErrors());
                return CommonResult.failed(message);
            }
            return this.orderService.create(orderDTO, userId);
        } catch (Exception e) {
            log.error("生成预支付订单信息出错", e);
            return CommonResult.failed();
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束生成预支付订单信息");
            }
        }
    }

    /**
     * <pre>
     * <p>微信APP支付授权回调接口</p>
     * @param notifyData
     * @return
     * </pre>
     */
    @PostMapping(value = "/pay/weixin-notify.do")
    public String weixinPayNotify(@RequestBody String notifyData) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始微信APP支付异步结果通知");
            }
            this.payService.weixinAppPayNotify(notifyData);
            return "<xml>\r\n" +
                    "  <return_code><![CDATA[SUCCESS]]></return_code>\r\n" +
                    "  <return_msg><![CDATA[OK]]></return_msg>\r\n" +
                    "</xml>";
        } catch (Exception e) {
            log.error("微信APP支付异步结果通知出现错误", e);
            return "<xml>\r\n" +
                    "  <return_code><![CDATA[FAIL]]></return_code>\r\n" +
                    "  <return_msg><![CDATA[微信APP支付异步结果通知出现错误]]></return_msg>\r\n" +
                    "</xml>";
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束微信APP支付异步结果通知");
            }
        }
    }

    /**
     * <pre>
     * <p>支付宝APP支付授权回调接口</p>
     * @param request
     * @param response
     * @return
     * </pre>
     */
    @PostMapping(value = "/pay/alipay-notify.do")
    public String aliPayNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始支付宝异步返回支付结果");
            }
            //从支付宝回调的request域中取值，获取支付宝返回的参数集合
            Map<String, String> conversionParams = MD5SignatureUtil.requestToMap(request);
            if (log.isDebugEnabled()) {
                log.debug("返回支付宝异步返回支付结果参数集合={}", conversionParams);
            }
            return this.payService.aliAppPayNotify(conversionParams);
        } catch (Exception e) {
            log.error("支付宝异步返回支付结果出现错误", e);
            return "fail";
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束支付宝异步返回支付结果");
            }
        }
    }

    /**
     * <pre>
     * <p>支付宝APP支付后返回接口</p>
     * @param request
     * @param response
     * @return
     * </pre>
     */
    @PostMapping(value = "/pay/alipay-return.do")
    public String aliPayReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始支付宝异步返回支付结果");
            }
            //从支付宝回调的request域中取值，获取支付宝返回的参数集合
            Map<String, String> conversionParams = MD5SignatureUtil.requestToMap(request);
            if (log.isDebugEnabled()) {
                log.debug("返回支付宝异步返回支付结果参数集合={}", conversionParams);
            }
            return this.payService.aliAppPayNotify(conversionParams);
        } catch (Exception e) {
            log.error("支付宝异步返回支付结果出现错误", e);
            return "fail";
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束支付宝异步返回支付结果");
            }
        }
    }
}
