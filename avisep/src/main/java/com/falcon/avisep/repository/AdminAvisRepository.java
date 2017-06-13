package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.falcon.avisep.model.Admin;

/**
 * Spring Data JPA repository for the AdminAvis entity.
 */
@SuppressWarnings("unused")
public interface AdminAvisRepository extends JpaRepository<Admin,Long> {

}
