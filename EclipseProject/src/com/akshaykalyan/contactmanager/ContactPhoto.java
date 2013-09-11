package com.akshaykalyan.contactmanager;

import android.graphics.Bitmap;


public class ContactPhoto {
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
}
