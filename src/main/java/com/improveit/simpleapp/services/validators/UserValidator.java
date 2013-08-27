package com.improveit.simpleapp.services.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.improveit.simpleapp.dao.UserDao;

@Component
public class UserValidator {
	
	// Some trick, not sure how it seems
	@Autowired
	private static UserDao userDao;
	
	public enum Rules {
		firstName,
		secondName,
		fathersName,
		phonenumber {
			public String valid(String value) {
				String error = null;
				if(value.matches("^\\d{11}$")) {
					if(userDao.getFirst("phonenumber", value) != null)
						error = "That number already exists in database, try another one";
				} else {
					error = "Must be valid phonenumber like '89135433445'";
				}
				return error;
			}
		},
		email {
			@Override
			public String valid(String value) {
				String error = null;
				if(value.matches("^[a-zA-Z._0-9]+@[a-zA-Z._0-9]+\\.[a-z]{1,4}$")) {
					if(userDao.getFirst("email", value) != null)
						error = "That email already exists in database, try another one";
				} else
					error = "Must be valid email";
				return error;
			}
		},
		password {
			@Override
			public String valid(String value) {
				String error = value.matches("^.{5,}$") ? null : "Must be more than 5 sumbols"; 
				return error;
			}
		},
		birthdate {
			@Override
			public String valid(String value) {
				String error = value.matches("^\\d{4}-\\d{2}-\\d{2}$") ? null : "Must be in format like this - 'yyyy-mm-dd'";
				return error;
			}
		},
		serial {
			@Override
			public String valid(String value) {
				String error = value.matches("^\\d{4}$") ? null : "Must be 4 digits";
				return error;
			}
		},
		number {
			@Override
			public String valid(String value) {
				String error = value.matches("^\\d{6}$") ? null : "Must be 6 digits";
				return error;
			}
		},
		serialnumber {
			@Override
			public String valid(String value) {
				Map<String, String> serialnumber = new HashMap<String, String>(2, 1);
				serialnumber.put("serial", value.substring(0, 5));
				serialnumber.put("number", value.substring(5));
				String error = null;
				if(userDao.getByParams(serialnumber).size() != 0)
					error = "Already have in database that pair of serial + number, must be unique";
				return error;
			}
		},
		region,
		city,
		street;
		
		/**
		 * 
		 * @param value to validate
		 * @return error if there is one, null otherwise
		 */
		public String valid(String value) {
			return null;
		};
	}
	
	public void setUserDao(UserDao userDao) {
		UserValidator.userDao = userDao;
	}
	
	/**
	 * 
	 * @param parameters key -> rule and value -> valueToTest
	 * @return errors like key -> rule and value -> error
	 */
	public Map<String, String> valid(Map<String, String> parameters) {
		Map<String, String> errors = new HashMap<String, String>();
		for(Entry<String, String> entry : parameters.entrySet()) {
			String error = Rules.valueOf(entry.getKey()).valid(entry.getValue());
			if(error != null)
				errors.put(entry.getKey(), error);
		}
		return errors;
	}
	
}
