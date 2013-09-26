package com.devmix.libs.utils.preferences;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface AtalhoPreferences {
	@DefaultBoolean(false)
	boolean atalhoCriado();

}