package com.fishep.user.aspect;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.user.annotation.AdminGuard;
import com.fishep.user.annotation.CustomerGuard;
import com.fishep.user.type.UserType;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author Fly.Fei
 * @Date 2023/5/26 16:57
 * @Desc
 **/
@Aspect
@Component
public class AuthGuardAspect {
    @Before("@annotation(adminGuard)")
    public void methodAdminGuard(AdminGuard adminGuard) {
        if (!UserType.ADMIN.toString().equals(UserContext.getCurrentUser().getType())) {
            throw new ServiceException("AuthGuardAspect Exception, The allowed UserContext is ADMIN, but current is " + UserContext.getCurrentUser().getType());
        }
    }

    @Before("@within(adminGuard)")
    public void classAdminGuard(AdminGuard adminGuard) {
        if (!UserType.ADMIN.toString().equals(UserContext.getCurrentUser().getType())) {
            throw new ServiceException("AuthGuardAspect Exception, The allowed UserContext is ADMIN, but current is " + UserContext.getCurrentUser().getType());
        }
    }

    @Before("@annotation(customerGuard)")
    public void methodCustomerGuard(CustomerGuard customerGuard) {
        if (!UserType.CUSTOMER.toString().equals(UserContext.getCurrentUser().getType())) {
            throw new ServiceException("AuthGuardAspect Exception, The allowed UserContext is CUSTOMER, but current is " + UserContext.getCurrentUser().getType());
        }
    }

    @Before("@within(customerGuard)")
    public void classCustomerGuard(CustomerGuard customerGuard) {
        if (!UserType.CUSTOMER.toString().equals(UserContext.getCurrentUser().getType())) {
            throw new ServiceException("AuthGuardAspect Exception, The allowed UserContext is CUSTOMER, but current is " + UserContext.getCurrentUser().getType());
        }
    }
}
