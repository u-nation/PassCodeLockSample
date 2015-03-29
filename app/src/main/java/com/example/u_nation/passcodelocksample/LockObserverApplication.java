package com.example.u_nation.passcodelocksample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.u_nation.passcodelocksample.util.LogUtil;

import java.util.HashSet;

public class LockObserverApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private HashSet<Integer> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        activityStack = new HashSet<>();
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(this);
        super.onTerminate();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.w("activityStack.size() = " + activityStack.size());
        if (activityStack.size() == 0) {
            LogUtil.w("起動");
        }
        activityStack.add(activity.hashCode());
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityStack.remove(activity.hashCode());
        LogUtil.w("activityStack.size() = " + activityStack.size());
        if (activityStack.size() == 0) {
            LogUtil.w("終了");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
