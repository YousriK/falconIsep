package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.Evaluation;

/**
 * Spring Data JPA repository for the Evaluation entity.
 */
@SuppressWarnings("unused")
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {

}
