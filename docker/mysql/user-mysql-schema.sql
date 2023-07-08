/******************************************/
/*   数据库全名 = user   */
/******************************************/

CREATE DATABASE IF NOT EXISTS user;

USE user;

CREATE USER 'user' IDENTIFIED BY 'user';

GRANT ALL ON user.* TO user;

FLUSH PRIVILEGES;

/******************************************/
/*   表名称 = users   */
/******************************************/
CREATE TABLE `users`
(
    `type`         varchar(15) not null comment '用户类型，ADMIN：管理员，公司内部用户；CUSTOMER：客户，任何在商城注册的用户；SUPPLIER：供应商，公司的供应商。',
    `id`           bigint      not null comment 'id',
    `name`         varchar(63) not null comment '用户名',
    `email`        varchar(63)          default null comment '邮箱',
    `phone_number` varchar(63)          default null comment '电话号码',
    `password`     varchar(255)         default null comment '密码',
    `created_at`   bigint      not null default 0 comment '创建时间',
    `updated_at`   bigint      not null default 0 comment '更新时间',
    primary key (`type`, `id`),
    unique key (`type`, `name`),
    unique key (`type`, `email`),
    unique key (`type`, `phone_number`)
) PARTITION BY LIST COLUMNS (type) (
    PARTITION p0 VALUES IN ('ADMIN'),
    PARTITION p1 VALUES IN ('CUSTOMER'),
    PARTITION p2 VALUES IN ('SUPPLIER')
    );

insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("ADMIN", 1, "root", "root@email.com", "16888888888", "hash12345678", 1681987720955);
insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("CUSTOMER", 1, "root", "root@email.com", "16888888888", "hash12345678", 1681987720955);
