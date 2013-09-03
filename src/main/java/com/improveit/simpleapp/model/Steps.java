package com.improveit.simpleapp.model;

import java.util.Map;

/**
 * Represent steps of registration,
 * also have exclusive rules for every step
 * 
 * @author ashubin
 *
 */
public enum Steps {
	finale(null),
	third(finale),
	second(third) {
		@Override
		public Map<String, String> addExclusiveRules(Map<String, String> existingValuesToValidate) {			
			String serialnumber = 
					existingValuesToValidate.get("serial")
						.concat(existingValuesToValidate.get("number"));
			existingValuesToValidate.put("serialnumber", serialnumber);
			return existingValuesToValidate;
		}
	},
	first(second) {
		@Override
		public Map<String, String> addExclusiveRules(Map<String, String> existingValuesToValidate) {
			StringBuilder builder = new StringBuilder(existingValuesToValidate.get("password").length() * 2 + 4);
			String repeatpassword = builder
					.append(existingValuesToValidate.get("password"))
					.append("|")
					.append(existingValuesToValidate.get("rpassword"))
					.toString();
			existingValuesToValidate.put("repeatpassword", repeatpassword);
			return existingValuesToValidate;
		}
	};
	
	private Steps next;
	
	private Steps(Steps next) {
		this.next = next;
	}
	
	/**
	 * Extend existing pairs of <rule, value> for exclusive check
	 * 
	 * @param existingValuesToValidate
	 * @return extended list of rules needs to be checked on this step
	 */
	public Map<String, String> addExclusiveRules(Map<String, String> existingValuesToValidate) {
		return existingValuesToValidate;
	}
	
	public Steps next() {
		return next;
	}
};
