//package com.rp.rahmawatiputrianasari.research00.receiver;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.BatteryManager;
//
//import com.rp.rahmawatiputrianasari.research00.model.DbBatteryStatus;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
///**
// * Created by rahmawatiputrianasari on 10/20/17.
// */
//
//public class PowerConnectionReceiver extends BroadcastReceiver {
//
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        DbBatteryStatus myDb = new DbBatteryStatus(context);
//        String action = intent.getAction();
//        String strAction;
//        if (action == Intent.ACTION_BATTERY_LOW) {
//            strAction = "BATTERY_LOW";
//        } else if (action == Intent.ACTION_BATTERY_OKAY) {
//            strAction = "BATTERY_OKEY";
//        } else if (action == Intent.ACTION_POWER_CONNECTED) {
//            strAction = "POWER_CONNECTED";
//        } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
//            strAction = "POWER_DISCONNECTED";
//        } else {
//            strAction = "unknown!";
//        }
//
//        int status;
//
//        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
//        Intent batteryStatus = context.registerReceiver(null, ifilter);
//        status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
//        String msg;
//        switch (status) {
//            case BatteryManager.BATTERY_STATUS_CHARGING:
//                strAction += " BATTERY_STATUS_CHARGING ";
//                break;
//            case BatteryManager.BATTERY_STATUS_DISCHARGING:
//                strAction += " BATTERY_STATUS_DISCHARGING ";
//                break;
//            case BatteryManager.BATTERY_STATUS_FULL:
//                strAction += " BATTERY_STATUS_FULL ";
//                break;
//            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
//                strAction += " BATTERY_STATUS_NOT_CHARGING ";
//                break;
//            case BatteryManager.BATTERY_STATUS_UNKNOWN:
//                strAction += " BATTERY_STATUS_UNKNOWN ";
//                break;
//            default:
//                strAction += "status = ...unknown! ";
//        }
//
//
//        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
//        if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) {
//            strAction += " BATTERY_PLUGGED_USB ";
//        }
//        if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) {
//            strAction += " BATTERY_PLUGGED_AC ";
//        }
//
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
//        String formattedDate = df.format(c.getTime());
//
////        boolean isInserted = myDb.insertData(formattedDate, strAction);
////        if (isInserted)
////            Toast.makeText(
////                    context,
////                    strAction,
////                    Toast.LENGTH_LONG).show();
////        else
////            Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();
//
//    }
//
//
//}
//
