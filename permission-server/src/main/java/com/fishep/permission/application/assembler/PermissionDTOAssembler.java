package com.fishep.permission.application.assembler;

import com.fishep.permission.application.dto.PermissionDTO;
import com.fishep.permission.domain.entity.Permission;
import org.springframework.stereotype.Component;

/**
 * @Author fly.fei
 * @Date 2023/7/6 16:13
 * @Desc
 **/
@Component
public class PermissionDTOAssembler {
    public PermissionDTO[] toPermissionDTOs(Permission[] permissions) {
        return null;
    }

    public String[] toPermissionStrings(Permission[] permissions) {
        String[] s = new String[permissions.length];

        for (int i = 0; i < s.length; i++) {
            s[i] = permissions[i].getName().getValue();
        }

        return s;
    }
}
