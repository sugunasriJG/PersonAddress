package com.coding.exercise;

import static org.junit.Assert.assertEquals;


import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.repository.PersonRepositoryImpl;
import com.coding.exercise.service.PersonService;
import com.coding.exercise.service.PersonServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {PersonServiceImpl.class})
public class PersonServiceTest {
	@Autowired
	private PersonService personService;
	
	@MockBean
	private PersonRepositoryImpl personRepo;
	
	@Test
	public void testSave() {
		Person person=new Person(11,"Nitya","Valli");
		Mockito.when(personRepo.save(person)).thenReturn(person);		
		assertEquals(person, personService.save(person));		
	}
	
	
	@Test
	public void testUpdatePerson_WhenPersonResourceFound() throws ResourceNotFoundException{			
		Optional<Person> person=Optional.of(new Person(11,"Nitya","Valli"));
		Mockito.when(personRepo.findById(11)).thenReturn(person);		
		Person p=new Person(11,"Sarvani","Latha");
		Mockito.when(personRepo.save(person.get())).thenReturn(p);
		
		assertEquals(p,personService.update(p,11));
		
		}

	@Test(expected = ResourceNotFoundException.class)
	public void testUpdatePerson_WhenPersonResourceNotFound() throws ResourceNotFoundException{			
		Optional<Person> person=Optional.empty();
		Mockito.when(personRepo.findById(11)).thenReturn(person);				
		Person p=new Person(11,"Sarvani","Latha");
		personService.update(p,11);		
		}

	
	@Test	
	public void testDeletePerson_WhenPersonResourceFound() throws ResourceNotFoundException{
		Optional<Person> person=Optional.of(new Person(11,"Nitya","Valli"));		
		Mockito.when(personRepo.findById(11)).thenReturn(person);				
		assertEquals("Successfully deleted!!", personService.deleteById(11));		
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testDeletePerson_WhenPersonResourseNotFound() throws ResourceNotFoundException {
		Optional<Person> person=Optional.empty();		
		Mockito.when(personRepo.findById(11)).thenReturn(person);
		personService.deleteById(11);		
	}

	@Test
	public void testGetNumberOfPersons() {		
		Mockito.when(personRepo.count()).thenReturn((long)4);		
		assertEquals(4, personRepo.count());
	}
	
	
	
}
