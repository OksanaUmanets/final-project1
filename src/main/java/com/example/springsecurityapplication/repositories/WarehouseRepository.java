package com.example.springsecurityapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsecurityapplication.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
	
}
