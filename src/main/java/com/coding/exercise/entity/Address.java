package com.coding.exercise.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name="ADDRESS")

public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ADDR_ID")
	int id;
	
	@Column(name="ADDR_STREET")
	String street;
	
	@Column(name="ADDR_CITY")
	String city;
	
	@Column(name="ADDR_STATE")
	String state;
	
	@Column(name="ADDR_POSTAL_CODE")
	String postalCode;
	
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRSN_ID", nullable=false, foreignKey = @ForeignKey(foreignKeyDefinition =
	        "FOREIGN KEY(PRSN_ID) REFERENCES PERSON(PRSN_ID) ON DELETE CASCADE", name = "Fk_nn_PRSN_ID"))	
	Person person;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Address() {
		super();
	}
	public Address(int id, String street, String city, String state, String postalCode, Person person) {
		super();
		this.id = id;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.person = person;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", street=" + street + ", city=" + city + ", state=" + state + ", postalCode="
				+ postalCode + ", person=" + person + "]";
	}

	
	
		
}
