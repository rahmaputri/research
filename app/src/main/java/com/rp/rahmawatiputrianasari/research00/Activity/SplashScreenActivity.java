package com.rp.rahmawatiputrianasari.research00.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.rp.rahmawatiputrianasari.research00.R;
import com.rp.rahmawatiputrianasari.research00.presenter.SplashPresenter;
import com.rp.rahmawatiputrianasari.research00.utils.AndroidPermissionUtils;
import com.rp.rahmawatiputrianasari.research00.view.SplashScreenView;

/**
 * Created by rahmawatiputrianasari on 10/10/17.
 */

public class SplashScreenActivity extends BaseActivity implements SplashScreenView {

    public static final String TAG = "SplashScreenActivity";

    private SplashPresenter presenter;
//    private ConfirmDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//            throw new RuntimeException("This is a crash");

//        Utils.initIdentity(this);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        } else {
            presenter = new SplashPresenter(this, this);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!AndroidPermissionUtils.doRequestPermissionIfNeed(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS)) {

            presenter.doProvisioning();

//            goToNextScreen();
        }
    }

    @Override
    protected String[] getPermissionNeedCheck() {
        return new String[0];
    }

//    @Override
//    protected String[] getPermissionNeedCheck() {
//        return new String[]{
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.RECEIVE_SMS,
//                Manifest.permission.CAMERA
//        };
//    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void goToNextScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                if (!Prefs.getString(Constants.PREFS_LOGIN_ACTIVE, "").isEmpty()) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
//                } else {
//                    Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//                    startActivity(i);
//                    finish();
//                }
//
            }
        }, 3000L);

    }

//    @Override
//    public void closeTheApp() {
//        finish();
//    }

    @Override
    public void showLoading() {
//        showLoadingDialog();
    }

    @Override
    public void dismissLoading() {

//        dismissLoadingDialog();
    }

    @Override
    public void loadContentError() {
//        LogUtil.debug("#load content error");
        goToNextScreen();
    }

    @Override
    public void refreshContent() {
//        LogUtil.debug("#refresh content");
    }

    @Override
    public void showDialog(String title, String message, final int action) {
////
////        switch (action) {
////            case Constants.ACTION_NONE:
////                confirmDialog = new ConfirmDialog(this, title, message, false);
////                break;
////
////            case Constants.ACTION_FORCE_UPDATE:
////                confirmDialog = new ConfirmDialog(this, title, message, true, true);
////                break;
////
////            case Constants.ACTION_SOFT_UPDATE:
////                confirmDialog = new ConfirmDialog(this, title, message, false, false);
////                break;
////        }
////
////        confirmDialog.setOnDialogButtonClickListener(new BaseDialog.OnDialogButtonClickListener() {
////            @Override
////            public void onDialogButtonClick(BaseDialog dialog, int button) {
////                if (button == BaseDialog.BUTTON_OK) {
////
////                    switch (action) {
////                        case Constants.ACTION_NONE:
////                            SplashScreenActivity.this.finish();
////                            break;
////
////                        case Constants.ACTION_FORCE_UPDATE:
////                            // icon_download file from our server
////                            //                            Utils.openApplicationOnGooglePlay(SplashScreenActivity.this, getApplication().getPackageName());
////                            SplashScreenActivity.this.finish();
////                            break;
////
////                        case Constants.ACTION_SOFT_UPDATE:
////                            // icon_download file from our server
//////                            Utils.openApplicationOnGooglePlay(SplashScreenActivity.this, getApplication().getPackageName());
////                            SplashScreenActivity.this.finish();
////                            break;
////                    }
////                } else {
////                    goToNextScreen();
////                }
//            }
//        });
//
//
//        confirmDialog.show();
    }

    @Override
    protected void onDestroy() {
//        try {
//            if (confirmDialog != null && confirmDialog.isShowing()) {
//                confirmDialog.dismiss();
//            }
//        } catch (Exception e) {
////            LogUtil.error("Failed dismiss dialog");
//        }

        super.onDestroy();
    }
}
