package com.rp.rahmawatiputrianasari.research00.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.rp.rahmawatiputrianasari.research00.model.ConnectionStatus;
import com.rp.rahmawatiputrianasari.research00.utils.DatabaseHelperSuper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by rahmawatiputrianasari on 11/13/17.
 */

public class ConnectionStatusService extends Service {
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        private Dao<ConnectionStatus, Integer> connectionStatusDao;
        public DatabaseHelperSuper databaseHelper = null;

        @Override
        public void onReceive(Context context, Intent intent) {
            databaseHelper = DatabaseHelperSuper.getInstance(context);
            connectionStatusDao = databaseHelper.getConnectionStatusDao();
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.getState() == NetworkInfo.State.CONNECTED) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    try {
                        connectionStatusDao.create(new ConnectionStatus(formattedDate, "WIFI"));
                        Toast.makeText(context,"WIFI",Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    try {
                        connectionStatusDao.create(new ConnectionStatus(formattedDate, "NETWORK"));
                        Toast.makeText(context,"NETWORK",Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
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
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

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