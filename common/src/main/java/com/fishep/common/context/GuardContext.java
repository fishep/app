package com.fishep.common.context;

/**
 * 当前guard
 */
public class GuardContext implements AutoCloseable {

    private static GuardContext instance = new GuardContext();

    private static final ThreadLocal<String> ctx = new ThreadLocal<>();

    private GuardContext() {
    }

    public void setGuard(String name) {
        if (ctx.get() != null) {
            throw new RuntimeException("GuardContext repeat setup");
        }

        ctx.set(name);
    }

    public String getGuard() {
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        ctx.remove();
    }

    public static GuardContext getInstance() {
        return instance;
    }

    public static String getCurrentGuard() {
        return instance.getGuard();
    }

    public static void setCurrentGuard(String name) {
        instance.setGuard(name);
    }

    public static void closeGuardContext() throws Exception {
        instance.close();
    }

}
