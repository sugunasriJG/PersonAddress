package com.coding.exercise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.service.PersonService;

@RestController
public class PersonController {
	@Autowired
	PersonService personService;
	
	@RequestMapping(value="/person",method=RequestMethod.POST)
	public Person savePerson(@RequestBody Person person) {		
		return personService.save(person);
	}
	
	@RequestMapping(value="/person/{id}",method=RequestMethod.PUT)
	public Person updatePerson(@RequestBody Person person,@PathVariable("id") int id) throws ResourceNotFoundException{
		return personService.update(person,id);
	}
	
	
	@RequestMapping(value="/person/{id}",method=RequestMethod.DELETE)
	public String deletePersonById(@PathVariable("id") int id) throws ResourceNotFoundException{
		return personService.deleteById(id);
	}
	
	@RequestMapping(value="/persons",method=RequestMethod.GET)
	public List<Person> getAllPersons() {
		List <Person> persons=personService.getAllPersons();		
		return persons;
	}
	
	@RequestMapping(value="/persons/count",method=RequestMethod.GET)
	public long getNumberOfPersons() {
		return personService.getNumberOfPersons();
	}
}
