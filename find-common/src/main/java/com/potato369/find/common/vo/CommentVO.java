package com.potato369.find.common.vo;
//用户评论数据

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentVO {

    /**
     * <pre>
     * @JSONField id：用户id
     * </pre>
     */
    @JsonProperty(value = "userId")
    @JSONField(name = "userId")
    private Long userId;
    
    /**
     * <pre>
     * @JSONField commentId：评论id
     * </pre>
     */
    @JsonProperty(value = "commentId")
    @JSONField(name = "commentId")
    private Long commentId;

    /**
     * <pre>
     * @JSONField nickname：用户昵称
     * </pre>
     */
    @JsonProperty(value = "nickname")
    @JSONField(name = "nickname")
    private String nickname;

    /**
     * <pre>
     * @JSONField head：用户头像
     * </pre>
     */
    @JsonProperty(value = "head")
    @JSONField(name = "head")
    private String head;

    /**
     * <pre>
     * @JSONField comment：评论内容
     * </pre>
     */
    @JsonProperty(value = "content")
    @JSONField(name = "content")
    private String comment;

    /**
     * <pre>
     * @JSONField commentDateTime：评论时间
     * </pre>
     */
    @JsonProperty(value = "dateTime")
    @JSONField(name = "dateTime")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDateTime;

    /**
     * <pre>
     * @JSONField isOrNotLikes：当前用户是否点赞这条评论
     * </pre>
     */
    @JsonProperty(value = "isOrNotLikes")
    @JSONField(name = "isOrNotLikes")
    private String isOrNotLikes;

    /**
     * <pre>
     * @JSONField likes：这条评论点赞数
     * </pre>
     */
    @JsonProperty(value = "likes")
    @JSONField(name = "likes")
    private Integer likes;
}
