package com.coding.exercise.entity;


import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="PERSON")

public class Person {
	
	@Id	
	@Column(name="PRSN_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	
	@Column(name="PRSN_FIRST_NM")
	String firstName;
	
	@Column(name="PRSN_LAST_NM")
	String lastName;		
	 
	@OneToMany(mappedBy = "person", fetch = FetchType.EAGER)	
	Set<Address> addresses;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




public Person() {
	super();
}
public Person(int id, String firstName, String lastName) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	
}


@Override
public String toString() {
	return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName 
			+ "]";
}



	
	
}
