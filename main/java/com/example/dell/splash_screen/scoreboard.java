package com.example.dell.splash_screen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.expandable_list_content;

public class scoreboard extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
   List<score_b> datas=new ArrayList<>();
    score_db scoreDb;
    private  score_b cscore;
    private int tlscore,flag=0;
    private  static int count=0;
    private String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        listView=(ListView)findViewById(R.id.listview);
        scoreDb=new score_db(this);

        tlscore=datas.size();

        display_data();

    }
    public void display_data()
    {

        datas= scoreDb.getscored();
            if (!datas.isEmpty()) {
                cscore = datas.get(count);
                p ="score is"+cscore.getScore();
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
                arrayAdapter.add(p);
                listView.setAdapter(arrayAdapter);
                count++;
            }

        else
        {

            Toast.makeText(this,"no record of Scores",Toast.LENGTH_SHORT).show();
        }
    }
}
