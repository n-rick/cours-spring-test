package com.example.coursspringtest.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PersonneControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPersonnes() throws Exception {

        mockMvc
                .perform(get("/personnes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].nom").value("Wick"));
    }

    @Test
    void testGetPersonne_OK() throws Exception {
        mockMvc
                .perform(get("/personnes/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("Wick"));
    }

    @Test
    void testGetPersonne_NotFound() throws Exception {
        mockMvc
                .perform(get("/personnes/{id}", 100))
                .andExpect(status().isNotFound());
    }

//    @Test
//    void testAddPersonnePersonne() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testDeletePersonne() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testAddPersonneIntPersonne() {
//        fail("Not yet implemented");
//    }
}
