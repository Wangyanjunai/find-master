package com.potato369.find.admin.service;

import com.potato369.find.mbg.model.AttacheInfo;
import com.potato369.find.mbg.model.Dynamic;
import com.potato369.find.mbg.model.DynamicInfo;
import com.potato369.find.mbg.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DynamicService {
	/**
	 * 读取文件内容之后的插入数据库
	 * @param user
	 * @param dynamic
	 * @param dynamicInfo
	 * @param attacheInfo
	 * @return
	 * @throws Exception
	 */
	int insert(User user, Dynamic dynamic, DynamicInfo dynamicInfo, AttacheInfo attacheInfo) throws Exception;
	
	/**
	 * 读取表格文件
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	String ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
