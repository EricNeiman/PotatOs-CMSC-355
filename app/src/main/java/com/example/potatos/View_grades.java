package com.example.potatos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class View_grades extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_grades);
    }

    public void onGotoQuizClick(View v) {
        if(v.getId() == R.id.bGotoQuiz) {
            Intent i = new Intent(this, ClassQuizzes.class);
            startActivity(i);
        }
    }
}
