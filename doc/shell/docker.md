```shell
docker build -t app/user-server:1.0-SNAPSHOT .
docker run --env <key>=<value>
docker build --build-arg HTTPS_PROXY=https://my-proxy.example.com .
docker build --build-arg JAR_FILE=./target/user-server-1.0-SNAPSHOT.jar -t app/user-server .
docker run -it --rm --name user-server app/user-server
docker run -it --name user-server app/user-server:1.0-SNAPSHOT
docker run -it --rm --name user-server app/user-server:1.0-SNAPSHOT
docker run -it --rm -p9020:9020 --name user-server app/user-server:1.0-SNAPSHOT
docker run -it --rm -p9021:9020 --name user-server app/user-server:1.0-SNAPSHOT
docker run -it --rm --entrypoint sh --name user-server app/user-server
docker run -it --entrypoint sh --name user-server app/user-server:1.0-SNAPSHOT
docker run -it --rm --entrypoint sh --name user-server app/user-server:1.0-SNAPSHOT
docker rmi app/user-server:1.0-SNAPSHOT
docker images


cd erp-gateway; docker build -t app/erp-gateway:1.0-SNAPSHOT .
docker run -it --rm -p9000:9000 --name erp-gateway-9000 app/erp-gateway:1.0-SNAPSHOT
docker run -it --rm -p9001:9000 --name erp-gateway-9001 app/erp-gateway:1.0-SNAPSHOT

cd shop-gateway; docker build -t app/shop-gateway:1.0-SNAPSHOT .
docker run -it --rm -p9002:9002 --name shop-gateway-9002 app/shop-gateway:1.0-SNAPSHOT
docker run -it --rm -p9003:9002 --name shop-gateway-9003 app/shop-gateway:1.0-SNAPSHOT

cd open-gateway; docker build -t app/open-gateway:1.0-SNAPSHOT .
docker run -it --rm -p9008:9008 --name open-gateway-9008 app/open-gateway:1.0-SNAPSHOT
docker run -it --rm -p9009:9008 --name open-gateway-9009 app/open-gateway:1.0-SNAPSHOT

cd user-server; docker build -t app/user-server:1.0-SNAPSHOT .
docker run -it --rm -p9020:9020 --name user-server-9020 app/user-server:1.0-SNAPSHOT
docker run -it --rm -p9021:9020 --name user-server-9021 app/user-server:1.0-SNAPSHOT

cd permission-server; docker build -t app/permission-server:1.0-SNAPSHOT .
docker run -it --rm -p9030:9030 --name permission-server-9030 app/permission-server:1.0-SNAPSHOT
docker run -it --rm -p9031:9030 --name permission-server-9031 app/permission-server:1.0-SNAPSHOT

cd oms-server; docker build -t app/oms-server:1.0-SNAPSHOT .
docker run -it --rm -p9070:9070 --name oms-server-9070 app/oms-server:1.0-SNAPSHOT
docker run -it --rm -p9071:9070 --name oms-server-9071 app/oms-server:1.0-SNAPSHOT

docker rmi app/erp-gateway:1.0-SNAPSHOT
docker rmi app/shop-gateway:1.0-SNAPSHOT
docker rmi app/open-gateway:1.0-SNAPSHOT
docker rmi app/user-server:1.0-SNAPSHOT
docker rmi app/permission-server:1.0-SNAPSHOT
docker rmi app/oms-server:1.0-SNAPSHOT
```

# 优化策略
- 1，减少指令的行数
- 2, 改变不频繁的内容往前放
- 3, 编译和运行分离
- 4, 删除不需要的依赖项
- 5, 避免凭证构建到镜像