package com.fishep.permission.infrastructure.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fishep.permission.infrastructure.dao.PermissionDao;
import com.fishep.permission.infrastructure.data.PermissionDO;
import com.fishep.permission.infrastructure.mapper.PermissionMapper;
import com.fishep.permission.type.PermissionId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * @Author fly.fei
 * @Date 2023/7/7 10:17
 * @Desc
 **/
@Component
public class PermissionDaoMybatisPlusImpl implements PermissionDao {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Boolean insert(PermissionDO p) {
        if (p.getName() == null || p.getGuard() == null || p.getModule() == null || p.getLocale() == null) {
            return Boolean.FALSE;
        }

        if (p.getId() == null) {
            p.setId(new PermissionId().getValue());
        }

        if (p.getCreatedAt() == null) {
            p.setCreatedAt(Instant.now().toEpochMilli());
        }

        int r = permissionMapper.insert(p);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean delete(PermissionDO p) {
        QueryWrapper<PermissionDO> wrapper = new QueryWrapper<>();

        if (p.getId() != null) {
            wrapper.eq("id", p.getId());
        } else if (p.getName() != null) {
            wrapper.eq("name", p.getName());
        } else {
            return Boolean.FALSE;
        }

        int r = permissionMapper.delete(wrapper);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean update(PermissionDO p) {
        if (p.getId() == null) {
            return Boolean.FALSE;
        }

        p.setUpdatedAt(Instant.now().toEpochMilli());

        int r = permissionMapper.updateById(p);

        return r == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public PermissionDO select(PermissionDO p) {
        QueryWrapper<PermissionDO> wrapper = new QueryWrapper<>();

        if (p.getId() != null) {
            wrapper.eq("id", p.getId());
        } else if (p.getName() != null) {
            wrapper.eq("name", p.getName());
        } else {
            return null;
        }

        return permissionMapper.selectOne(wrapper);
    }
}
