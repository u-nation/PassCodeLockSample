package com.example.u_nation.passcodelocksample.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.example.u_nation.passcodelocksample.AppConfig;
import com.example.u_nation.passcodelocksample.InitPassCodeActivity;
import com.example.u_nation.passcodelocksample.R;
import com.example.u_nation.passcodelocksample.util.LogUtil;
import com.example.u_nation.passcodelocksample.util.PrefUtil;
import com.example.u_nation.passcodelocksample.util.ShowToast;


public class MainActivity extends ActionBarActivity {

    private static final String KEY_PASSWORD = "key_password";

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    public static Intent createIntent(Context context, int password) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_PASSWORD, password);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("onCreate");
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MainActivity");
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

    /*スタックを残さないケース*/
    public void onSample1(View view) {
        startActivity(Sample1Activity.createIntent(getApplicationContext()));
        finish();
    }

    /*スタックを残す(タブ管理などの基幹的なActivity)場合*/
    public void onSample2(View view) {
        startActivity(Sample2Activity.createIntent(getApplicationContext()));
    }

    public void onLock(View view) {
        // 入力画面へ
        startActivity(InitPassCodeActivity.createIntent(getApplicationContext()));
    }

    public void onUnlock(View view) {
        PrefUtil.setBool(getApplicationContext(), AppConfig.PREF_KEY_IS_LOCKED, false);
        PrefUtil.setInt(getApplicationContext(), AppConfig.PREF_KEY_PASSWORD, 0);
        ShowToast.show("パスコード解除しました！", this);
    }
}
