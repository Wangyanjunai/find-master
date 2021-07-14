package com.potato369.find.common.vo;
//分页用户评论数据

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageCommentVOs {

    private long totalSize;

    private int totalPage;

    private List<CommentVO> list;
}
