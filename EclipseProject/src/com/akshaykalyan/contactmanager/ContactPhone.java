package com.akshaykalyan.contactmanager;

public class ContactPhone {
	private String mobilePhone, homePhone, workPhone;
	
	public ContactPhone(String mobilePhone, String homePhone, String workPhone) {
		this.mobilePhone = mobilePhone;
		this.homePhone = homePhone;
		this.workPhone = workPhone;
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	
}

