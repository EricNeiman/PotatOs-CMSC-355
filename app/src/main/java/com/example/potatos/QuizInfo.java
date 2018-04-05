package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class QuizInfo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizinfo);


    }
    public  void onCreatenewQuizClick(View v) {
        if(v.getId() == R.id.Bnewquiz) {

            Intent i = new Intent(QuizInfo.this, CreateQuiz.class);
            startActivity(i);
        }

    }


}