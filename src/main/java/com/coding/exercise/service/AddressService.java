package com.coding.exercise.service;


import com.coding.exercise.entity.Address;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;

public interface AddressService {
	public Address save(Address address) throws ResourceNotFoundException;
	public Address update(Address address, int id) throws ResourceNotFoundException;
	public String deleteById(int id) throws ResourceNotFoundException;
	 
}
