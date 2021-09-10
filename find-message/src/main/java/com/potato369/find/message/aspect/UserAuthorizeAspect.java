package com.potato369.find.message.aspect;

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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
@Slf4j
@Scope("request")
public class UserAuthorizeAspect {

    private UserMapper userMapperReader;

    @Autowired
    public void setUserMapperReader(UserMapper userMapperReader) {
        this.userMapperReader = userMapperReader;
    }

    @Pointcut("execution(public * com.potato369.find.message.controller.*Controller.*(..))"
            + "&& !execution(public * com.potato369.find.message.controller.DruidStatController.druidStat(..))"
            + "&& !execution(public * com.potato369.find.message.controller.MessageController.messages(..))"
            + "&& !execution(public * com.potato369.find.message.controller.MessageController.deleteApplications(..))")
    public void verify() {
    }

    @Before("verify()")
    @SuppressWarnings("unchecked")
    public CommonResult<Map<String, Object>> doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        NativeWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        assert map != null;
        Long userIdLong = Long.valueOf((String) map.get("id"));
        if (log.isDebugEnabled()) {
            log.debug("用户id={}", userIdLong);
        }
        User user = this.userMapperReader.selectByPrimaryKey(userIdLong);
        if (Objects.isNull(user)) {
            return CommonResult.failed(ResultCode.USER_IS_NOT_EXIST.getMessage());
        } else {
            if (user.getStatus().equals(UserStatusEnum.Abnormal.getCode().toString())) {
                return CommonResult.failed(ResultCode.USER_ACCOUNT_IS_ABNORMAL.getMessage());
            }
        }
        return null;
    }
}
