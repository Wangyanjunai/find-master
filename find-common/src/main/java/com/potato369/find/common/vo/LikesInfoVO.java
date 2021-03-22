package com.potato369.find.common.vo;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
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
public class LikesInfoVO {

	//点赞的用户id
    @JSONField(name = "userId")
    @JsonProperty(value = "userId")
    private Long userId;
    
    //点赞的用户头像
    @JSONField(name = "head")
    @JsonProperty(value = "head")
    private String head;

    //点赞时发送的消息内容
    @JSONField(name = "content")
    @JsonProperty(value = "content")
    private String content;
    
    //点赞的动态内容类型
    @JSONField(name = "attacheType")
    @JsonProperty(value = "attacheType")
    private String attacheType;
    
    //点赞的动态内容附件文件名称列表
    @JSONField(name = "filenameList")
    @JsonProperty(value = "filenameList")
    private List<String> attacheFilenameList;
}
