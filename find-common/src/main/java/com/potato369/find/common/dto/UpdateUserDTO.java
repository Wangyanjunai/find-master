package com.potato369.find.common.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class UpdateUserDTO extends AbstractDTO{

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
}
