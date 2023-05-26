package com.fishep.user.interfaces.controller.auth;

import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Fly.Fei
 * @Date 2023/5/26 11:14
 * @Desc
 **/
@SpringBootTest
@AutoConfigureMockMvc
class ShopRegisterControllerTest {

    private String uri = "/api/user/auth/shop/register";

    @Autowired
    private MockMvc mockMvc;

    private String requestJson;

    @BeforeEach
    public void setUp() {
        Map<String, String> request = new HashMap<>();
        request.put("name", "mock");
        request.put("email", "mock@test.com");
        request.put("password", "12345678");
        request.put("password_confirm", "12345678");

        requestJson = JSONObject.toJSONString(request);
    }

    @Test
    void register() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "SHOP");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }
}