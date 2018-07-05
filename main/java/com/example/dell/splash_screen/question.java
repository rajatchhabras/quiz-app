package com.example.dell.splash_screen;

public class question {//this class is made to provide data to the question and correct answer details to pass in database..
private  String ques;// in database we have object of question class to easilt insert the questions to the database ..
private  String option1;
    private  String option2;
    private  String option3;
    private  String option4;
    private String declaration;
    private int correct_an;
public question(){}//default constructor that we have used to pass in the function ..

    public question(String ques, String option1, String option2, String option3, String option4, String declaration,int correct_an) {//constructor for insertion in database
        this.ques = ques;
        this.option1 = option1;//The constructor will automatically provide values to the data above..
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.declaration=declaration;
        this.correct_an = correct_an;
    }

    public String getQues() {// we need this to insert it into the database..
        return ques;
    }

    public void setQues(String ques) {// to set the values still it is added but to overwrite or update the question we have made the setter iption
        this.ques = ques;//we have entered a lot of question in database and now we want to access the first value of ques we have entered   so using cursor the
    }//we  assigned question ..

    public String getOption1() {// take input using getter and setter..
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {// for insertion we need to the get the values of the that class
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCorrect_an() {
        return correct_an;
    }

    public void setCorrect_an(int correct_an) {
        this.correct_an = correct_an;
    }
    public  String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }
}
