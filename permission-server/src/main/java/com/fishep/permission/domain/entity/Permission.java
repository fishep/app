package com.fishep.permission.domain.entity;

import com.fishep.common.type.Guard;
import com.fishep.common.type.Locale;
import com.fishep.common.type.Module;
import com.fishep.permission.type.PermissionId;
import com.fishep.permission.type.PermissionName;

import java.time.Instant;

/**
 * @Author fly.fei
 * @Date 2023/7/6 15:49
 * @Desc
 **/
public class Permission {

    private PermissionId id;

    private PermissionName name;

    private Guard guard;

    private Module module;

    private Locale locale;

    private String comment;

    private Instant createdAt;

    private Instant updatedAt;
}
