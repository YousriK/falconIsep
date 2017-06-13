package com.falcon.avisep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.falcon.avisep.model.Cours;
import com.falcon.avisep.model.Module;

/**
 * Spring Data JPA repository for the Cours entity.
 */
@SuppressWarnings("unused")
public interface CoursRepository extends JpaRepository<Cours,Long> {
	@Query("select cours from Cours cours where cours.module.id=:module_id and cours.form.id is not null")
	List<Cours> findAllCoursByModuleId(@Param("module_id")long module_id);
}
