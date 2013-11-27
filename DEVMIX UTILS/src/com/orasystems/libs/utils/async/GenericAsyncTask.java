package com.orasystems.libs.utils.async;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;

public interface GenericAsyncTask {

	public void publishProgress(int progress,String message);
	public void hideProgressBar();
	public ProgressDialog getProgressDialog();
	public void showProgressBar(Activity activity,String title,String message,int max,Drawable icon,boolean setCancelable,boolean setIndeterminate,boolean progressHorizontal);
	public void atualizaProgressDialog(GenericAsyncTask asyncTask,String message,int progress);
}
