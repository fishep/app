package com.fishep.permission.domain.entity;

import com.fishep.common.type.Guard;
import com.fishep.common.type.Locale;
import com.fishep.common.type.Module;
import com.fishep.permission.type.PermissionId;
import com.fishep.permission.type.PermissionName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

/**
 * @Author fly.fei
 * @Date 2023/7/6 15:49
 * @Desc
 **/
@Getter
public class Permission {

    private PermissionId id;

    private PermissionName name;

    private Guard guard;

    private Module module;

    private Locale locale;

    private String comment;

    private Instant createdAt;

    private Instant updatedAt;

    public Permission(PermissionId id, PermissionName name) {
        this.id = id;
        this.name = name;
    }

    public void setGuard(Guard guard) {
        this.guard = guard;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
