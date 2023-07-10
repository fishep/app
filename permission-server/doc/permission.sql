show databases;

create database permission;

use permission;

show tables;

drop table if exists `permissions`;
create table `permissions`
(
    `id`         bigint       not null comment 'id',
    `name`       varchar(63)  not null comment '权限名，唯一',
    `guard`      varchar(15)  not null comment '所属系统, 用guard来表示',
    `module`     varchar(15)  not null comment '所属模块',
    `locale`     json         not null comment '国际化，样例：{"en_US":"Adding user privileges","zh_CN":"添加用户权限"}',
    `comment`    varchar(255) not null default '' comment '备注信息',
    `created_at` bigint       not null default 0 comment '创建时间',
    `updated_at` bigint       not null default 0 comment '更新时间',
    primary key (`id`),
    unique key (`name`)
);

drop table if exists `roles`;
create table `roles`
(
    `id`         bigint       not null comment 'id',
    `name`       varchar(63)  not null comment '角色名，唯一',
    `guard`      varchar(15)  not null comment '所属系统, 用guard来表示',
    `is_system`  boolean      not null default false comment '是否系统角色，false：否，true：是，系统角色不允许删除',
    `locale`     json         not null comment '国际化，样例：{"en_US":"SuperAdmin","zh_CN":"超管"}',
    `comment`    varchar(255) not null default '' comment '备注信息',
    `created_at` bigint       not null default 0 comment '创建时间',
    `updated_at` bigint       not null default 0 comment '更新时间',
    primary key (`id`),
    unique key (`name`)
);

drop table if exists `user_roles`;
create table `user_roles`
(
    `user_type`  varchar(15) not null comment '用户类型，ADMIN：管理员，公司内部用户；CUSTOMER：客户，任何在商城注册的用户；SUPPLIER：供应商，公司的供应商。',
    `user_id`    bigint      not null comment '用户id',
    `role_id`    bigint      not null comment '角色id',
    `is_default` boolean     not null default false comment '是否是用户默认的角色，false：否，true：是，当用户有多个角色，需要选出一个时使用',
    `created_at` bigint      not null default 0 comment '创建时间',
    FOREIGN KEY foreign_user_has_roles_role_id (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    INDEX (`user_id`)
);

drop table if exists `role_permissions`;
create table `role_permissions`
(
    `role_id`       bigint not null comment '角色id',
    `permission_id` bigint not null comment '权限id',
    `created_at`    bigint not null default 0 comment '创建时间',
    FOREIGN KEY foreign_role_permissions_role_id (role_id) REFERENCES roles (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY foreign_role_permissions_permission_id (permission_id) REFERENCES permissions (id) ON DELETE CASCADE ON UPDATE CASCADE
);

insert into permissions(id, name, guard, module, locale, comment, created_at)
VALUES (1, 'permission.test.permission.check', 'ERP', 'PERMISSION', '{"en_US": "test permission check", "zh_CN": "测试权限检测"}', '测试权限', 1681987720955);
insert into permissions(id, name, guard, module, locale, comment, created_at)
VALUES (2, 'user.test.api.permission.apiPermission', 'ERP', 'PERMISSION', '{"en_US": "test api permission", "zh_CN": "测试接口权限"}', '测试权限', 1681987720955);
insert into permissions(id, name, guard, module, locale, comment, created_at)
VALUES (3, 'oms.order.admin.orders.create', 'ERP', 'PERMISSION', '{"en_US": "test order create permission", "zh_CN": "测试订单创建权限"}', '测试权限', 1681987720955);

insert into `roles`(`id`, `name`, `guard`, `is_system`, `locale`, `comment`, `created_at`)
values (1, 'SuperAdmin', 'ERP', true, '{"en_US": "SuperAdmin", "zh_CN": "超管"}', '系统角色，超级管理员，拥有所有的权限', 1681987720955);

insert into role_permissions(role_id, permission_id, created_at) VALUES (1, 1, 1681987720955);
insert into role_permissions(role_id, permission_id, created_at) VALUES (1, 2, 1681987720955);
insert into role_permissions(role_id, permission_id, created_at) VALUES (1, 3, 1681987720955);

insert into user_roles(`user_type`, `user_id`, `role_id`, `is_default`, `created_at`) values ('ADMIN', 1, 1, true, 1681987720955);

select * from user_roles;
select * from roles;
select * from role_permissions;
select * from permissions;

desc roles;

delete from user_roles where 1;
delete from roles where 1;
delete from role_permissions where 1;
delete from permissions where 1;