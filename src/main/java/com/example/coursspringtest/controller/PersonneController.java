package com.example.coursspringtest.controller;

import java.util.List;

import com.example.coursspringtest.model.Personne;
import com.example.coursspringtest.service.PersonneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/personnes")
@Slf4j
@AllArgsConstructor
@CrossOrigin("http://localhost:8080")
public class PersonneController {
    private PersonneService personneService;

    @GetMapping()
    public List<Personne> getPersonnes() {
        log.info("Liste personne consultée");
        return personneService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonne(@PathVariable int id) {

        var personne = personneService.findById(id);
        if (personne == null) {
//			throw new ResponseStatusException(
//					HttpStatus.NOT_FOUND,
//					"La personne rechcerchée  n'existe pas"
//
//					);
            log.error("Personne recherchée avec l'identifiant {} est introuvable", id);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        log.info("Personne recherchée avec l'identifiant {}", id);
        return new ResponseEntity<Personne>(personne, HttpStatus.OK);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personne addPersonne(@RequestBody Personne personne) {
        System.out.println(personne);
        log.info("Personne ajoutée dans la base de données  {} ", personne);
        return personneService.save(personne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersonne(@PathVariable int id) {
        var personne = personneService.findById(id);
        if (personne == null) {
            log.error("Personne à supprimer avec l'identifiant {} est introuvable", id);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        log.info("Personne supprimée avec l'identifiant {}", id);
        personneService.deleteById(id);
        return new ResponseEntity<Boolean>(true, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addPersonne(@PathVariable int id, @RequestBody Personne personne) {
        if (id != personne.getId()) {
            log.error("Requête incohérente {} !=  {} ", id, personne);
            return new ResponseEntity<Personne>(personne, HttpStatus.BAD_REQUEST);
        } else if (personneService.findById(id) == null) {
            log.error("Personne introuvable {} !=  {} ", id, personne);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        log.info("Personne modifiée dans la base de données  {} ", personne);
        return new ResponseEntity<Personne>(personneService.update(personne), HttpStatus.ACCEPTED);
    }
}
