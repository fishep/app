package com.fishep.server.aspect;

import com.fishep.common.context.GuardContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.server.annotation.OpenGuard;
import com.fishep.server.annotation.ErpGuard;
import com.fishep.server.annotation.MultiGuard;
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

    @Before("@annotation(multiGuard)")
    public void methodGuard(MultiGuard multiGuard) {
        if (!Arrays.asList(multiGuard.value()).contains(GuardContext.getCurrentGuard())) {
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + multiGuard + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(multiGuard)")
    public void classGuard(MultiGuard multiGuard) {
        if (!Arrays.asList(multiGuard.value()).contains(GuardContext.getCurrentGuard())) {
            throw new ServiceException("GuardAspect Exception, The allowed contexts are " + multiGuard + ", but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(erpGuard)")
    public void methodErpGuard(ErpGuard erpGuard){
        if (GuardContext.getCurrentGuard() != Guard.ERP){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are ERP, but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(erpGuard)")
    public void classErpGuard(ErpGuard erpGuard){
        if (GuardContext.getCurrentGuard() != Guard.ERP){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are ERP, but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(shopGuard)")
    public void methodShopGuard(ShopGuard shopGuard){
        if (GuardContext.getCurrentGuard() != Guard.SHOP){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are SHOP, but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(shopGuard)")
    public void classShopGuard(ShopGuard shopGuard){
        if (GuardContext.getCurrentGuard() != Guard.SHOP){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are SHOP, but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@annotation(openGuard)")
    public void methodOpenGuard(OpenGuard openGuard){
        if (GuardContext.getCurrentGuard() != Guard.OPEN){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are OPEN, but current is " + GuardContext.getCurrentGuard());
        }
    }

    @Before("@within(openGuard)")
    public void classOpenGuard(OpenGuard openGuard){
        if (GuardContext.getCurrentGuard() != Guard.OPEN){
            throw new ServiceException("GuardAspect Exception, The allowed contexts are OPEN, but current is " + GuardContext.getCurrentGuard());
        }
    }
}
