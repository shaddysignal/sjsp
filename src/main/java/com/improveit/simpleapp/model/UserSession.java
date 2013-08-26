package com.improveit.simpleapp.model;

public class UserSession {

	private int userId = 0;
	private String step = "first";
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getStep() {
		return step;
	}
	
	public void setStep(String step) {
		this.step = step;
	}
	
}
