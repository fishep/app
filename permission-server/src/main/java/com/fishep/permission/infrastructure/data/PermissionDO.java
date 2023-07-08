package com.fishep.permission.infrastructure.data;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fishep.common.type.Locale;
import lombok.Data;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:20
 * @Desc
 **/
@Data
@TableName("permissions")
public class PermissionDO {
    private Long id;

    private String name;

    private String guard;

    private String module;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Locale locale;

    private String comment;

    private Long createdAt;

    private Long updatedAt;
}
