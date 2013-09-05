package br.com.devmix.libs.utils.test;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import br.com.devmix.libs.utils.AsyncUtils;
import br.com.devmix.libs.utils.GenericAsyncTask;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Fullscreen;
import com.googlecode.androidannotations.annotations.res.DrawableRes;

@EActivity(R.layout.activity_main)
@Fullscreen
public class MainActivity extends Activity {

	/**
	 * ASYNCTASK
	 */
	@Bean(AsyncUtils.class)
	public GenericAsyncTask asyncTask;
	@DrawableRes(R.drawable.ic_launcher)
	public Drawable icon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		asyncTask.showProgressBar(this, "Aguarde", "Teste de progressbar", 10,icon , true, true, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}

}
