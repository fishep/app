package com.fishep.user.client.service.impl;

import com.fishep.user.client.feign.TestFeign;
import com.fishep.user.client.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class TestServiceFeignImpl implements TestService {

    @Autowired
    @Lazy
    TestFeign testFeign; // @TODO 必须要赖加载，不然会循环依赖，why？

    @Override
    public String apiString() {
        /**
         * @TODO 这里必须用异步，why？
         * @TODO 有两个线程池会随机执行此处，why？ boundedElastic-5 reactor-http-nio-1
         */
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> testFeign.apiString());

        String s = null;
        try {
            s = f.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return s;
    }
}
