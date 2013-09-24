package com.devmix.libs.utils.dao;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.os.Environment;

import com.devmix.libs.utils.files.FileManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;

public class DataBaseManager {
	public static void restore(Context context,String backupDir,String dbName,OrmLiteSqliteOpenHelper dbHelper) throws IOException {
	    if (Environment.getExternalStorageDirectory().canWrite()) {
	        File fileBackup = new File(backupDir, dbName);
	        if (!fileBackup.exists()) {
	            throw new IOException("Arquivo n�o encontrado!");
	        }
	        dbHelper.close();
			dbHelper = null;

			File file = new File(context.getDatabasePath(dbName).toString());
			if(!FileManager.copy(fileBackup.getPath().toString(), file.getPath().toString())){
				throw new IOException("N�o foi poss�vel realizar a restaura��o");
			}
	    } else {
	        throw new IOException("Sem permiss�o para escrita no SD Card");
	    }
	}
	public static void backup(Context context,String backupDir,String dbName,String dbBackupName,OrmLiteSqliteOpenHelper dbHelper) throws IOException{
		if (Environment.getExternalStorageDirectory().canWrite()) {
			File fileBackup = new File(backupDir, dbBackupName);

	        dbHelper.close();
			dbHelper = null;

			File file = new File(context.getDatabasePath(dbName).toString());
			if(!FileManager.copy(file.getPath().toString(),fileBackup.getPath().toString())){
				throw new IOException("N�o foi poss�vel realizar o backup");
			}
		} else {
	        throw new IOException("Sem permiss�o para escrita no SD Card");
	    }
	}
}
