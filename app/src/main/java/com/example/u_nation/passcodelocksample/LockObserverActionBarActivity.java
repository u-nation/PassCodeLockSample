package com.example.u_nation.passcodelocksample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.u_nation.passcodelocksample.util.LogUtil;
import com.example.u_nation.passcodelocksample.util.PrefUtil;
import com.example.u_nation.passcodelocksample.util.ShowToast;

import static com.example.u_nation.passcodelocksample.AppConfig.PREF_KEY_APPLICATION_BACKGROUND;
import static com.example.u_nation.passcodelocksample.AppConfig.PREF_KEY_IS_LOCKED;

public class LockObserverActionBarActivity extends ActionBarActivity {
    /* Activityがバックグラウンドに行く時、画面遷移なのか、アプリから離れたのかを判断 */
    public static boolean isTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.isDebug = true;
        LogUtil.i("onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume");
        if (checkIsLocked())
            checkDidBackground();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i("onPause");
        checkIsTransition();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i("onStop");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.i("onUserLeaveHint");
    }

    private void checkIsTransition() {
        if (!isTransition) {
            if (checkIsLocked()) {
                ShowToast.show("バックグラウンド", this);
                PrefUtil.setBool(getApplicationContext(), PREF_KEY_APPLICATION_BACKGROUND, true);
            }
        } else {
            isTransition = false;
        }
    }

    /* パスワード設定しているか判定 */
    protected boolean checkIsLocked() {
        return PrefUtil.getBool(getApplicationContext(), PREF_KEY_IS_LOCKED);
    }

    /* onUserLeaveHint後からの復帰か判定 */
    private void checkDidBackground() {
        if (PrefUtil.getBool(getApplicationContext(), PREF_KEY_APPLICATION_BACKGROUND)) {
            startActivity(ConfirmPassCodeActivity.createIntent(getApplicationContext()));
        }
    }

    /* 遷移時にバックグラウンド判定にならないようにする */
    @Override
    public void startActivity(Intent intent) {
        isTransition = true;
        super.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        isTransition = true;
        super.startActivityForResult(intent, requestCode);
    }

    /* finish()後に前のActivityに戻る場合 */
    protected void finishToActivity(Activity activity) {
        isTransition = true;
        activity.finish();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }
}