package org.example.handler;

import com.alibaba.fastjson.JSON;
import org.example.domain.ResponseResult;
import org.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/5 16:35
 */
@Component
//这个类只处理授权异常，不处理认证异常
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    //第一个参数是请求对象，第二个参数是响应对象，第三个参数是异常对象。把异常封装成认证的对象，然后封装到handle方法
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您没有权限进行访问");
        //把上面那行拿到的result对象转换为JSON字符串
        String json = JSON.toJSONString(result);
        //WebUtils是我们在utils目录写好的类
        WebUtils.renderString(response,json);

    }
}
