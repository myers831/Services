package com.example.admin.services.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.example.admin.services.model.Car;

import java.util.ArrayList;
import java.util.List;

public class MyBoundService extends Service {
    IBinder iBinder = new MyBinder();
    List<Car> carList;
    public static final String TAG = "myBoundServiceTag";
    
    public MyBoundService() {
    }

    public class MyBinder extends Binder {
        public MyBoundService getService(){
            return  MyBoundService.this;
        }
    }
    
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    public  void initData(){
        carList = new ArrayList<>();
        carList.add(new Car("Sedan", "Dodge","Black", 1987));
        carList.add(new Car("Sedan", "BMW","Red", 2017));
    }
    
    public List<Car> getCarList(){
        return carList;
    }
    
    public boolean addCar(Car car){
        return carList.add(car);
    }
}
