package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.Classe;

/**
 * Spring Data JPA repository for the Classe entity.
 */
@SuppressWarnings("unused")
public interface ClasseRepository extends JpaRepository<Classe,Long> {

}
