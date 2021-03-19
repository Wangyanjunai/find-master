package com.potato369.find.message.config.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

// 推送实体类
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PushBean {
	
    // alert：必填，通知内容，这里指定了，则会覆盖上级统一指定的alert信息；内容可以为空字符串，则表示不展示到通知栏。
    private String alert;
    
    //title：可选，android专用，通知标题，如果指定了，则通知里原来展示App名称的地方，将展示成这个字段。
    private String title;
    
    //extras：可选，扩展字段，这里自定义 JSON 格式的 Key / Value 信息，以供业务使用。针对部分厂商跳转地址异常，可通过 third_url_encode 兼容处理 "extras": { "third_url_encode": true //notification - android - extras ，true表示需要极光encode处理，值需要是布尔类型 } "extras": { "third_url_encode": false //notification - android - extras ，false，或者无此字段，表示不需要极光encode处理，值需要是布尔类型 }
    private Map<String, String> extras;
}

