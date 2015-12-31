package com.android.hukang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.demo_bt);
        tv = (TextView)findViewById(R.id.demo);
    }
    public void onClick(View view){
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis()-startTime<10*1000){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        button.setText("你好");
    }
}
