package com.devmix.libs.utils;

import android.app.Activity;
import android.content.Intent;

import com.devmix.libs.utils.preferences.AtalhoPreferences_;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;
@EBean
/**
 * <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT" />
 * @author echer
 *
 */
public class AtalhoManager {
	/** 
	 * SHARED PREFERENCES
	 */
	@Pref 
	public AtalhoPreferences_ prefAtalho;
	public void adicionaAtalho(Activity activity,int iconImageDrawable,String iconName,String packageIntent,String intentName,String nomeAtalho) {

		if(!prefAtalho.atalhoCriado().exists()){
			Intent shortcutIntent = new Intent();
			shortcutIntent.setClassName(packageIntent, intentName);
			shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			// shortcutIntent.putExtra("someParameter", "HelloWorld");

			Intent addIntent = new Intent();
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, nomeAtalho);
			addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
					Intent.ShortcutIconResource.fromContext(activity,
							iconImageDrawable));

			addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
			activity.sendBroadcast(addIntent);
			prefAtalho.edit().atalhoCriado().put(true).apply();
		}
	}
}
