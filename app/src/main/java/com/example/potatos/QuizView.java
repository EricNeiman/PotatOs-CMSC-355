package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizview);
        String quizname = getIntent().getStringExtra("Quizname");
        TextView tv = (TextView)findViewById(R.id.TVquizname);
        tv.setText(quizname);
    }

    public void onGotoQuizClick(View v)  {
        if(v.getId() == R.id.bGotoQuiz) {
            Intent i = new Intent(this, CreateQuiz.class);
            startActivity(i);
        }
    }

    public void onSeeGradesClick (View v) {
        if(v.getId() == R.id.bGrades)  {
            Intent i = new Intent(this, View_grades.class);
            startActivity(i);

        }
    }

    public void onGotoMainClick (View v)  {
        if(v.getId() == R.id.bGotoMain)  {
            Intent i = new Intent(this, ClassQuizzes.class);
            startActivity(i);

        }

    }
}
