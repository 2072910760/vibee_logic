package org.example.vibee.web.Intereptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.example.vibee.vo.ResponseData;
import org.springframework.web.servlet.HandlerInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登录检查拦截器
 */
@Log4j2
public class LoginCheckedInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.debug("当前请求路径为:"+url);

        HttpSession session = request.getSession(false); // false表示不创建新session
        if (session != null && session.getAttribute("currentUser") != null) {
            // 用户已登录，放行
            return true;
        }

        // 用户未登录，返回错误信息
        log.warn("未授权的请求: {}", url);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        ResponseData responseData = ResponseData.fail(401, "用户未登录或会话已过期，请重新登录");
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(responseData));

        return false;
    }
}
