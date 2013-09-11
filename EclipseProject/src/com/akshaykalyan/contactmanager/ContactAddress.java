package com.akshaykalyan.contactmanager;

public class ContactAddress {
	private String addressLine1, addressLine2, addressLine3, addressLine4;
	
	public ContactAddress() {
		new ContactAddress("","","","");
	}
	
	ContactAddress(String line1, String line2, String line3, String line4) {
		this.addressLine1 = line1;
		this.addressLine2 = line2;
		this.addressLine3 = line3;
		this.addressLine4 = line4;
	}
	
	public String getAddressLine1() {
		return addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public String getAddressLine4() {
		return addressLine4;
	}
	
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	
	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}
}
