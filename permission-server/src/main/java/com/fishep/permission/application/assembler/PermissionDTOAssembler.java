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
        return null;
    }
}
