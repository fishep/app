package com.fishep.permission.infrastructure.assembler;

import com.fishep.common.type.Guard;
import com.fishep.common.type.Module;
import com.fishep.permission.domain.entity.Permission;
import com.fishep.permission.infrastructure.data.PermissionDO;
import com.fishep.permission.type.PermissionId;
import com.fishep.permission.type.PermissionName;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:24
 * @Desc
 **/
@Component
public class PermissionDOAssembler {

    public Permission[] toPermissionArray(List<PermissionDO> doList) {
        Permission[] arr = new Permission[doList.size()];
        for (int i = 0; i < doList.size(); i++) {
            arr[i] = toPermission(doList.get(i));
        }

        return arr;
    }

    public List<Permission> toPermissionList(List<PermissionDO> doList) {
        LinkedList<Permission> list = new LinkedList<>();

        for (PermissionDO pdo : doList) {
            list.add(toPermission(pdo));
        }

        return list;
    }

    public Permission toPermission(PermissionDO pdo) {
        Permission permission = new Permission(new PermissionId(pdo.getId()), new PermissionName(pdo.getName()));
        permission.setGuard(Guard.valueOf(pdo.getGuard()));
        permission.setModule(Module.valueOf(pdo.getModule()));
        permission.setLocale(pdo.getLocale());
        permission.setComment(pdo.getComment());
        permission.setCreatedAt(Instant.ofEpochMilli(pdo.getCreatedAt()));
        permission.setUpdatedAt(Instant.ofEpochMilli(pdo.getUpdatedAt()));
        return permission;
    }


}
