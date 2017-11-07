package com.rp.rahmawatiputrianasari.research00.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.rp.rahmawatiputrianasari.research00.utils.AndroidPermissionUtils;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private static final long KEY_BACK_INTERVAL = 2000L;
    private static MainActivity mainActivity;

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


//    /**
//     * The method check the location feature is enabled or disabled.
//     * @return true if need to request enable location feature, false if otherwise
//     */
//    private boolean doRequestEnableLocationIfNeed() {
//        boolean ret = false;
//        if (!TopjekUtils.isLocationEnabled(this)) {
//            ret = true;
//            try {
//                LocationRequest locationRequest = LocationRequest.create();
//                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
//                        .addLocationRequest(locationRequest);
//                builder.setAlwaysShow(true);
//                PendingResult result =
//                        LocationServices.SettingsApi.checkLocationSettings(
//                                mGoogleApiClient,
//                                builder.build()
//                        );
//                result.setResultCallback(this);
//            } catch (Exception ex){
//                TopjekUtils.requestEnableGPS(this,true, App.itsme.getUserProfile() != null && App.itsme.getUserProfile().isDriver());
//            }
//        }
//        return ret;
//    }

//    /**
//     * the method will be call if the location settings is enabled
//     */
//    protected void onLocationFeatureEnable(){
//
//    }
//    /**
//     * the method will be call if the location settings is enabled
//     */
//    protected void onLocationFeatureDisable(){
//
//    }

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

//    @Override
//    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        BaseFragment currentFragment = getCurrentFragment();
//
//        if (fragmentManager != null) {
//            if (currentFragment != null) {
//                currentFragment.onBackPressed();
//            } else {
//                super.onBackPressed();
//            }
//        } else {
//            super.onBackPressed();
//        }
//
////        if (getCurrentFragment())
//    }

//    public void goToFragment(Fragment fragment, String tag) {
//        goToFragment(fragment, tag, true);
//    }
//
//    @SuppressWarnings("ResourceType")
//    public void goToFragment(Fragment fragment, String tag, boolean isAddtoBackStack) {
//        try {
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////        if (isAddtoBackStack) {
//            if (Utils.isEnableAnimatorScale(getApplicationContext()))
//                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
////            transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
//
////            }
////        }
//            transaction.replace(R.id.flContent, fragment, tag);
//            if (isAddtoBackStack) {
//                transaction.addToBackStack(tag);
//
//                // hack navigation
////            if (!(fragment instanceof HomeFragment))
////                getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
//
//            }
////        if (App.itsme.isRunningInForeGround()) {
//            transaction.commit();
////        } else {
////            transaction.commitAllowingStateLoss();
////        }
//
//        } catch (Exception ex) {
//
//        }
//    }

//    public BaseFragment getCurrentFragment() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        BaseFragment currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.flContent);
//
//        return currentFragment;
//    }
//
//    public LoadingDialog showLoadingDialog() {
//        if (loadingDialog == null)
//            loadingDialog = new LoadingDialog(this);
//
//        return showLoadingDialog(false);
//    }

//    public void showDialog(String title, String message, boolean isOkButtonOnly) {
//        confirmDialog = new ConfirmDialog(getApplicationContext(), title, message, isOkButtonOnly);
//        confirmDialog.setOnDialogButtonClickListener(new BaseDialog.OnDialogButtonClickListener() {
//            @Override
//            public void onDialogButtonClick(BaseDialog dialog, int button) {
//                if (button == BaseDialog.BUTTON_OK) {
//                    LogUtil.debug("OK clicked");
//                } else {
//                    LogUtil.debug("Cancel clicked");
//                }
//            }
//        });
//        confirmDialog.show();
//    }

