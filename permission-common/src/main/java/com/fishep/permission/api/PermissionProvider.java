package com.fishep.permission.api;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 15:06
 * @Desc
 **/
public interface PermissionProvider {

    String[] currentUserPermissions();

}
