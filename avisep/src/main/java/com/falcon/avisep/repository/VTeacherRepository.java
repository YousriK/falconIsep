package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.VTeacher;

/**
 * Spring Data JPA repository for the VTeacher entity.
 */
@SuppressWarnings("unused")
public interface VTeacherRepository extends JpaRepository<VTeacher,Long> {

}
