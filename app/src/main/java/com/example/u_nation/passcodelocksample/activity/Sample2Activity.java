package com.example.u_nation.passcodelocksample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.u_nation.passcodelocksample.LockObserverActionBarActivity;
import com.example.u_nation.passcodelocksample.R;
import com.example.u_nation.passcodelocksample.util.LogUtil;

public class Sample2Activity extends LockObserverActionBarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Sample2Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_sample2);
        getSupportActionBar().setTitle("Sample2Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d("onStop");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.d("onUserLeaveHint");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }

    public void onSample22(View view) {
        startActivity(Sample22Activity.createIntent(getApplicationContext()));
    }

    public void onBack(View view) {
        finishToActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LogUtil.i("KeyEvent.KEYCODE_BACK");
            finishToActivity(this);
        }
        return super.onKeyDown(keyCode, event);
    }
}
