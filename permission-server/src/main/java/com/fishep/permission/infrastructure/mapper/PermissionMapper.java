package com.fishep.permission.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishep.permission.infrastructure.data.PermissionDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:19
 * @Desc
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO> {
}
