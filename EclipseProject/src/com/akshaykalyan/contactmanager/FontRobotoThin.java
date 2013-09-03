package com.akshaykalyan.contactmanager;

import android.content.Context;
import android.graphics.Typeface;

public class FontRobotoThin {
	private static Typeface robotoRegTypeface = null;
	
	protected FontRobotoThin() {
		// hidden to stop instantiation
	}
	
	public static Typeface getTypeface(Context context) {
		if (robotoRegTypeface == null) {
			robotoRegTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
		}
		return robotoRegTypeface;
	}
}
