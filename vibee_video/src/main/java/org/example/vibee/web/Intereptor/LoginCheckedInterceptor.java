package org.example.vibee.web.Intereptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录检查拦截器
 */
@Log4j2
public class LoginCheckedInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.debug("当前请求路径为:"+url);
        return true;
    }
}
