package com.coding.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.exercise.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
}
