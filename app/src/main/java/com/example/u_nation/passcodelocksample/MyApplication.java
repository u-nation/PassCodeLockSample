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
        Timber.i("activityStack.size() = %s\nactivity_name = %s", activityStack.size(), activity.toString());
        boolean isForeground = activityStack.size() == 0;
        activityStack.add(activity.hashCode());
        if (isForeground) {
            if (PrefUtil.getBoolean(PREF_KEY_IS_LOCKED)) activity.startActivity(PassCodeConfirmActivity.createIntent(getApplicationContext()));
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
        Timber.d("activityStack.size() = %s\nactivity_name = %s", activityStack.size(), activity.toString());
        boolean isBackground = activityStack.size() == 0;
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
