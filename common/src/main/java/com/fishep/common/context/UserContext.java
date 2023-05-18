package com.fishep.common.context;

/**
 * 当前用户
 */
public class UserContext implements AutoCloseable {

    private static UserContext instance = new UserContext();

    private static final ThreadLocal<Long> ctx = new ThreadLocal<>();

    private UserContext() {
    }

    public void setUser(String id) {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContext repeat setup");
        }

        ctx.set(Long.valueOf(id));
    }

    public void setUser(Long id) {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContext repeat setup");
        }

        ctx.set(id);
    }

    public Long getUser() {
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        ctx.remove();
    }

    public static void setCurrentUser(String id) {
        instance.setUser(id);
    }

    public static void setCurrentUser(Long id) {
        instance.setUser(id);
    }

    public static Long getCurrentUser() {
        return instance.getUser();
    }

    public static UserContext getInstance() {
        return instance;
    }

    public static void closeUserContext() throws Exception {
        instance.close();
    }
}
