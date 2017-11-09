package com.necer.timing;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements TextWatcher {
    private int totleTime;//总练习时间，分钟
    private int waterProgress;//圆环的进度
    private TextView timeView;
    private WaterWaveProgress waterWaveProgress;

    private int intervalTime;//间隔10秒休息，秒
    private int exerciseTime;//练习时间，秒

    private int intervalKeepTime;//倒计时间隔时间，每隔10分钟

    private boolean isInterval;//是否是第一次间隔时间到了

    private EditText etTotleTime;
    private EditText etExerciseTime;
    private EditText etIntervalTime;

    private Button btOption;
    private boolean isRunning;


    private MediaPlayer mediaPlayer;


    private TextView tvState;

    Handler handler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        timeView = findViewById(R.id.tv_time);
        tvState = findViewById(R.id.tv_state);
        etTotleTime = findViewById(R.id.et_totle_time);
        etExerciseTime = findViewById(R.id.et_exercise_time);
        etIntervalTime = findViewById(R.id.et_interval_time);
        btOption = findViewById(R.id.bt_option);

        waterWaveProgress = findViewById(R.id.waterWaveProgress);
        btOption.setText("开始");

        etTotleTime.addTextChangedListener(this);

        waterWaveProgress.setShowProgress(false);
        waterWaveProgress.animateWave();
        waterWaveProgress.setProgress(0);

        mediaPlayer = new MediaPlayer();

        try {
            AssetFileDescriptor fd = getAssets().openFd("cc.aac");
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {

            e.printStackTrace();
        }

        //  play();


    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            doTime();
        }
    };


    private void doTime() {
        totleTime--;
        waterProgress++;
        if (totleTime == -1) {
            handler.removeCallbacks(runnable);
            //结束了，
            Log.d("time", "结束了");

            //play();
            // mediaPlayer.start();
            tvState.setText("结束了");
            btOption.setText("开始");
            isRunning = false;
            return;
        }

        timeView.setText(Utils.changeTime(totleTime));

        waterWaveProgress.setProgress(waterProgress);

        intervalKeepTime++;

        if (intervalKeepTime == exerciseTime) {
            intervalKeepTime = 0;
            handler.postDelayed(runnable, 1000 * intervalTime);
            //间隔时间到了
            Log.d("time", "休息时间到了");
            // play();
            mediaPlayer.start();
            tvState.setText("休息时间");
            isInterval = true;

        } else {
            if (isInterval) {
                Log.d("time", "休息时间结束开始练习");
                //  play();
                mediaPlayer.start();
                tvState.setText("练习");
                isInterval = false;
            }
            handler.postDelayed(runnable, 1000);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (isRunning) {
            handler.removeCallbacks(runnable);
        }

        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();

    }


    public void option(View view) {


        if (isRunning) {
            btOption.setText("开始");
            handler.removeCallbacks(runnable);
            isRunning = false;

            tvState.setText("停止了");
            return;
        }


        if (Utils.isEmpty(etTotleTime)) {
            Toast.makeText(this, "请输入总时间！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Utils.isEmpty(etExerciseTime)) {
            Toast.makeText(this, "请输入练习时间！", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Utils.isEmpty(etIntervalTime)) {
            Toast.makeText(this, "请输入休息时间！", Toast.LENGTH_SHORT).show();
            return;
        }

        totleTime = Utils.getTime(etTotleTime) * 60;
        exerciseTime = Utils.getTime(etExerciseTime);
        intervalTime = Utils.getTime(etIntervalTime);

        isInterval = false;

        waterWaveProgress.setMaxProgress(totleTime);
        waterProgress = 0;

        waterWaveProgress.setProgress(0);

        intervalKeepTime = 0;

        btOption.setText("停止");
        tvState.setText("练习");
        timeView.setText(Utils.changeTime(totleTime));

        handler.postDelayed(runnable, 1000);
        isRunning = true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String time = s.toString();
        if (time != null && time.length() != 0) {
            timeView.setText(Utils.changeTime(Integer.parseInt(time) * 60));
        }
    }

    private long mExitTime;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}
