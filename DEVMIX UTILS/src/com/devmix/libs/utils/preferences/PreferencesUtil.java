package com.devmix.libs.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesUtil {

	public static SharedPreferences.Editor getPrefEditor(Context context){
		SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
		return preference.edit();
	}
	public static SharedPreferences getPref(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
