package com.improveit.simpleapp.services;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.RuleForBirthdate;
import com.improveit.simpleapp.services.rules.RuleForCity;
import com.improveit.simpleapp.services.rules.RuleForEmail;
import com.improveit.simpleapp.services.rules.RuleForFathersName;
import com.improveit.simpleapp.services.rules.RuleForFirstName;
import com.improveit.simpleapp.services.rules.RuleForNumber;
import com.improveit.simpleapp.services.rules.RuleForPassword;
import com.improveit.simpleapp.services.rules.RuleForPhonenumber;
import com.improveit.simpleapp.services.rules.RuleForRegion;
import com.improveit.simpleapp.services.rules.RuleForSecondName;
import com.improveit.simpleapp.services.rules.RuleForSerial;
import com.improveit.simpleapp.services.rules.RuleForSerialNumber;
import com.improveit.simpleapp.services.rules.RuleForStreet;
import com.improveit.simpleapp.services.rules.interfaces.Rule;


enum Rules {
	firstName(new RuleForFirstName()),
	secondName(new RuleForSecondName()),
	fathersName(new RuleForFathersName()),
	phonenumber(new RuleForPhonenumber()),
	email(new RuleForEmail()),
	password(new RuleForPassword()),
	birthdate(new RuleForBirthdate()),
	serial(new RuleForSerial()),
	number(new RuleForNumber()),
	serialnumber(new RuleForSerialNumber()),
	region(new RuleForRegion()),
	city(new RuleForCity()),
	street(new RuleForStreet());
	
	private Rule rule;
	
	private Rules(Rule rule) {
		this.rule = rule;
	}
		
	public String valid(String value, UserDao userDao) {
		return rule.valid(value, userDao);
	}
}