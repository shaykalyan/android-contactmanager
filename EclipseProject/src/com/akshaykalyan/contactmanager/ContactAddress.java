package com.akshaykalyan.contactmanager;

public class ContactAddress {
	private String fAddressLine1, fAddressLine2, fAddressLine3, fAddressLine4;
	
	public ContactAddress() {
		new ContactAddress("","","","");
	}
	
	ContactAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddressLine1 = addressLine1;
		this.fAddressLine2 = addressLine2;
		this.fAddressLine3 = addressLine3;
		this.fAddressLine4 = addressLine4;
	}
	
	public String getAddressLine1() {
		return fAddressLine1;
	}
	public String getAddressLine2() {
		return fAddressLine2;
	}
	public String getAddressLine3() {
		return fAddressLine3;
	}
	public String getAddressLine4() {
		return fAddressLine4;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.fAddressLine1 = addressLine1;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.fAddressLine2 = addressLine2;
	}
	
	public void setAddressLine3(String addressLine3) {
		this.fAddressLine3 = addressLine3;
	}
	
	public void setAddressLine4(String addressLine4) {
		this.fAddressLine4 = addressLine4;
	}
}
