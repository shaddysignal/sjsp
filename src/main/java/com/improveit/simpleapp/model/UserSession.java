package com.improveit.simpleapp.model;

public class UserSession {

	private User user = new User();
	private String step = "first";
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		for(String attribute : user) {
			if(user.getParam(attribute) != null) 
				this.user.setParam(attribute, user.getParam(attribute));
		}
	}
	
	public String getStep() {
		return step;
	}
	
	public void setStep(String step) {
		this.step = step;
	}
	
}
