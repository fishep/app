package com.fishep.oms.interfaces.controller;

import com.fishep.common.context.GuardContext;
import com.fishep.common.context.UserContext;
import com.fishep.common.type.Guard;
import com.fishep.permission.annotation.Permission;
import com.fishep.server.annotation.ShopGuard;
import com.fishep.user.annotation.AdminGuard;
import com.fishep.user.annotation.CustomerGuard;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Fly.Fei
 * @Date 2023/5/8 18:30
 * @Desc
 **/
@ShopGuard
@RestController
@RequestMapping("/order")
public class OrderController {

    @CustomerGuard
    @PostMapping("/orders")
    public String create() {
        Guard guard = GuardContext.getCurrentGuard();
        UserContext.User user = UserContext.getCurrentUser();
        System.out.println("guard: " + guard);
        System.out.println("type: " + user.getType());
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());

        return "客户自己下单完成！";
    }

    @AdminGuard
    @PostMapping("/admin/orders")
    @Permission("oms.order.admin.orders.create")
    public String createByAdmin() {

        Guard guard = GuardContext.getCurrentGuard();
        UserContext.User user = UserContext.getCurrentUser();
        System.out.println("guard: " + guard);
        System.out.println("type: " + user.getType());
        System.out.println("id: " + user.getId());
        System.out.println("name: " + user.getName());

        return "管理员帮客户下单完成！";
    }

}
