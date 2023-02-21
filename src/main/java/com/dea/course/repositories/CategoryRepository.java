package com.dea.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.course.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
}
