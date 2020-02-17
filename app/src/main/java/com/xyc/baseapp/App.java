package com.xyc.baseapp;

import android.app.Application;
import android.content.Context;

import com.xyc.monitor.TimeMonitorConfig;
import com.xyc.monitor.TimeMonitorManager;

public class App extends Application{

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TMC_APPLICATION_START).recordTimeTag("Application-onAttach");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TMC_APPLICATION_START).recordTimeTag("Application-onCreate");
    }
}
