package com.akshaykalyan.contactmanager;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class ContactPhoto implements Parcelable {
	private Bitmap fPhotoBitmap;
	
	ContactPhoto(Bitmap photoBitmap) {
		this.fPhotoBitmap = photoBitmap;
	}
	
	public Bitmap getPhotoBitmap() {
		return fPhotoBitmap;
	}
	

	public void setPhotoBitmap(Bitmap photoBitmap) {
		this.fPhotoBitmap = photoBitmap;
	}
	
	//Parcelable methods
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeValue(fPhotoBitmap);
	}
	
	public static final Parcelable.Creator<ContactPhoto> CREATOR = new Parcelable.Creator<ContactPhoto>() {
		public ContactPhoto createFromParcel(Parcel in) {
			Bitmap photoBitmap = (Bitmap)in.readValue(getClass().getClassLoader());
			
			return new ContactPhoto(photoBitmap);
		};
		
		public ContactPhoto[] newArray(int size) {
			return new ContactPhoto[size];
		};
	};
	
	private ContactPhoto(Parcel in) {
		fPhotoBitmap = (Bitmap)in.readValue(getClass().getClassLoader());
	}
}
