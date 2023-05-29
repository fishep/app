package com.fishep.user.client.service.impl;

import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
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
    public String api() {
        // @TODO 如果使用异步，有多线程问题，取不到请求数据， 怎么传递全局数据呢？
//        CompletableFuture<Result> f = CompletableFuture.supplyAsync(() -> testFeign.api());
//
//        Result ret;
//        try {
//            ret = f.get();
//        } catch (Exception e) {
//            throw new ServiceException(e);
//        }
//
//        return ret.toString();

        Result<String> result = testFeign.api();

        return result.toString();
    }

    @Override
    public String apiPermission() {
        Result<String> result = testFeign.apiPermission();
        if (result == null || result.getData() == null || result.getData().isEmpty()) {
            throw new ServiceException(result.getMessage());
        }

        return result.toString();
    }
}
