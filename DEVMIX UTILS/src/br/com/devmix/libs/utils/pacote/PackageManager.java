package br.com.devmix.libs.utils.pacote;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.util.Log;

public class PackageManager {

	public static void listLaunchActivityes(Activity activity){
		android.content.pm.PackageManager packmngr = activity.getPackageManager(); 
        Intent ints = new Intent(Intent.ACTION_MAIN, null);
        ints.addCategory(Intent.CATEGORY_LAUNCHER); 
        List<ResolveInfo> list = packmngr.queryIntentActivities(ints, android.content.pm.PackageManager.PERMISSION_GRANTED); 
        if(list != null){
	        for(ResolveInfo rlnfo: list)
	        {
	             //Adjust the code and do tests within here.
	            Log.i("ACTIVITY INFO: ", rlnfo.activityInfo != null ? rlnfo.activityInfo.toString():"");
	            Log.i("ACTIVITY NAME: ",rlnfo.resolvePackageName != null ? rlnfo.resolvePackageName.toString():"");
	        }
        }
	}
}
