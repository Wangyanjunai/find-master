package com.potato369.find.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotTopicInfoVO {
    //话题总数量
    private long totalCount;
    //话题标题
    private String topicTitle;
}
