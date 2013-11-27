package com.orasystems.libs.utils.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import android.util.Log;

import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EBean;
import com.orasystems.libs.utils.files.FileManager;
import com.orasystems.libs.utils.log.manager.LogManager;
import com.orasystems.libs.utils.log.model.LogDB;

@EBean
public class LogUtils {
	
	@Bean
	public LogManager logManager;

	public static final String tag = "LogUtils";
	public String pathSD;
	public String directory;
	
	public LogUtils build(String pathSD,String directory){
		this.pathSD = pathSD;
		this.directory = directory;
		return this;
	}

	public void writeLogInfo(String tag,String metodo,String string) {
		Log.i(tag, string);
		LogDB l = new LogDB();
		l.setLog(string);
		l.setTipo(LogDB.TIPO_INFO);
		l.setData(new Date(System.currentTimeMillis()));
		l.setMetodo(metodo);
		try {
			logManager.save(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void writeLogError(String tag,String metodo,String string) {
		Log.e(tag, string);
		LogDB l = new LogDB();
		l.setLog(string);
		l.setTipo(LogDB.TIPO_ERROR);
		l.setData(new Date(System.currentTimeMillis()));
		l.setMetodo(metodo);
		try {
			logManager.save(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeLogWarn(String tag,String metodo,String string) {
		Log.w(tag, string);
		LogDB l = new LogDB();
		l.setLog(string);
		l.setTipo(LogDB.TIPO_WARN);
		l.setData(new Date(System.currentTimeMillis()));
		l.setMetodo(metodo);
		try {
			logManager.save(l);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void showLogInfo(String tag,String string){
		Log.i(tag, string);
	}
	
	public void showLogError(String tag, String string){
		Log.e(tag, string);
	}
	
	public void showLogWarn(String tag,String string){
		Log.w(tag, string);
	}
	
	public String stackTraceToString(Throwable e) {
	    StringBuilder sb = new StringBuilder();
	    for (StackTraceElement element : e.getStackTrace()) {
	        sb.append(element.toString());
	        sb.append("\n");
	    }
	    return sb.toString();
	}
	
	public String getStackTrace(Throwable t) {
	    StringWriter sw = new StringWriter();
	    t.printStackTrace(new PrintWriter(sw));
	    return sw.toString();
	}
	
	@SuppressWarnings("unused")
	private boolean permissao() {
		if(!FileManager.isExternalStorageReadable()){
			Log.i(tag, "Sem permissão de leitura no cartão SD");
			return false;
		}
		if(!FileManager.isExternalStorageWritable()){
			Log.i(tag, "Sem permissão de escrita no cartão SD");
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean diretorio() {
		if(!FileManager.diretorioExiste(pathSD)){
			if(!FileManager.criaDiretorio(pathSD, directory)){
				Log.i(tag, "Erro ao criar diretório");
				return false;
			}
			return true;
		}else{
			return true;
		}
	}
	
}
