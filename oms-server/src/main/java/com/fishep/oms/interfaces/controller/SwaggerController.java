package com.fishep.oms.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fly.Fei
 * @Date 2023/5/26 17:13
 * @Desc
 **/
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    //springdoc url: http://localhost:9030/swagger-ui/index.html
    @Operation(summary = "hello swagger!")
    public String show(@Parameter(description = "Parameter description") String desc) {
        return "hello swagger!";
    }

}
