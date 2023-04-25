# app
> web 应用

- common        公共模块         port: 
- base          基础设施         port: 9000 - 9009  REDIS_DATABASE:0
  - gateway     网关            port: 9000 - 9001 
- user          用户管理模块      port: 9010 - 9019  REDIS_DATABASE:1
- permission    权限管理模块      port: 9020 - 9029  REDIS_DATABASE:2


> 模块的划分标准
