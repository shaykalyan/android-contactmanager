package com.akshaykalyan.contactmanager;

public class ContactEmail {
	private String emailString;
	
	public ContactEmail() {
		new ContactEmail("");
	}
	
	public ContactEmail(String emailString) {
		this.emailString = emailString;
	}
	
	public String getEmailString() {
		return emailString;
	}
	
	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}
	
	@Override
	public String toString() {
		return emailString;
	}
}
