package com.fishep.client.interceptor;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.type.Guard;
import feign.RequestInterceptor;
import feign.RequestTemplate;
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

        UserContext.User user = UserContext.getCurrentUser();
        Guard guard = GuardContext.getCurrentGuard();
        requestTemplate.header("App-Guard", guard == null ? null : guard.toString());
        requestTemplate.header("App-User-Type", user == null ? null : user.getType());
        requestTemplate.header("App-User-Id", user == null ? null : String.valueOf(user.getId()));
        requestTemplate.header("App-User-Name", user == null ? null : user.getName());
        requestTemplate.header("Accept", "application/json, text/plain, */*");
//        requestTemplate.header("Accept-Language", "zh-CN");
    }
}
