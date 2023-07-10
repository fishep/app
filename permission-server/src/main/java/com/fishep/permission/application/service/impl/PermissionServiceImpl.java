package com.fishep.permission.application.service.impl;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Guard;
import com.fishep.permission.application.assembler.PermissionDTOAssembler;
import com.fishep.permission.application.dto.PermissionDTO;
import com.fishep.permission.application.dto.UserDTO;
import com.fishep.permission.application.service.PermissionService;
import com.fishep.permission.domain.entity.Permission;
import com.fishep.permission.domain.repository.PermissionRepository;
import com.fishep.permission.type.Message;
import com.fishep.user.type.UserId;
import com.fishep.user.type.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:25
 * @Desc
 **/
@Component
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionDTOAssembler permissionDTOAssembler;

    @Override
    public String[] contextUserPermissions() {
        Guard guard = GuardContext.getCurrentGuard();
        UserContext.User user = UserContext.getCurrentUser();

        if (guard == null || user == null) {
            throw new ServiceException(Message.__(Message.CONTEXT_IS_NULL));
        }

        Permission[] permissions = permissionRepository.findUserPermissions(UserType.valueOf(user.getType()), new UserId(user.getId()), guard);
        String[] ps = permissionDTOAssembler.toPermissionStrings(permissions);

        return ps;
    }

    @Override
    public PermissionDTO[] userPermissions(UserDTO userDTO) {
        Permission[] permissions = permissionRepository.findUserPermissions(userDTO.getUserType(), userDTO.getUserId(), userDTO.getGuard());

        return permissionDTOAssembler.toPermissionDTOs(permissions);
    }
}
