package com.rp.rahmawatiputrianasari.research00;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */
// test revert commit for log beanstalk (merge with ut_installment_fandi)
public class App extends Application implements Application.ActivityLifecycleCallbacks {
    private static App instance = null;
    private final String TAG = "APP";
//    public static boolean isSupportAnimation;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        instance = this;
//        // UNIVERSAL IMAGE LOADER SETUP
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheOnDisc(true).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                getApplicationContext())
//                .defaultDisplayImageOptions(defaultOptions)
//                .memoryCache(new WeakMemoryCache())
//                .imageDownloader(new AuthDownloader(getApplicationContext()))
//                .discCacheSize(100 * 1024 * 1024).build();
//
//        ImageLoader.getInstance().init(config);
//        // END - UNIVERSAL IMAGE LOADER SETUP


//        isSupportAnimation = Utils.isEnableAnimatorScale(getApplicationContext());

//        FacebookSdk.sdkInitialize(getApplicationContext());
//        AppEventsLogger.activateApp(this);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
