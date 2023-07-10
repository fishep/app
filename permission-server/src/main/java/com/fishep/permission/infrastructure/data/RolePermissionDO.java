package com.fishep.permission.infrastructure.data;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:20
 * @Desc
 **/
@Data
@TableName("role_permissions")
public class RolePermissionDO {

    private Long roleId;

    private Long permissionId;

    private Long createdAt;

}
