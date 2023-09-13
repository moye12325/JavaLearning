package com.moye.handler.security;

import com.alibaba.fastjson.JSON;
import com.moye.domain.ResponseResult;
import com.moye.enums.AppHttpCodeEnum;
import com.moye.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        //        响应给前端
        e.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(result));
    }
}
