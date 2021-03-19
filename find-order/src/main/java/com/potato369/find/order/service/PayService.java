package com.potato369.find.order.service;

import java.util.Map;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.potato369.find.common.dto.OrderInfoDTO;
import com.potato369.find.common.vo.result.AliAppPayResult;
import com.potato369.find.common.vo.result.WeChatAppPayResult;

public interface PayService {

    /**
     * <pre>
     * 生成微信APP预支付信息
     * @return
     * </pre>
     */
	WeChatAppPayResult payWithWeixinAppPay(OrderInfoDTO orderInfoDTO);
    
    /**
     * <pre>
     * 生成支付宝APP预支付信息
     * @return
     * </pre>
     */
	AliAppPayResult payWithAliAppPay(OrderInfoDTO orderInfoDTO);
    
    /**
     * <pre>
     * 微信APP支付异步通知结果
     * @param notifyData 异步结果通知数据
     * @return
     * </pre>
     */
	WxPayOrderNotifyResult weixinAppPayNotify(String notifyData);
    
    /**
     * <pre>
     * 支付宝APP支付异步通知结果
     * @param conversionParams
     * @return String
     * </pre>
     */
    String aliAppPayNotify(Map<String, String> conversionParams);
}
