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

/**
 * @Author Fly.Fei
 * @Date 2023/5/26 11:14
 * @Desc
 **/
@SpringBootTest
@AutoConfigureMockMvc
class ShopLoginControllerTest {

    private String loginUri = "/api/user/auth/shop/login";

    @Autowired
    private MockMvc mockMvc;

    private String requestJson1;
    private String requestJson2;

    @BeforeEach
    public void setUp() {
        Map<String, String> request = new HashMap<>();
        request.put("identity", "test");
        request.put("token", "12345678");

        requestJson1 = JSONObject.toJSONString(request);

        request.put("token", "1234");
        requestJson2 = JSONObject.toJSONString(request);
    }

    @Test
    void loginCustomer1() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri + "/customer")
                .content(requestJson1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "SHOP");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void loginCustomer2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri + "/customer")
                .content(requestJson2)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "SHOP");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void loginAdmin1() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri + "/admin")
                .content(requestJson1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "SHOP");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void loginAdmin2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri + "/admin")
                .content(requestJson2)
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