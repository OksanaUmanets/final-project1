package com.example.springsecurityapplication.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.springsecurityapplication.enumm.Status;
import com.example.springsecurityapplication.models.OrderPerson;
import com.example.springsecurityapplication.models.OrderProduct;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
	
	
	
    
	  @Query(value = "select * from order_product where order_person_id = ?1", nativeQuery = true)
	    List<OrderProduct> findByOrderP(int id);
	 

  
    
}
