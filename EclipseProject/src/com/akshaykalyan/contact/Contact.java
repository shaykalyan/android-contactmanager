package com.akshaykalyan.contact;

import java.util.Comparator;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to represent a Contact object. 
 * Each object have the following states:
 * 		Name, Photo, Phone, Email, Birthday and Address.
 *  
 * @author Akshay Pravin Kalyan | akal881 | 5786866
 */
public class Contact implements Parcelable {

	private ContactName fName;
	private ContactAddress fAddress;
	private ContactEmail fEmail;
	private ContactPhone fPhone;
	private ContactBirthday fBirthday;
	private ContactPhoto fPhoto;
	private int fId;
	
	/**
	 * SortBy enumeration types returns a Comparator object allowing Contact objects to be sorted in various ways.
	 * 
	 * FirstName - Sorts contacts by first name attribute
	 * LastName - Sorts contacts by last name attribute  
	 * PhoneNumberMobile - Sorts contacts by the Mobile number attribute of the Phone object composed within a Contact
	 * PhoneNumberHome - Sorts contacts by the Home number attribute of the Phone object composed within a Contact
	 * PhoneNumberWork - Sorts contacts by the Work number attribute of the Phone object composed within a Contact
	 */
	public enum SortBy {
		FirstName(1), LastName(2), PhoneNumberMobile(3), PhoneNumberHome(4), PhoneNumberWork(5);
		
