package com.akshaykalyan.contactmanager;


public class ContactPhone {
	private String fMobilePhone, fHomePhone, fWorkPhone;
	
	
	public ContactPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fMobilePhone = mobilePhone;
		this.fHomePhone = homePhone;
		this.fWorkPhone = workPhone;
	}
	
	public String getHomePhone() {
		return fHomePhone;
	}
	public String getMobilePhone() {
		return fMobilePhone;
	}
	public String getWorkPhone() {
		return fWorkPhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.fHomePhone = homePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.fMobilePhone = mobilePhone;
	}
	public void setWorkPhone(String workPhone) {
		this.fWorkPhone = workPhone;
	}
	
}

