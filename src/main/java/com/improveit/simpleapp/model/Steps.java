package com.improveit.simpleapp.model;

import java.util.List;
import java.util.Map;

/**
 * Represent steps of registration,
 * also have exclusive rules for every step
 * 
 * @author ashubin
 *
 */
public enum Steps {
	finale(null), third(finale), second(third),	first(second);
	
	private Steps next;
	
	private Steps(Steps next) {
		this.next = next;
	}
	
	public Steps next() {
		return next;
	}
};
