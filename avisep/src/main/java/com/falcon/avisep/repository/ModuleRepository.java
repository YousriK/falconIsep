package com.falcon.avisep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.falcon.avisep.model.Module;

/**
 * Spring Data JPA repository for the Module entity.
 */
@SuppressWarnings("unused")
public interface ModuleRepository extends JpaRepository<Module,Long> {
	@Query("select module from Module module where module.form.id is not null")
	List<Module> findAllModulesWithForm();
	
}
