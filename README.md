# 微服务应用
> 微服务应用

- 网关                  所有网关            port: 9000 - 9009
  - erp-gateway        后台网关            port: 9000 - 9001
  - shop-gateway       商城网关            port: 9002 - 9003
  - -gateway           其他网关            port: 9004 - 9007
  - open-gateway       开放网关（第三方应用） port: 9008 - 9009

- common               公共模块         port: 
- server               公共模块         port: 
- client               公共模块         port:
- data-server          基础数据模块      port: 9010 - 9019  REDIS_DATABASE:1
- user-server          用户管理模块      port: 9020 - 9029  REDIS_DATABASE:2
- permission-server    权限管理模块      port: 9030 - 9039  REDIS_DATABASE:3
- ems-server           企业管理模块      port: 9040 - 9049  REDIS_DATABASE:4
- crm-server           客户管理模块      port: 9050 - 9059  REDIS_DATABASE:5
- pdt-server           产品管理模块      port: 9060 - 9069  REDIS_DATABASE:6
- oms-server           订单管理模块      port: 9070 - 9079  REDIS_DATABASE:7
- pms-server           采购管理模块      port: 9080 - 9089  REDIS_DATABASE:8
- wms-server           库存管理模块      port: 9090 - 9099  REDIS_DATABASE:9
- lms-server           物流管理模块      port: 9100 - 9109  REDIS_DATABASE:10
- cms-server           报关管理模块      port: 9110 - 9119  REDIS_DATABASE:11
- fms-server           财务管理模块      port: 9120 - 9129  REDIS_DATABASE:12

> 依赖关系
```shell
#业务模块依赖关系
       client  <----------------  xx-client
      /                          /
common  <--------------  xx-common
      \                          \
       server  <----------------  xx-server
                                 /
                        yy-client
```

> api 分类  
- 所有api = 后台api（erp-gateway，注解ErpGuard）+ 商城api（shop-gateway，注解ShopGuard）+ 开放api（open-gateway，OpenGuard）+ 其他api
  - 后台api = 访客api（不能在登录的状态下访问）+ 认证api（必须在登录的状态下访问，只提供ADMIN的登录）
  - 商城api = 访客api（不能在登录的状态下访问）+ 认证api（必须在登录的状态下访问，提供ADMIN，CUSTOMER的登录）+ 其他api（是否登录都可以）
    - 认证api = 认证ADMIN的api（AdminGuard） + 认证CUSTOMER的api（CustomerGuard）
  - 开放api = 
  - 其他api =

# package
```shell
mvn clean package -DskipTests -Pdev
mvn clean package -DskipTests -Ptest
mvn clean package -DskipTests -Pprod
```

# 基础服务
> 基础服务，redis, mysql, nacos, sentinel等等
```shell
docker-compose -f docker-compose-base.yml up
```

# 微服务，业务应用
```shell
docker-compose -f docker-compose.yml up
docker-compose up
```

# 建议
- 执行 docker-compose -f docker-compose-base.yml up  启动基础服务
- 在ide上直接运行微服务程序，debug，开发完成
- 运行 mvn clean package -DskipTests -Pdev 打包完成，执行 java -jar example.jar, 运行微服务，验证jar包可以正常运行
- 运行 mvn clean package -DskipTests -Pprod 打包完成， docker-compose up 在docker中启动所有服务，验证运行正常
