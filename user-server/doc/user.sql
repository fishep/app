show databases ;

create database user;

use user;

show tables ;

drop table if exists `users`;
create table `users`
(
    `id` bigint not null comment 'id',
    `name` varchar(255) not null comment '用户名',
    `email` varchar(255) not null comment '邮箱',
    `password` varchar(255) not null comment '密码',
    `created_at` bigint not null default 0 comment '创建时间',
    `updated_at` bigint not null default 0 comment '更新时间',
    primary key (`id`),
    unique key (`name`),
    unique key (`email`)
);

insert into `users`(`id`, `name`, `email`, `password`, `created_at`) VALUES (1, "test", "test@email.com", "hash12345678", 1681987720955);
insert into `users`(`id`, `name`, `email`, `password`, `created_at`) VALUES (1572870916451598336, "username", "user@email.com", "***", 1681987720955);
delete from `users` where `id` = 1572870916451598336;
update `users` set `name`="usernameupdated", `email`="userupdated@email.com", `updated_at`=1664183128 where `id` = 1572870916451598336;
select * from `users`;
select * from `users` where `id` = 1572870916451598336;

drop table if exists `accounts`;
create table `accounts`
(
    `id` bigint not null comment 'id',
    `number` char(16) not null comment '账户编号',
    `user_id` bigint not null comment 'users.id',
    `name` varchar(255) not null comment '账户名',
    `currency` char(3) not null comment '开户币种,例如：USD,CNY',
    `amount` int unsigned not null comment '账户金额，单位：分',
    `quota` int unsigned not null comment '信用额度，单位：分',
    `created_at` bigint not null default 0 comment '创建时间',
    `updated_at` bigint not null default 0 comment '更新时间',
    primary key (`id`),
    unique key (`number`),
    foreign key accounts_user_id_foreign(`user_id`) REFERENCES users(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
);

insert into `accounts`(`id`, `number`, `user_id`, `name`, `currency`, `amount`, `quota`, `created_at`, `updated_at`) VALUES (1572870916451594444, "AC20220926000001",1572870916451598336, "account1", "CNY", 10000, 100, 1664183128, 1664183128);
insert into `accounts`(`id`, `number`, `user_id`, `name`, `currency`, `amount`, `quota`, `created_at`, `updated_at`) VALUES (1572870916451594445, "AC20220926000002",1572870916451598336, "account2", "USD", 100, 1000, 1664183128, 1664183128);
delete from `accounts` where `id` = 1572870916451594444;
update `accounts` set `updated_at` = 1664183128 where `id` = 1572870916451594444;
select * from `accounts`;
select * from `accounts` where id = 1572870916451594444;

