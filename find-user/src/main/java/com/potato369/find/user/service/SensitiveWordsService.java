package com.potato369.find.user.service;

import com.potato369.find.mbg.model.SensitiveWords;

public interface SensitiveWordsService {

	SensitiveWords checkHasSensitiveWords(String content);
}
