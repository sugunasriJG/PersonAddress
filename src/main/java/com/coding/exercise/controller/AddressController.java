package com.coding.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coding.exercise.entity.Address;
import com.coding.exercise.exceptionhandler.ResourceNotFoundException;
import com.coding.exercise.service.AddressService;

@RestController
public class AddressController {
	@Autowired
	AddressService addressService;
	

	@RequestMapping(value="/address",method=RequestMethod.POST)
	public Address saveAddress(@RequestBody Address address) throws ResourceNotFoundException {
		System.out.println("inside save Address:"+address);
		return addressService.save(address);
	}
	
	@RequestMapping(value="/address/{id}",method=RequestMethod.PUT)
	public Address updateAddress(@RequestBody Address address,@PathVariable("id") int id) throws ResourceNotFoundException {
		System.out.println("inside edit Address:"+address);
		return addressService.update(address, id);
	}
	
	
	@RequestMapping(value="/address/{id}",method=RequestMethod.DELETE)
	public String deleteAddressById(@PathVariable("id") int id) throws ResourceNotFoundException {
		return addressService.deleteById(id);
	}
	
}
