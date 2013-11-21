package com.orasystems.libs.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.ContentValues;

public class DateManager {

	 /**
     * Formata uma data DD/MM/YYYY
     *
     * @param data Data para formatar
     * @return Data formatada
     */
    public static String FormatarData(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s/%s/%s", valores.getAsString("DD"), valores.getAsString("MM"), valores.getAsString("AA"));
    }

    /**
     * Formata uma data DD/MM/YYYY HH:MM:SS
     *
     * @param data Data para formatar
     * @return Data formatada
     */
    public static String FormatarDataHora(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s/%s/%s %s:%s:%s", valores.getAsString("DD"), valores.getAsString("MM"), valores.getAsString("AA"),
            valores.getAsString("HH"), valores.getAsString("MI"), valores.getAsString("SS"));
    }

    /**
     * Formata uma data YYYY-MM-DD
     *
     * @param data Data para formatar
     * @return Data formatada
     */
    public static String FormatarDataBanco(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s-%s-%s", valores.getAsString("AA"), valores.getAsString("MM"), valores.getAsString("DD"));
    }

    /**
     * Formata uma data YYYY-MM-DD HH:MM:SS
     *
     * @param data Data para formatar
     * @return Data formatada
     */
    public static String FormatarDataHoraBanco(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s-%s-%s %s:%s:%s", valores.getAsString("AA"), valores.getAsString("MM"), valores.getAsString("DD"),
            valores.getAsString("HH"), valores.getAsString("MI"), valores.getAsString("SS"));
    }

    /**
     * Formata uma data MM/DD/YYYY HH:MM:SS
     *
     * @param data Data para formatar
     * @return Data formatada
     */
    public static String FormatarDataHoraAmericano(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s/%s/%s %s:%s:%s", valores.getAsString("MM"), valores.getAsString("DD"), valores.getAsString("AA"),
            valores.getAsString("HH"), valores.getAsString("MI"), valores.getAsString("SS"));
    }

    /**
     * Formata data e hora DD/MM/YYYY HH:MM
     *
     * @param data Data e Hora para formatar
     * @return Data e Hora formatada
     */
    public static String FormatarDataHoraHHMM(Date data)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(data, valores);
        return String.format("%s/%s/%s %s:%s", valores.getAsString("DD"), valores.getAsString("MM"), valores.getAsString("AA"),
            valores.getAsString("HH"), valores.getAsString("MI"));
    }

    /**
     * Formata hora HH:MM
     *
     * @param hora Hora para formatar
     * @return Hora formatada
     */
    public static String FormatarHoraHHMM(Date hora)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(hora, valores);
        return String.format("%s:%s", valores.getAsString("HH"), valores.getAsString("MI"));
    }

    /**
     * Formata hora HH:MM:SS
     *
     * @param hora Hora para formatar
     * @return Hora formatada
     */
    public static String FormatarHoraBanco(Date hora)
    {
        ContentValues valores = new ContentValues();
        ComponentesDaDataFmt(hora, valores);
        return String.format("%s:%s:%s", valores.getAsString("HH"), valores.getAsString("MI"), valores.getAsString("SS"));
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
    /**
     * Obtém os componentes da data em formato numérico
     *
     * @param data    Data
     * @param valores Componentes da data
     */
    public static void ComponentesDaDataFmt(Date data, ContentValues valores)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);

        valores.put("AA", String.format("%04d", cal.get(Calendar.YEAR)));
        valores.put("MM", String.format("%02d", cal.get(Calendar.MONTH) + 1));
        valores.put("DD", String.format("%02d", cal.get(Calendar.DAY_OF_MONTH)));
        valores.put("HH", String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)));
        valores.put("MI", String.format("%02d", cal.get(Calendar.MINUTE)));
        valores.put("SS", String.format("%02d", cal.get(Calendar.SECOND)));
        valores.put("MS", String.format("%02d", cal.get(Calendar.MILLISECOND)));
    }
}
