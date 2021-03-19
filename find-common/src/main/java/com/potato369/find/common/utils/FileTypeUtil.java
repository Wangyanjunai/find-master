package com.potato369.find.common.utils;

import com.potato369.find.common.enums.AudioTypeEnum;
import com.potato369.find.common.enums.ImageTypeEnum;

public class FileTypeUtil {
	// 获取文件的后缀名 统一转为小写再进行判断是否是常见图片格式文件
	public static boolean isImageType(String contentType, String name) {
		if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_PNG_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_JPG_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_GIF_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_JPEG_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_BMP_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_TIFF_WITH_PREFIX.getName())) {
			return true;
		}  else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_TIF_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(ImageTypeEnum.TYPE_SVG_WITH_PREFIX.getName())) {
			return true;
		} else {
			return false;
		}
	}
	
	// 获取文件的后缀名 统一转为小写再进行判断是否是常见音频格式文件
	public static boolean isAudioType(String contentType, String name) {
		if (name.toLowerCase().endsWith(AudioTypeEnum.TYPE_MID_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(AudioTypeEnum.TYPE_MIDI_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(AudioTypeEnum.TYPE_WAV_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(AudioTypeEnum.TYPE_MP3_WITH_PREFIX.getName())) {
			return true;
		} else if (name.toLowerCase().endsWith(AudioTypeEnum.TYPE_AMR_WITH_PREFIX.getName())) {
			return true;
		} else {
			return false;
		}
	}
}
