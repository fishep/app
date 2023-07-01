package com.fishep.user.client.service.impl;

import cn.hutool.extra.spring.SpringUtil;
import com.fishep.common.exception.ServiceException;
import com.fishep.common.type.Result;
import com.fishep.user.client.feign.TestFeign;
import com.fishep.user.client.service.TestService;
import com.fishep.user.type.Message;
import org.springframework.stereotype.Component;

@Component
public class TestServiceFeignImpl implements TestService {
    @Override
    public String api() {
        TestFeign testFeign = SpringUtil.getBean(TestFeign.class);

        Result<String> result = testFeign.api();

        return result.toString();
    }

    @Override
    public String apiPermission() {
        TestFeign testFeign = SpringUtil.getBean(TestFeign.class);

        Result<String> result = testFeign.apiPermission();
        if (result == null || result.getData() == null || result.getData().isEmpty()) {
            throw new ServiceException(Message.__(Message.TEST_SERVICE_EXCEPTION));
        }

        return result.toString();
    }
}
