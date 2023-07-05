package com.fishep.user.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fishep.user.infrastructure.data.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author fly.fei
 * @Date 2023/7/5 11:52
 * @Desc
 **/
@Mapper
public interface UserPlusMapper extends BaseMapper<UserDO> {
}
