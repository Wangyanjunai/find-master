package com.potato369.find.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SensitiveWordsService {

    /**
     * 读取表格文件
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response);
}
