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
            EditText b = (EditText)findViewById(R.id.TFpassword);
            String str = a.getText().toString();
            String str1 = b.getText().toString();
            Intent i = new Intent(QuizInfo.this, QuizView.class);
            i.putExtra("Quizname", str);
            i.putExtra("Quizpassword", str1);
            startActivity(i);
        }

    }


}