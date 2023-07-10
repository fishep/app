package com.fishep.permission.infrastructure.data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:20
 * @Desc
 **/
@Data
@TableName("user_roles")
public class UserRoleDO {

    private String userType;

    private Long userId;

    private Long roleId;

    private Boolean isDefault;

    private Long createdAt;

//    @TableField(exist = false)
//    private RoleDO[] roles;

}
