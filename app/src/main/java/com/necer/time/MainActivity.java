package com.necer.time;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.necer.timing.CountDownView;


public class MainActivity extends AppCompatActivity{

    CountDownView countDownView;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        countDownView = findViewById(R.id.countDownView);
        countDownView.setTextStyle("点击",Color.parseColor("#444444"),Color.parseColor("#000000"),"S",Color.parseColor("#cccccc"));
        countDownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "开始", Toast.LENGTH_SHORT).show();
                countDownView.start();
            }
        });

        countDownView.setOnCountDownListener(new CountDownView.OnCountDownListener() {
            @Override
            public void onCountDownFinish() {
                Toast.makeText(MainActivity.this, "结束", Toast.LENGTH_SHORT).show();
            }
        });

       /* TimingView timeView = findViewById(R.id.timeView);

        timeView.setOnTimeFinishListener(new TimingView.OnTimeFinishListener() {
            @Override
            public void onTimeFinish() {

                Toast.makeText(MainActivity.this, "结束", Toast.LENGTH_SHORT).show();

            }
        }).start();*/

    }
}
