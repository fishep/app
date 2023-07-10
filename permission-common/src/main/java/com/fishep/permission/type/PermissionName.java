package com.fishep.permission.type;

import lombok.Getter;

/**
 * @Author fly.fei
 * @Date 2023/7/7 11:07
 * @Desc
 **/
@Getter
public class PermissionName {
    private String value;

    public PermissionName(String value) {
        this.value = value;
    }
}
