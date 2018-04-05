package com.example.potatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ClassQuizzes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_quizzes);
    }

    public  void onCreateQuizclick(View v) {
        if(v.getId() == R.id.BcreateQuiz) {


            Intent i = new Intent(this, QuizInfo.class);
            startActivity(i);
        }

    }
}
