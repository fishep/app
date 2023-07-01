package com.fishep.server.aspect;

import com.fishep.common.context.GuardContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.common.type.Message;
import com.fishep.server.annotation.ErpGuard;
import com.fishep.server.annotation.MultiGuard;
import com.fishep.server.annotation.OpenGuard;
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
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{multiGuard, GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@within(multiGuard)")
    public void classGuard(MultiGuard multiGuard) {
        if (!Arrays.asList(multiGuard.value()).contains(GuardContext.getCurrentGuard())) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{multiGuard, GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@annotation(erpGuard)")
    public void methodErpGuard(ErpGuard erpGuard) {
        if (GuardContext.getCurrentGuard() != Guard.ERP) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"ERP", GuardContext.getCurrentGuard()}));

        }
    }

    @Before("@within(erpGuard)")
    public void classErpGuard(ErpGuard erpGuard) {
        if (GuardContext.getCurrentGuard() != Guard.ERP) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"ERP", GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@annotation(shopGuard)")
    public void methodShopGuard(ShopGuard shopGuard) {
        if (GuardContext.getCurrentGuard() != Guard.SHOP) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"SHOP", GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@within(shopGuard)")
    public void classShopGuard(ShopGuard shopGuard) {
        if (GuardContext.getCurrentGuard() != Guard.SHOP) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"SHOP", GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@annotation(openGuard)")
    public void methodOpenGuard(OpenGuard openGuard) {
        if (GuardContext.getCurrentGuard() != Guard.OPEN) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"OPEN", GuardContext.getCurrentGuard()}));
        }
    }

    @Before("@within(openGuard)")
    public void classOpenGuard(OpenGuard openGuard) {
        if (GuardContext.getCurrentGuard() != Guard.OPEN) {
            throw new ServiceException(Message.__(Message.GUARD_CONTEXT_MISMATCH, new Object[]{"OPEN", GuardContext.getCurrentGuard()}));
        }
    }
}
