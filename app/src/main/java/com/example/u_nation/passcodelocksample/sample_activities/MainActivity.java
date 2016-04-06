package com.example.u_nation.passcodelocksample.sample_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.u_nation.passcodelocksample.Constants;
import com.example.u_nation.passcodelocksample.PassCodeSetActivity;
import com.example.u_nation.passcodelocksample.R;
import com.example.u_nation.passcodelocksample.util.PrefUtil;
import com.example.u_nation.passcodelocksample.util.ShowToast;

import timber.log.Timber;


public class MainActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_main);
        setTitle(this.getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.d("onStop");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Timber.d("onUserLeaveHint");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy");
    }

    /*no stack*/
    public void onSample1(View view) {
        startActivity(Sample1Activity.createIntent(getApplicationContext()));
        finish();
    }

    /*remain stack*/
    public void onSample2(View view) {
        startActivity(Sample2Activity.createIntent(getApplicationContext()));
    }

    public void onLock(View view) {
        startActivity(PassCodeSetActivity.createIntent(getApplicationContext()));
    }

    public void onUnlock(View view) {
        PrefUtil.putBoolean(Constants.PREF_KEY_IS_LOCKED, false);
        PrefUtil.putInt(Constants.PREF_KEY_PASSWORD, 0);
        ShowToast.show("Unlock passcode!", this);
    }
}
