package com.rp.rahmawatiputrianasari.research00.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.rp.rahmawatiputrianasari.research00.model.BatteryStatus;
import com.rp.rahmawatiputrianasari.research00.utils.DatabaseHelperSuper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 10/13/17.
 */

public class BatteryCheck extends BroadcastReceiver {
    //    DatabaseHelper myDb;
    private Dao<BatteryStatus, Integer> batteryStatusDao;
    public DatabaseHelperSuper databaseHelper = null;

    @Override
    public void onReceive(Context ctxt, Intent intent) {
//        myDb = new DatabaseHelper(ctxt);
        databaseHelper = DatabaseHelperSuper.getInstance(ctxt);
        batteryStatusDao = databaseHelper.getBatteryStatusDao();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = ctxt.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        String aa = String.valueOf(level) + "%";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        try {
            batteryStatusDao.create(new BatteryStatus(formattedDate, aa));
            Toast.makeText(ctxt, "Battery level Inserted", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(ctxt, "Battery level not Inserted", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
