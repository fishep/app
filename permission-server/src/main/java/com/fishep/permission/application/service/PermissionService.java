package com.fishep.permission.application.service;

import com.fishep.permission.application.dto.PermissionDTO;
import com.fishep.permission.application.dto.UserDTO;
import com.fishep.permission.contract.UserPermissionProvider;

/**
 * @Author Fly.Fei
 * @Date 2023/5/29 16:09
 * @Desc
 **/
public interface PermissionService extends UserPermissionProvider {

    PermissionDTO[] userPermissions(UserDTO userDTO);

}
