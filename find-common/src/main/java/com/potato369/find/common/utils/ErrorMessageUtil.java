package com.potato369.find.common.utils;

import java.io.File;
import java.util.List;

import org.springframework.validation.ObjectError;

public class ErrorMessageUtil {
	
	public static String messageBuild(List<ObjectError> errorList) {
		StringBuffer message = new StringBuffer();
		for (int i = 0; i < errorList.size(); i++) {
			message.append(errorList.get(i).getDefaultMessage());
			if (errorList.size() == 1) {
				message.append("。");
			} else {
				if (i == errorList.size() - 1) {
					message.append("。");
				} else {
					message.append("，");
				}
			}
		}
		return message.toString();
	}
	
	public static String fileNameBuild(List<File> files, String fileString) {
		StringBuffer fileName = new StringBuffer();
		for (int i = 0; i < files.size(); i++) {
			fileName.append(fileString).append(files.get(i).getName());
			if (files.size() == 1) {
				fileName.append("");
			} else {
				if (i == files.size() - 1) {
					fileName.append("");
				} else {
					fileName.append("||");
				}
			}
		}
		return fileName.toString();
	}

}
