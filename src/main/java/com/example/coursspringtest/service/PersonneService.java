package com.example.coursspringtest.service;

import com.example.coursspringtest.model.Personne;

import java.util.List;


public interface PersonneService {
    public List<Personne> findAll();

    public Personne findById(int id);

    public Personne save(Personne personne);

    public Personne update(Personne personne);

    public void deleteById(int id);
}
