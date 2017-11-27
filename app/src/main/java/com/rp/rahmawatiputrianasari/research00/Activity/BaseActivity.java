package com.rp.rahmawatiputrianasari.research00.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.rp.rahmawatiputrianasari.research00.utils.AndroidPermissionUtils;
import com.rp.rahmawatiputrianasari.research00.utils.DatabaseHelperSuper;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final long KEY_BACK_INTERVAL = 2000L;
    private static MainActivity mainActivity;
    public DatabaseHelperSuper databaseHelperSuper = null;

    /**
     * You'll need this in your class to cache the helper in the class.
     */
    private long backPressedPeriod;

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static void setMainActivity(MainActivity mainActivity) {
        BaseActivity.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // check runtime permission on Android is enable or not
        if (AndroidPermissionUtils.doRequestPermissionIfNeed(this, this.getPermissionNeedCheck())) {
            this.onPermissionDenied();
        } else {
            this.onPermissionGranted();
        }
    }

    /**
     * User do not granted all permission need on this activity
     */
    protected void onPermissionDenied() {
    }

    /**
     * the all permission is granted
     */
    protected void onPermissionGranted() {
    }
    public DatabaseHelperSuper getDatabaseHelper() {
        if (databaseHelperSuper == null) {
//            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
            databaseHelperSuper = databaseHelperSuper.getInstance(this);
        }
        return databaseHelperSuper;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AndroidPermissionUtils.REQUEST_CODE: {
                if (!AndroidPermissionUtils.doCheckRequestPermissionsResult(AndroidPermissionUtils.REQUEST_CODE, permissions, grantResults)) {
                    this.onRequestPermissionDenied();
                } else {
                    this.onRequestPermissionGranted();
                }
                break;
            }
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

    }

    /**
     * User do not granted all permission need on this activity
     */
    protected void onRequestPermissionDenied() {
//        showToast("Aplikasi ini membutuhkan \"permission\" ini dalam penggunaannya!");
        this.finish();
    }

    /**
     * the all permission is granted
     */
    protected void onRequestPermissionGranted() {
    }

    /**
     * get the array permission need to check when user open this activity
     *
     * @return the permission need to check on this activity
     */
    protected abstract String[] getPermissionNeedCheck();

}

