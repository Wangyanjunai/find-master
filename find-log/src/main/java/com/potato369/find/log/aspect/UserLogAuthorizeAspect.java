package com.potato369.find.log.aspect;

import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.enums.UserStatusEnum;
import com.potato369.find.common.exception.UserAuthorizeException;
import com.potato369.find.mbg.mapper.UserMapper;
import com.potato369.find.mbg.model.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class UserLogAuthorizeAspect {
	
	private UserMapper userMapperReader;

	@Autowired
	public void setUserMapperReader(UserMapper userMapperReader) {
		this.userMapperReader = userMapperReader;
	}
	
	@Pointcut("execution(public * com.potato369.find.log.controller.UserLogController.*(..))")
	public void verify() {
	}
	
	@Before("verify()")
	@SuppressWarnings("unchecked")
	public void doVerify() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		NativeWebRequest webRequest = new ServletWebRequest(request);
		Map<String, String> map = (Map<String, String>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		if (log.isDebugEnabled()) {
			log.debug("map={}", map);
		}
		Long userIdLong = Long.valueOf(map.get("id"));
		if (log.isDebugEnabled()) {
			log.debug("用户id={}", userIdLong);
		}
		User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
		if (user == null) {
			UserAuthorizeException userAuthorizeException = new UserAuthorizeException();
			userAuthorizeException.setMessage(ResultCode.USER_IS_NOT_EXIST.getMessage());
			log.error(userAuthorizeException.getMessage(), userAuthorizeException);
			throw userAuthorizeException;
		} else {
			if (user.getStatus().equals(UserStatusEnum.Abnormal.getCode().toString())) {
				UserAuthorizeException userAuthorizeException = new UserAuthorizeException();
				userAuthorizeException.setMessage(ResultCode.USER_ACCOUNT_IS_ABNORMAL.getMessage());
				log.error(userAuthorizeException.getMessage(), userAuthorizeException);
				throw userAuthorizeException;
			}
		}
	}
}
