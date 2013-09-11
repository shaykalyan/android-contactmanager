package com.akshaykalyan.contactmanager;


import android.graphics.Bitmap;


public class Contact {
	private ContactName fName;
	private ContactAddress fAddress;
	private ContactEmail fEmail;
	private ContactPhone fPhone;
	private ContactPhoto fPhoto;
	
	public Contact(String firstName, String lastName, String contactAddressLine1, String contactAddressLine2,
			String contactAddressLine3, String contactAddressLine4, String email, String mobilePhone, String workPhone,
			String homePhone, Bitmap photo) {
		this.fName = new ContactName(firstName, lastName);
		this.fAddress = new ContactAddress(contactAddressLine1, contactAddressLine2, contactAddressLine3, contactAddressLine4);
		this.fEmail = new ContactEmail(email);
		this.fPhone = new ContactPhone(mobilePhone, homePhone, workPhone);
		this.fPhoto = new ContactPhoto(photo);
	}
	
	//NAME
	public void setfName(String firstName, String lastName) {
		this.fName.setFirstName(firstName);
		this.fName.setLastName(lastName);
	}
	
	public ContactName getfName() {
		return fName;
	}
	
	//ADDRESS
	public void setfAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddress.setAddressLine1(addressLine1);
		this.fAddress.setAddressLine2(addressLine2);
		this.fAddress.setAddressLine3(addressLine3);
		this.fAddress.setAddressLine4(addressLine4);
	}
	
	public ContactAddress getfAddress() {
		return fAddress;
	}
	
	//EMAIL
	public void setfEmail(String emailString) {
		this.fEmail.setEmailString(emailString);
	}
	
	public ContactEmail getfEmail() {
		return fEmail;
	}
	
	//PHONE	
	public void setfPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fPhone.setMobilePhone(mobilePhone);
		this.fPhone.setHomePhone(homePhone);
		this.fPhone.setWorkPhone(workPhone);
	}
	
	public ContactPhone getfPhone() {
		return fPhone;
	}
	
	
	// PHOTO
	public void setfPhoto(Bitmap photoBitmap) {
		this.fPhoto.setPhotoBitmap(photoBitmap);
	}
	
	public ContactPhoto getfPhoto() {
		return fPhoto;
	}
	
}
