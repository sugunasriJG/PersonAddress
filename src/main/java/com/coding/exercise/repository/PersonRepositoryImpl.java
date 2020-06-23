package com.coding.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.exercise.entity.Person;

public interface PersonRepositoryImpl extends JpaRepository<Person, Integer>, PersonRepository{
	

}
