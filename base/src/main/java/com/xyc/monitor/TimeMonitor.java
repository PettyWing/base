package com.xyc.monitor;

import android.util.Log;

import java.util.HashMap;

/**
 * 耗时监听器，记录整个过程的耗时情况，可以用在需要统计的地方
 */
public class TimeMonitor {
    private static final String TAG = "TimeMonitor";
    // 当前观察的用户id
    private int mMonitorId = -1;
    private boolean isWorking; // 监听器是否在工作

    private HashMap<String, Long> mTimeTag = new HashMap<>();
    private long mStartTime = 0;

    public TimeMonitor(int mMonitorId) {
        Log.d(TAG, "init TimeMonitor id: " + mMonitorId);
        this.mMonitorId = mMonitorId;
    }

    public int getmMonitorId() {
        return mMonitorId;
    }

    /**
     * 启动耗时监听器
     */
    public void startMonitor() {
        // 启动前清除掉之前的记录
        if (mTimeTag.size() > 0) {
            mTimeTag.clear();
        }
        // 记录启动时间
        mStartTime = System.currentTimeMillis();
        isWorking = true;
    }

    /**
     * 记录输入的这个Tag的时间
     *
     * @param tag
     */
    public void recordTimeTag(String tag) {
        if (!isWorking) {
            startMonitor();
        }
        // 如果有相同的TAG，就清除之前的记录
        if (mTimeTag.get(tag) != null) {
            mTimeTag.remove(tag);
        }
        long time = System.currentTimeMillis() - mStartTime;
        Log.d(TAG, "recordTimeTag " + tag + ": " + time);
        mTimeTag.put(tag, time);
    }

    /**
     * 结束耗时监听器，并传入结束Tag
     *
     * @param tag
     * @param writeLog
     */
    public void end(String tag, boolean writeLog) {
        recordTimeTag(tag);
        end(writeLog);
        isWorking = false;
    }

    /**
     * 结束耗时监听器
     *
     * @param writeLog
     */
    public void end(boolean writeLog) {
        if (writeLog) {
            //写入到本地文件
        }
    }

    public HashMap<String, Long> getTimeTags() {
        return mTimeTag;
    }
}
