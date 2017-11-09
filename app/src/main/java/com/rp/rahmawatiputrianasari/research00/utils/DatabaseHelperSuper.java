package com.rp.rahmawatiputrianasari.research00.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.rp.rahmawatiputrianasari.research00.model.PowerStatus;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */

public class DatabaseHelperSuper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "ut_v2.db";
    private static final int DATABASE_VERSION = 7;


    private static DatabaseHelperSuper sInstance;

    private Dao<PowerStatus, Integer> powerStatus;

    public DatabaseHelperSuper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelperSuper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHelperSuper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            setNullDaoObject();
            TableUtils.createTableIfNotExists(connectionSource, PowerStatus.class);
        } catch (SQLException | java.sql.SQLException e) {
            LogUtil.error("Could not create new table: " + e);
        }

    }

    private void setNullDaoObject() {
        powerStatus = null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        Log.d("DatabaseHelper", "onUpgrade: " + oldVersion + " to " + newVersion);

        try {


            onCreate(db, connectionSource);
        } catch (SQLException e) {
            LogUtil.error("Could not create new table: " + e);
        }
    }

    public Dao<PowerStatus, Integer> getPowerStatus() {
        if (powerStatus == null) {
            try {
                powerStatus = getDao(PowerStatus.class);
            } catch (java.sql.SQLException e) {
                LogUtil.error("Failed get powerStatusDao: " + e.getMessage());
            }
        }
        return powerStatus;
    }

    @Override
    public void close() {
        setNullDaoObject();

        super.close();
    }
}