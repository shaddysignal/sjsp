package com.improveit.simpleapp.services.rules;

import java.util.HashMap;
import java.util.Map;

import com.improveit.simpleapp.dao.UserDao;
import com.improveit.simpleapp.services.rules.interfaces.Rule;

public class RuleForSerialNumber implements Rule {
	
	public String valid(String value, UserDao userDao) {
		Map<String, String> serialnumber = new HashMap<String, String>(2, 1);
		serialnumber.put("serial", value.substring(0, 5));
		serialnumber.put("number", value.substring(5));
		String error = null;
		if(userDao.getByParams(serialnumber).size() != 0)
			error = "Already have in database that pair of serial + number, must be unique";
		return error;
	}

}
