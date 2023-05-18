package com.fishep.server.interceptor;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ContextHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String guard = request.getHeader("App-Guard");
        if (guard == null || guard.isEmpty()){
            throw new ServiceException("ContextHandlerInterceptor App-Guard is empty");
        }
        GuardContext.setCurrentGuard(guard);

        String userId = request.getHeader("App-User-Id");
        if (userId != null && !userId.isEmpty()){
            UserContext.setCurrentUser(userId);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        UserContext.closeUserContext();
        GuardContext.closeGuardContext();

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
