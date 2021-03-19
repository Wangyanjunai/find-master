package com.potato369.find.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
//参考
//https://wenku.baidu.com/view/b9ca1f2058fb770bf78a558d.html
//https://blog.csdn.net/zhuyu19911016520/article/details/88714289
public enum AudioTypeEnum implements CodeEnum<Integer> {
	TYPE_MID_WITH_PREFIX(0, "audio/midi", ".mid"),
	TYPE_MIDI_WITH_PREFIX(1, "audio/midi", ".midi"),
	TYPE_WAV_WITH_PREFIX(2, "audio/x-wav", ".wav"),
	TYPE_MP3_WITH_PREFIX(3, "audio/mpeg", ".mp3"),
	TYPE_AMR_WITH_PREFIX(4, "audio/amr", ".amr"),
	;
    private Integer code;
    
    private String type;

    private String name;
}
