package com.fishep.user.interfaces.controller.auth;

import net.minidev.json.JSONObject;
import org.hamcrest.Matchers;
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

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {

    private String loginUri = "/api/user/auth/login/";
    private String passwordLoginUri = "/api/user/auth/login/passwordLogin";
    private String codeLoginUri = "/api/user/auth/login/codeLogin";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("identity", "test");
        request.put("token", "12345678");
        String requestJson = JSONObject.toJSONString(request);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void loginFail() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("identity", "test");
        request.put("token", "123456789");
        String requestJson = JSONObject.toJSONString(request);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(loginUri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(1000)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.nullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void passwordLogin() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("identity", "test");
        request.put("token", "12345678");
        String requestJson = JSONObject.toJSONString(request);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(passwordLoginUri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

    @Test
    void codeLogin() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("identity", "test");
        request.put("token", "1234");
        String requestJson = JSONObject.toJSONString(request);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(codeLoginUri)
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }
}