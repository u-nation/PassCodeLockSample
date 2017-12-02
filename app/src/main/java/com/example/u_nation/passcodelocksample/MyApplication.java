package com.example.u_nation.passcodelocksample;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.os.Bundle;

import com.example.u_nation.passcodelocksample.util.PrefUtil;

import timber.log.Timber;

import static com.example.u_nation.passcodelocksample.Constants.PREF_KEY_IS_LOCKED;

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

  private boolean isNeedPassCodeConfirmation = true;
  private static MyApplication app;

  public static MyApplication getInstance() {
    if (app == null) app = new MyApplication();
    return app;
  }

  @Override public void onCreate() {
    super.onCreate();
    Timber.plant(new Timber.DebugTree());
    PrefUtil.setSharedPreferences(getApplicationContext());
    registerActivityLifecycleCallbacks(this);
  }

  @Override public void onTrimMemory(int level) {
    super.onTrimMemory(level);
    if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
      isNeedPassCodeConfirmation = true;
    }
  }

  @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
  }

  @Override public void onActivityStarted(Activity activity) {
    if (isNeedPassCodeConfirmation && PrefUtil.getBoolean(PREF_KEY_IS_LOCKED)) {
      activity.startActivity(PassCodeConfirmationActivity.createIntent(getApplicationContext()));
    }
    isNeedPassCodeConfirmation = false;
  }

  @Override public void onActivityResumed(Activity activity) {
  }

  @Override public void onActivityPaused(Activity activity) {

  }

  @Override public void onActivityStopped(Activity activity) {
  }

  @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
  }

  @Override public void onActivityDestroyed(Activity activity) {
  }
}
