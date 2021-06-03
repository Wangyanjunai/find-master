package com.potato369.find.user;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.vo.result.baidu.JsonRootBean;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class Test01 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数：" + stats.getMax());
        System.out.println("列表中最小的数：" + stats.getMin());
        System.out.println("所有数之和：" + stats.getSum());
        System.out.println("平均数：" + stats.getAverage());
        String ip = "183.14.30.158";
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setIp(ip);
        locationDTO.setCountry("中国");
        if (StrUtil.isNotEmpty(ip)) {
            Map<String, Object> params = new HashMap<>();
            params.put("ak", "OvTyDm7rLM2soYix1D0tZKEs4SL0GlAx");
            params.put("ip", ip);
            String urlString = StrUtil.trimToEmpty("https://api.map.baidu.com/location/ip");
            String result;
            try {
                result = HttpRequest.get(urlString)
                        .header(Header.USER_AGENT, "Tools http")
                        .charset(CharsetUtil.CHARSET_UTF_8)
                        .form(params)
                        .timeout(2000)
                        .execute()
                        .body();
                log.info("result={}", result);
                JsonRootBean jsonRootBean = JSON.parseObject(result, JsonRootBean.class);
                log.info("JsonRootBean={}", jsonRootBean);
//                if (jsonb != null) {
//                    if (jsonb.getIntValue("status") == 0) {
//                        String address = jsonb.getString("address");
//                        String content = jsonb.getString("content");
//                        String[] adder = StrUtil.split(address, "|");
//                        if (adder != null && adder.length > 0) {
//                            locationDTO.setCountry("中国");
//                            locationDTO.setProvince(adder[1] + "省");
//                            locationDTO.setCity(adder[2] + "市");
//                            if (StrUtil.isNotEmpty(locationDTO.getCity())) {
//                                MunicipalityConstant constant = new MunicipalityConstant();
//                                List<String> municipalityList = constant.getMunicipalityList();
//                                if (municipalityList.contains(locationDTO.getCity())) {
//                                    locationDTO.setProvince(adder[1] + "市");
//                                    locationDTO.setCity(adder[2] + "市");
//                                }
//                            }
//                        }
//                    }
//                }
//                log.info("locationDTO={}", locationDTO);
            } catch (Exception e) {
                log.error("调用百度普通IP定位接口失败", e);
            }
        }
    }
}