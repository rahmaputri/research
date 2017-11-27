package com.rp.rahmawatiputrianasari.research00.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.TrafficStats;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.rp.rahmawatiputrianasari.research00.model.DataUsage;
import com.rp.rahmawatiputrianasari.research00.utils.DatabaseHelperSuper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 10/23/17.
 */

public class DataUsageReceiver extends BroadcastReceiver {
    private Dao<DataUsage, Integer> dataUsageDao;
    public DatabaseHelperSuper databaseHelper = null;

    @Override
    public void onReceive(Context context, Intent intent) {
//        DbDataUsage myDb2 = new DbDataUsage(context);
        databaseHelper = DatabaseHelperSuper.getInstance(context);
        dataUsageDao = databaseHelper.getDataUsageDao();
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
        try {
            dataUsageDao.create(new DataUsage(formattedDate, Long.toString(total), Long.toString(wifi), Long.toString(mobile)));
            Toast.makeText(context, "Data Usage Inserted", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context, "Data Usage not Inserted", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
