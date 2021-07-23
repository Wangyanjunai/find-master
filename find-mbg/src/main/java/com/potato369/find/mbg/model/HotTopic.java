package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * <pre>
 * @PackageName com.potato369.find.mbg.model
 * @ClassName HotTopic
 * @Desc 热门话题数据实体
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/23 11:42
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Data
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HotTopic {
    //话题总数量
    private int totalCount;
    //话题标题
    private String topicTitle;
}
