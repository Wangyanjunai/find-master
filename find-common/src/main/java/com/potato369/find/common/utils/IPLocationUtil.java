package com.potato369.find.common.utils;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.vo.result.aliyun.AliyunJsonRootBean;
import com.potato369.find.common.vo.result.baidu.BaiduJsonRootBean;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 * @PackageName com.potato369.find.common.utils
 * @ClassName IPLocationUtil
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/06/07 10:05
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Slf4j
public class IPLocationUtil {
    public static LocationDTO getLocationByBaiduIP(String apk, String url, String ip) {
        // 根据ip调用百度定位获取地址
        LocationDTO locationDTO = LocationDTO.builder().build();
        if (StrUtil.isNotEmpty(ip)) {
            try {
                if (log.isDebugEnabled()) {
                    log.debug("开始调用百度普通IP定位接口");
                }
                locationDTO.setIp(ip);
                Map<String, Object> params = new ConcurrentHashMap<>();
                params.put("ak", StrUtil.trimToEmpty(apk));
                params.put("ip", ip);
                String result = HttpRequest.get(url)
                        .header(Header.USER_AGENT, "Tools http")
                        .charset(CharsetUtil.CHARSET_UTF_8)
                        .form(params)
                        .timeout(5000)
                        .execute()
                        .body();
                log.info("result={}", result);
                BaiduJsonRootBean jsonRootBean = null;
                if (StrUtil.isNotEmpty(result)) {
                    if (result == null) {
                        log.error("调用百度普通IP定位接口失败");
                    } else {
                        jsonRootBean = JSON.parseObject(result, BaiduJsonRootBean.class);
                    }
                }
                if (jsonRootBean != null) {
                    if (jsonRootBean.getStatus() == 0) {
                        locationDTO.setCountry("中国");
                        locationDTO.setProvince(jsonRootBean.getContent().getAddressDetail().getProvince());
                        locationDTO.setCity(jsonRootBean.getContent().getAddressDetail().getCity());
                        locationDTO.setDistrict(jsonRootBean.getContent().getAddressDetail().getDistrict());
                        locationDTO.setOther(jsonRootBean.getContent().getAddressDetail().getStreet());
                        Double longitude = new BigDecimal(jsonRootBean.getContent().getPoint().getX()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                        Double latitude = new BigDecimal(jsonRootBean.getContent().getPoint().getY()).setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                        locationDTO.setLongitude(longitude);
                        locationDTO.setLatitude(latitude);
                    }
                }
            } catch (Exception e) {
                log.error("调用百度普通IP定位接口失败", e);
            } finally {
                if (log.isDebugEnabled()) {
                    log.debug("结束调用百度普通IP定位接口");
                }
            }
        }
        return locationDTO;
    }

    public static LocationDTO getLocationByAliyunIP(String appCode, String url, String ip) {
        LocationDTO locationDTO = LocationDTO.builder().build();
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始调用阿里普通IP定位接口");
            }
            locationDTO.setIp(ip);
            if (StrUtil.isNotEmpty(ip)) {
                Map<String, Object> query = new HashMap<>();
                query.put("ip", ip);
                String result = HttpRequest.get(url)
                        .header(Header.USER_AGENT, "Tools http")
                        .header("Authorization", "APPCODE " + StrUtil.trimToEmpty(appCode))
                        .charset(CharsetUtil.CHARSET_UTF_8)
                        .form(query)
                        .timeout(2000)
                        .execute()
                        .body();
                log.info("result={}", result);
                AliyunJsonRootBean aliyunJsonRootBean = null;
                if (StrUtil.isNotEmpty(result)) {
                    if (result == null) {
                        log.error("调用阿里普通IP定位接口失败");
                    } else {
                        aliyunJsonRootBean = JSON.parseObject(result, AliyunJsonRootBean.class);
                    }
                }
                if (aliyunJsonRootBean != null) {
                    if (StrUtil.isNotEmpty(aliyunJsonRootBean.getStatus()) && "1".equals(aliyunJsonRootBean.getStatus())
                            && StrUtil.isNotEmpty(aliyunJsonRootBean.getInfo()) && "OK".equals(aliyunJsonRootBean.getInfo())) {
                        locationDTO.setCountry("中国");
                        locationDTO.setProvince(aliyunJsonRootBean.getProvince());
                        locationDTO.setCity(aliyunJsonRootBean.getCity());
                        String rectangle = aliyunJsonRootBean.getRectangle();
                        if (StrUtil.isNotEmpty(rectangle)) {
                            locationDTO.setLongitude(Double.parseDouble(StrUtil.split(StrUtil.split(rectangle, ";")[0], ",")[0]));
                            locationDTO.setLatitude(Double.parseDouble(StrUtil.split(StrUtil.split(rectangle, ";")[0], ",")[1]));
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error("调用阿里普通IP定位接口失败", e);
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("结束调用阿里普通IP定位接口");
            }
        }
        return locationDTO;
    }
}
