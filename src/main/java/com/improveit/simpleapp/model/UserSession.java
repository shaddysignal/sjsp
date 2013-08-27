package com.improveit.simpleapp.model;

public class UserSession {

	private int userId = 0;
	private Steps step = Steps.first;
	
	public Integer getUserId() {
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
