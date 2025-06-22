package org.example.vibee.web.handler;

import org.example.vibee.vo.ResponseData;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;


/**
 * 全局异常处理
 */
@RestController
@Log4j2
public class GlobalExceptionController implements ErrorController {
    @RequestMapping("/error")
    public ResponseData handleError(HttpServletRequest request, HttpServletResponse response) {
        //错误状态码
        int status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        //错误消息
        String message = null;
        if (404 == status) {
            message = "访问资源不存在404";
        } else if (500 == status) {
            //服务器异常
            ServletException exception = (ServletException) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
            message = exception.getMessage();
        } else {
            message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        }
        if (StringUtils.isEmpty(message)) {
            message = "服务器运行异常";
        }
        log.info(message);
        return ResponseData.fail(10000 + status, message);
    }
}
