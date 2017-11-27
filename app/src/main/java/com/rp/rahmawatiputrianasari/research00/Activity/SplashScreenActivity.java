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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
        }
    }

    @Override
    protected String[] getPermissionNeedCheck() {
        return new String[0];
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void goToNextScreen() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000L);

    }

    @Override
    public void showLoading() {
    }

    @Override
    public void dismissLoading() {
    }

    @Override
    public void loadContentError() {
        goToNextScreen();
    }

    @Override
    public void refreshContent() {
    }

    @Override
    public void showDialog(String title, String message, final int action) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
