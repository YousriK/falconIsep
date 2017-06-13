package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.Student;

/**
 * Spring Data JPA repository for the Student entity.
 */
@SuppressWarnings("unused")
public interface StudentRepository extends JpaRepository<Student,Long> {

}
