package com.rp.rahmawatiputrianasari.research00.service;

import android.util.Log;

/**
 * Created by rahmawatiputrianasari on 11/7/17.
 */


public class MyJobService extends com.firebase.jobdispatcher.JobService {

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.v("Running", "====>>>>MyJobService");

        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.v("Stopping", "====>>>>MyJobService");
        return false;
    }
}
