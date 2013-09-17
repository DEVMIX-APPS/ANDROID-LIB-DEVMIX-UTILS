package com.devmix.libs.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import android.annotation.SuppressLint;
import android.net.ParseException;
import android.util.Log;

public class DateManager {
	
	private static final String tag = "DateManager";
	/*
	 * @autor fernando
	 * @param data data para converter, a data deve vir neste formato - 25012012
	 * @param formato formato para qual se deseja converter a data Ex: dd/MM/yyyy
	 * @return retorna data formatada no formato desejado
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date converteData(String data,String formato) {
		
        String dia = null, mes = null, ano = null;
        java.sql.Date dataFormatada = null;
        SimpleDateFormat format = new SimpleDateFormat(formato); 
        dia = data.substring(0, 2);
        mes = data.substring(2, 4);
        ano = data.substring(4, 8); 
        data = dia + "/" + mes + "/" + ano; 
        try {
            dataFormatada = new java.sql.Date(format.parse(data).getTime());
            Log.i(tag, "Data formatada: "+dataFormatada);
        } catch (ParseException e) {
            Log.i(tag, "Exception ao converter data: "+e.getMessage());
        } catch (java.text.ParseException e) {
			Log.i(tag, "Exception ao converter data: "+e.getMessage());
		} 
        return dataFormatada;
    } 
	/*
	 * @autor fernando
	 * @param hora hora para converter
	 * @param formato formato para qual se deseja converter a hora Ex: HH:mm:ss
	 * @return retorna a hora no formato desejado
	 */
    @SuppressLint("SimpleDateFormat")
	public static Time converteHora(String hora,String formato) {
        String hh = null, min = null, seg = null;
        java.sql.Time horaFormatada = null;
        SimpleDateFormat format = new SimpleDateFormat(formato); 

        hh = hora.substring(8, 10);
        min = hora.substring(10, 12);
        seg = hora.substring(12, 14);
        hora = hh + ":" + min + ":" + seg; 
        try {
            horaFormatada = new java.sql.Time(format.parse(hora).getTime());
            Log.i(tag, "Data formatada: "+horaFormatada);
        } catch (ParseException e) {
        	Log.i(tag, "Exception ao converter data: "+e.getMessage());
        } catch (java.text.ParseException e) {
        	Log.i(tag, "Exception ao converter data: "+e.getMessage());
		} 
        return horaFormatada;
    }
    /*
     * @autor echer
     * @return retorna data atual no formato HH:mm:ss Ex: 17:01:02
     */
    public static String getHorasAtual(){
    	return new Time(System.currentTimeMillis()).toString();
    }
    /*
     * @autor echer
     * @param formato formato no qual se deseja formatar a data atual Ex:dd/MM/yyyy
     * @return retorna data atual no formato desejado
     * 
     */
    @SuppressLint("SimpleDateFormat")
	public static String getDateAtual(String formato){
    	return new SimpleDateFormat(formato).format(new Date(System.currentTimeMillis()));
    }
}
