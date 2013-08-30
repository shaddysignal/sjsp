package com.improveit.simpleapp.services.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.improveit.simpleapp.dao.UserDao;

@Component
public class UserValidator {
	
	// Some trick, not sure how it seems
	private static UserDao userDao;
	
	enum Rules {
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
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				sdf.setLenient(false);
				String error = null;
				try {
					Date test = sdf.parse(value);
					test.after(new Date());
				} catch (ParseException e) {
					error = "Must be in format like this - 'yyyy-MM-dd'";
				}
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
				serialnumber.put("serial", value.substring(0, 4));
				serialnumber.put("number", value.substring(4));
				String error = null;
				if(userDao.getByParams(serialnumber).size() != 0)
					error = "Already have in database that pair of serial + number, must be unique";
				return error;
			}
		},
		repeatpassword {
			@Override
			public String valid(String value) {
				String error = value.matches("^(.*)\\|\\1$") ? null : "Passwords must be identical";
				return error;
			}
		},
		// Looks like spike
		defaultRule {
			@Override
			public String valid(String value) {
				return null;
			}
		};
		
		/**
		 * 
		 * @param value to validate
		 * @return error if there is one, null otherwise
		 */
		public abstract String valid(String value);
		
		public static Rules permissiveValueOf(String name) {
			for(Rules rule : Rules.values()) {
				if (rule.toString().equals(name)) {
		            return rule;
		        }
			}
			return Rules.defaultRule;
		}
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
			String error = null;
			try {
				error = Rules.permissiveValueOf(entry.getKey()).valid(entry.getValue());
			} catch(IllegalArgumentException iae) {
				error = null;
			}
			if(error != null)
				errors.put(entry.getKey(), error);
		}
		return errors;
	}
	
}
