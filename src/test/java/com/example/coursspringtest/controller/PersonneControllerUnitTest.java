package com.example.coursspringtest.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.example.coursspringtest.model.Personne;
import com.example.coursspringtest.service.PersonneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
class PersonneControllerUnitTest {

    @MockBean
    private PersonneService personneService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetPersonnes() throws Exception {
        List<Personne> fakePersonnes = List.of(
                new Personne("Wick", "John", 45),
                new Personne("Linus", "Benjamin", 30),
                new Personne("Pradel", "Jacques", 65)
        );
        when(personneService.findAll()).thenReturn(fakePersonnes);
        mockMvc
                .perform(get("/personnes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].nom").value("Wick"));
        verify(personneService, times(1)).findAll();
    }

    @Test
    void testGetPersonne_Ok() throws Exception {
      int id = 2;
        var pers = new Personne("Wick", "John", 45);
        when(personneService.findById(id)).thenReturn(pers);
        mockMvc
                .perform(get("/personnes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("Wick"));
        verify(personneService, times(1)).findById(id);
    }

    @Test
    void testGetPersonne_notFound() throws Exception {
        int id = 123;
        when(personneService.findById(id)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND, "Personne non trouvée"));
        mockMvc
                .perform(get("/personnes/{id}", id))
                .andExpect(status().isNotFound());
        verify(personneService, times(1)).findById(id);
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
