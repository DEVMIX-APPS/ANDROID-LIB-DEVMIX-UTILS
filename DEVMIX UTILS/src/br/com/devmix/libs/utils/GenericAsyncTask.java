package br.com.devmix.libs.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;

public interface GenericAsyncTask {

	public void publishProgress(int progress,String message);
	public void hideProgressBar();
	public void showProgressBar(Activity activity,String title,String message,int max,Drawable icon,boolean setCancelable,boolean setIndeterminate,boolean progressHorizontal);
}
