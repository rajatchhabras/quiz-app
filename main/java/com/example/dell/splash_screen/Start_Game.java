package com.example.dell.splash_screen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Start_Game extends AppCompatActivity {

    Button start,exit,scoreboard;
TextView hscore,scored;
private  static  int st;
private int received;
private  int max;
private ColorStateList scored_f;
private int c,i;
private String score;
private score_db scoreDb;
private int total,count;
private  String change;
private  double per;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start__game);
        start=findViewById(R.id.btn);
        hscore=findViewById(R.id.high);
        scored=findViewById(R.id.score);
        scoreboard=findViewById(R.id.scoreboard);
        exit=findViewById(R.id.button);//linking front end with code
        loadHighscore();
       scored_f=scored.getTextColors();
       // get the score and flag that user has played game for not
st=getIntent().getIntExtra("resultvalue",0);
received=getIntent().getIntExtra("getthis",0);
total=getIntent().getIntExtra("total",0);
count=getIntent().getIntExtra("attempted",0);
// it will check that the game is played for not as default is also zero
        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(Start_Game.this,scoreboard.class);
                startActivity(it);
            }
        });




if(received==1)
{
    change=Integer.toString(st);
    per=(st/total)*100;
   // scoreDb.filldb(5,2,"5",45.77954585,7);
    if(st>3)
    {
        // if the score is greater then 3 color will automaically be changed to green else red. ie default.
        scored.setTextColor(Color.rgb(0,255,0));
        scored.setText("Your Score: "+st);
    }
    else
    {
        scored.setTextColor(scored_f);
        scored.setText("Your Score: "+st);
    }
}
else
{
    // if not played the game..
    scored.setTextColor(scored_f);
    scored.setText(" ");
}
if(st>max)
{

    updateScore(st);
}
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Game has Started",Toast.LENGTH_SHORT).show();
                Intent it=new Intent(Start_Game.this,quiz.class);//on clicking on start button it will move you to the quiz window
                startActivity(it);//i have not written finish() here so that it will move back to the start_game where he can choose
                //start or exit...
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
                System.exit(0);//to get out from the app
                return;

            }
        });
    }
    // it will load the last shared prefered data saved in devoce if not assigned it zero..
    private void  loadHighscore()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);
        max=sharedPreferences.getInt("keyHighscore",0);
        hscore.setText("High Score :"+max);
    }
    //we have made this function using shared preferrence to update the hight score for hat mobile
    private  void updateScore(int score)
    {
        max=score;
        hscore.setText("High Score :"+max);
        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("keyHighscore",max);
        editor.apply();
    }
}
