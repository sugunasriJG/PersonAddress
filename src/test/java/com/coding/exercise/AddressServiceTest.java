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

import com.coding.exercise.entity.Address;
import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.repository.AddressRepository;
import com.coding.exercise.repository.PersonRepositoryImpl;
import com.coding.exercise.service.AddressService;
import com.coding.exercise.service.AddressServiceImpl;
import com.coding.exercise.service.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes= {AddressServiceImpl.class})
public class AddressServiceTest {
	
	@Autowired
	private AddressService addressService;
	
	@MockBean
	private PersonService personService;
	
	@MockBean
	private PersonRepositoryImpl personRepo;
	
	@MockBean
	private AddressRepository addressRepo;
	
	
	@Test
	public void testSaveAddress_WhenPersonFound() throws ResourceNotFoundException{
		Person person=new Person(11,"Harika","P");		
		Address address=new Address(121,"2nd street","Hyd","Telangana","520011",person);				
		Optional<Person> personRec=Optional.of(person);		
		Mockito.when(personService.getPersonById(person.getId())).thenReturn(personRec);				
		Mockito.when(addressRepo.save(address)).thenReturn(address);
		assertEquals(address,addressService.save(address));
				
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testSaveAddress_WhenPersonNotFound() throws ResourceNotFoundException{
		Person person=new Person(22,"Harika","P");		
		Address address=new Address(121,"2nd street","Hyd","Telangana","520011",person);						
		Mockito.when(personService.getPersonById(person.getId())).thenReturn(Optional.empty());
		addressService.save(address);				
	}

	/*@Test
	public void testUpdateAddress_WhenAddressResourceFound() throws ResourceNotFoundException{
		Person person=new Person(11,"Harika","P");
		Address address=new Address(121,"2nd street","Hyd","Telangana","520011",person);
		Optional<Address> addressRec=Optional.of(address);
		

		Address addressNew=new Address(121,"3rd street","Hyderabad","Telangana","520121",person);
		Mockito.when(addressRepo.findById(121)).thenReturn(addressRec);		
		Mockito.when(addressRepo.save(addressNew)).thenReturn(addressNew);
		
		assertEquals(addressNew,addressService.update(addressNew,121));
		
		}*/

	
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateAddress_WhenAddressResourceNotFound() throws ResourceNotFoundException{
		Person person=new Person(22,"Harika","P");		
		Address addressNew=new Address(121,"3rd street","Hyderabad","Telangana","520121",person);
		
		Optional<Address> addressRec=Optional.empty();		
		Mockito.when(addressRepo.findById(100)).thenReturn(addressRec);		
		
		addressService.update(addressNew,100);		
		}
	@Test(expected = ResourceNotFoundException.class)
	public void testUpdateAddress_WhenPersonResourceNotFound() throws ResourceNotFoundException{
		Person person1=new Person(11,"Harika","P");
		Address address=new Address(121,"2nd street","Hyd","Telangana","520011",person1);
		Optional<Address> addressRec=Optional.of(address);
		
		Person person2=new Person(22,"Niharika","H");
		Address addressNew=new Address(121,"3rd street","Hyderabad","Telangana","520121",person2);				
		Mockito.when(addressRepo.findById(121)).thenReturn(addressRec);		
		
		addressService.update(addressNew,121);		
		}
	
	@Test	
	public void testDeleteAddress_WhenAddressResourceFound() throws ResourceNotFoundException{
		
		Person person=new Person(11,"Nitya","Valli");
		Address address=new Address(15,"3-str","Vijayawada","Andhra","52001",person);
		Optional<Address> addressRec=Optional.of(address);
		Mockito.when(addressRepo.findById(11)).thenReturn(addressRec);				
		assertEquals("Successfully deleted!!", addressService.deleteById(11));		
	}
	
	@Test(expected=ResourceNotFoundException.class)	
	public void testDeleteAddress_WhenAddressResourceNotFound() throws ResourceNotFoundException{		
		Optional<Address> address=Optional.empty();		
		Mockito.when(addressRepo.findById(11)).thenReturn(address);		
		addressService.deleteById(11);
	}
		


}
