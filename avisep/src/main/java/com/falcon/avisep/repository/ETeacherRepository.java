package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.ETeacher;

/**
 * Spring Data JPA repository for the ETeacher entity.
 */
@SuppressWarnings("unused")
public interface ETeacherRepository extends JpaRepository<ETeacher,Long> {

}
