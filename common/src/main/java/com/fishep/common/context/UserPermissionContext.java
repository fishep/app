package com.fishep.common.context;

public class UserPermissionContext {

    private static final ThreadLocal<String[]> ctx = new ThreadLocal<>();

    public static String[] get() {
        return ctx.get();
    }

    public static void set(String[] permissions) {
        ctx.set(permissions);
    }

    public static void remove() {
        ctx.remove();
    }
}