//    public LoadingDialog showLoadingDialog(boolean isCancelAble) {
//        if (!this.isFinishing() && loadingDialog != null) {
//            if (!loadingDialog.isShowing()) {
//                loadingDialog.show();
//            }
//            loadingDialog.setCancelable(isCancelAble);
//        }
//        return loadingDialog;
//    }
//
//    public void dismissLoadingDialog() {
//        if (!this.isFinishing() && loadingDialog != null && loadingDialog.isShowing()) {
//            loadingDialog.dismiss();
//        }
//    }
//
//    public void showDialogErrorNetwork() {
//        dismissLoadingDialog();
//
//        confirmDialog = new ConfirmDialog(this, getString(R.string.text_title_dialog_internet_connection_problem), getString(R.string.text_message_dialog_internet_connection), true);
//
//        confirmDialog.setOnDialogButtonClickListener(new BaseDialog.OnDialogButtonClickListener() {
//            @Override
//            public void onDialogButtonClick(BaseDialog dialog, int button) {
//                if (button == BaseDialog.BUTTON_OK) {
//                    LogUtil.debug("OK clicked");
//                } else {
//                    LogUtil.debug("Cancel clicked");
//                }
//            }
//        });
//
//        confirmDialog.show();
//    }
//
//    public void showBaseDialog(String title, String message, int action) {
//        dismissLoadingDialog();
//
//        confirmDialog = new ConfirmDialog(this, title, message, true);
//
//        confirmDialog.setOnDialogButtonClickListener(new BaseDialog.OnDialogButtonClickListener() {
//            @Override
//            public void onDialogButtonClick(BaseDialog dialog, int button) {
//                if (button == BaseDialog.BUTTON_OK) {
//                    LogUtil.debug("OK clicked");
//                } else {
//                    LogUtil.debug("Cancel clicked");
//                }
//            }
//        });
//
//        confirmDialog.show();
//    }

//    public void backToHomeScreen() {
//        popToTopFragment();
//    }
//
//    public void popFragment() {
//        getSupportFragmentManager().popBackStack();
//
////        int count = getSupportFragmentManager().getBackStackEntryCount();
////
////        if (count == 0) {
////            super.onBackPressed();
////            //additional code
////        } else {
////            getSupportFragmentManager().popBackStack();
////        }
//    }
//
//    public void popFragment(String tag, int option) {
//        getSupportFragmentManager().popBackStack(tag, option);
//    }
//
//    public void popToTopFragment() {
//        FragmentManager fm = getSupportFragmentManager();
//        int popId = 0;
//        if (fm.getBackStackEntryCount() > 0) {
//            popId = fm.getBackStackEntryAt(0).getId();
//        }
//        getSupportFragmentManager().popBackStack(popId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
////        getSupportFragmentManager().popBackStackImmediate();
//        getSupportFragmentManager().executePendingTransactions();
//    }
//
//    public void handleBackTwiceToExit() {
//        if (isEnableBackTwiceToExit()) {
//            if (backPressedPeriod + KEY_BACK_INTERVAL > System.currentTimeMillis()) {
////                super.onBackPressed();
//                finish();
//            } else {
//                backPressedPeriod = System.currentTimeMillis();
//                Utils.showToast(this, getString(R.string.text_tap_again_to_exit));
//            }
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    private boolean isEnableBackTwiceToExit() {
//        return true;
//    }
//
//    BaseActivity getBaseActivity() {
//        return this;
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//
//        releaseDatabaseHelper();
//    }
//
//    /**
//     * You'll need this in your class to get the helper from the manager once per class.
//     */
//    public DatabaseHelper getDatabaseHelper() {
//        if (databaseHelper == null) {
////            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
//            databaseHelper = DatabaseHelper.getInstance(this);
//        }
//        return databaseHelper;
//    }
//
//    /**
//     * You'll need this in your class to release the helper when done.
//     */
//    public void releaseDatabaseHelper() {
//        if (databaseHelper != null) {
//            OpenHelperManager.releaseHelper();
//            databaseHelper = null;
//        }
//    }

}

