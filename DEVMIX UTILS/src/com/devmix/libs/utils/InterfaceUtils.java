package com.devmix.libs.utils;

import android.app.Activity;
import android.widget.Toast;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.RootContext;
import com.googlecode.androidannotations.annotations.UiThread;

@EBean
public class InterfaceUtils {

	@RootContext
	Activity activity;
	/**
	 * EXIBE TOAST NA TELA
	 * @param string STRING A SER EXIBIDA
	 */
	@UiThread
	public void exibeToast(String string) {
		Toast.makeText(activity, string, Toast.LENGTH_SHORT).show();
	}
	
	@UiThread
	public void exibeMsg(String msg){
		DialogUtils.exibeMsg(msg, activity);
	}
}
