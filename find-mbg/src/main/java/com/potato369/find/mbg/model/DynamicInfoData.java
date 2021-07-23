package com.potato369.find.mbg.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * @PackageName com.potato369.find.mbg.model
 * @ClassName DynamicInfoData
 * @Desc 类实现的功能描述
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2020/12/31 15:05
 * @CreateBy Intellij IDEA 2020.3 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DynamicInfoData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "头像图片地址")
    private String headIcon;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "发布时间")
    private String publishTime;

    @ApiModelProperty(value = "动态内容id")
    private Long dynamicInfoId;

    @ApiModelProperty(value = "动态内容")
    private String content;

    @ApiModelProperty(value = "是否公开定位，0->否，1->是")
    private String publishStatus;

    @ApiModelProperty(value = "是否话题，0->否,1->是")
    private String isTopic;

    @ApiModelProperty(value = "话题标题")
    private String topicTitle;

    @ApiModelProperty(value = "是否匿名，0->否，1->是")
    private String isAnonymous;

    @ApiModelProperty(value = "评论数")
    private Integer comments;

    @ApiModelProperty(value = "定位（国家）")
    private String country;

    @ApiModelProperty(value = "定位（省份）")
    private String province;

    @ApiModelProperty(value = "定位（城市）")
    private String city;

    @ApiModelProperty(value = "定位（区/县）")
    private String district;

    @ApiModelProperty(value = "定位（其它）")
    private String other;

    @ApiModelProperty(value = "定位（经度）")
    private Double longitude;

    @ApiModelProperty(value = "定位（纬度）")
    private Double latitude;

    @ApiModelProperty(value = "点赞数")
    private Integer likes;

    @ApiModelProperty(value = "申请加微信数")
    private Integer applications;

    @ApiModelProperty(value = "附件存储文件类型")
    private String attacheFileDataType;

    @ApiModelProperty(value = "附件存储文件名称列表")
    private String fileName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
