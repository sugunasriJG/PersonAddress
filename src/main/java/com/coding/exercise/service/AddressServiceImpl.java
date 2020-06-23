package com.coding.exercise.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.entity.Address;
import com.coding.exercise.entity.Person;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepo;
		
	@Autowired
	PersonService personService;
	@Override
	public Address save(Address address) throws ResourceNotFoundException{
		Optional<Person> person=personService.getPersonById(address.getPerson().getId());
		if(person.isPresent())
		{
			address.setPerson(person.get());
			return addressRepo.save(address);
		}
		else
			throw new ResourceNotFoundException("Address Cannot Be Added, Person Not Found With Given Id!!");
	}
	@Override
	public Address update(Address address, int id) throws ResourceNotFoundException{
		Optional<Address> addressRec=addressRepo.findById(id);
		if(addressRec.isPresent()) 			
		{
			Address addr=addressRec.get();
			if(addr.getPerson().getId()==address.getPerson().getId())					
			{
					if(address.getStreet()!=null)
					addr.setStreet(address.getStreet());
					if(address.getCity()!=null)
					addr.setCity(address.getCity());
					if(address.getState()!=null)
					addr.setState(address.getState());
					if(address.getPostalCode()!=null)
					addr.setPostalCode(address.getPostalCode());
					Optional<Person> person=personService.getPersonById(address.getPerson().getId());
					addr.setPerson(person.get());		
					return addressRepo.save(addr);
			}
			else
				throw new ResourceNotFoundException("Address Cannot Be Updated, Person Not Found With Given Id!!");
		}
		else
			throw new ResourceNotFoundException("Address Not Found With Given Id!!");
	}
	@Override
	public String deleteById(int id) throws ResourceNotFoundException{		
		if(addressRepo.findById(id).isPresent()) {
			addressRepo.deleteById(id);
			return "Successfully deleted!!";
		}
						
		else
			throw new ResourceNotFoundException("Address Not Found With Given Id!!");
		
	}	
	
}
