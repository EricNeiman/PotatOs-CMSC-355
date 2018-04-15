package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class QuizInfo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizinfo);


    }
    public  void onCreatenewQuizClick(View v) {
        if(v.getId() == R.id.Bnewquiz) {

            EditText a = (EditText)findViewById(R.id.TFquizname);
            String str = a.getText().toString();
            Intent i = new Intent(QuizInfo.this, ClassQuizzes.class);
            i.putExtra("Quizname", str);
            startActivity(i);
        }

    }


}