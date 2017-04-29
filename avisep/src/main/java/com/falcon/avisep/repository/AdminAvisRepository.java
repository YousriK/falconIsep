package com.falcon.avisep.repository;

import com.falcon.avisep.model.AdminAvis;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Spring Data JPA repository for the AdminAvis entity.
 */
@SuppressWarnings("unused")
public interface AdminAvisRepository extends JpaRepository<AdminAvis,Long> {

}
