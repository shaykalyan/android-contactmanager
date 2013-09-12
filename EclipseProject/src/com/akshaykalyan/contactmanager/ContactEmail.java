package com.akshaykalyan.contactmanager;

public class ContactEmail {
	private String fEmailString;
	
	public ContactEmail() {
		new ContactEmail("");
	}
	
	public ContactEmail(String emailString) {
		this.fEmailString = emailString;
	}
	
	public String getEmailString() {
		return fEmailString;
	}
	
	public void setEmailString(String emailString) {
		this.fEmailString = emailString;
	}
	
	@Override
	public String toString() {
		return fEmailString;
	}
}
