package com.example.dell.splash_screen;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class quiz extends AppCompatActivity {
    public int i=1;
    public static  final String extrascore="extrascore";// we need to access it in start_game..
    private  static final long count_milli=30000;
TextView score_b,total_q,timers,questions,declaree;
RadioGroup rg;
RadioButton rb1,rb2,rb3,rb4;
Button confirmed;
private List<question> questionList;
private ColorStateList defualtcolor,defaultquescolor;//for saving default color of the radio button..
private int totalquestion;// total numbers of the questions given to the user...
private int questioncount;//no of questions attempted...
    private question cquestion;// it will save the current question ..
    private int score;// to increase the score..
    private boolean answered;// is the answer clicked any radio button is checked or not
private ColorStateList defaultscorecolor,colorfortimer;//to change the color of score if score is more than =4
    private CountDownTimer countDownTimer;
    private long timeleft;
    static int k=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        // linking front end with backend
        score_b=findViewById(R.id.score);
        total_q=findViewById(R.id.tquestion);
        declaree=findViewById(R.id.declaration);
        timers=findViewById(R.id.timer);
        questions=findViewById(R.id.question);
        rg=findViewById(R.id.radi);
        rb1=findViewById(R.id.rbtn);
        rb2=findViewById(R.id.rbtn1);
        rb3=findViewById(R.id.rbtn2);
        rb4=findViewById(R.id.rbtn3);
        confirmed=findViewById(R.id.btn);
        colorfortimer=timers.getTextColors();
        defualtcolor=rb1.getTextColors();//it wwill save the text color i.e black heree..
        defaultquescolor=questions.getTextColors();
        defaultscorecolor=score_b.getTextColors();//it will get the default color of the score board
        DatabaseHelper databaseHelper=new DatabaseHelper(this);//context is the working activity
        questionList=databaseHelper.getdata();//get the list of the questions subsequently;
        totalquestion=questionList.size();//it will save the size of the list mean total number of questions in it.
      Collections.shuffle(questionList);//to shuffle the questions
        timeleft=count_milli;// assign the timer value

        shownextone();
        ischecking();

// works only when the user clicked on the value................


        confirmed.setOnClickListener(new View.OnClickListener() {//whenver the button is clicked if the user has not answered
            @Override//yet and the no radio button is checked in that case a toast will be output..
            public void onClick(View view) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()) {

                        checkAnswer();
                    } else {
                        Toast.makeText(getApplicationContext(), "Select the desired option", Toast.LENGTH_SHORT).show();
                    }
                } else {
                         shownextone();//if the user has answered the next question will be displayed
                }
            }
        });


    }
    private void ischecking()
    {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                startcountdown();
            }
        });
    }

  private  void shownextone()
    {

//as whenever the next question will be posted you need to change the color of the radio button which we have already saved;
        rb1.setTextColor(defualtcolor);
        rb2.setTextColor(defualtcolor);
        rb3.setTextColor(defualtcolor);
        rb4.setTextColor(defualtcolor);
        questions.setTextColor(defaultquescolor);// As for description color will be changed to blue..
        declaree.setText(" ");

        if(score>3)
        {
           score_b.setTextColor(Color.GREEN);// for score if >3 color will be green
        }
        else
        {
            score_b.setTextColor(defaultscorecolor);// else the default color
        }
        //you need to check all the radio button should be unchecked for next question
        rg.clearCheck();
        if(questioncount<totalquestion)// if you have still queestion
        {
            cquestion=questionList.get(questioncount);//currentquestion will be the next question
            questions.setText(cquestion.getQues());//current question is of question type it will help us to fetch ques from it..
            rb1.setText(cquestion.getOption1());//providing option values to the radio buttons..
            rb2.setText(cquestion.getOption2());
            rb3.setText(cquestion.getOption3());
            rb4.setText(cquestion.getOption4());

            questioncount++;//questions will be increment to move to the next question
            //we need to change change the attempted question below the score board
            total_q.setText("Questions: "+ questioncount + "/" +totalquestion);



            answered=false;// because we have not clicked any question..
            //Still our question is not clicked yet.. so button will give text as confirm
            confirmed.setText("Confirm");


        }
        else // as if question count is more than question total we have no more questions to display;
        {
            finishquiz();
        }
    }


    private void startcountdown()
    {
        countDownTimer=new CountDownTimer(timeleft,1000) {//tick value is 10000 milliseconds
            @Override
            public void onTick(long l) {
        timeleft=l;
           updatecountdown();
            }

            @Override
            public void onFinish() {// if the timer has closed it will move it to score window
            timeleft=0;

                finishquiz();
            }
        }.start();
    }
    private  void updatecountdown()
    {
        int minutes=(int) (timeleft/1000)/60;// if we have bigger timer value in that case we need the time in minutes
        int seconds=(int)(timeleft/1000)%60;// if we have big time in that we want only seconds after deleting complete minures so %60;
        String timeformatted=String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);//format for display
        timers.setText(timeformatted);
        // if the time is less than 10 sec it will change it text color to red otherwise defalut as black
        if(timeleft<10000)
        {
            timers.setTextColor(Color.RED);
        }else
        {
            timers.setTextColor(colorfortimer);
        }
    }
    private  void checkAnswer()// if the user has check the radio button
    {
        answered=true;//give it a flag

        RadioButton radioButton=findViewById(rg.getCheckedRadioButtonId());
        int clickanswer=rg.indexOfChild(radioButton) +1;// we will save the index of the right answer in a int and +1 for as the child number
        if(clickanswer==cquestion.getCorrect_an())// for a radiogroup is started from 0 ..
        {
            score++;// score will be incremented if the user checked answer is correct
            score_b.setText("Score: "+score);// and will be updated on the score board
            Toast.makeText(getApplicationContext(),"Right answer",Toast.LENGTH_SHORT).show();// toast if answer is correct;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Wrong answer",Toast.LENGTH_SHORT).show();//toast for wrong answer..
        }
        showsolution();// and whether the question is correct or wrong  solution for it will be printed
    }
    private  void showsolution()
    {
        RadioButton radioButtons=findViewById(rg.getCheckedRadioButtonId());
        radioButtons.setTextColor(Color.RED);// change the color of clicked item..

        switch (cquestion.getCorrect_an())
        {
            case 1:// if the first solution is correct then a description and a output correct
            {
               rb1.setTextColor(Color.GREEN);

              questions.setText("Answer 1 is correct ");
                declaree.setText("Declaration is : "+cquestion.getDeclaration());
                questions.setTextColor(Color.BLUE);
              break;
            }
            case 2:
            {
                rb2.setTextColor(Color.GREEN);

                questions.setText("Answer 2 is correct ");
                declaree.setText("Declaration is : "+cquestion.getDeclaration());
                questions.setTextColor(Color.BLUE);
                break;
            }
            case 3:
            {
                rb3.setTextColor(Color.GREEN);
                questions.setText("Answer 3 is correct ");
                declaree.setText("Declaration is : "+cquestion.getDeclaration());
                questions.setTextColor(Color.BLUE);
                break;
            }
            case 4:
            {
                rb4.setTextColor(Color.GREEN);
                questions.setText("Answer 4 is correct ");
                declaree.setText("Declaration is : "+cquestion.getDeclaration());
                questions.setTextColor(Color.BLUE);
                break;
            }
        }
        if(questioncount<totalquestion)//we  need tpo change button text till we reach to the last question
            confirmed.setText("Next Question");
        else
            confirmed.setText("Finish");

    }
    private  void finishquiz()

    {
        countDownTimer.cancel();
Intent it=new Intent(quiz.this,Start_Game.class);
int result=score;
it.putExtra("resultvalue",result);
it.putExtra("getthis",i);
it.putExtra("total",totalquestion);
it.putExtra("attempted",questioncount-1);
startActivity(it);
finish();// it will end up our acctivity
    }
// this willl be done to stop timer .
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
        {
            countDownTimer.cancel();
        }


    }
}
