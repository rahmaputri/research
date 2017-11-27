package com.rp.rahmawatiputrianasari.research00.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.rp.rahmawatiputrianasari.research00.model.PowerStatus;
import com.rp.rahmawatiputrianasari.research00.utils.DatabaseHelperSuper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */

public class PowerStatusService extends Service {
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        private Dao<PowerStatus, Integer> powerStatusDao;
        private DatabaseHelperSuper databaseHelperSuper = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            databaseHelperSuper = DatabaseHelperSuper.getInstance(context);
            powerStatusDao = databaseHelperSuper.getPowerStatusDao();
            String action = intent.getAction();
            String strAction;
            if (action == Intent.ACTION_BATTERY_LOW) {
                strAction = "BATTERY_LOW";
            } else if (action == Intent.ACTION_BATTERY_OKAY) {
                strAction = "BATTERY_OKAY";
            } else if (action == Intent.ACTION_POWER_CONNECTED) {
                strAction = "POWER_CONNECTED";
            } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
                strAction = "POWER_DISCONNECTED";
            } else {
                strAction = "unknown!";
            }

            int status;

            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = context.registerReceiver(null, ifilter);
            status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            String msg;
            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    strAction += " BATTERY_STATUS_CHARGING ";
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    strAction += " BATTERY_STATUS_DISCHARGING ";
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    strAction += " BATTERY_STATUS_FULL ";
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    strAction += " BATTERY_STATUS_NOT_CHARGING ";
                    break;
                case BatteryManager.BATTERY_STATUS_UNKNOWN:
                    strAction += " BATTERY_STATUS_UNKNOWN ";
                    break;
                default:
                    strAction += "status = ...unknown! ";
            }

            int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) {
                strAction += " BATTERY_PLUGGED_USB ";
            }
            if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) {
                strAction += " BATTERY_PLUGGED_AC ";
            }

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            try {
                Toast.makeText(
                        context,
                        strAction,
                        Toast.LENGTH_LONG).show();
                powerStatusDao.create(new PowerStatus(formattedDate, strAction));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        filter.addAction("android.intent.action.ACTION_BATTERY_LOW");
        filter.addAction("android.intent.action.ACTION_BATTERY_OKAY");

        registerReceiver(receiver, filter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(receiver);
    }


}