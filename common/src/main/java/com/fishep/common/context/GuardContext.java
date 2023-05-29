package com.fishep.common.context;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;

/**
 * 当前guard
 */
public class GuardContext implements AutoCloseable {

    private static GuardContext instance = new GuardContext();

    private static final ThreadLocal<Guard> ctx = new ThreadLocal<>();

    private GuardContext() {
    }

    public void setGuard(String name) {
        if (ctx.get() != null) {
            throw new ServiceException("GuardContext repeat setup");
        }

        ctx.set(Guard.valueOf(name));
    }

    public void setGuard(Guard guard) {
        if (ctx.get() != null) {
            throw new ServiceException("GuardContext repeat setup");
        }

        ctx.set(guard);
    }

    public Guard getGuard() {
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        ctx.remove();
    }

    public static GuardContext getInstance() {
        return instance;
    }

    public static Guard getCurrentGuard() {
        return instance.getGuard();
    }

    public static void setCurrentGuard(String name) {
        instance.setGuard(name);
    }

    public static void setCurrentGuard(Guard guard) {
        instance.setGuard(guard);
    }

    public static void closeGuardContext() throws Exception {
        instance.close();
    }

}
