package com.fishep.user.interfaces.controller.auth;

import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${server.port}")
    private Integer port;

    @PostMapping("/token/check")
    public TokenCheckResponse check(@RequestHeader("App-Guard") String AppGuard, @RequestHeader("Authorization") String authorization) {

//        @TODO 校验 jwt
//        String jwt = "ver:1"; // jwt 版本号，如果希望以前发布的失效，把数字加1
//        jwt += " guard:" + guard ;
//        jwt += " type:" + type ;
//        jwt += " id:" + user.getId().getValue();
//        jwt += " name:" + user.getName().getValue();
        String[] jwt = authorization.split(" ");
        String version = jwt[1].split(":")[1];
        String guard = jwt[2].split(":")[1];
        String userType = jwt[3].split(":")[1];
        String userId = jwt[4].split(":")[1];
        String userName = jwt[5].split(":")[1];

        if (AppGuard == null || !AppGuard.equals(guard)){
            return null;
        }

        return new TokenCheckResponse(Integer.valueOf(version), guard, userType, Long.valueOf(userId), userName);
    }

}
