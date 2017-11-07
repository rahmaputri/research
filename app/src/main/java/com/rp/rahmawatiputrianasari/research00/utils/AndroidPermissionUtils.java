package com.rp.rahmawatiputrianasari.research00.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */


public class AndroidPermissionUtils {
    public static final int REQUEST_CODE = 56789;
    public final static int COARSE_LOCATION_RESULT = 100;
    public final static int FINE_LOCATION_RESULT = 101;
    public final static int CALL_PHONE_RESULT = 102;
    public final static int CAMERA_RESULT = 103;
    public final static int READ_CONTACTS_RESULT = 104;
    public final static int WRITE_EXTERNAL_RESULT = 105;
    public final static int RECORD_AUDIO_RESULT = 106;
    public final static int ALL_PERMISSIONS_RESULT = 107;


    public static boolean doCheckGrantPermission(Context pContext, String... permissions) {
        boolean ret = true;
        if (pContext != null) {
            if (isNeedCheckRuntimePermission() && permissions != null && permissions.length > 0) {
                // Here, thisActivity is the current activity
                for (String permissionItem : permissions) {
                    if (ContextCompat.checkSelfPermission(pContext,
                            permissionItem)
                            != PackageManager.PERMISSION_GRANTED) {
                        ret = false;
                    }
                }
            }
        } else {
            ret = false;
        }
        return ret;
    }

    public static void doCheckGrantPermission(Activity activity, String... permissions) {
        boolean ret = true;
        if (activity != null) {
            if (isNeedCheckRuntimePermission() && permissions != null && permissions.length > 0) {
                // Here, thisActivity is the current activity
                for (String permissionItem : permissions) {
                    if (ContextCompat.checkSelfPermission(activity,
                            permissionItem)
                            != PackageManager.PERMISSION_GRANTED) {

                        // Should we show an explanation?
                        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                permissionItem)) {

                            // Show an explanation to the user *asynchronously* -- don't block
                            // this thread waiting for the user's response! After the user
                            // sees the explanation, try again to request the permission.

                        } else {

                            // No explanation needed, we can request the permission.

                            ActivityCompat.requestPermissions(activity,
                                    new String[]{permissionItem},
                                    REQUEST_CODE);

                            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                            // app-defined int constant. The callback method gets the
                            // result of the request.
                        }

                    }
                }
            }
        } else {
            return;
        }
    }

    public static boolean doRequestPermissionIfNeed(Activity pActivity, String... permisions) {
        boolean ret = false;
        if (pActivity == null || pActivity.isFinishing()) {
            ret = true;
        } else {
            if (isNeedCheckRuntimePermission() && permisions != null && permisions.length > 0) {
                List<String> permissionNeedRequest = new ArrayList<>();
                for (String permissionItem : permisions) {
                    if (ContextCompat.checkSelfPermission(pActivity,
                            permissionItem)
                            != PackageManager.PERMISSION_GRANTED) {
                        permissionNeedRequest.add(permissionItem);
                    }
                }
                if (permissionNeedRequest != null && !permissionNeedRequest.isEmpty()) {
                    ActivityCompat.requestPermissions(pActivity,
                            permissionNeedRequest.toArray(new String[permissionNeedRequest.size()]),
                            REQUEST_CODE);
                    ret = true;
                }
            }
        }
        return ret;
    }

//    public static boolean doRequestPermissionIfNeed(Fragment pFragment, String... permisions){
//        boolean ret = false;
//        if (pFragment != null  && isNeedCheckRuntimePermission() && permisions != null && permisions.length > 0){
//            List<String> permissionNeedRequest = new ArrayList<>();
//            for (String permissionItem : permisions) {
//                if (ContextCompat.checkSelfPermission(pFragment.getActivity(),
//                        permissionItem)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    permissionNeedRequest.add(permissionItem);
//                }
//            }
//            if (permissionNeedRequest != null && !permissionNeedRequest.isEmpty()){
//                pFragment.requestPermissions(
//                        permissionNeedRequest.toArray(new String[permissionNeedRequest.size()]),
//                        REQUEST_CODE);
//                ret = true;
//            }
//        }
//        return ret;
//    }


    /**
     * check request permission result.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     * @return true if all permission has been accepted by user, false if otherwise
     */
    public static boolean doCheckRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean ret = false;
        if (requestCode == REQUEST_CODE && permissions != null && permissions.length == grantResults.length) {
            for (int resultItem : grantResults) {
                if (resultItem == PackageManager.PERMISSION_GRANTED) {
                    ret = true;
                } else {
                    ret = false;
                    break;
                }
            }
        }
        return ret;
    }

    public static boolean isNeedCheckRuntimePermission() {
        boolean ret = false;
        ret = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
        return ret;
    }

}
