package com.potato369.find.dynamic.config.props;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
