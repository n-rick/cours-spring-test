package com.example.coursspringtest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class HomeControllerTest {
//    HomeController homeController = new HomeController();

//    public HomeControllerTest(MockMvc mockMvc) {
//        this.mockMvc = mockMvc;
//    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        mockMvc
                .perform(get("/home").param("nom", "toto"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("nom", "toto"))
                .andExpect(view().name("home"));
    }
}
