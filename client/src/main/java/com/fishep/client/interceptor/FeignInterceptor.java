package com.fishep.client.interceptor;

import com.fishep.common.context.UserContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;
//import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;

//import java.util.Enumeration;

@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            String value = request.getHeader(name);
//            requestTemplate.header(name, value);
//        }

        Long id = UserContext.getInstance().currentUser();
        requestTemplate.header("App-User-Id", id == null ? null : String.valueOf(id));
        requestTemplate.header("Accept", "application/json, text/plain, */*");
        requestTemplate.header("Accept-Language", "zh-CN");
    }
}
