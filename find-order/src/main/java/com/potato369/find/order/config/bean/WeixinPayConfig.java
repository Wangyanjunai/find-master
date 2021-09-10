package com.potato369.find.order.config.bean;

import cn.hutool.core.util.StrUtil;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants.TradeType;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.potato369.find.common.enums.StatusEnum;
import com.potato369.find.mbg.mapper.WeixinConfigMapper;
import com.potato369.find.mbg.model.WeixinConfig;
import com.potato369.find.mbg.model.WeixinConfigExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.List;

@Configuration
@ConditionalOnClass(WxPayService.class)
@Scope("request")
public class WeixinPayConfig {
	
	private WeixinConfigMapper weixinConfigMapperReader;

	@Autowired
	public void setWeixinConfigMapperReader(WeixinConfigMapper weixinConfigMapperReader) {
		this.weixinConfigMapperReader = weixinConfigMapperReader;
	}
	
	@Bean
	public WxPayService mobileWxPayService() {
		WxPayConfig payConfig = new WxPayConfig();
		WeixinConfigExample example = new WeixinConfigExample();
		example.setDistinct(true);
		example.createCriteria().andStatusEqualTo(StatusEnum.Enabled.getCode().toString());
		List<WeixinConfig> list = this.weixinConfigMapperReader.selectByExample(example);
		WeixinConfig weixinConfig = new WeixinConfig();
		if (!list.isEmpty()) {
			weixinConfig = list.get(0);
		}
		payConfig.setAppId(StrUtil.trimToNull(weixinConfig.getAppId()));
		payConfig.setMchId(StrUtil.trimToNull(weixinConfig.getSysServiceProviderId()));
		payConfig.setMchKey(StrUtil.trimToNull(weixinConfig.getSysServiceProviderKey()));
		payConfig.setKeyPath(StrUtil.trimToNull(weixinConfig.getKeyPath()));
		payConfig.setNotifyUrl(StrUtil.trimToNull(weixinConfig.getNotifyUrl()));
		payConfig.setTradeType(TradeType.APP);
		payConfig.setUseSandboxEnv(false);
		payConfig.setIfSaveApiData(true);
		WxPayService wxPayService = new WxPayServiceImpl();
		wxPayService.setConfig(payConfig);
		return wxPayService;
	}
}
