package com.potato369.find.common.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <pre>
 * @PackageName com.potato369.find.common.dto
 * @ClassName CommentDTO
 * @Desc 评论数据
 * @WebSite https://www.potato369.com
 * @Author admin
 * @Date 2021/07/12 10:58
 * @CreateBy IntelliJ IDEA 2021.1 (Ultimate Edition)
 * @Copyright Copyright (c) 2016 ~ 2028 版权所有 (C) 土豆互联科技(深圳)有限公司 https://www.potato369.com All Rights Reserved。
 * </pre>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommentDTO extends AbstractDTO{

	//动态内容id
	@ApiModelProperty(value = "动态内容id")
	@NotNull(message = "动态内容id不能为空。")
	private Long dynamicInfoId;
	
	//评论内容
	@ApiModelProperty(value = "评论内容")
	@NotEmpty(message = "评论内容不能为空。")
	private String content;
}
