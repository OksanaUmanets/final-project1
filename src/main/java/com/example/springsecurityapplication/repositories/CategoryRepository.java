package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    com.example.springsecurityapplication.models.Category findByName(String name);
}
