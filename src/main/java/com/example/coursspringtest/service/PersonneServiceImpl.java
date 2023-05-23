package com.example.coursspringtest.service;


import com.example.coursspringtest.dao.PersonneRepository;
import com.example.coursspringtest.model.Personne;
import org.springframework.stereotype.Service;


import lombok.AllArgsConstructor;

import java.util.List;

// @Service = @Component
@Service
@AllArgsConstructor
public class PersonneServiceImpl implements PersonneService {
    private PersonneRepository personneRepository;
    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Personne findById(int id) {
        return personneRepository.findById(id).orElse(null);
    }

    public Personne save(Personne personne) {
        // adresseRepository.saveAll(personne.getAdresses());
        return personneRepository.save(personne);
    }

    public void deleteById(int id) {
        personneRepository.deleteById(id);
    }

    public Personne update(Personne personne) {
        return personneRepository.save(personne);
    }
}
