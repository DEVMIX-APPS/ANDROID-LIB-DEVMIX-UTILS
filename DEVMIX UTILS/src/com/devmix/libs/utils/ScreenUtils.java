package com.devmix.libs.utils;

import android.app.Activity;
import android.view.Display;

public class ScreenUtils {
	@SuppressWarnings("deprecation")
	public static int getScreenWidth(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay(); 
		return display.getWidth();  // deprecated
	}

	@SuppressWarnings("deprecation")
	public static int getScreenHeight(Activity activity) {
		Display display = activity.getWindowManager().getDefaultDisplay(); 
		return display.getHeight();  // deprecated
	}
}
