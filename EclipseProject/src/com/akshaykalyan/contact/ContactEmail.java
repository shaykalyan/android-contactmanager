package com.akshaykalyan.contact;

import java.util.regex.Pattern;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import com.akshaykalyan.contactmanager.R;

public class ContactEmail implements Parcelable {
	private String fEmail;
	
	public ContactEmail() {
		new ContactEmail("");
	}
	
	public ContactEmail(String emailString) {
		this.fEmail = emailString;
	}
	
	public String getEmailString() {
		return fEmail;
	}
	
	public void setEmailString(String emailString) {
		this.fEmail = emailString;
	}
	
	@Override
	public String toString() {
		return fEmail;
	}
	
	//Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fEmail);
	}
	
	public static final Parcelable.Creator<ContactEmail> CREATOR = new Parcelable.Creator<ContactEmail>() {
		public ContactEmail createFromParcel(Parcel in) {
			String emailString = (String)in.readValue(getClass().getClassLoader());
			return new ContactEmail(emailString);
		};
		
		public ContactEmail[] newArray(int size) {
			return new ContactEmail[size];
		};
	};
	
	private ContactEmail(Parcel in) {
		fEmail = (String)in.readValue(getClass().getClassLoader());
	}
	
	
	// email validation
	
	public static class Validation {
		private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
				"(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		private static final String MSG = "Invalid Email";
		
		public static boolean isValidEmailAddress(EditText editText) {
			
			String text = editText.getText().toString().trim();
			
			// text required and editText is blank, so return false
	        if (!hasText(editText) ) {
	        	return true;
	        }
	 
	        // pattern doesn't match so returning false
	        if (!Pattern.matches(EMAIL_REGEX, text)) {
	            editText.setError(MSG);
	            return false;
	        } else {
	        	return true;
	        }

	    }
		
		public static boolean hasText(EditText editText) {
			 
	        String text = editText.getText().toString().trim();
	        editText.setError(null);
	 
	        // length 0 means there is no text
	        if (text.length() == 0) {
	            return false;
	        }
	        return true;
	    }
	}
}
