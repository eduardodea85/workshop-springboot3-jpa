package com.dea.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dea.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
