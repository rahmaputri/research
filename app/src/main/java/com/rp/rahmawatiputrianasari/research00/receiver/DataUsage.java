package com.rp.rahmawatiputrianasari.research00.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.widget.Toast;

import com.rp.rahmawatiputrianasari.research00.model.DbDataUsage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 10/23/17.
 */

public class DataUsage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        DbDataUsage myDb2 = new DbDataUsage(context);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());

        long totalRX = TrafficStats.getTotalRxBytes();
        long totalTX = TrafficStats.getTotalTxBytes();
        long mobileRX = TrafficStats.getMobileRxBytes();
        long mobileTX = TrafficStats.getMobileTxBytes();


        long total = totalRX + totalTX;
        long mobile = mobileRX + mobileTX;
        long wifi = total - mobile;

        boolean isInserted = myDb2.insertData(formattedDate, Long.toString(mobile), Long.toString(wifi), Long.toString(total));
        if (isInserted)
            Toast.makeText(context, "Data Inserted", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();


    }
}
