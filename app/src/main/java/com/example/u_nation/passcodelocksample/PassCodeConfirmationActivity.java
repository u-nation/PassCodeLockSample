package com.example.u_nation.passcodelocksample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.u_nation.passcodelocksample.util.PrefUtil;

import timber.log.Timber;

public class PassCodeConfirmationActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TEXT_SUB_MISTAKE = "Wrong passcode.Please try it again";
    private TextView text_sub_pass;
    private ImageView[] array_image_view;
    private StringBuilder stringBuilder = new StringBuilder();
    private SparseArray<String> array_box = new SparseArray<>();
    private Bitmap bitmapBlack = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
    private Bitmap bitmapGrey = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);

    public static Intent createIntent(Context context) {
        return new Intent(context, PassCodeConfirmationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pass_code);
        initViews();
        initCircleCanvas();
    }

    private void initViews() {
        text_sub_pass = (TextView) findViewById(R.id.text_sub_pass);
        array_image_view = new ImageView[]{(ImageView) findViewById(R.id.circle1), (ImageView) findViewById(R.id.circle2), (ImageView) findViewById(R.id.circle3), (ImageView) findViewById(R.id.circle4)};

        int[] array_id = {R.id.box0, R.id.box1, R.id.box2, R.id.box3, R.id.box4, R.id.box5, R.id.box6, R.id.box7, R.id.box8, R.id.box9};
        for (int i = 0; i <= 9; i++) {
            findViewById(array_id[i]).setOnClickListener(this);
            array_box.put(array_id[i], String.valueOf(i));
        }
    }

    private void initCircleCanvas() {
        // black circle
        Canvas canvas;
        canvas = new Canvas(bitmapBlack);
        Paint paint;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawCircle(150, 150, 148, paint);
        // grey circle
        Canvas canvas2;
        canvas2 = new Canvas(bitmapGrey);
        Paint paint2;
        paint2 = new Paint();
        paint2.setColor(Color.parseColor("#f5f5f5"));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
        canvas2.drawCircle(150, 150, 148, paint2);
    }

    private void initStringBuilder() {
        stringBuilder.setLength(0);
        stringBuilder.trimToSize();
    }

    private void initCircleColor() {
        for (ImageView circle : array_image_view) circle.setImageBitmap(bitmapGrey);
    }

    @Override
    public void onClick(View v) {
        if (array_box.indexOfKey(v.getId()) >= 0) inputPassword(array_box.get(v.getId()));
    }

    public void onDelete(View view) {
        int length = stringBuilder.length();
        deleteCircleColor(length);
        if (length != 0) stringBuilder.deleteCharAt(length - 1);
    }

    private void deleteCircleColor(int length) {
        if (length > 0) array_image_view[length - 1].setImageBitmap(bitmapGrey);
    }

    private void inputPassword(String password) {
        Timber.d(password);
        int length = stringBuilder.length();
        if (length > 3) return;
        array_image_view[length].setImageBitmap(bitmapBlack);
        stringBuilder.append(password);
        if (length == 3) new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                confirmPassword();
            }
        }, 200);
    }

    private void confirmPassword() {
        if (Integer.parseInt(stringBuilder.toString()) == PrefUtil.getInt(Constants.PREF_KEY_PASSWORD)) {
            finish();
        } else {
            text_sub_pass.setText(TEXT_SUB_MISTAKE);
            initStringBuilder();
            initCircleColor();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish(); //remove stack
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true); //Application will be background without regard for Activity stack
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
