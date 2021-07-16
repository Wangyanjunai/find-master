package com.potato369.find.message.service;

import com.potato369.find.mbg.model.SensitiveWords;

public interface SensitiveWordsService {

	SensitiveWords checkHasSensitiveWords(String content);
}
