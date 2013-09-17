package com.devmix.libs.utils.gps;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
/**
 * <uses-feature android:name="android.hardware.location" android:required="true"/>
 * <uses-feature android:name="android.hardware.location.gps" android:required="true"/>
 * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 * Classe para gestao do GPS do android
 * @author echer
 *
 */
public class GPS implements LocationListener{
	private static final String tag = GPS.class.getName();
	private static GPS instance;
	private Context context;
	private LocationManager lManager;
	private int miliseconds,meters = 0;
	private boolean onDisabledForceEnable = false;
	public boolean provedorLigado = false;
	private int statusProvedorInt = -1;
	private String statusProvedorStr = "";
	private Location location;
	/**
	 * cria uma instancia do GPS
	 * @param context contexto utilizado para inicializar o gerenciador de localizacao
	 * @param miliseconds tempo definido para pegar atualiza��es
	 * @param meters distancia definida para pegar atualiza��es
	 * @param onDisabledForceEnable quando o provedor de localiza��o for desativado for�ar ativa��o novamente?
	 * @return retorna uma instancia do GPS
	 */
	public static GPS buildInstance(Context context,int miliseconds, int meters,boolean onDisabledForceEnable){
		instance = new GPS(context,miliseconds,meters,onDisabledForceEnable);
		return instance;
	}
	public static GPS getInstance(){
		return instance;
	}
	private GPS(Context context,int miliseconds,int meters,boolean onDisabledForceEnable){
		this.miliseconds = miliseconds;
		this.meters = meters;
		this.onDisabledForceEnable = onDisabledForceEnable;
		this.context = context;
		if(instance != null)pauseGPS();
		turnGPSOnOff(context);
		lManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}
	/**
	 * Ativa o GPS do aparelho
	 * @param context contexto utilizado para ativa��o do GPS
	 */
	public static void turnGPSOnOff(Context context){
		  String provider = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		  if(!provider.contains("gps")){
		    final Intent poke = new Intent();
		    poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
		    poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
		    poke.setData(Uri.parse("3")); 
		    context.sendBroadcast(poke);
		  }
	}
	/**
	 * verifica se o provedor de gps via network esta ativado
	 * @return retorna true se estiver ativo senao retorna false
	 */
	public boolean isProviderEnabledNetWork(){
		return lManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	}
	/**
	 * verifica se o provedor de gps via gps esta ativado
	 * @return retorna true se estiver ativo senao retorna false
	 */
	public boolean isProviderEnabledGPS(){
		return lManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	}
	/**
	 * verifica se o provedor de gps passivo esta ativado
	 * @return retorna true se estiver ativo senao retorna false
	 */
	public boolean isProviderEnabledPassive(){
		return lManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
	}
	/**
	 * para o provedor de localiza��o atual
	 */
	public void pauseGPS(){
		try{
			if(lManager != null){
				lManager.removeUpdates(this);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * inicia gps, se nenhum provedor de gps estiver ativo gps � ligado
	 * @return retorna 1+msg de gps ativo para provedor de gps,2+msg de gps ativo para provedor de network,3+msg de gps ativo para provedor passivo
	 */
	public String startGPS(){
		turnGPSOnOff(context);
		if(isProviderEnabledGPS()){
			startAtualizaGPSGPS_Provider();
			return "1GPS Iniciado com sucesso via provedor de GPS";
		}else if(isProviderEnabledNetWork()){
			startAtualizaGPSNetwork_Provider();
			return "2GPS Iniciado com sucesso via provedor de network";
		}else if(isProviderEnabledPassive()){
			startAtualizaGPSPassive_Provider();
			return "3GPS Iniciado com sucesso via provedor passivo";
		}else{
			return "4Nenhum provedor iniciado";
		}
	}
	private void startAtualizaGPSGPS_Provider(){
		lManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, miliseconds, meters, this);
		if(this.getLocation() == null){
			this.setLocation(new Location(LocationManager.GPS_PROVIDER));
		}
	}
	
	private void startAtualizaGPSNetwork_Provider(){
		lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, miliseconds, meters, this);
		if(this.getLocation() == null){
			this.setLocation(new Location(LocationManager.NETWORK_PROVIDER));
		}
	}
	private void startAtualizaGPSPassive_Provider(){
		lManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, miliseconds, meters, this);
		if(this.getLocation() == null){
			this.setLocation(new Location(LocationManager.PASSIVE_PROVIDER));
		}
	}
	@Override
	public void onLocationChanged(Location location) {
		Log.i(tag, "Location changed " +
				"\nProvedor:" + location.getProvider()+
				"\nLatitude:"+location.getLatitude()+ 
				"\nLongitude: "+location.getLongitude()+
				"\nPrecis�o: "+location.getAccuracy()+
				"\nMetros/segundos:"+location.getSpeed()+
				"\nInforma��es Adicionais: "+location.getExtras()+
				"\nDire��o horizontal: "+location.getBearing()
			);
		this.setLocation(location);
	}
	@Override
	public void onProviderDisabled(String provider) {
		Log.i(tag, "Provedor desativado: "+provider);
		provedorLigado = false;
		
		if(onDisabledForceEnable){
			GPS.turnGPSOnOff(context);
			provedorLigado = true;
			Log.i(tag, "Provedor ativado: "+provider);
		}
	}
	@Override
	public void onProviderEnabled(String provider) {
		Log.i(tag, "Provedor ativado: "+provider);
		provedorLigado = true;
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		switch (status) {
        case LocationProvider.OUT_OF_SERVICE:
        	setStatusProvedorInt(status);
        	setStatusProvedorStr("Provedor de localiza��o est� fora de servi�o");
        	Log.i(tag, getStatusProvedorStr());
            break;
        case LocationProvider.TEMPORARILY_UNAVAILABLE:
        	setStatusProvedorInt(status);
        	setStatusProvedorStr("Provedor de localiza��o est� indispon�vel"); 
        	Log.i(tag, getStatusProvedorStr());
            break;
        case LocationProvider.AVAILABLE:
        	setStatusProvedorInt(status);
        	setStatusProvedorStr("Provedor de localiza��o est� dispon�vel");
        	Log.i(tag, getStatusProvedorStr());
            break;
        default:
        	setStatusProvedorInt(status);
        	setStatusProvedorStr("Provedor de localiza��o est� em um status desconhecido");
        	Log.i(tag, getStatusProvedorStr());
        }
	}
	/**
	 * pega o valor atual do status do provedor, valor default -1
	 * @return status do provedor
	 */
	public int getStatusProvedorInt() {
		return statusProvedorInt;
	}
	private void setStatusProvedorInt(int statusProvedorInt) {
		this.statusProvedorInt = statusProvedorInt;
	}
	/**
	 * pega a resposta do provedor, valor default ""
	 * @return status do provedor
	 */
	public String getStatusProvedorStr() {
		return statusProvedorStr;
	}
	private void setStatusProvedorStr(String statusProvedorStr) {
		this.statusProvedorStr = statusProvedorStr;
	}
	/**
	 * pega a localiza��o atual, valor default = null
	 * @return location
	 */
	public Location getLocation() {
		return location;
	}
	private void setLocation(Location location) {
		this.location = location;
	}
}
