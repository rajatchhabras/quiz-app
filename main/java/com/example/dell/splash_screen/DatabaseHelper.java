package com.example.dell.splash_screen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

private  SQLiteDatabase db;
    final  private static int id = 1;// so that the question no will be incremented itself...

    public DatabaseHelper(Context context) {// to create the database..
        super(context, "quizapp.db", null, 10);
        // this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//creating the table..
this.db=db;
db.execSQL("create table quiz_qz(id INTEGER PRIMARY KEY AUTOINCREMENT,question text,option1 text,option2 text,option3 text,option4 text,declare text,answer_c integer)");
            fillquestion();//the question will be filled form here because to avoid the repeation of same question as one table will be formed once one...

    }


    @Override// in case if the table already exists it will drop the table the again made it dor us
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists quiz_qz");
        onCreate(db);
    }

    private void fillquestion()// we have used our questions constructor to add values to the database;;
    {

        question q = new question("who made java programming language?", "James Gosling", "Dennis Ritchie", "Bjarne Stroustrup", "None of these","Truth Statement", 1);
        addques(q);
        question q1 = new question("What of the following is the default value of a local variable?", "null", "0", "Depends upon the type of variable", "Not assigned","Local variables are not assigned any value by default.",4);
        addques(q1);
        question q2 = new question("What is the size of short variable?", "8 bit", " 16 bit", "32 bit", " 64 bit","The short data type is represented by 16-bit signed two's complement integer.\n" +
                "\n" +
                "Minimum value: -32,768\n" +
                "\n" +
                "Maximum value: 32,767", 1);
        addques(q2);
        question q3 = new question("What is the default value of long variable?", "0", "0.0", "0L", "not defined", "long variable has default value of 0L if defined as an instance/static variable.",3);
        addques(q3);
        question q4 = new question("What is polymorphism?", "Polymorphism is a technique to define different objects of same type.", "Polymorphism is the ability of an object to take on many forms.", "Polymorphism is a technique to define different methods of same type.", "None of the above.", "Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object.",2);
        addques(q4);
        question q5 = new question("What is Set Interface?", "Set is a collection of element which contains elements along with their key.", "Set is a collection of element which contains hashcode of elements.", "Set is a collection of element which cannot contain duplicate elements.", "Set is a collection of element which can contain duplicate elements.", "Set is a collection of element which cannot contain duplicate elements. The Set interface contains only methods inherited from Collection and adds the restriction that duplicate elements are prohibited.",3);
        addques(q5);
        question q6 = new question("Method Overloading is an example of", "Static Binding.", "Dynamic Binding.", "Both of the above.", "None of the above.", "Method Overloading is example of static binding.",1);
        addques(q6);
        question q7 = new question("What invokes a thread's run() method?", "JVM invokes the thread's run() method when the thread is initially executed.", "Main application running the thread.", "start() method of the thread class.", "None of the above.", "After a thread is started, via its start() method of the Thread class, the JVM invokes the thread's run() method when the thread is initially executed.",1);
        addques(q7);
        question q8 = new question(" Under what conditions is an object's finalize() method invoked by the garbage collector?", " When it detects that the object has become unreachable.", " As soon as object is set as null.", "At fixed intervalm it checks for null value.", "None of the above.", "The garbage collector invokes an object's finalize() method when it detects that the object has become unreachable.",1);
        addques(q8);
        question q9 = new question("What is inheritance?", "It is the process where one object acquires the properties of another.", "inheritance is the ability of an object to take on many forms.", "inheritance is a technique to define different methods of same type.", "None of the above.", "It is the process where one object acquires the properties of another. With the use of inheritance the information is made manageable in a hierarchical order.",1);
        addques(q9);
        question q10 = new question("What is currentThread()?", "It is a Thread public static method used to obtain a reference to the current thread.", "It is a thread's instance method used to get thread count.", "It is a object's public static method used obtain a reference to the current thread.", " It is a object's instance method used to get thread count.", "currentThread() is a public static method of Thread class used to obtain a reference to the current thread.",1);
        addques(q10);
    }

    private void addques(question question)//here  we have need of default constructor ...
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("question", question.getQues());//    we have getques() question from question table for insertion..
        contentValues.put("option1", question.getOption1());//to get goption that we have inserted  in question constructor.
        contentValues.put("option2", question.getOption2());//to get option to add to databse so that we can reterive it..
        contentValues.put("option3", question.getOption3());
        contentValues.put("option4", question.getOption4());
        contentValues.put("declare",question.getDeclaration());
        contentValues.put("answer_c", question.getCorrect_an());
        db.insert("quiz_qz", null, contentValues);// we have inserted the Content vvalues direct to the table entry..
    }

    public List<question> getdata() {
        List<question> data = new ArrayList<>();//this list will take input of type questions as in db we have int string type so we made a class qustion
        SQLiteDatabase db = getReadableDatabase();//moreever can do this list<> data=new arraylist<>(); as a list can take data of any type
        Cursor cursor = db.rawQuery("select * from quiz_qz", null);
        if (cursor.moveToFirst()) {
            do {
                question question = new question();
                // we can also use this to reterive int index=cursor.getColumnIndex("question");
                // String question=cursor.getString(index);
                //data.add(question)//simillarly for other one.. options
                question.setQues(cursor.getString(cursor.getColumnIndex("question")));
                question.setOption1(cursor.getString(cursor.getColumnIndex("option1")));//the value of the sebsequent option are setted in the question class and is addded to the list
                question.setOption2(cursor.getString(cursor.getColumnIndex("option2")));
                question.setOption3(cursor.getString(cursor.getColumnIndex("option3")));
                question.setOption4(cursor.getString(cursor.getColumnIndex("option4")));
                question.setDeclaration(cursor.getString(cursor.getColumnIndex("declare")));
                question.setCorrect_an(cursor.getInt(cursor.getColumnIndex("answer_c")));
                data.add(question);// data of the sebsequent rows will be added to the list and renerated easily

            } while (cursor.moveToNext());
        }
        cursor.close();

        return data;
    }

}

