package com.example.dell.splash_screen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class score_db extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public score_db(Context context) {
        super(context, "scored.db",null,4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            this.db = db;
            db.execSQL("create table Scoress (correct_ans INTEGER,incorrect_ans INTEGER, score TEXT,percentage INTEGER,t_answered INTEGER)");
            filldb();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("drop table if exists Scoress");
        onCreate(db);
    }
    public void filldb() {

            score_b s1 = new score_b(5,2,"5",10.25,7);
            adddata(s1);

    }
   private void adddata(score_b scoree)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("correct_ans",scoree.getC_answer());
        contentValues.put("incorrect_ans",scoree.getI_answer());
        contentValues.put("score",scoree.getScore());
        contentValues.put("percentage",scoree.getPrecentage());
        contentValues.put("t_answered",scoree.getT_questionss());

                db.insert("Scoress",null,contentValues);
    }
    public List<score_b> getscored()
    {
        List<score_b> data=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from Scoress",null);
        if (cursor.moveToFirst()) {
            do {
                score_b scoreB=new score_b();

                scoreB.setC_answer(cursor.getInt(cursor.getColumnIndex("correct_ans")));
                scoreB.setI_answer(cursor.getInt(cursor.getColumnIndex("incorrect_ans")));
                scoreB.setScore(cursor.getString(cursor.getColumnIndex("score")));
                scoreB.setPrecentage(cursor.getFloat(cursor.getColumnIndex("percentage")));
                scoreB.setT_questionss(cursor.getInt(cursor.getColumnIndex("t_answered")));
                data.add(scoreB);

            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;
    }

    }

