package com.improveit.simpleapp.model;

public class UserScope {

	private User user;
	private int step;
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public int getStep() {
		return step;
	}
	
	public void setStep(int step) {
		this.step = step;
	}
	
	public void incrementStep() {
		this.step++;
	}
	
}
