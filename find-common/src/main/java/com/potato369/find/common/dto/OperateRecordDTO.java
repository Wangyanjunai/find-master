package com.potato369.find.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class OperateRecordDTO extends AbstractDTO {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "状态：0->失败，1->成功，默认：1->成功")
    private String status;

    @ApiModelProperty(value = "类型：0->发布动态，1->删除动态，2->点赞动态，3->申请加微信，4->分享动态，5->拉黑用户，6->举报动态，7-创建用户，8->修改资料")
    private Integer type;
}
