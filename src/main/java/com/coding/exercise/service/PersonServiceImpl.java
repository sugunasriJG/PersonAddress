package com.coding.exercise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.repository.PersonRepositoryImpl;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepositoryImpl personRepo;
	
	@Override
	public Person save(Person person)
	{
		return personRepo.save(person);
	}

	@Override
	public Person update(Person person, int id) throws ResourceNotFoundException{
		Optional<Person> personRec=personRepo.findById(id);
		if(personRec.isPresent()) {
			Person personNow=personRec.get();
			if(person.getFirstName()!=null)
				personNow.setFirstName(person.getFirstName());
			if(person.getLastName()!=null)
				personNow.setLastName(person.getLastName());
			return personRepo.save(personNow);		
		}
		else
			throw new ResourceNotFoundException("Person Not Found With Given Id!!");
	}

	@Override
	public String deleteById(int id) throws ResourceNotFoundException {
		Optional<Person> person=personRepo.findById(id);
		if(person.isPresent())
		{
			personRepo.delete(person.get());
			return "Successfully deleted!!";
		}
		else
			throw new ResourceNotFoundException("Person Not Found With Given Id!!");
	}

	@Override
	public List<Person> getAllPersons() {
		
		List<Person> persons=personRepo.findAll();
		return persons;
	}

	@Override
	public long getNumberOfPersons() {
				
		return personRepo.count();
	}

	public Optional<Person> getPersonById(int id) {
		return personRepo.findById(id);
	}
}
