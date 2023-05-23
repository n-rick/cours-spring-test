package com.example.coursspringtest.service;

import com.example.coursspringtest.dao.PersonneRepository;
import com.example.coursspringtest.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class PersonneServiceUnitTest {

    @Autowired
    private PersonneService personneService;

    @MockBean
    private PersonneRepository personneRepository;

    @Test
    void testFindAll() {
        List<Personne> fakePersonnes = List.of(
                new Personne("Wick", "John", 45),
                new Personne("Linus", "Benjamin", 30),
                new Personne("Pradel", "Jacques", 65)
        );
        when(personneRepository.findAll()).thenReturn(fakePersonnes);
        assertEquals(fakePersonnes, personneService.findAll());
        verify(personneRepository, times(1)).findAll();
    }

    @Test
    void testFindById() {
        int id = 1;
        var pers = Optional.of(new Personne("Wick", "John", 45));
        when(personneRepository.findById(id)).thenReturn(pers);
        var resultat = personneService.findById(id);
        assertEquals(pers.get(), resultat);
        verify(personneRepository, times(1)).findById(id);
    }

//    @Test
//    void testSave() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testDeleteById() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testUpdate() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    void testPersonneServiceImpl() {
//        fail("Not yet implemented");
//    }
}
