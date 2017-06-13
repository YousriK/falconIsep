package com.falcon.avisep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.falcon.avisep.model.Form;

/**
 * Spring Data JPA repository for the Form entity.
 */
@Repository
public interface FormRepository extends JpaRepository<Form,Long> {

//	Form saveAndFlush(Form form, long l);
//	@Modifying
//	@Query("insert into Form (user_avis_id) VALUES (:id)")
//	Form saveAndFlush(@Param("id")Long id);
//	
//	
	 // Example with positional params
//	   @Query("update Form f set f.user_avis_id = ?1 where f.id = ?2")
//	   Form setUserIdToForm(String user_id, String formId);
//    @Query("select distinct form from Form form left join fetch form.templates")
//    List<Form> findAllWithEagerRelationships();
//
//    @Query("insert into Form (user_avis_id) values (:user_avis_id)")
//    Form create(@Param("user_avis_id") Long user_avis_id);
//    @Query("update form  set content=?, in_template=?, is_pertinent=?, q_type=? where id=?")
//    Form create(@Param("user_avis_id") Long id);
    
    

}
