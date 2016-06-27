package com.example.u_nation.passcodelocksample;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.u_nation.passcodelocksample.util.PrefUtil;

import java.util.HashSet;

import timber.log.Timber;

import static com.example.u_nation.passcodelocksample.Constants.PREF_KEY_IS_LOCKED;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private HashSet<Integer> activityStack = new HashSet<>();
    private static MyApplication app;

    private Handler handler = new Handler(Looper.getMainLooper());
    private int orientation = Configuration.ORIENTATION_PORTRAIT;
    private boolean is_rotated = false;

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
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        handler.removeCallbacksAndMessages(null);
        Timber.i("Started\nactivityStack.size() = %s\nactivity_name = %s", activityStack.size(), activity.toString());
        boolean isForeground = activityStack.size() == 0 && !is_rotated;
        is_rotated = false;
        activityStack.add(activity.hashCode());
        if (isForeground && PrefUtil.getBoolean(PREF_KEY_IS_LOCKED)) {
            activity.startActivity(PassCodeConfirmActivity.createIntent(getApplicationContext()));
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
        Timber.i("Stopped\nactivityStack.size() = %s\nactivity_name = %s", activityStack.size(), activity.toString());

        // Check Orientation
        Configuration conf = getResources().getConfiguration();
        is_rotated = orientation != conf.orientation;
        orientation = conf.orientation;
        Timber.i(orientation == Configuration.ORIENTATION_PORTRAIT ? "PORTRAIT" : "LANDSCAPE");

        boolean isBackground = activityStack.size() == 0 && !is_rotated;
        if (isBackground) {
            Timber.i("normal isBackground");
            //doing something
        }

        if (activityStack.size() == 0 && is_rotated) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    is_rotated = false;
                    Timber.i("postDelayed isBackground");
                    //doing something
                }
            }, 200);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }
}
