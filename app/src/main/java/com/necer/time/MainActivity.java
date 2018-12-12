package com.necer.time;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.necer.timing.CountDownView;


public class MainActivity extends AppCompatActivity {


    CountDownView countDownView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countDownView = findViewById(R.id.countDownView);

        countDownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownView.start();
            }
        });

    }
}
