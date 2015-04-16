package com.example.u_nation.passcodelocksample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.example.u_nation.passcodelocksample.R;
import com.example.u_nation.passcodelocksample.util.LogUtil;

public class Sample1Activity extends Activity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Sample1Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_sample1);
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

    public void onSample11(View view) {
        startActivity(Sample11Activity.createIntent(getApplicationContext()));
        finish();
    }

    public void onBack(View view) {
        startActivity(MainActivity.createIntent(getApplicationContext()));
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LogUtil.d("KeyEvent.KEYCODE_BACK");
            startActivity(MainActivity.createIntent(getApplicationContext()));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
