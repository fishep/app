package com.fishep.user.client.service.impl;

import com.fishep.common.type.Result;
import com.fishep.user.client.feign.TestFeign;
import com.fishep.user.client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.CompletableFuture;

@Component
public class TestServiceFeignImpl implements TestService {

    @Autowired
    @Lazy
    TestFeign testFeign; // @TODO 必须要赖加载，不然会循环依赖，why？

    @Override
    public String api() {
        /**
         * @TODO 这里必须用异步，why？
         * @TODO 有两个线程池会随机执行此处，why？ boundedElastic-5 reactor-http-nio-1
         */
        CompletableFuture<Result> f = CompletableFuture.supplyAsync(() -> testFeign.api());

        Result ret;
        try {
            ret = f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ret.toString();
    }

    @Override
    public String apiPermission() {
        // @TODO 如果服务端有权限校验，则无法通过，怎么传递权限数据呢？ 从当前请求获取，在设置到到feign? 麻烦了点，有没有统一设置的
        CompletableFuture<Result<String>> f = CompletableFuture.supplyAsync(() -> testFeign.apiPermission());

        Result<String> ret;
        try {
            ret = f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ret.toString();
    }
}
