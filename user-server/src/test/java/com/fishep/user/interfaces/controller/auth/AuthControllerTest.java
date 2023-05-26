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
 * @Date 2023/5/26 14:51
 * @Desc
 **/
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    private String uri = "/api/user/auth/token/check";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void check1() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "ERP")
                .header("Authorization", "Bearer ver:1 guard:ERP type:ADMIN id:1 name:test");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void check2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "SHOP")
                .header("Authorization", "Bearer ver:1 guard:SHOP type:ADMIN id:1 name:test");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }
}