package com.fishep.common.type;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Id {

    protected Long value;

    public Id() {
        this(IdUtil.getSnowflakeNextId());
    }

}
