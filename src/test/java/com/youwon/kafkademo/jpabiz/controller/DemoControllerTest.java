package com.youwon.kafkademo.jpabiz.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youwon.kafkademo.jpabiz.entity.DemoEntity;
import com.youwon.kafkademo.jpabiz.service.DemoEntityService;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemoEntityService demoEntityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testPostMethod() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/saveData2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"value\":\"test value\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists()) // id 값이 존재하는지 확인
                .andExpect(jsonPath("$.value").value("test value")); // value 값이 "test value"인지 확인
    }
}
