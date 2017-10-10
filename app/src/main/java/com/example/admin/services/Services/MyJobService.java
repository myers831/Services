package com.example.admin.services.Services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Admin on 10/9/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService{
    public static final String TAG = "myJobServiceTag";
    
    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        Log.d(TAG, "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), MyStartedService.class);
        startService(intent);
        
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: ");
        return false;
    }
}
