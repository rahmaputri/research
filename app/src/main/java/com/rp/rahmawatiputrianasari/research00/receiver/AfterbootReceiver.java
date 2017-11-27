package com.rp.rahmawatiputrianasari.research00.receiver;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.rp.rahmawatiputrianasari.research00.service.ConnectionStatusService;
import com.rp.rahmawatiputrianasari.research00.service.PowerStatusService;
import com.rp.rahmawatiputrianasari.research00.utils.AndroidPermissionUtils;

/**
 * Created by norms on 12/28/16.
 */

public class AfterbootReceiver extends BroadcastReceiver {
    private static final String TAG = "Afterboot";

    @Override
    public void onReceive(Context context, Intent intent) {

        // we double check here for only boot complete event
        if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

            // start location service
            if (AndroidPermissionUtils.doCheckGrantPermission(context, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
//                context.startService(new Intent(context, LocationsService.class));
            }

            // start connection and power status listener
            context.startService(new Intent(context, PowerStatusService.class));
            context.startService(new Intent(context, ConnectionStatusService.class));

        }
    }

}
