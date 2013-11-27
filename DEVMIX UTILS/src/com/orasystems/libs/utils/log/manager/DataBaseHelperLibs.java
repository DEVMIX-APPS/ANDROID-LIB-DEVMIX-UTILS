package com.orasystems.libs.utils.log.manager;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.orasystems.libs.utils.log.model.LogDB;

/**
 * Created by echer on 04/06/13.
 */
public class DataBaseHelperLibs extends OrmLiteSqliteOpenHelper {



    public static final String DATABASE_NAME = "database_library.db";
    private static final int DATABASE_VERSION = 1;

    @Override
    public void close() {
        super.close();
    }

    public DataBaseHelperLibs(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database,
                         final ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, LogDB.class);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
                          int arg3) {

    }

}
