package com.rp.rahmawatiputrianasari.research00.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.rp.rahmawatiputrianasari.research00.model.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 10/13/17.
 */

public class BatteryCheck extends BroadcastReceiver {
    DatabaseHelper myDb;

    @Override
    public void onReceive(Context ctxt, Intent intent) {
        myDb = new DatabaseHelper(ctxt);
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = ctxt.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        String aa = String.valueOf(level) + "%";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());

//        boolean isInserted = myDb.insertData(formattedDate, aa);
//        if (isInserted)
//            Toast.makeText(ctxt, "Data Inserted", Toast.LENGTH_LONG).show();
//        else
//            Toast.makeText(ctxt, "Data not Inserted", Toast.LENGTH_LONG).show();

    }


}
