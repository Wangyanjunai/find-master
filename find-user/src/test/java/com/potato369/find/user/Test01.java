package com.potato369.find.user;

import cn.hutool.core.util.StrUtil;
import com.potato369.find.common.dto.LocationDTO;
import com.potato369.find.common.utils.IPLocationUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

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
        String ip = "183.14.29.234";
        if (StrUtil.isNotEmpty(ip)) {
            LocationDTO locationDTO = IPLocationUtil.getLocationByBaiduIP("OvTyDm7rLM2soYix1D0tZKEs4SL0GlAx", "https://api.map.baidu.com/location/ip", ip);
            log.info("locationDTO 1={}", locationDTO);
        }
        if (StrUtil.isNotEmpty(ip)) {
            LocationDTO locationDTO = IPLocationUtil.getLocationByAliyunIP("5c21289ddc9749f6a105b40d24479398", "http://iploc.market.alicloudapi.com/v3/ip", ip);
            log.info("locationDTO 2={}", locationDTO);
        }
    }
}