package com.example.coursspringtest.dao;

import com.example.coursspringtest.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
