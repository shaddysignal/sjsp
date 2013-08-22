package com.improveit.simpleapp.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="users")
public class User implements Iterable<String> {

	@Transient
	private final Map<String, String> mapper = new HashMap<String, String>(12);
	
	@Id
	@GeneratedValue
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	{
		mapper.put("firstName", "");
		mapper.put("secondName", "");
		mapper.put("fathersName", "");
		mapper.put("phonenumber", "");
		mapper.put("email", "");
		mapper.put("password", "");
		mapper.put("birthdate", "");
		mapper.put("serial", "");
		mapper.put("number", "");
		mapper.put("region", "");
		mapper.put("city", "");
		mapper.put("street", "");
	}
	
	@Column(name="user_second_name")
	public String getSecondName() {
		return mapper.get("secondName");
	}

	public void setSecondName(String secondName) {

		mapper.put("secondName", secondName);
	}
	
	@Column(name="user_fathers_name")
	public String getFathersName() {
		return mapper.get("fathersName");
	}

	public void setFathersName(String fathersName) {
		mapper.put("fathersName", fathersName);
	}

	@Column(name="user_phone_number")
	public String getPhonenumber() {
		return mapper.get("phonenumber");
	}

	public void setPhonenumber(String phonenumber) {
		mapper.put("phonenumber", phonenumber);
	}

	@Column(name="user_email")
	public String getEmail() {
		return mapper.get("email");
	}

	public void setEmail(String email) {
		mapper.put("email", email);
	}

	@Column(name="user_password")
	public String getPassword() {
		return mapper.get("password");
	}

	public void setPassword(String password) {
		mapper.put("password", password);
	}

	@Column(name="user_birthdate")
	public String getBirthdate() {
		return mapper.get("birthdate");
	}

	public void setBirthdate(String birthdate) {
		mapper.put("birthdate", birthdate);
	}

	@Column(name="user_serial")
	public String getSerial() {
		return mapper.get("serial");
	}

	public void setSerial(String serial) {
		mapper.put("serial", serial);
	}

	@Column(name="user_number")
	public String getNumber() {
		return mapper.get("number");
	}

	public void setNumber(String number) {
		mapper.put("number", number);
	}

	@Column(name="user_region")
	public String getRegion() {
		return mapper.get("region");
	}

	public void setRegion(String region) {
		mapper.put("region", region);
	}

	@Column(name="user_city")
	public String getCity() {
		return mapper.get("city");
	}

	public void setCity(String city) {
		mapper.put("city", city);
	}

	@Column(name="user_street")
	public String getStreet() {
		return mapper.get("street");
	}

	public void setStreet(String street) {
		mapper.put("street", street);
	}

	@Column(name="user_first_name")
	public String getFirstName() {
		return mapper.get("firstName");
	}

	public void setFirstName(String firstName) {
		mapper.put("firstName", firstName);
	}

	public Iterator<String> iterator() {
		return mapper.keySet().iterator();
	}
	
	/**
	 * Just try to avoid reflection
	 * 
	 * @param paramName name of param
	 * @return param value
	 */
	@Transient
	public String getParam(String paramName) {
		return mapper.get(paramName);
	}
	
	/**
	 * Just try to avoid reflection
	 * 
	 * @param paramName
	 * @param paramValue
	 */
	public void setParam(String paramName, String paramValue) {
		mapper.put(paramName, paramValue);
	}

}
