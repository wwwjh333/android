package com.jnu.exp7;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //百度地图SDK的全局初始化
        SDKInitializer.initialize(getApplicationContext());
    }
}