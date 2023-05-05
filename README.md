# app
> web 应用

- common               公共模块         port: 
- server               公共模块         port: 
- client               公共模块         port: 
- 基础服务              基础服务         port: 9000 - 9009  
  - gateway            网关            port: 9000 - 9001  REDIS_DATABASE:0
- user-server          用户管理模块      port: 9010 - 9019  REDIS_DATABASE:1
- permission-server    权限管理模块      port: 9020 - 9029  REDIS_DATABASE:2

> 依赖关系
```shell
#用户模块依赖关系
       server  <----------------  user-server
      /                          /
common  <-----------  user-common
      \                          \
       client  <----------------  user-client
```

```shell
#权限模块依赖关系
       server  <----------------  permission-server
      /                          /
common  <-----------  permission-common
      \                          \
       client  <----------------  permission-client
```