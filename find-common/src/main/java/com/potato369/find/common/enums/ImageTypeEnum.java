package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//参考
//https://blog.csdn.net/zhuyu19911016520/article/details/88714289
// https://www.kamilet.cn/image-file-formats/
public enum ImageTypeEnum implements CodeEnum<Integer> {
	TYPE_PNG_WITH_PREFIX(0, "image/png", ".png"),
	TYPE_JPG_WITH_PREFIX(1, "image/jpeg", ".jpg"),
	TYPE_JPEG_WITH_PREFIX(2, "image/jpeg", ".jpeg"),
	TYPE_GIF_WITH_PREFIX(3, "image/gif", ".gif"),
	TYPE_BMP_WITH_PREFIX(4, "image/bmp", ".bmp"),
	TYPE_TIF_WITH_PREFIX(5, "image/tiff", ".tif"),
	TYPE_TIFF_WITH_PREFIX(6, "image/tiff", ".tiff"),
	TYPE_SVG_WITH_PREFIX(7, "image/svg+xml", ".svg"),
	;
    private Integer code;
    
    private String type;

    private String name;
}
