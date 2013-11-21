package com.orasystems.libs.utils.dao;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.orasystems.libs.utils.files.FileManager;

public class DataBaseManager {
	public static void restore(Activity activity,String backupDir,String dbName,OrmLiteSqliteOpenHelper dbHelper,Class<?> actiivtyRestartClass) throws IOException {
	    if (Environment.getExternalStorageDirectory().canWrite()) {
	        File fileBackup = new File(backupDir, dbName);
	        if (!fileBackup.exists()) {
	            throw new IOException("Arquivo não encontrado!");
	        }
	        dbHelper.close();
			dbHelper = null;

			File file = new File(activity.getDatabasePath(dbName).toString());
			if(!FileManager.copy(fileBackup.getPath().toString(), file.getPath().toString())){
				throw new IOException("Não foi possível realizar a restauração");
			}else{
				if(actiivtyRestartClass != null){
					activity.finish();
					activity.startActivity(new Intent(activity, actiivtyRestartClass));
				}
			}
	    } else {
	        throw new IOException("Sem permissão para escrita no SD Card");
	    }
	}
	public static void backup(Activity activity,String backupDir,String dbName,String dbBackupName,OrmLiteSqliteOpenHelper dbHelper,Class<?> actiivtyRestartClass) throws IOException{
		if (Environment.getExternalStorageDirectory().canWrite()) {
			File fileBackup = new File(backupDir, dbBackupName);

	        dbHelper.close();
			dbHelper = null;

			File file = new File(activity.getDatabasePath(dbName).toString());
			if(!FileManager.copy(file.getPath().toString(),fileBackup.getPath().toString())){
				throw new IOException("Não foi possível realizar o backup");
			}else{
				if(actiivtyRestartClass != null){
					activity.finish();
					activity.startActivity(new Intent(activity, actiivtyRestartClass));
				}
			}
		} else {
	        throw new IOException("Sem permissão para escrita no SD Card");
	    }
	}
}
