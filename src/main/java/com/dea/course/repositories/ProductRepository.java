package com.dea.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.course.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
