package com.fishep.user.client.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
import com.fishep.user.client.feign.AuthFeign;
import com.fishep.user.client.service.AuthService;
import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AuthServiceFeignImpl implements AuthService {

    @Override
    public TokenCheckResponse check(String guard, String token) {
//        return syncCheck(guard, token);
        return asyncCheck(guard, token);
    }

    public TokenCheckResponse syncCheck(String guard, String token){
        AuthFeign authFeign = SpringUtil.getBean(AuthFeign.class);

        Result<TokenCheckResponse> result = authFeign.check(guard, token);
        if (result == null || result.getData() == null) {
            throw new ServiceException("Token authentication passed differently");
        }
        return result.getData();
    }

    /**
     * 在 reactor 里调用 ，必须用异步
     * @TODO 这里必须用异步，why？被gateway调用的必须要用异步？
     * @TODO 有两个线程池会随机执行此处，why？ boundedElastic-5 reactor-http-nio-1
     *
     * @TODO 此处有bug， https://github.com/spring-cloud/spring-cloud-openfeign/issues/475
     * @TODO 应用启动之后，第一个请求必须是直接转发（例如登录，注册）的， 不能先调用 openfeign client， 以便feign client 成功初始化，在实际运行时没问题，单个接口测试就bug
     * @TODO 这个bug 莫名其妙的好了
     *
     */
    public TokenCheckResponse asyncCheck(String guard, String token){

        System.out.println("asyncCheck");

        AuthFeign authFeign = SpringUtil.getBean(AuthFeign.class);

        Result<TokenCheckResponse> result;
        CompletableFuture<Result<TokenCheckResponse>> future = CompletableFuture.supplyAsync(() -> authFeign.check(guard, token));
        try {
            result = future.get();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        if (result == null || result.getData() == null) {
            throw new ServiceException("Token authentication passed differently, " + result.getMessage());
        }
        return result.getData();
    }

}