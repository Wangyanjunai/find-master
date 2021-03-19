package com.potato369.find.dynamic.config.props;

import org.springframework.beans.factory.annotation.Value;
//动态筛选默认年龄范围
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Component
@Getter
@Setter
@ToString
@NoArgsConstructor
public class DynamicDefaultAgeProps {

    @Value("${dynamic.age.default.min}")
    private int minDefaultAge;

    @Value("${dynamic.age.default.max}")
    private int maxDefaultAge;

    @Value("${dynamic.age.range.min}")
    private int minRangeAge;

    @Value("${dynamic.age.range.max}")
    private int maxRangeAge;

    @Value("${dynamic.age.range.timeAfterHour}")
    private int timeRangeAfterHour;
}
