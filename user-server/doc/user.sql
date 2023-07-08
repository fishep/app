show databases;

create database user;

use user;

show tables;

drop table if exists `users`;
create table `users`
(
    `type`         varchar(15) not null comment '用户类型，ADMIN：管理员，公司内部用户；CUSTOMER：客户，任何在商城注册的用户；SUPPLIER：供应商，公司的供应商。',
    `id`           bigint      not null comment 'id',
    `name`         varchar(63) not null comment '用户名',
    `email`        varchar(63)          default null comment '邮箱',
    `phone_number` varchar(63)          default null comment '电话号码',
    `password`     varchar(255)         default null comment '密码',
    `created_at`   bigint      not null default 0 comment '创建时间',
    `updated_at`   bigint      not null default 0 comment '更新时间',
    primary key (`type`,`id`),
    unique key (`type`, `name`),
    unique key (`type`, `email`),
    unique key (`type`, `phone_number`)
)PARTITION BY LIST COLUMNS(type) (
    PARTITION p0 VALUES IN('ADMIN'),
    PARTITION p1 VALUES IN('CUSTOMER'),
    PARTITION p2 VALUES IN('SUPPLIER')
    );

explain select * from users;
explain SELECT * FROM users WHERE `type` = 'ADMIN';
explain SELECT * FROM users PARTITION (p0, p1) WHERE `type` = 'ADMIN';
explain SELECT * FROM users PARTITION (p2) WHERE `type` = 'ADMIN';
select * from information_schema.PARTITIONS where TABLE_SCHEMA = "user" and TABLE_NAME = "users";

select * from users;
insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("ADMIN", 1, "root", "root@email.com", "16888888888", "hash12345678", 1681987720955);
insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("ADMIN", 2, "root2", "root2@email.com", "16888888882", "hash12345678", 1681987720955);
insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("CUSTOMER", 1, "root", "root@email.com", "16888888888", "hash12345678", 1681987720955);
insert into `users`(`type`, `id`, `name`, `email`, `phone_number`, `password`, `created_at`) VALUES ("CUSTOMER", 2, "root2", "root2@email.com", "16888888882", "hash12345678", 1681987720955);

update `users` set `email`="userupdated@email.com", `phone_number`="10087", `updated_at`=1664183128 where `id` = 2;
update `users` set `email`="userupdated@email.com", `phone_number`="10087", `updated_at`=1664183128 where `type` = "ADMIN" and `id` = 2;
delete from `users` where 1;
delete from `users` where `id` = 2;
delete from `users` where `type` = "ADMIN" and `id` = 2 ;

