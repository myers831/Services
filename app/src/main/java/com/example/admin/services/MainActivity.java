package com.example.admin.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.services.R;
import com.example.admin.services.Services.MyBoundService;
import com.example.admin.services.Services.MyIntentService;
import com.example.admin.services.Services.MyJobService;
import com.example.admin.services.Services.MyStartedService;
import com.example.admin.services.model.Car;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "mainActivityTag";
    MyBoundService myBoundService;
    boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void startService(View view) {
        Intent normalIntent = new Intent(this, MyStartedService.class);
        Intent intIntent = new Intent(this, MyIntentService.class);
        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch(view.getId()){
            case R.id.btnStartedService:
                normalIntent.putExtra("data", "Some Data ");
                startService(normalIntent);
                break;
            case R.id.btnStopStartedService:
                stopService(normalIntent);
                break;
            case R.id.btnStopIntentService:
                intIntent.putExtra("data", "Some Data ");
                intIntent.setAction("Task1");
                startService(intIntent);
                break;
            case R.id.btnStopIntent2Service:
                intIntent.putExtra("data", "Some Data ");

                intIntent.setAction("Task2");
                startService(intIntent);
                break;

            case R.id.btnBoundService:

                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnBoundServiceGetCars:

                if(isBound){
                    List<Car> carList = myBoundService.getCarList();

                    for(Car car: carList){
                        Log.d(TAG, "onServiceConnected: " + "Make: " + car.getMake()
                                + " Type: " + car.getType()
                                + " Color: " + car.getColor()
                                + " Year: " + car.getYear());
                    }
                }
                break;
            case R.id.btnBoundServiceAddCar:

                if(isBound){
                    Car car = new Car("Sedan", "Dodge","Blue", 1987);
                    boolean isCarAdded = myBoundService.addCar(car);
                    if(isCarAdded){
                        Toast.makeText(myBoundService, "Car Added", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnUnBoundService:

                if(isBound){
                   unbindService(serviceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnJobSchedularService:

                ComponentName componentName = new ComponentName(this, MyJobService.class);
                JobInfo jobInfo = new JobInfo.Builder(0, componentName)
                        .setMinimumLatency(1000)
                        .setOverrideDeadline(5000)
                        .build();

                JobScheduler jobScheduler = getSystemService(JobScheduler.class);
                jobScheduler.schedule(jobInfo);
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();

            isBound = true;
            myBoundService.initData();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };
}
