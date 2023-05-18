package com.fishep.server.aspect;

import com.fishep.common.context.GuardContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.server.annotation.AppGuard;
import com.fishep.server.annotation.ErpGuard;
import com.fishep.server.annotation.Guard;
import com.fishep.server.annotation.ShopGuard;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author Fly.Fei
 * @Date 2023/5/18 17:31
 * @Desc
 **/

@Aspect
@Component
public class GuardAspect {

    @Before("@annotation(guard)")
    public void methodGuard(Guard guard) {
        if (!Arrays.asList(guard.value()).contains(GuardContext.getCurrentGuard())) {
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + guard + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(guard)")
    public void classGuard(Guard guard) {
        if (!Arrays.asList(guard.value()).contains(GuardContext.getCurrentGuard())) {
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + guard + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(erpGuard)")
    public void methodErpGuard(ErpGuard erpGuard){
        String eg = com.fishep.common.type.Guard.ERP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(erpGuard)")
    public void classErpGuard(ErpGuard erpGuard){
        String eg = com.fishep.common.type.Guard.ERP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(shopGuard)")
    public void methodShopGuard(ShopGuard shopGuard){
        String eg = com.fishep.common.type.Guard.SHOP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(shopGuard)")
    public void classShopGuard(ShopGuard shopGuard){
        String eg = com.fishep.common.type.Guard.SHOP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(appGuard)")
    public void methodAppGuard(AppGuard appGuard){
        String eg = com.fishep.common.type.Guard.APP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(appGuard)")
    public void classAppGuard(AppGuard appGuard){
        String eg = com.fishep.common.type.Guard.APP.toString();
        if (!eg.equals(GuardContext.getCurrentGuard())){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + eg + ", but current is " + GuardContext.getCurrentGuard());
        }
    }
}
