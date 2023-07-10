package com.fishep.server.interceptor;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.context.UserPermissionContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ContextHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 清理上下文，确保万无一失 （其实这里可以不用执行）
        contextClear();

        String guard = request.getHeader("App-Guard");
        if (guard == null || guard.isEmpty()) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_NULL));
        }
        GuardContext.setCurrentGuard(guard);

        String userType = request.getHeader("App-User-Type");
        String userId = request.getHeader("App-User-Id");
        String userName = request.getHeader("App-User-Name");
        if (userType != null && !userType.isEmpty() && userId != null && !userId.isEmpty() && userName != null && !userName.isEmpty()) {
            UserContext.setCurrentUser(userType, userId, userName);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        contextClear();

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private void contextClear() throws Exception {
        UserContext.closeUserContext();
        GuardContext.closeGuardContext();
        UserPermissionContext.remove();
    }
}
