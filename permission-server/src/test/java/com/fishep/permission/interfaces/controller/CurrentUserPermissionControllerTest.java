package com.fishep.permission.interfaces.controller;

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

/**
 * @Author fly.fei
 * @Date 2023/7/10 17:03
 * @Desc
 **/
@SpringBootTest
@AutoConfigureMockMvc
class CurrentUserPermissionControllerTest {

    private String uri = "/api/permission/current/user/permissions";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void currentUserPermissions() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("App-Guard", "ERP")
                .header("App-User-Type", "ADMIN")
                .header("App-User-Id", "1")
                .header("App-User-Name", "root");

        ResultActions response = mockMvc.perform(requestBuilder);
        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(200)));
        response.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()));

        response.andDo(MockMvcResultHandlers.print());
    }

}