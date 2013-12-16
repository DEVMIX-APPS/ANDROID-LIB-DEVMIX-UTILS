package com.orasystems.libs.utils.log.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.fima.cardsui.objects.CardStack;
import com.fima.cardsui.views.CardUI;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.NoTitle;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringArrayRes;
import com.orasystems.libs.utils.R;
import com.orasystems.libs.utils.log.LogUtils;
import com.orasystems.libs.utils.log.model.LogDB;

/**
 * <activity android:name="com.orasystems.libs.utils.ActivityLog_"
 * android:screenOrientation="portrait" > </activity>
 * 
 * @author echer
 * 
 */
@NoTitle
@EActivity
// @EActivity
public class ActivityLog extends FragmentActivity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.l_activity_log);
	}

	@Bean
	public LogUtils log;

	@ViewById
	public CardUI logsView;
	@ViewById
	public Button btnDtFinal;
	@ViewById
	public Button btnDtInicial;
	@ViewById
	public Spinner spTipo;
	@ViewById
	public Spinner spMetodo;

	@StringArrayRes
	public String[] spTipoValues;

	public ArrayList<String> spMetodoValues;
	
	@Override
	public void onBackPressed(){
		finish();
	}
	
	@AfterViews
	public void afterViews() {

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		btnDtFinal.setText(String.format("%d/%d/%d", day, month + 1, year));
		btnDtInicial.setText(String.format("%d/%d/%d", day, month + 1, year));

		logsView.setSwipeable(false);

		ArrayList<String> spTipoValuesArr = new ArrayList<String>();
		for (String s : spTipoValues) {
			spTipoValuesArr.add(s);
		}

		spTipo.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, spTipoValuesArr));

		spMetodoValues = new ArrayList<String>();

		try {
			spMetodoValues = log.logManager.listGroupByMetodo();
		} catch (Exception e) {
			e.printStackTrace();
		}

		spMetodo.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, spMetodoValues));

	}

	/**
	 * ABRE DIALOG FRAGMENT PARA A SELEÇÃO DA DATA FINAL
	 * 
	 * @param v
	 */
	@Click
	public void btnDtFinal(View v) {
		DialogFragment newFragment = new DatePickerDataFiltroFinal(0, 0, 0);
		newFragment.show(this.getSupportFragmentManager(), "datePickerFinal");
	}

	@SuppressLint("ValidFragment")
	class DatePickerDataFiltroFinal extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		private int year, month, day = 0;

		public DatePickerDataFiltroFinal(int year, int month, int day) {
			final Calendar c = Calendar.getInstance();
			if (year == 0)
				this.year = c.get(Calendar.YEAR);
			if (month == 0)
				this.month = c.get(Calendar.MONTH);
			if (day == 0)
				this.day = c.get(Calendar.DAY_OF_MONTH);
			if (year != 0)
				this.year = year;
			if (month != 0)
				this.month = month;
			if (day != 0)
				this.day = day;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			setDataFinal(year, month, day);
		}
	}

	/**
	 * ABRE DIALOG FRAGMENT PARA SELEÇÃO DA DATA INICIAL
	 * 
	 * @param v
	 */
	@Click
	public void btnDtInicial(View v) {
		DialogFragment newFragment = new DatePickerDataFiltroInicial(0, 0, 0);
		newFragment.show(getSupportFragmentManager(), "datePickerInicial");
	}

	/**
	 * CLASSE PARA FILTRAGEM DA DATA INICIAL
	 * 
	 * @author ECHER
	 * 
	 */
	@SuppressLint("ValidFragment")
	class DatePickerDataFiltroInicial extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {
		private int year, month, day = 0;

		public DatePickerDataFiltroInicial(int year, int month, int day) {
			final Calendar c = Calendar.getInstance();
			if (year == 0)
				this.year = c.get(Calendar.YEAR);
			if (month == 0)
				this.month = c.get(Calendar.MONTH);
			if (day == 0)
				this.day = c.get(Calendar.DAY_OF_MONTH);
			if (year != 0)
				this.year = year;
			if (month != 0)
				this.month = month;
			if (day != 0)
				this.day = day;
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			setDataInicial(year, month, day);
		}
	}

	/**
	 * SALVA DATA INICIAL NO BOTÃO E NAS PREFERENCIAS
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	@SuppressLint("DefaultLocale")
	private void setDataInicial(int year, int month, int day) {
		String data = String.format("%d/%d/%d", day, month + 1, year);
		btnDtInicial.setText(data);
	}

	/**
	 * SALVA DATA INICIAL NO BOTÃO E NAS PREFERENCIAS
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	@SuppressLint("DefaultLocale")
	private void setDataFinal(int year, int month, int day) {
		String data = String.format("%d/%d/%d", day, month + 1, year);
		btnDtFinal.setText(data);
	}
	
	@Click
	public void btnExcluir(View v){
		try {
			log.logManager.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Click
	public void btnSearch(View v) {
		CardStack stack = new CardStack();
		stack.setTitle("Logs");
		
		logsView.reset();

		//logsView.removeAllViews();

		// extract date
		String[] dataInicial = btnDtInicial.getText().toString().split("/");
		String[] dataFinal = btnDtFinal.getText().toString().split("/");

		// put date in calendar
		Calendar cInicial = Calendar.getInstance();
		cInicial.set(Calendar.YEAR, Integer.valueOf(dataInicial[2]));
		cInicial.set(Calendar.MONTH, Integer.valueOf(dataInicial[1]) - 1);
		cInicial.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dataInicial[0]));
		cInicial.set(Calendar.HOUR_OF_DAY, 0);
		cInicial.set(Calendar.MINUTE, 0);
		cInicial.set(Calendar.SECOND, 0);

		// put date in calendar
		Calendar cFinal = Calendar.getInstance();
		cFinal.set(Calendar.YEAR, Integer.valueOf(dataFinal[2]));
		cFinal.set(Calendar.MONTH, Integer.valueOf(dataFinal[1]) - 1);
		cFinal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dataFinal[0]));
		cFinal.set(Calendar.HOUR_OF_DAY, 23);
		cFinal.set(Calendar.MINUTE, 59);
		cFinal.set(Calendar.SECOND, 59);

		try {
			String tipo = spTipo.getSelectedItem().toString();
			
			String metodo = spMetodo.getAdapter() != null&& spMetodo.getAdapter().getCount() > 0 ? spMetodo.getSelectedItem().toString() : "";
			
			if(tipo != null && "Todos".equals(tipo))tipo = null;

			if(metodo != null && "Todos".equals(metodo))metodo = null;
			
			for (Object l : log.logManager.list(new Date(cInicial.getTimeInMillis()),new Date(cFinal.getTimeInMillis()), tipo,metodo)) {
				LogDB logDB = (LogDB) l;
				MyCard card = new MyCard(logDB.getMetodo() + " - "+ logDB.getData().toLocaleString(),logDB.getMessage()+"\n\n"+ logDB.getLog());
				stack.add(card);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// add stack to cardView
		logsView.addStack(stack);

		// draw cards
		logsView.refresh();
	}
}
