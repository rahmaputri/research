package com.rp.rahmawatiputrianasari.research00.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rp.rahmawatiputrianasari.research00.model.DbNetworkSc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 10/20/17.
 */

public class NetworkCheck extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DbNetworkSc myDb = new DbNetworkSc(context);
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());
//            if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//                boolean isInserted = myDb.insertData(formattedDate, "WIFI");
//                if (isInserted)
//                    Toast.makeText(context, "connect wifi", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();
//
//            } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
//                boolean isInserted = myDb.insertData(formattedDate, "MOBILE_NETWORK");
//                if (isInserted)
//                    Toast.makeText(context, "connect NETWORK", Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();
//            }
        }

    }

}
