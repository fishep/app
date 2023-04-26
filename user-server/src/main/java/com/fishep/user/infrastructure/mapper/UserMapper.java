package com.fishep.user.infrastructure.mapper;

import com.fishep.user.infrastructure.data.UserDO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into `users`(`id`, `name`, `email`, `password`, `created_at`, `updated_at`) VALUES (#{user.id}, #{user.name}, #{user.email}, #{user.password}, #{user.createdAt}, #{user.updatedAt})")
    Boolean insert(@Param("user") UserDO user);

    @Delete("delete from `users` where `id` = #{id}")
    Boolean deleteById(@Param("id") Long id);

    @Delete("delete from `users` where `name` = #{name}")
    Boolean deleteByName(@Param("name") String name);

    @Delete("delete from `users` where `email` = #{email}")
    Boolean deleteByEmail(@Param("email") String email);

    @Update("update `users` set `name`=#{user.name}, `email`=#{user.email}, `password`=#{user.password}, `created_at`=#{user.createdAt}, `updated_at`=#{user.updatedAt} where `id` = #{user.id}")
    Boolean update(@Param("user") UserDO user);

    @Update("update `users` set `name`=#{user.name}, `email`=#{user.email}, `updated_at`=#{user.updatedAt} where `id` = #{user.id}")
    Boolean updateNameAndEmail(@Param("user") UserDO user);

    @Update("update `users` set `password`=#{user.password}, `updated_at`=#{user.updatedAt} where `id` = #{user.id}")
    Boolean updatePassword(@Param("user") UserDO user);

    @Select("select `id`, `name`, `email`, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `id` = #{id}")
    UserDO selectById(@Param("id") Long id);

    @Select("select `id`, `name`, `email`, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `name` = #{name}")
    UserDO selectByName(@Param("name") String name);

    @Select("select `id`, `name`, `email`, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `email` = #{email}")
    UserDO selectByEmail(@Param("email") String email);
}
