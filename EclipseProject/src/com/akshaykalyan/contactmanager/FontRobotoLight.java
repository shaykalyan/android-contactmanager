package com.akshaykalyan.contactmanager;

import android.content.Context;
import android.graphics.Typeface;

public class FontRobotoLight {
	private static Typeface robotoRegTypeface = null;
	
	protected FontRobotoLight() {
		// hidden to stop instantiation
	}
	
	public static Typeface getTypeface(Context context) {
		if (robotoRegTypeface == null) {
			robotoRegTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		}
		return robotoRegTypeface;
	}
}
