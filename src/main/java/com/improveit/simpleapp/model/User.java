package com.improveit.simpleapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="users")
@SelectBeforeUpdate
@DynamicUpdate
@DynamicInsert
public class User {

	@Id
	@GeneratedValue
	private int id;
		
	@Column(name="user_first_name")
	private String firstName;
	
	@Column(name="user_second_name")
	private String secondName;
	
	@Column(name="user_fathers_name")
	private String fathersName;
	
	@Column(name="user_phonenumber", unique=true)
	private String phonenumber;
	
	@Column(name="user_email", unique=true)
	private String email;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_birthdate")
	private String birthdate;
	
	@Column(name="user_serial")
	private String serial;
	
	@Column(name="user_number")
	private String number;

	@Column(name="user_region")
	private String region;
	
	@Column(name="user_city")
	private String city;
	
	@Column(name="user_street")
	private String street;

	@Column(name="user_step")
	@Enumerated(EnumType.STRING)
	private Steps step = Steps.first;
	
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
	
	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Steps getStep() {
		return step;
	}

	public void setStep(Steps step) {
		this.step = step;
	}
	
}
