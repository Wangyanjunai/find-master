package com.potato369.find.common.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

/**
 * <pre>
 * @PackageName com.potato369.common.vo
 * @ClassName DynamicInfoVO
 * @Desc 动态内容信息前端数据载体
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/11/19 11:44
 * @CreateBy Intellij IDEA 2020.2.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicInfoVO {

    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId; // 用户id

    @JSONField(name = "headUrl")
    @JsonProperty(value = "headUrl")
    private String headIcon; // 头像图片URL

    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickName; // 昵称

    @JSONField(name = "publishTime")
    @JsonProperty(value = "publishTime")
    private String publishTime; // 发布时间

    @JSONField(name = "dynamicInfoId")
    @JsonProperty(value = "dynamicInfoId")
    private Long dynamicInfoId; //动态内容id

    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content; //动态内容

    @JSONField(name = "address")
    @JsonProperty(value = "address")
    private String address; // 发布动态定位地址

    @JSONField(name = "likes")
    @JsonProperty(value = "likes")
    private Integer likes; // 点赞数

    @JSONField(name = "likeStatus")
    @JsonProperty(value = "likeStatus")
    private boolean likeStatus; // 点赞状态，true->已点赞，false->未点赞

    @JSONField(name = "applications")
    @JsonProperty(value = "applications")
    private Integer applications; //申请加微信数

    @JSONField(name = "applicationStatus")
    @JsonProperty(value = "applicationStatus")
    private boolean applicationStatus; // 申请加微信状态，true->已申请添加微信，false->未申请添加微信

    @ApiModelProperty(value = "附件存储文件类型")
    @JSONField(name = "dataType")
    @JsonProperty(value = "dataType")
    private String attacheFileDataType;
    
    @JSONField(name = "attacheFileUrlList")
    @JsonProperty(value = "attacheFileUrlList")
    private List<String> fileName;// 附件内容列表
}
