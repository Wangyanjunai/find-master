package com.potato369.find.user;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.vo.result.baidu.JsonRootBean;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
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
        double f = 111231.5585523625;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info("f1={}", f1);
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
                double x = Double.parseDouble(jsonRootBean.getContent().getPoint().getX()) / 100000;
                BigDecimal x1 = new BigDecimal(x);
                double x11 = x1.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                double y = Double.parseDouble(jsonRootBean.getContent().getPoint().getY()) / 100000;
                BigDecimal y1 = new BigDecimal(y);
                double y11 = y1.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
                log.info("x11={}, y11={}", x11, y11);
            } catch (Exception e) {
                log.error("调用百度普通IP定位接口失败", e);
            }
        }

        String host = "http://iploc.market.alicloudapi.com";
        String path = "/v3/ip";
        //String method = "GET";
        String appcode = "5c21289ddc9749f6a105b40d24479398";
//        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, Object> query = new HashMap<>();
        query.put("ip", ip);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            String result2 = HttpRequest.get(host + path)
                    .header(Header.USER_AGENT, "Tools http")
                    .header("Authorization", "APPCODE " + appcode)
                    .charset(CharsetUtil.CHARSET_UTF_8)
                    .form(query)
                    .timeout(2000)
                    .execute()
                    .body();
            log.info("result2={}", result2);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            log.error("出现错误", e);
        }
    }
}