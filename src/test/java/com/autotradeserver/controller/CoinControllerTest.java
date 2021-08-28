package com.autotradeserver.controller;

import com.autotradeserver.config.Configs;
import com.autotradeserver.service.domain.StreamData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class CoinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StreamData streamData;

    @Test
    @DisplayName("Web MVC Test")
    void defaultRestApiTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/coin"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Web MVC Test")
    void defaultTradeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/coin/trade"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
