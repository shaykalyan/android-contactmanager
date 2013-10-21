package com.akshaykalyan.contactutilities;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Singleton Class to return a custom type face object to use with the changing of font type of text.
 * Font typeface = Roboto Regular
 * 
 * @author Akshay Pravin Kalyan | akal881 | 57886866
 */
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
