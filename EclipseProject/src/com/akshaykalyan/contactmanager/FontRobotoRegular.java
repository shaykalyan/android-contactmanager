package com.akshaykalyan.contactmanager;

import android.content.Context;
import android.graphics.Typeface;

public class FontRobotoRegular {
	private static Typeface robotoRegTypeface = null;
	
	protected FontRobotoRegular() {
		// hidden to stop instantiation
	}
	
	public static Typeface getTypeface(Context context) {
		if (robotoRegTypeface == null) {
			robotoRegTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
		}
		return robotoRegTypeface;
	}
}
