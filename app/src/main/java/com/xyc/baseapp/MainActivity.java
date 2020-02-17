package com.xyc.baseapp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.komect.baseapp.R;
import com.komect.baseapp.databinding.ActivityMainBinding;
import com.xyc.monitor.TimeMonitorConfig;
import com.xyc.monitor.TimeMonitorManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        TimeMonitorManager.getInstance().getTimeMonitor(TimeMonitorConfig.TMC_APPLICATION_START).recordTimeTag("Activity-onCreate-Over");
    }
}
