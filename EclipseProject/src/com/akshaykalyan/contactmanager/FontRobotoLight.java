package com.akshaykalyan.contactmanager;

import android.content.Context;
import android.graphics.Typeface;

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
