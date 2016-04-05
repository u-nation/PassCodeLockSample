package com.example.u_nation.passcodelocksample;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.example.u_nation.passcodelocksample.util.PrefUtil;

import java.util.HashSet;

import timber.log.Timber;

import static com.example.u_nation.passcodelocksample.Constants.PREF_KEY_IS_LOCKED;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private HashSet<Integer> activityStack = new HashSet<>();
    private static MyApplication app;
    private boolean isLaunchApp;

    public static MyApplication getInstance() {
        if (app == null) app = new MyApplication();
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        PrefUtil.setSharedPreferences(getApplicationContext());
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
        Timber.i("activityStack.size() = " + activityStack.size());
        isLaunchApp = activityStack.size() == 0;
        activityStack.add(activity.hashCode());
        if (isLaunchApp) {
            if (PrefUtil.getBoolean(PREF_KEY_IS_LOCKED)) activity.startActivity(ConfirmPassCodeActivity.createIntent(getApplicationContext()));
        }
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
        Timber.i("activityStack.size() = " + activityStack.size());
        if (activityStack.size() == 0) {
            Timber.i("終了");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
