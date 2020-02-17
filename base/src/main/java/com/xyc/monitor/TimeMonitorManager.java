package com.xyc.monitor;

import java.util.HashMap;

public class TimeMonitorManager {

    private static volatile TimeMonitorManager monitorManager;
    private HashMap<Integer, TimeMonitor> timeMonitorManagerHashMap;

    public synchronized static TimeMonitorManager getInstance() {
        if (monitorManager == null) {
            monitorManager = new TimeMonitorManager();
        }
        return monitorManager;
    }

    private TimeMonitorManager() {
        timeMonitorManagerHashMap = new HashMap<Integer, TimeMonitor>();
    }

    /**
     * 获取打点器
     * @param id  TimeMonitorConfig类型
     * @return
     */
    public TimeMonitor getTimeMonitor(int id) {
        TimeMonitor monitor = timeMonitorManagerHashMap.get(id);
        if (monitor == null) {
            monitor = new TimeMonitor(id);
            timeMonitorManagerHashMap.put(id, monitor);
        }
        return monitor;
    }
}
