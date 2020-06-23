package com.coding.exercise.service;

import java.util.List;
import java.util.Optional;

import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;

public interface PersonService {
	public Person save(Person person);
	
	public Person update(Person person,int id) throws ResourceNotFoundException;

	public String deleteById(int id) throws ResourceNotFoundException;

	public List<Person> getAllPersons();

	public long getNumberOfPersons();
	
	public Optional<Person> getPersonById(int id);
}
