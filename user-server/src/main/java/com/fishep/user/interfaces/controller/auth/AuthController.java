package com.fishep.user.interfaces.controller.auth;

import com.fishep.user.response.auth.TokenCheckResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/token/check")
    public TokenCheckResponse check(@RequestHeader("Authorization") String authorization) {

//        @TODO 校验 token
        Pattern compile = Pattern.compile("Bearer\\sauthToken_(\\d+)");
        Matcher matcher = compile.matcher(authorization);
        if (matcher.matches()) {
            String id = matcher.group(1);
            return new TokenCheckResponse(Boolean.TRUE, Long.valueOf(id));
        }
        return new TokenCheckResponse(Boolean.FALSE, null);
    }

}
