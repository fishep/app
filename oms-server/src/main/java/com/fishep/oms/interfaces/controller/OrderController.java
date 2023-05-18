package com.fishep.oms.interfaces.controller;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.permission.annotation.Permission;
import com.fishep.server.annotation.ErpGuard;
import com.fishep.server.annotation.Guard;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fly.Fei
 * @Date 2023/5/8 18:30
 * @Desc
 **/
//@Guard({"SHOP", "APP"})
@ErpGuard
@RestController
@RequestMapping("/order")
public class OrderController {

    //springdoc url: http://localhost:9030/swagger-ui/index.html
    @PostMapping("/orders")
    @Permission("oms.order.orders.create")
    @Operation(summary = "create order")
//    @Guard({"SHOP", "APP"})
//    @ErpGuard
    public String create(@Parameter(description = "order description") String desc) {

        String guard = GuardContext.getCurrentGuard();
        Long id = UserContext.getCurrentUser();
        System.out.println("guard: " + guard);
        System.out.println("id: " + id);

        return "hello order!";
    }

}
