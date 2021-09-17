package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UpdateUserDTO extends AbstractDTO {

    /**
     * <pre>
     * nickname：昵称
     * </pre>
     */
    @ApiModelProperty(value = "昵称")
    @JSONField(name = "nickname")
    @JsonProperty(value = "nickname")
    private String nickname;

    /**
     * <pre>
     * autograph：签名
     * </pre>
     */
    @ApiModelProperty(value = "签名")
    @JSONField(name = "autograph")
    private String autograph;

    /**
     * <pre>
     * weixinId：微信号
     * </pre>
     */
    @ApiModelProperty(value = "微信号")
    @JSONField(name = "weixinId")
    @JsonProperty(value = "weixinId")
    private String weixinId;

    /**
     * <pre>
     * year：出生年代
     * </pre>
     */
    @ApiModelProperty(value = "出生年份")
    @JSONField(name = "year")
    @JsonProperty(value = "year")
    private String year;

    /**
     * <pre>
     * month：出生月份
     * </pre>
     */
    @ApiModelProperty(value = "出生月份")
    @JSONField(name = "month")
    @JsonProperty(value = "month")
    private String month;

    /**
     * <pre>
     * date：出生日期
     * </pre>
     */
    @ApiModelProperty(value = "出生日期")
    @JSONField(name = "date")
    @JsonProperty(value = "date")
    private String date;

    /**
     * <pre>
     * constellation：星座
     * </pre>
     */
    @ApiModelProperty(value = "星座")
    @JSONField(name = "constellation")
    @JsonProperty(value = "constellation")
    private String constellation;

    /**
     * <pre>
     * professionId：职业编号
     * </pre>
     */
    @ApiModelProperty(value = "职业编号")
    @JSONField(name = "professionId")
    @JsonProperty(value = "professionId")
    private Long professionId;

    /**
     * <pre>
     * tag1：标签1
     * </pre>
     */
    @ApiModelProperty(value = "标签1")
    @JSONField(name = "tag1")
    @JsonProperty(value = "tag1")
    private String tag1;

    /**
     * <pre>
     * tag2：标签2
     * </pre>
     */
    @ApiModelProperty(value = "标签2")
    @JSONField(name = "tag2")
    @JsonProperty(value = "tag2")
    private String tag2;

    /**
     * <pre>
     * tag3：标签3编号
     * </pre>
     */
    @ApiModelProperty(value = "标签3")
    @JSONField(name = "tag3")
    @JsonProperty(value = "tag3")
    private String tag3;

    /**
     * <pre>
     * tag4：标签4
     * </pre>
     */
    @ApiModelProperty(value = "标签4")
    @JSONField(name = "tag4")
    @JsonProperty(value = "tag4")
    private String tag4;

    /**
     * <pre>
     * tag5：标签5
     * </pre>
     */
    @ApiModelProperty(value = "标签5")
    @JSONField(name = "tag5")
    @JsonProperty(value = "tag5")
    private String tag5;
    
    /**
     * <pre>
     * tag5：标签5
     * </pre>
     */
    @ApiModelProperty(value = "更新时间")
    @JSONField(name = "updateTime")
    @JsonProperty(value = "updateTime")
    private Date updateTime;
}
