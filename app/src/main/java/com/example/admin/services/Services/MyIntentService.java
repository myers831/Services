package com.example.admin.services.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    
    public static final String TAG = "myIntentServiceTag";
    
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d(TAG, "onStartCommand: " + intent.getStringExtra("data") + Thread.currentThread());

            switch(intent.getAction()){
                case "Task1":
                    Log.d(TAG, "onHandleIntent: Executing Task 1");

                    for(int i = 0; i < 5; i++) {
                        try {
                            Log.d(TAG, "onHandleIntent: Task complettion " + i);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.d(TAG, "onHandleIntent: Completed Task 1");
                    break;

                case "Task2":
                    Log.d(TAG, "onHandleIntent: Executing Task 2");
                    List<String> stringList = new ArrayList<>();
                    stringList.add("string 1");
                    stringList.add("string 2");
                    stringList.add("string 3");

                    for(String s : stringList) {
                        Log.d(TAG, "onHandleIntent: Task success for " + s);
                    }
                    Log.d(TAG, "onHandleIntent: Completed Task 2");
                    break;

            }
        }
    }
}
