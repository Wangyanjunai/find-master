package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

//用户点赞记录
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikesMessageRecord implements Serializable {

    //点赞消息id
    @ApiModelProperty(value = "点赞消息id")
    private Long messageId;

    //动态内容id
    @ApiModelProperty(value = "动态内容id")
    private Long dynamicInfoId;

    //点赞消息记录状态
    @ApiModelProperty(value = "点赞消息记录状态，0->取消，1->点赞")
    private String status;

    //点赞记录创建时间
    @ApiModelProperty(value = "点赞消息记录创建时间")
    private Date createTime;

    //点赞记录更新时间
    @ApiModelProperty(value = "点赞消息记录更新时间")
    private Date updateTime;

    //点赞用户id
    @ApiModelProperty(value = "点赞用户id")
    private Long userId;

    //点赞用户昵称
    @ApiModelProperty(value = "点赞记录用户昵称")
    private String nickname;

    //点赞用户头像图片
    @ApiModelProperty(value = "点赞记录用户头像图片")
    private String headIcon;

    //点赞的动态信息内容
    @ApiModelProperty(value = "点赞发送的消息内容")
    private String likesContent;

    //点赞的动态附件类型
    @ApiModelProperty(value = "点赞的动态附件类型")
    private String attacheType;

    //点赞的动态内容附件文件名称
    @ApiModelProperty(value = "点赞的动态内容附件文件名称")
    private String attacheFilename;

    private static final long serialVersionUID = 1L;
}
