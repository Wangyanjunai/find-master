package com.potato369.find.admin.service.impl;

import com.potato369.find.admin.service.SensitiveWordsService;
import com.potato369.find.admin.utils.ExcelUtil;
import com.potato369.find.mbg.mapper.SensitiveWordsMapper;
import com.potato369.find.mbg.model.SensitiveWords;
import com.potato369.find.mbg.model.SensitiveWordsExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class SensitiveWordsServiceImpl implements SensitiveWordsService {

	private SensitiveWordsMapper sensitiveWordsMapperReader;

	private SensitiveWordsMapper sensitiveWordsMapperWriter;

	@Autowired
	public void setSensitiveWordsMapperReader(SensitiveWordsMapper sensitiveWordsMapperReader) {
		this.sensitiveWordsMapperReader = sensitiveWordsMapperReader;
	}

	@Autowired
	public void setSensitiveWordsMapperWriter(SensitiveWordsMapper sensitiveWordsMapperWriter) {
		this.sensitiveWordsMapperWriter = sensitiveWordsMapperWriter;
	}

	@Override
	@Transactional(readOnly = false)
	public String ajaxUploadExcel(HttpServletRequest request, HttpServletResponse response) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("upfile");
		if (file != null && file.isEmpty()) {
			log.error("文件不存在");
			return "文件不存在";
		}
		InputStream in = null;
		try {
			if (file != null) {
				in = file.getInputStream();
			}
		} catch (Exception e) {
			log.error("文件转换为文件流失败", e);
			return "文件转换为文件流失败";
		}
		List<List<Object>> listObj = null;
		try {
			if (in != null) {
				listObj = new ExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
			}
		} catch (Exception e) {
			log.error("转换文件流为对象列表失败", e);
			return "转换文件流为对象列表失败";
		}
		if (listObj != null) {
			for (List<Object> list : listObj) {
				if (list != null && !list.isEmpty() && list.size() >= 3) {
					Long id = Long.parseLong(String.valueOf(list.get(0)));
					String typeName = String.valueOf(list.get(1));
					String sensitiveWords = String.valueOf(list.get(2));
					log.info("id={}, typeName={}, sensitiveWords={}", id, typeName, sensitiveWords);
					SensitiveWordsExample example = new SensitiveWordsExample();
					example.createCriteria().andTypeNameEqualTo(typeName);
					List<SensitiveWords> sensitiveWordsList = this.sensitiveWordsMapperReader.selectByExample(example);
					SensitiveWords sensitiveWords2 = null;
					SensitiveWords sensitiveWords3 = new SensitiveWords();
					if (sensitiveWordsList != null && !sensitiveWordsList.isEmpty()) {
						sensitiveWords2 = sensitiveWordsList.get(0);
						sensitiveWords3.setTypeName(sensitiveWords2.getTypeName());
					} else {
						sensitiveWords3.setTypeName(typeName);
					}
					sensitiveWords3.setContent(sensitiveWords);
					this.sensitiveWordsMapperWriter.insertSelective(sensitiveWords3);
					log.info("sensitiveWords3={}", sensitiveWords3);
				}
			}
			log.info("size={}", listObj.size());
		}
		return "导入敏感词汇成功";
	}
}
