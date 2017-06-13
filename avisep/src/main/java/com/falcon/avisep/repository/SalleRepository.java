package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.Salle;

/**
 * Spring Data JPA repository for the Salle entity.
 */
@SuppressWarnings("unused")
public interface SalleRepository extends JpaRepository<Salle,Long> {

}
