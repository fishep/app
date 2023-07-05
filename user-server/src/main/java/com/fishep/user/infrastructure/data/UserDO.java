package com.fishep.user.infrastructure.data;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class UserDO {

    private Long id;

    /**
     *     ADMIN // 管理员，公司内部用户
     *  CUSTOMER // 客户，任何在商城注册的用户
     *  SUPPLIER // 供应商，公司的供应商
     */
    private String type;

    private String name;

    private String email;

    private String phoneNumber;

    private String password;

    private Long createdAt;

    private Long updatedAt;

}
