package com.akshaykalyan.contactutilities;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Singleton Class to return a custom type face object to use with the changing of font type of text.
 * Font typeface = Roboto Light
 * 
 * @author Akshay Pravin Kalyan | akal881 | 57886866
 */
public class FontRobotoLight {
	private static Typeface robotoLightTypeface = null;
	
	protected FontRobotoLight() {
		// hidden to stop instantiation
	}
	
	public static Typeface getTypeface(Context context) {
		if (robotoLightTypeface == null) {
			robotoLightTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Light.ttf");
		}
		return robotoLightTypeface;
	}
}