		public Comparator<Contact> fComparator;
		/**
		 * Constructor to initialise comparator field.
		 * Note: If either of the compared fields is an empty string, that contact object is 'pushed back'/
		 * given lower priority than other. 
		 * 		e.g: "" compared to "abc" -- "" will be placed after "abc" in the list.
		 */
		private SortBy(int i) {
			switch (i) {
				case 1:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fName.getFirstName().isEmpty()) {
								return 1;
								
							} else if (rhs.fName.getFirstName().isEmpty()) {
								return -1;
							}
							return lhs.fName.getFirstName().compareTo(rhs.fName.getFirstName());
						};
					};
					break;
				case 2:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fName.getLastName().isEmpty()) {
								return 1;
								
							} else if (rhs.fName.getLastName().isEmpty()) {
								return -1;
							}
							return lhs.fName.getLastName().compareTo(rhs.fName.getLastName());
						};
					};
					break;
				case 3:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							if (lhs.fPhone.getMobilePhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getMobilePhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getMobilePhone().compareTo(rhs.fPhone.getMobilePhone());
						};
					};
					break;
				case 4:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							// empty string is lower in order
							if (lhs.fPhone.getHomePhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getHomePhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getHomePhone().compareTo(rhs.fPhone.getHomePhone());
						};
					};
					break;
				case 5:
					fComparator = new Comparator<Contact>() {
						public int compare(Contact lhs, Contact rhs) {
							// empty string is lower in order
							if (lhs.fPhone.getWorkPhone().isEmpty()) {
								return 1;
								
							} else if (rhs.fPhone.getWorkPhone().isEmpty()) {
								return -1;
							}
							return lhs.fPhone.getWorkPhone().compareTo(rhs.fPhone.getWorkPhone());
						};
					};
					break;
				default:
					break;
			}
		}
		
	}
	
	/**
	 * Constructor which creates a new Contact object with respective fields instantiated. 
	 * Strings and a bitmap object is expected
	 * 
	 * @param firstName - contact's first name
	 * @param lastName - contact's last name
	 * @param mobilePhone - contact's mobile phone number
	 * @param workPhone - contact's work phone number
	 * @param homePhone - contact's home phone number
	 * @param email - email address 
	 * @param birthday - date of birthday (Input to be formatted as D(D)-M(M)-YYYY
	 * @param contactAddressLine1 - address line one 
	 * @param contactAddressLine2 - address line two
	 * @param contactAddressLine3 - address line three
	 * @param contactAddressLine4 - address line four
	 * @param photo - Bitmap object denoting the contact's photo attribute
	 * @param id - Contact ID used for database
	 */
	public Contact(String firstName, String lastName, 
			String mobilePhone, String homePhone, String workPhone, 
			String email, 
			String birthday,
			String contactAddressLine1, String contactAddressLine2, String contactAddressLine3, String contactAddressLine4,  
			Bitmap photo, int id) {
		this.fName = new ContactName(firstName, lastName);
		this.fAddress = new ContactAddress(contactAddressLine1, contactAddressLine2, contactAddressLine3, contactAddressLine4);
		this.fEmail = new ContactEmail(email);
		this.fPhone = new ContactPhone(mobilePhone, homePhone, workPhone);
		this.fBirthday = new ContactBirthday(birthday);
		this.fPhoto = new ContactPhoto(photo);
		this.fId = id;
	}
	
	/**
	 * Constructor which creates a contact object from the various associating Contact attribute objects
	 * @param name
	 * @param phone
	 * @param email
	 * @param birthday
	 * @param address
	 * @param photo
	 * @param id
	 */
	public Contact(ContactName name, ContactPhone phone, ContactEmail email, ContactBirthday birthday,
					ContactAddress address, ContactPhoto photo, int id) {
		this.fName = name;
		this.fPhone = phone;
		this.fEmail = email;
		this.fBirthday = birthday;
		this.fAddress = address;
		this.fPhoto = photo;
		this.fId = id;
	}
	
	/**
	 * Name - Setter 
	 */
	public void setName(String firstName, String lastName) {
		this.fName.setFirstName(firstName);
		this.fName.setLastName(lastName);
	}
	
	/**
	 * Name - Getter 
	 */
	public ContactName getName() {
		return fName;
	}
	
	/**
	 * Address - Setter
	 */
	public void setAddress(String addressLine1, String addressLine2, String addressLine3, String addressLine4) {
		this.fAddress.setAddressLine1(addressLine1);
		this.fAddress.setAddressLine2(addressLine2);
		this.fAddress.setAddressLine3(addressLine3);
		this.fAddress.setAddressLine4(addressLine4);
	}
	
	/**
	 * Address - Getter
	 */
	public ContactAddress getAddress() {
		return fAddress;
	}
	
	/**
	 * Email - Setter
	 */
	public void setEmail(String emailString) {
		this.fEmail.setEmail(emailString);
	}
	
	/**
	 * Email - Getter
	 */
	public ContactEmail getEmail() {
		return fEmail;
	}
	
	/**
	 * Phone - Setter
	 */
	public void setPhone(String mobilePhone, String homePhone, String workPhone) {
		this.fPhone.setMobilePhone(mobilePhone);
		this.fPhone.setHomePhone(homePhone);
		this.fPhone.setWorkPhone(workPhone);
	}
	/**
	 * Phone - Getter
	 */
	public ContactPhone getPhone() {
		return fPhone;
	}
	
	/**
	 * Birthday - Setter
	 */
	public void setBirthday(String birthday) {
		this.fBirthday.setBirthday(birthday);
	}
	
	/**
	 * Birthday - Getter
	 */
	public ContactBirthday getBirthday() {
		return fBirthday;
	}
	
	/**
	 * Photo - Setter
	 */
	public void setPhoto(Bitmap photoBitmap) {
		this.fPhoto.setPhotoBitmap(photoBitmap);
	}
	
	/**
	 * Photo - Getter
	 */
	public ContactPhoto getPhoto() {
		return fPhoto;
	}
	
	/**
	 * ID - Getter
	 */
	public int getId() {
		return fId;
	}
	
	/**
	 * ID - Setter
	 */
	public void setId(int fId) {
		this.fId = fId;
	}
	
	
	/** Parcelable Interface Methods */
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fPhoto);
		dest.writeValue(fName);
		dest.writeValue(fPhone);
		dest.writeValue(fEmail);
		dest.writeValue(fBirthday);
		dest.writeValue(fAddress);
		dest.writeValue(fId);
	}
	
	public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
		@Override
		public Contact createFromParcel(Parcel in) {
			ContactPhoto photo = (ContactPhoto)in.readValue(getClass().getClassLoader());
			ContactName name = (ContactName)in.readValue(getClass().getClassLoader());
			ContactPhone phone = (ContactPhone)in.readValue(getClass().getClassLoader());
			ContactEmail email = (ContactEmail)in.readValue(getClass().getClassLoader());
			ContactBirthday birthday = (ContactBirthday)in.readValue(getClass().getClassLoader());
			ContactAddress address = (ContactAddress)in.readValue(getClass().getClassLoader());
			int id = (Integer)in.readValue(getClass().getClassLoader());
			
			return new Contact(name, phone, email, birthday, address, photo, id);
		}
		
		@Override
		public Contact[] newArray(int size) {
			return new Contact[size];
		}
	};
	
	@Override
	/**
	 * Override to String to return contact name in lower-case for a list adapter's filter
	 * function to work with this Contact implementation.
	 */
	public String toString() {
		return getName().toString().toLowerCase();
	}
}
