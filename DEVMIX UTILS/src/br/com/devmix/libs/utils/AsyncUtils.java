package br.com.devmix.libs.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;

import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.UiThread;

@EBean
public class AsyncUtils implements GenericAsyncTask{
	@UiThread
	public void publishProgress(int progress,String message) {
		progressDialog.setProgress(progress);
		if(message != null)progressDialog.setMessage(message);
	}
	@UiThread
	public void hideProgressBar(){
		progressDialog.dismiss();
	}
	private ProgressDialog progressDialog;
	@UiThread
	public void showProgressBar(Activity activity,String title,String message,int max,Drawable icon,boolean setCancelable,boolean setIndeterminate,boolean progressHorizontal){
		progressDialog = LightProgressDialog.create(activity,progressHorizontal);
        progressDialog.setCancelable(setCancelable);
        progressDialog.setIndeterminate(setIndeterminate);
        progressDialog.setMax(max);
        progressDialog.setTitle(title);
        progressDialog.setIcon(icon);
        progressDialog.setMessage(message);
        progressDialog.show();
	}

}
