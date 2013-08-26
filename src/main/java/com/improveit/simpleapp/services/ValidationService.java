package com.improveit.simpleapp.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

/**
 * Class that validate through Rules 
 * 
 * @see Rules
 * @author ashubin
 */
@Service
public class ValidationService {
	
	@Autowired
	private UserDao userDao;
	
	public Map<String, String> valid(Map<String, String> parameters) {
		// Ho-ho sure you can blame for that
		if(parameters.containsKey("serial"))
			parameters.put("serialnumber", parameters.get("serial").concat(parameters.get("number")));
		
		Map<String, String> errors = new HashMap<String, String>();
		for(String key : parameters.keySet()) {
			String error = Rules.valueOf(key).valid(parameters.get(key), userDao);
			if(error != null)
				errors.put(key, error);
		}
		return errors;
	}
	
}
