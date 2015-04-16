package com.example.u_nation.passcodelocksample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;

import com.example.u_nation.passcodelocksample.R;
import com.example.u_nation.passcodelocksample.util.LogUtil;

public class Sample22Activity extends ActionBarActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Sample22Activity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_sample22);
        getSupportActionBar().setTitle("Sample22Activity");
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

    public void onBack(View view) {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LogUtil.i("KeyEvent.KEYCODE_BACK");
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
