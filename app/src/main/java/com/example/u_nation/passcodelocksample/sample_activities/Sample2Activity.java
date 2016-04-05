package com.example.u_nation.passcodelocksample.sample_activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.example.u_nation.passcodelocksample.R;

import timber.log.Timber;

public class Sample2Activity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Sample2Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_sample2);
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

    public void onSample4(View view) {
        startActivity(Sample4Activity.createIntent(getApplicationContext()));
    }

    public void onBack(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Timber.i("KeyEvent.KEYCODE_BACK");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
