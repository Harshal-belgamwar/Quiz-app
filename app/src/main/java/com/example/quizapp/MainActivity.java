package com.example.quizapp;
import android.app.AlertDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.view.View;
import android .widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
      TextView questionTextView;
      TextView totalquestionTextView;
      Button btn_submit;

      Button ansA,ansB,ansC,ansD;

      int Score=0;

      int totalQuestion=QuestionAnswer.question.length;

      int currentQuestionIndex=0;
      String selectedAnswer="";


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalquestionTextView = findViewById(R.id.total_question);
        questionTextView=findViewById(R.id.question);
        ansA=findViewById(R.id.ans_a);
        ansB=findViewById(R.id.ans_b);
        ansC=findViewById(R.id.ans_c);
        ansD=findViewById(R.id.ans_d);

        btn_submit=findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        totalquestionTextView.setText("Total questions:"+totalQuestion);

        loadnewQuestion();
    }
    private void loadnewQuestion(){
        if(currentQuestionIndex==totalQuestion) {
            finishQuiz();
            return;

        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        selectedAnswer="";
    }
    private void finishQuiz(){
        String passStatus;
        if(Score >=totalQuestion*0.6){
            passStatus="passed";
        }else{
            passStatus="failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("your score is"+Score+"out of "+totalQuestion)
                .setPositiveButton("Restart",((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }
    private void restartQuiz(){
        Score=0;
        currentQuestionIndex=0;
        loadnewQuestion();
    }
    @Override
    public void onClick(View View){
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button ClickedButton =(Button) View;
        if (View.getId() == R.id.ans_a || View.getId() == R.id.ans_b ||
                View.getId() == R.id.ans_c || View.getId() == R.id.ans_d) {

            // Highlight the clicked option button
            ClickedButton.setBackgroundColor(Color.YELLOW);

            // Capture the selected answer
            selectedAnswer = ClickedButton.getText().toString();
        }

        if (ClickedButton.getId()==R.id.btn_submit){
            if(!selectedAnswer.isEmpty()){
                if(selectedAnswer.equals((QuestionAnswer.correctanswer[currentQuestionIndex]))){
                    Score++;
                }else{
                    ClickedButton.setBackgroundColor(Color.MAGENTA);
                }
                currentQuestionIndex++;
                loadnewQuestion();
            }else{
                selectedAnswer=ClickedButton.getText().toString();
                ClickedButton.setBackgroundColor(Color.YELLOW);
            }
        }
    }
}