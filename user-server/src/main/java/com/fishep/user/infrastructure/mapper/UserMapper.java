package com.fishep.user.infrastructure.mapper;

import com.fishep.user.infrastructure.data.UserDO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into `users`(`id`, `type`, `name`, `email`, `phone_number`, `password`, `created_at`, `updated_at`) VALUES (#{user.id}, #{user.type}, #{user.name}, #{user.email}, #{user.phoneNumber}, #{user.password}, #{user.createdAt}, #{user.updatedAt})")
    Boolean insert(@Param("user") UserDO user);

    @Delete("delete from `users` where `type` = #{user.type} and `id` = #{user.id}")
    Boolean deleteById(@Param("user") UserDO user);

    @Delete("delete from `users` where `type` = #{user.type} and `name` = #{user.name}")
    Boolean deleteByName(@Param("user") UserDO user);

    @Delete("delete from `users` where `type` = #{user.type} and `email` = #{user.email}")
    Boolean deleteByEmail(@Param("user") UserDO user);

    @Delete("delete from `users` where `type` = #{user.type} and `phone_number` = #{user.phoneNumber}")
    Boolean deleteByPhoneNumber(@Param("user") UserDO user);

    @Update("update `users` set `type`=#{user.type}, `name`=#{user.name}, `email`=#{user.email}, `phone_number`=#{user.phoneNumber}, `password`=#{user.password}, `created_at`=#{user.createdAt}, `updated_at`=#{user.updatedAt} where `type` = #{user.type} and `id` = #{user.id}")
    Boolean update(@Param("user") UserDO user);

    @Update("update `users` set `name`=#{user.name}, `email`=#{user.email}, `updated_at`=#{user.updatedAt} where `type` = #{user.type} and `id` = #{user.id}")
    Boolean updateNameAndEmail(@Param("user") UserDO user);

    @Update("update `users` set `password`=#{user.password}, `updated_at`=#{user.updatedAt} where `type` = #{user.type} and `id` = #{user.id}")
    Boolean updatePassword(@Param("user") UserDO user);

    @Select("select `id`, `type`, `name`, `email`, `phone_number` as phoneNumber, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `type` = #{user.type} and `id` = #{user.id}")
    UserDO selectById(@Param("user") UserDO user);

    @Select("select `id`, `type`, `name`, `email`, `phone_number` as phoneNumber, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `type` = #{user.type} and `name` = #{user.name}")
    UserDO selectByName(@Param("user") UserDO user);

    @Select("select `id`, `type`, `name`, `email`, `phone_number` as phoneNumber, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `type` = #{user.type} and `email` = #{user.email}")
    UserDO selectByEmail(@Param("user") UserDO user);

    @Select("select `id`, `type`, `name`, `email`, `phone_number` as phoneNumber, `password`, `created_at` as createdAt, `updated_at` as updatedAt from `users` where `type` = #{user.type} and `phone_number` = #{user.phoneNumber}")
    UserDO selectByPhoneNumber(@Param("user") UserDO user);
}
