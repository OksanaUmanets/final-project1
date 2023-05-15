package com.example.springsecurityapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.OrderPerson;


@Repository
public interface OrderPersonRepository extends JpaRepository<OrderPerson, Integer> {
	
	List<OrderPerson> findByPersonId(int id);
	
	

	   @Query(value = "select * from order_person where opnumber = ?1", nativeQuery = true)
	    Optional<OrderPerson> findByOrderId(int id);
		
	   @Query(value = "select * from order_person where person_id = ?1", nativeQuery = true)
	    List<OrderPerson> findByPersonIdNumber(int id);
	 
	   
	/*   public default Optional<OrderPerson> findByOrderNumber(String name) {
	      return em.createQuery("select u from User u where u.name =:name", OrderProduct.class)
	               .setParameter("name", name)
	                .getResultStream().findAny();
	   }*/
	    
	    
	    

	    @Transactional
	    @Query(value = "update orders u  set u.status = ?2  where  number = ?1", nativeQuery = true)
	    Optional<OrderPerson> updateByOrderId(int id, OrderPerson orderPerson);
	  


  
  
    
}
