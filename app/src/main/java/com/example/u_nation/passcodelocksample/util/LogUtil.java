package com.example.u_nation.passcodelocksample.util;

import android.util.Log;

/**
 * ログを表示するクラス NOTE！注意！ 本番リリース前には必ず isDebug を false にすること
 * isDebugがtrueだとログ表示、トースト表示
 */
public class LogUtil {

	private static final String TAG = "LogUtil";
	public static boolean isDebug = false;

	public static void d() {
		if (isDebug) {
			Log.d(TAG, getMetaInfo());
		}
	}

	public static void d(String message) {
        if (isDebug) {
            Log.d(TAG, getMetaInfo() + null2str(message));
        }
    }

	public static void d(String tag, String message) {
		if (isDebug) {
			Log.d(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}

	public static void i() {
		if (isDebug) {
			Log.i(TAG, getMetaInfo());
		}
	}

	public static void i(String message) {
		if (isDebug) {
			Log.i(TAG, getMetaInfo() + null2str(message));
		}
	}

	public static void i(String tag, String message) {
		if (isDebug) {
			Log.i(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}

	public static void w() {
		if (isDebug) {
			Log.w(TAG, getMetaInfo());
		}
	}

	public static void w(String message) {
		if (isDebug) {
			Log.w(TAG, getMetaInfo() + null2str(message));
		}
	}

	public static void w(String tag, String message) {
		if (isDebug) {
			Log.w(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}
	
	private static String null2str(String string) {
		if (string == null) {
			return "(null)";
		}
		return string;
	}

	/**
	 * ログ呼び出し元のメタ情報を取得する
	 * 
	 * @return [className#methodName:line]
	 */
	private static String getMetaInfo() {
		// スタックトレースから情報を取得 // 0: VM, 1: Thread, 2: LogUtil#getMetaInfo, 3:
		// LogUtil#d など, 4: 呼び出し元
		final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
		return LogUtil.getMetaInfo(element);
	}

	/**
	 * スタックトレースからクラス名、メソッド名、行数を取得する
	 * 
	 * @return [className#methodName:line]
	 */
	public static String getMetaInfo(StackTraceElement element) {
		// クラス名、メソッド名、行数を取得
		final String fullClassName = element.getClassName();
		final String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		final String methodName = element.getMethodName();
		final int lineNumber = element.getLineNumber();
		// メタ情報
		final String metaInfo = "[" + simpleClassName + "#" + methodName + ":" + lineNumber + "]";
		return metaInfo;
	}
}