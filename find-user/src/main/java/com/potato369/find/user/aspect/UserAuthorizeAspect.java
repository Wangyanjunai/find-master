package com.potato369.find.user.aspect;

import com.potato369.find.common.api.CommonResult;
import com.potato369.find.common.api.ResultCode;
import com.potato369.find.common.enums.UserStatusEnum;
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
public class UserAuthorizeAspect {
	
	private UserMapper userMapperReader;

	@Autowired
	public void setUserMapperReader(UserMapper userMapperReader) {
		this.userMapperReader = userMapperReader;
	}
	
	@Pointcut("execution(public * com.potato369.find.user.controller.*Controller.*(..))"
			+ "&& !execution(public * com.potato369.find.user.controller.UserController.*register(..))"
			+ "&& !execution(public * com.potato369.find.user.controller.UserController.*login(..))"
			+ "&& !execution(public * com.potato369.find.user.controller.TagController.*list(..))"
			+ "&& !execution(public * com.potato369.find.user.controller.ProfessionController.*list(..))")
	public void verify() {
	}
	
	@Before("verify()")
	@SuppressWarnings("unchecked")
	public CommonResult<Map<String, Object>> doVerify() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		NativeWebRequest webRequest = new ServletWebRequest(request);
		Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
		Long userIdLong = Long.valueOf((String)map.get("id"));
		if (log.isDebugEnabled()) {
			log.debug("用户id={}", userIdLong);
		}
		User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
		if (user == null) {
			return CommonResult.failed(ResultCode.USER_IS_NOT_EXIST.getMessage());
		} else {
			if (user.getStatus().equals(UserStatusEnum.Abnormal.getCode().toString())) {
				return CommonResult.failed(ResultCode.USER_ACCOUNT_IS_ABNORMAL.getMessage());
			}
		}
		return null;
	}
}
