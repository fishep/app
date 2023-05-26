package com.fishep.common.context;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 当前用户
 */
public class UserContext implements AutoCloseable {

    @AllArgsConstructor
    @Getter
    public class User {
        String type;
        Long id;
        String name;
    }

    private static UserContext instance = new UserContext();

    private static final ThreadLocal<User> ctx = new ThreadLocal<>();

    private UserContext() {
    }

    public void setUser(String type, String id, String name) {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContext repeat setup");
        }

        ctx.set(new User(type, Long.valueOf(id), name));
    }

    public void setUser(String type, Long id, String name) {
        if (ctx.get() != null) {
            throw new RuntimeException("UserContext repeat setup");
        }

        ctx.set(new User(type, id, name));
    }

    public User getUser() {
        return ctx.get();
    }

    @Override
    public void close() throws Exception {
        ctx.remove();
    }

    public static void setCurrentUser(String type, String id, String name) {
        instance.setUser(type, id, name);
    }

    public static void setCurrentUser(String type, Long id, String name) {
        instance.setUser(type, id, name);
    }

    public static User getCurrentUser() {
        return instance.getUser();
    }

    public static UserContext getInstance() {
        return instance;
    }

    public static void closeUserContext() throws Exception {
        instance.close();
    }
}
