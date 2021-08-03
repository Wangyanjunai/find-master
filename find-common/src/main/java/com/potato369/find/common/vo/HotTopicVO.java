package com.potato369.find.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotTopicVO {
    @JsonProperty(value = "list")
    List<HotTopicInfoVO> topicTitleList;
    private long totalSize;
    private int totalPage;
}
