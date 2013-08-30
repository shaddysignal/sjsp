package com.improveit.simpleapp.model;

import org.springframework.stereotype.Component;

@Component
public class UserSession {

	private int userId;
	private Steps step = Steps.define;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public Steps getStep() {
		return step;
	}
	
	public void setStep(Steps step) {
		this.step = step;
	}
	
}
