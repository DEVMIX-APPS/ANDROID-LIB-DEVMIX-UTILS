package br.com.devmix.libs.utils.internet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Conexao extends BroadcastReceiver
{
	private String tag = "Conexao";
	private ConnectivityManager cManager;
	public Conexao(Context context){
		cManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}
	/**
	 * @autor echer
	 * verifica atividade da network se esta conectado
	 * @return true para conectado false para desconectado
	 */
	public boolean conectadoOuConectando(){
		try{
			return cManager.getActiveNetworkInfo().isConnectedOrConnecting();
		}catch(Exception e){
			return false;
		}
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(tag, "Action: " + intent.getAction());
    	if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
		    @SuppressWarnings("deprecation")
			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
		    String typeName = info.getTypeName();
		    String subtypeName = info.getSubtypeName();
		    boolean available = info.isAvailable();
		    Log.i(tag, "Network Type: " + typeName 
				+ ", subtype: " + subtypeName
				+ ", available: " + available);
    	}
	}
	
}