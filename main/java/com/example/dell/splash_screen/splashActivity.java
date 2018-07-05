package com.example.dell.splash_screen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread th=new Thread()//we have used thread to run the program handled once and forcefully stoped it using sleep fn;
        {
public  void run()
{
    try
    {
sleep(5000);
        Intent it=new Intent(splashActivity.this,Start_Game.class);
        startActivity(it);
        finish();
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
        };
        th.start();
    }
}
